package me.gaigeshen.wecha.tpl.util.wechat;

import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * 
 * 
 * @author gaigeshen
 */
public class WechatAuthorized {
	
	public static final String SESSIONKEY = "WECHAT.AUTHORIZED";
	
	private WxMpUser wxMpUser;
	
	private WechatAuthorized() { }
	
	public WechatAuthorized(WxMpUser wxMpUser) {
		this();
		this.wxMpUser = wxMpUser;
	}
	
	public WxMpUser wxMpUser() {
		return this.wxMpUser;
	}
	

}
