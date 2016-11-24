package me.gaigeshen.wecha.tpl.config;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/**
 * 
 * @author gaigeshen
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan("me.gaigeshen.wecha.tpl")
public class ContextControllerConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

		StringHttpMessageConverter stringConverter =
				new StringHttpMessageConverter(Charset.forName("utf-8"));
		
		MappingJackson2HttpMessageConverter jsonConverter =
				new MappingJackson2HttpMessageConverter(this.objectMapper());
		
		jsonConverter.setSupportedMediaTypes(
				Arrays.asList(MediaType.APPLICATION_JSON_UTF8,
				new MediaType(MediaType.TEXT_HTML, Charset.forName("utf-8")))
		);
		
		converters.add(stringConverter);
		converters.add(jsonConverter);

	}

	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		
		configurer.defaultContentType(MediaType.APPLICATION_JSON);
		configurer.favorPathExtension(false);
		
	}

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		
		registry.jsp("/WEB-INF/view/", ".jsp");
		
	}

	@Bean
	public ObjectMapper objectMapper() {

		ObjectMapper objMpr = new ObjectMapper();

		objMpr.setSerializationInclusion(Include.NON_NULL);

		objMpr.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);

		objMpr.configure(SerializationFeature.INDENT_OUTPUT, true);

		objMpr.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

		return objMpr;
	}

}
