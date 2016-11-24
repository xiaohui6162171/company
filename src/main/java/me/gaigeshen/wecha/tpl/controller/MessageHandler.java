package me.gaigeshen.wecha.tpl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 
 * 
 * @author gaigeshen
 */
@RestController
@RequestMapping(path = "/messagehandler")
public class MessageHandler {

	@Autowired
	private WxMpMessageRouter messageRouter;

	@Autowired
	private WxMpService wxMpSrv;

	@RequestMapping(path = "/handle", method = RequestMethod.GET)
	public String handshake(String signature, String timestamp, String nonce,
			String echostr) {

		if (this.wxMpSrv.checkSignature(timestamp, nonce, signature))
			return echostr;

		return null;

	}

	@RequestMapping(path = "/handle", method = RequestMethod.POST)
	public String handle(@RequestBody String message) {

		WxMpXmlMessage inMsg = WxMpXmlMessage.fromXml(message);

		WxMpXmlOutMessage outMsg = this.messageRouter.route(inMsg);

		if (outMsg != null)
			return outMsg.toXml();

		// No message
		return "success";

	}

}
