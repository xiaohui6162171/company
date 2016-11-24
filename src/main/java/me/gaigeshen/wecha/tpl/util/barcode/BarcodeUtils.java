package me.gaigeshen.wecha.tpl.util.barcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

/**
 * 
 * @author gaigeshen
 */
public final class BarcodeUtils {
	
	private static final Map<EncodeHintType, String> hints = new HashMap<>();
	
	/* Additional parameters */
	static { hints.put(EncodeHintType.CHARACTER_SET, "utf-8"); }

	/**
	 * 生成二维码并写入到图片文件
	 * 
	 * @param content
	 *          二维码内容
	 * @param width
	 *          宽度
	 * @param height
	 *          高度
	 * @param file
	 *          图片文件
	 */
	public static void generateQRCode(String content, int width, int height, File file) {
		
		String path = file.getAbsolutePath();
		int lastPointIndex = path.lastIndexOf(".");
		
		String format = path.substring(lastPointIndex + 1);
		
		try {
			
			BitMatrix matrix = new MultiFormatWriter()
				.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			
			MatrixToImageWriter.writeToPath(matrix, format, file.toPath());
			
		} catch (WriterException | IOException e) {
			
			throw new BarcodeCreationException("生成二维码失败");
			
		}
		
	}
	
	/**
	 * 生成二维码并写入到输出流
	 * 
	 * @param content
	 *          二维码内容
	 * @param width
	 *          宽度
	 * @param height
	 *          高度
	 * @param out
	 *          输出流
	 */
	public static void generateQRCode(String content, int width, int height, OutputStream out) {
		
		// 默认png格式
		String defaultFormat = "png";
		
		try {
			
			BitMatrix matrix = new MultiFormatWriter()
				.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			
			MatrixToImageWriter.writeToStream(matrix, defaultFormat, out);
			
		} catch (WriterException | IOException e) {
			
			throw new BarcodeCreationException("生成二维码失败");
			
		}
		
	}
	
	/**
	 * 生成二维码并输出到缓冲图
	 * 
	 * @param content
	 *          二维码内容
	 * @param width
	 *          宽度
	 * @param height
	 *          高度
	 * @return 缓冲图
	 */
	public static BufferedImage generateQRCode(String content, int width, int height) {
		
		try {
			
			BitMatrix matrix = new MultiFormatWriter()
				.encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			
			return MatrixToImageWriter.toBufferedImage(matrix);
			
		} catch (WriterException e) {
			
			throw new BarcodeCreationException("生成二维码失败");
			
		}
		
	}
	
}
