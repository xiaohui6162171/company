package me.gaigeshen.wecha.tpl.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import me.gaigeshen.wecha.tpl.util.http.RequestContextUtils;
import me.gaigeshen.wecha.tpl.util.wechat.WechatAuthorized;

/**
 * 
 * 
 * @author gaigeshen
 */
@Aspect
@Component
public class AuthorizingAspect {

	private Logger logger = LogManager.getLogger();
	
	@Pointcut("execution(* me.gaigeshen.wecha.tpl.controller..*.*(..))")
	public void controllerMethod() {}
	
	@Pointcut("@annotation(me.gaigeshen.wecha.tpl.controller.Authorizing)")
	public void annotationedMethod() {}
	
	
	@Around("controllerMethod() and annotationedMethod()")
	public Object beforeAuthorizing(ProceedingJoinPoint joinPoint) {
		
		HttpServletRequest req = RequestContextUtils.request();
		HttpSession sess = req.getSession();
		
		WechatAuthorized authorized = (WechatAuthorized) sess
				.getAttribute(WechatAuthorized.SESSIONKEY);
		
		if (authorized == null) {
			
			String redirectTo = "/authority/authorizing?target=";
			redirectTo += RequestContextUtils.requestUrl();
			
//			joinPoint.
		
			return "redirect:" + redirectTo;
			
		} else {
			
			try {
				// Target arguments
				Object[] args = joinPoint.getArgs();
				Object[] newargs = new Object[args.length];
				
				for (int i = 0; i < args.length; i++) {
					newargs[i] = args[i];
					if (args[i] != null) {
						// Change specified argument
						if (args[i].getClass() == WechatAuthorized.class)
							newargs[i] = authorized;
					}
				}
				
				return joinPoint.proceed(newargs);
				
			// Any exception
			} catch (Throwable e) { this.logger.catching(e); return null; }
			
		}
		
	}
	
}
