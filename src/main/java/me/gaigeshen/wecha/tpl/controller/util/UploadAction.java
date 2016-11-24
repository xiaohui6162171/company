package me.gaigeshen.wecha.tpl.controller.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.gaigeshen.wecha.tpl.model.Result;
import me.gaigeshen.wecha.tpl.util.DateUtil;
import sun.misc.BASE64Decoder;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;

@RequestMapping("/uploadimg")
@Controller  
public class UploadAction {  
  
	private static final Logger logger = Logger.getLogger(UploadAction.class);
	
	@Autowired
	private WebApplicationContext applicationContext;
	

	
	// The upload directory
	private final String storage = "upload";
	
	@RequestMapping("/uploadindex")  
    public  @ResponseBody Result upload(@RequestParam(value = "file", required = false) MultipartFile fileToUpload, HttpServletRequest request, ModelMap model,HttpServletResponse response,String name) {  
    	Result ajaxJson=new Result();
    	ajaxJson.setSuccess(false);
        response.setContentType("text/plain; charset=UTF-8");
        String path = request.getSession().getServletContext().getRealPath("upload")+"/"+name+"/"+DateUtil.getNowStringDate();  
       // String fileName = fileToUpload.getOriginalFilename(); 
        String st=fileToUpload.getOriginalFilename().substring(fileToUpload.getOriginalFilename().lastIndexOf(".")+1);
        String fileName = new Date().getTime()+"."+st;
          
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        
        //淇濆瓨  
        try {  
        	fileToUpload.transferTo(targetFile);
            ajaxJson.setSuccess(true);
            ajaxJson.setMsg("upload/"+name+"/"+DateUtil.getNowStringDate()+"/"+fileName);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        model.addAttribute("fileUrl", request.getContextPath()+"/upload/"+fileName);  
  
        return ajaxJson;  
    }  
	
	public static boolean GenerateImage(String imgStr,String path) { // 对字节数组字符串进行Base64解码并生成图片
		if (imgStr == null) // 图像数据为空
		return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
		// Base64解码
		byte[] b = decoder.decodeBuffer(imgStr);
		for (int i = 0; i < b.length; ++i) {
		if (b[i] < 0) {// 调整异常数据
		b[i] += 256;
		}
		}
		// 生成jpeg图片
		OutputStream out = new FileOutputStream(path);
		out.write(b);
		out.flush();
		out.close();
		return true;
		} catch (Exception e) {
		return false;
		}
		}
	
	@RequestMapping("/base64uploadindex")  
	 public  @ResponseBody Result uploadBase64(String base64Img,HttpServletRequest request,HttpServletResponse response,String name){
		Result ajaxJson=new Result();
	     ajaxJson.setSuccess(false);
	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 		 String dir = sdf.format(new Date());
	     ServletContext ctx = this.applicationContext.getServletContext();
	     String path = ctx.getRealPath(this.storage);
	     File fileOfDir = this.makeDirectory(path);
	     System.out.println(fileOfDir);
	     String img = this.generateNameForFile() + ".jpg";
	     File f = new File(fileOfDir, img);
	     if (GenerateImage(base64Img,f.getAbsolutePath())){
	    	 ajaxJson.setSuccess(true);
	    	 ajaxJson.setMsg("upload"+File.separator+dir+File.separator+img);
	     }
	 		return ajaxJson;
	 }
	
	@ResponseBody
	@RequestMapping(value = "/uploadAll")
	public Map<String, Object> UploadAll(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		List<String> paths=new ArrayList<String>();
		MultipartResolver resolver = new CommonsMultipartResolver(request
				.getSession().getServletContext());
		if (resolver.isMultipart(request)) {
			MultipartHttpServletRequest multipartRequest = resolver
					.resolveMultipart(request);
			Iterator<String> iter = multipartRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multipartRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String st = file.getOriginalFilename().substring(
								file.getOriginalFilename().lastIndexOf(".") + 1);
						String fileName = this.generateNameForFile() + "." + st;
						// 定义上传路径
						String path = request.getSession().getServletContext()
								.getRealPath("upload")
								+ "/"+ DateUtil.getNowStringDate()+"/"+fileName;
						System.out.println(path);
						File localFile = new File(path);
						if(!localFile.exists()){  
							localFile.mkdirs();  
				        }  
						file.transferTo(localFile);
						String p=localFile.getAbsolutePath().substring(path.indexOf("upload"));
						// Compress ?
						if(isimage(st)){
						Integer width=2000;
						String abspath =localFile.getAbsolutePath();
						BufferedImage bufferedImage = ImageIO.read(new File(abspath));   
						if(bufferedImage.getWidth()!=400 ){
							 p = this.compress(abspath, width); 
						}
						}
						if(ismovie(st)){
							String codcFilePath =  request.getSession().getServletContext()
									.getRealPath("upload")
									+ "/"+ DateUtil.getNowStringDate()+"/"+this.generateNameForFile()  + ".mp4";
							 String mediaPicPath=request.getSession().getServletContext()
										.getRealPath("upload")
										+ "/"+ DateUtil.getNowStringDate()+"/"+this.generateNameForFile()+".jpg";
//							  String ffmpegPath = request.getServletContext().getRealPath("/resource/util/ffmpeg.exe");
							  String ffmpegPath = "/usr/local/ffmpeg/bin/ffmpeg";
//							  mediaService.executeCodecs(ffmpegPath, localFile.getAbsolutePath(), codcFilePath, mediaPicPath);
							  p=codcFilePath.substring(path.indexOf("upload"));
						}
						paths.add(p);
					}
				}
				map.put("paths", paths);
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}
		}
		return map;
	}
    
	// 压缩包上传
	@ResponseBody
	@RequestMapping("/uploadZipAndRar")
	public Map<String, Object> uploadZipAndRar(@RequestParam(value = "file",required = false) MultipartFile fileToUpload,HttpServletRequest request,HttpServletResponse response){
		Map<String, Object> resMap = new HashMap<String, Object>();
		response.setContentType("text/plain; charset=UTF-8");
		String name = "Files";
		String path = request.getSession().getServletContext().getRealPath("download")+"/"+name+"/"+DateUtil.getNowStringDate();
		String st = fileToUpload.getOriginalFilename().substring(fileToUpload.getOriginalFilename().lastIndexOf(".")+1);
		String fileName = new Date().getTime() + "." + st;
		
		// 
		File targetFile = new File(path, fileName);  
		
        if(!targetFile.getParentFile().exists()){  
            targetFile.getParentFile().mkdirs();  
        }
        
        // 淇濆瓨
        try {
        	fileToUpload.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
        resMap.put("msg", request.getContextPath()+"/download/"+name+"/"+DateUtil.getNowStringDate()+"/"+fileName);

        return resMap;
	}
    
	@RequestMapping("/uploadindex2")    
    public @ResponseBody Result addUser( @RequestParam MultipartFile[] fileToUpload, HttpServletRequest request, HttpServletResponse response){
        //可以在上传文件的同时接收其它参数
    	Result ajaxJson=new Result();
    	ajaxJson.setSuccess(false);
        //如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\upload\\文件夹中
        //这里实现文件上传操作用的是commons.io.FileUtils类,它会自动判断/upload是否存在,不存在会自动创建
        String realPath = request.getSession().getServletContext().getRealPath("/upload");
        //设置响应给前台内容的数据格式
        response.setContentType("text/plain; charset=UTF-8");
        //设置响应给前台内容的PrintWriter对象
       
        //上传文件的原名(即上传前的文件名字)
        String originalFilename = null;
        //如果只是上传一个文件,则只需要MultipartFile类型接收文件即可,而且无需显式指定@RequestParam注解
        //如果想上传多个文件,那么这里就要用MultipartFile[]类型来接收文件,并且要指定@RequestParam注解
        //上传多个文件时,前台表单中的所有<input type="file"/>的name都应该是myfiles,否则参数里的myfiles无法获取到所有上传的文件
        for(MultipartFile myfile : fileToUpload){
            if(myfile.isEmpty()){
                
                return ajaxJson;
            }else{
                originalFilename = myfile.getOriginalFilename();
               
                logger.info("文件原名: " + originalFilename);
                logger.info("文件名称: " + myfile.getName());
                logger.info("文件长度: " + myfile.getSize());
                logger.info("文件类型: " + myfile.getContentType());
                logger.info("========================================");
                originalFilename=new Date().getTime()+""+originalFilename.substring(originalFilename.indexOf("."),originalFilename.length());
                logger.info("文件原名: " + originalFilename);
                File targetFile = new File(realPath, originalFilename); 
                try {
                    //这里不必处理IO流关闭的问题,因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉
                    //此处也可以使用Spring提供的MultipartFile.transferTo(File dest)方法实现文件的上传
                   // FileUtils.copyInputStreamToFile(myfile.getInputStream(), new File(realPath, originalFilename));
                			myfile.transferTo(targetFile);
                	  	ajaxJson.setSuccess(true);
                      ajaxJson.setMsg(originalFilename);
                } catch (Exception e) {
                	 logger.info("文件[" + originalFilename + "]上传失败,堆栈轨迹如下");
                    e.printStackTrace();
                 
                    return ajaxJson;
                }
            }
        }
        //此时在Windows下输出的是[D:\Develop\apache-tomcat-6.0.36\webapps\AjaxFileUpload\\upload\愤怒的小鸟.jpg]
        //System.out.println(realPath + "\\" + originalFilename);
        //此时在Windows下输出的是[/AjaxFileUpload/upload/愤怒的小鸟.jpg]
        //System.out.println(request.getContextPath() + "/upload/" + originalFilename);
        //不推荐返回[realPath + "\\" + originalFilename]的值
        //因为在Windows下<img src="file:///D:/aa.jpg">能被firefox显示,而<img src="D:/aa.jpg">firefox是不认的
      
        return ajaxJson;
    }
	
	////////////////////////
	public static final String FILE_PATH = "";  
	
	@RequestMapping("/uploadindex3")  
	public @ResponseBody Result upload3(MultipartFile file, @RequestParam(required = false) Integer width) {
		String ext = this.isImage(file.getOriginalFilename());
		Result ajaxJson=new Result();
    	ajaxJson.setSuccess(false);
		if (ext == null) return ajaxJson;
		
		// e.g. /upload
		ServletContext ctx = this.applicationContext.getServletContext();
		String path = ctx.getRealPath(this.storage);
//		path = path.replace("GodsDesign", "Design");
		// e.g. /upload/2016-01-01
		File fileOfDir = this.makeDirectory(path);
		
		// Generate a name for the file, and join the extension
		String name = this.generateNameForFile() + ext;
		
		File f = new File(fileOfDir, name);
		
		try {
			file.transferTo(f);
			
			String abspath = f.getAbsolutePath();
			String original = abspath.substring(abspath.indexOf(this.storage));
			String thumbnail = null;

			// Compress ?
			if (width != null) { thumbnail = this.compress(abspath, width); }
	
            ajaxJson.setMsg(original);
            ajaxJson.setMsg1(thumbnail);
		} catch (Exception e) {
			this.logger.error("Handle image failed", e);
		}
		return ajaxJson;
	}

	public static File getFile(String fileName) {  
		return new File(FILE_PATH, fileName);  
	}  
	
	/** 
	*  
	* @param picPath 图片路径 
	* @param drawPicPath draw后的路径 
	* @param width draw后的宽度 
	* @param height draw后的高度 
	* @throws IOException 
	* @throws InterruptedException 
	* @throws IM4JavaException 
	*/  
	public void drawImg(String picPath,String drawPicPath,int width, int height) throws IOException, InterruptedException, IM4JavaException{  
		IMOperation op = new IMOperation(); 
		//待处理图片的绝对路径 
		op.addImage();  
		if(width == 0){//根据高度缩放图片    
			op.resize(null, height);        
		}else if(height == 0){//根据宽度缩放图片    
			op.resize(width, null);    
		}
		//图片压缩比，有效值范围是0.0-100.0，数值越大，缩略图越清晰  
//		op.quality(95d);  
		//处理后图片的绝对路径 
		op.addImage();  
		//IM4JAVA是同时支持ImageMagick和GraphicsMagick的，如果为true则使用GM，如果为false支持IM。 
		ConvertCmd cmd = new ConvertCmd(true);  
		String osName = System.getProperty("os.name").toLowerCase();      
//		if(osName.indexOf("win")>=0) {  //linux下不要设置此值，不然会报错  
//			cmd.setSearchPath("D:\\tools\\GraphicsMagick-1.3.22-Q16");   
//		}  
		cmd.setErrorConsumer(StandardStream.STDERR);  
		cmd.run(op, picPath , drawPicPath);  
	} 

	/**
	 * Checks the file, if it is a image then
	 * return the extension of the file. Or return null.
	 * 
	 * @param name
	 *          The file name
	 * @return The extension of the file if it is a image, contains the point.
	 */
	private String isImage(String name) {
		
		// Just keep simple
		String[] pattern = new String[]{
		  "jpg", "jpeg", "png", "gif"
		};
		
		if (StringUtils.containsAny(name.toLowerCase(), pattern)) {
			return name.substring(name.lastIndexOf("."));
		}
		
		return null;
	}
	
	private boolean isimage(String name){
		String[] pattern = new String[]{
				  "jpg", "jpeg", "png", "gif"
				};
		if (StringUtils.containsAny(name.toLowerCase(), pattern)) {
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Makes new child directory with date format
	 * 
	 * @param path
	 *          The parent path
	 * @return The file of the new directory
	 */
	private File makeDirectory(String path) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dir = sdf.format(new Date());
		
		File file = new File(new File(path), dir);
		
		if (!file.exists())
			file.mkdirs();
		
		if (file.isDirectory())
			return file;
		
		this.logger.error("The file is exists and it is not directory");
		throw new FileHandleException();
	}
	
	private boolean ismovie(String name){
		List<String> list=new ArrayList<String>();
		list.add("asx");
		list.add("asf");
		list.add("mpg");
		list.add("wmv");
		list.add("3gp");
		list.add("mp4");
		list.add("mov");
		list.add("avi");
		list.add("flv");
		if(list.contains(name.toLowerCase())){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * Generate a new name for file, by uuid string.
	 * 
	 * @return The new name
	 */
	private String generateNameForFile() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * Compress image
	 * 
	 * @param original
	 *          The path original
	 * @param maxLength
	 *          The target image max length
	 * @return The target path
	 * @throws Exception
	 *           Any exception
	 */
	private String compress(String original, Integer maxLength) throws Exception {

		// Returns filename
		String filename = original.substring(
  		original.lastIndexOf(File.separator) + 1,
  		original.lastIndexOf(".")
		);
		
		String thumbnail = original.replace(filename, this.generateNameForFile());
		
		ConvertCmd cmd = new ConvertCmd(true);
		IMOperation oper = new IMOperation();
		
		oper.addImage();
		
		oper.resize(maxLength);
		
		oper.addImage();
		
		cmd.run(oper, original, thumbnail);
		
		return thumbnail.substring(thumbnail.indexOf(this.storage));
		
	}

	/**
	 * Image path holder, contains original and thumbnail.
	 * 
	 * @author gaigeshen
	 */
	public class PathHolder {
		private final String original;
		private final String thumbnail;
		
		public PathHolder(String original, String thumbnail) {
			this.original = original;
			this.thumbnail = thumbnail;
		}
		
		public String original() {
			return this.original;
		}
		
		public String thumbnail() {
			return this.thumbnail;
		}
		
	}
	
}  
