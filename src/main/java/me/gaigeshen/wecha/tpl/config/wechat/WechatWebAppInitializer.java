package me.gaigeshen.wecha.tpl.config.wechat;

import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.ServletRegistration.Dynamic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 
 * @author gaigeshen
 */
public
abstract
class WechatWebAppInitializer
extends AbstractAnnotationConfigDispatcherServletInitializer {
	
	private final Logger logger = LogManager.getLogger();

	@Override
	protected final String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected final Filter[] getServletFilters() {
		return this.servletFilters();
	}

	@Override
	protected final void customizeRegistration(Dynamic registration) {
		this.configRegistration(registration);
	}

	@Override
	protected final Class<?>[] getRootConfigClasses() {
		
		this.logger.info("Starting get root config classes");
		
		return this.rootConfigClasses();
	}

	@Override
	protected final Class<?>[] getServletConfigClasses() {
		
		this.logger.info("Starting get servlet config classes");

		Class<?>[] servletConfigClasses1 = new Class<?>[] { WechatConfig.class };
		
		Class<?>[] servletConfigClasses2 = this.servletConfigClasses();
		
		int servletConfigClasses2Count = servletConfigClasses2.length;

		servletConfigClasses1 = Arrays.copyOf(
				servletConfigClasses1, 1 + servletConfigClasses2Count);
		
		System.arraycopy(servletConfigClasses2, 0,
				servletConfigClasses1, 1, servletConfigClasses2Count);

		return servletConfigClasses1;

	}
	
	// Default do nothing
	protected Filter[] servletFilters() { return null; }
	protected void configRegistration(Dynamic registration) { };

	// Subclass must override these methods
	protected abstract Class<?>[] rootConfigClasses();
	protected abstract Class<?>[] servletConfigClasses();

}
