package me.gaigeshen.wecha.tpl.controller.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.gaigeshen.wecha.tpl.util.http.RequestContextUtils;

/**
 * 微信分享
 */
@Controller
public class PutJsConfigController {
	
	@Autowired private WxMpService wxMpService;
	private Logger logger = LogManager.getLogger();
	
	// 微信分享配置
	public void putJsConfig(Model model) {
		
		String url = RequestContextUtils.requestUrl();
		
		WxJsapiSignature sign = null;
		
		try {
			sign = this.wxMpService.createJsapiSignature(url);
		} catch (WxErrorException e) { this.logger.catching(e); }
		
		if (sign != null)
			model.addAttribute("jsConfig", sign);
		
	}

}
