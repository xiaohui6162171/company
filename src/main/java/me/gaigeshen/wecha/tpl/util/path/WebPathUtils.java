package me.gaigeshen.wecha.tpl.util.path;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import me.gaigeshen.wecha.tpl.util.http.RequestContextUtils;

/**
 * 
 * 
 * @author gaigeshen
 */
public final class WebPathUtils {

	/**
	 * 
	 * 
	 * @return
	 */
	public static String webappsRealpath() {
		
		HttpServletRequest req = RequestContextUtils.request();
		
		// May be null
		if (req == null) return null;
		
		String pathStr = req.getServletContext().getRealPath("/");
		
		Path path = Paths.get(pathStr).getParent();
		
		return path.toFile().getAbsolutePath();
		
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public static String webappRealpath() {

		HttpServletRequest req = RequestContextUtils.request();

		// May be null
		if (req == null) return null;
		
		String pathStr = req.getServletContext().getRealPath("/");
		
		Path path = Paths.get(pathStr);
		
		return path.toFile().getAbsolutePath();
		
	}
	
	/**
	 * 
	 * 
	 * @param realpath
	 */
	public static void createDirIfAbsent(String realpath) {
		
		File file = new File(realpath);
		
		if (file.exists())
			return;
		
		file.mkdirs();
		
	}

}
