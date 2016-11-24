package me.gaigeshen.wecha.tpl.util.http;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Tools for http request.
 * 
 * Spring framework must be supported.
 * 
 * @author gaigeshen
 */
public final class RequestContextUtils {
	
	/**
	 * 
	 * 
	 * @return
	 */
	public static String protocolWithSlash() {
		
		String url = RequestContextUtils.requestUrl();
		
		int slashIndex = url.indexOf("/");
		
		return url.substring(0, slashIndex + 2);
		
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public static String requestUrl() {
		
		HttpServletRequest req = RequestContextUtils.request();
		
		if (req != null) {
		
			String url = req.getRequestURL().toString();
			String qs = req.getQueryString();
	
			if (qs != null)
				url += ("?" + qs);
			
			return url;
		}
		
		return null;
		
	}

	
	/**
	 * 
	 * 
	 * @return
	 */
	public static String contextPath() {
		
		HttpServletRequest req = RequestContextUtils.request();
		
		if (req != null)
			return req.getContextPath();
		
		return null;
		
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public static String serverName() {
		
		HttpServletRequest req = RequestContextUtils.request();
		
		if (req != null)
			return req.getServerName();
		
		return null;
		
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public static Integer serverPort() {
		
		HttpServletRequest req = RequestContextUtils.request();
		
		if (req != null)
			return req.getServerPort();
		
		return null;
		
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public static String serverContextPath() {
		
		if (RequestContextUtils.request() != null) {
			
			String path = RequestContextUtils.serverName();
			
			Integer port = RequestContextUtils.serverPort();
			if (port != null && port != 80)
				path += (":" + port);
			
			path += contextPath();
			
			return path;
			
		}
		
		return null;
		
	}
	
	/**
	 * 
	 * 
	 * @return
	 */
	public static HttpServletRequest request() {
		
		RequestAttributes reqAttrs = RequestContextHolder
				.getRequestAttributes();

		if (!(reqAttrs instanceof ServletRequestAttributes))
			return null;

		ServletRequestAttributes sreqAttrs = (ServletRequestAttributes) reqAttrs;

		HttpServletRequest req = sreqAttrs.getRequest();
		
		return req;
		
	}

}
