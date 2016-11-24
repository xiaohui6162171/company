package me.gaigeshen.wecha.tpl.controller;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.gaigeshen.wecha.tpl.util.http.RequestContextUtils;
import me.gaigeshen.wecha.tpl.util.wechat.WechatAuthorized;

/**
 * 
 * 
 * @author gaigeshen
 */
@Controller
@RequestMapping(path = "/authority")
public class AuthorityController {
	
	private Logger logger = LogManager.getLogger();

	@Autowired private WxMpService srv;

	
	/**
	 * 
	 * 
	 * @param sess
	 * @param target
	 * @return
	 */
	@RequestMapping(path = "/authorizing", method = RequestMethod.GET)
	public String authorizing(HttpSession sess, String target) {
		
		WechatAuthorized authorized = (WechatAuthorized) sess
				.getAttribute(WechatAuthorized.SESSIONKEY);
		
		if (authorized != null)
			return "redirect:" + target;
		
		String redirecturl=RequestContextUtils.protocolWithSlash()+RequestContextUtils.serverContextPath()+"/authority/redirect";
		String url = this.srv.oauth2buildAuthorizationUrl(redirecturl,
				WxConsts.OAUTH2_SCOPE_USER_INFO, "state");
		
		sess.setAttribute("wechat.target", target);
		
		return "redirect:" + url;
		
	}
	
	/**
	 * 
	 * 
	 * @param code
	 * @param sess
	 * @return
	 */
	@RequestMapping(path = "/redirect", method = RequestMethod.GET)
	public String redirect(String code, HttpSession sess) {
		
		try {
			
			WxMpOAuth2AccessToken token = this.srv.oauth2getAccessToken(code);
			WxMpUser userInfo = this.srv.oauth2getUserInfo(token, "zh_CN");
			
			WechatAuthorized authorized = new WechatAuthorized(userInfo);
			sess.setAttribute(WechatAuthorized.SESSIONKEY, authorized);
			String target = (String) sess.getAttribute("wechat.target");
			
			return "redirect:" + target;
		} catch (WxErrorException e) {
			this.logger.catching(e);
			
			// 你看着办
			return "redirect:";
			
		} 
		
	}
	
}
