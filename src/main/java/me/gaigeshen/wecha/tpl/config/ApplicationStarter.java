package me.gaigeshen.wecha.tpl.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletRegistration.Dynamic;

import me.gaigeshen.wecha.tpl.config.wechat.WechatWebAppInitializer;

/**
 * 
 * @author gaigeshen
 */
public class ApplicationStarter extends WechatWebAppInitializer {

	@Override
	protected Class<?>[] rootConfigClasses() {
		
		Class<?>[] classes = new Class[] {
			ContextPersistenceConfig.class
		};
		
		return classes;
	}

	@Override
	protected Class<?>[] servletConfigClasses() {
		
		Class<?>[] classes = new Class[] {
			ContextControllerConfig.class
		};
		
		return classes;
	}

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		
		super.onStartup(servletContext);
		
		ServletRegistration reg = servletContext.getServletRegistration("default");
		
		reg.addMapping("/resource/*");
		reg.addMapping("/plug-in/*");
		reg.addMapping("/images/*");
		reg.addMapping("/upload/*");
		reg.addMapping("/download/*");
		reg.addMapping("/games/*");
		reg.addMapping("/propagate/*");
		reg.addMapping("/vote/*");
		reg.addMapping("/phone/*");
	}

	@Override
	protected void configRegistration(Dynamic registration) {
		
		
		String temFile = System.getProperty("java.io.temp");
		
		registration.setMultipartConfig(new MultipartConfigElement(temFile, 50 * 1024 * 1024, 100 * 1024 * 1024, 1 * 1024 * 1024));
		
		
	}

	
}
