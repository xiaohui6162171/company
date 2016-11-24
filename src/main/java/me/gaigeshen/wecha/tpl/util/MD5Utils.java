package me.gaigeshen.wecha.tpl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;

public class MD5Utils {
	
	public static void main(String[] args) {
		String str = "123456a";
		System.out.println(md5(str));
	}
	
	
	public static String md5(String toMd5){
		if(!StringUtils.isNotEmpty(toMd5)) {
			return null;
		}
		return toString(md5Private(toMd5.getBytes())).toUpperCase();
	}
	
	/**
	 * 进行md5运算
	 * @param cs
	 * @return
	 */
	private static byte[] md5Private(byte[] cs) {
		byte[] rs = null;
	      try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			rs = md.digest(cs);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return rs;
	}
	
	public static String toString(byte[] a) {
        if (a == null)
            return "null";
        if (a.length == 0)
            return "";
 
        StringBuilder buf = new StringBuilder();
 
        for (int i = 0; i < a.length; i++) {
        	if (a[i] < 0)
        		buf.append(Integer.toHexString(a[i]&0xff));
        	else if (a[i] < 16) {
        		buf.append('0');
        		buf.append(Integer.toHexString(a[i]));
        	} else {
        		buf.append(Integer.toHexString(a[i]));
        	}
        }
 
        return buf.toString();
    }
}