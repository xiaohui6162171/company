package me.gaigeshen.wecha.tpl.config.wechat;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageHandler;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpUserService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpUserServiceImpl;
import me.chanjar.weixin.mp.bean.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.WxMpXmlOutMessage;

/**
 * 
 * @author gaigeshen
 */
@Configuration
@PropertySource("classpath:wechat.properties")
public class WechatConfig {

	@Value("${appid}")
	private String appid;

	@Value("${secret}")
	private String secret;

	@Value("${token}")
	private String token;

	@Value("${aeskey}")
	private String aeskey;

	@Value("${oauth2.redirect.url}")
	private String oauth2RedirectUrl;

	@Value("${pay.partnerid}")
	private String partnerId;

	@Value("${pay.partnerkey}")
	private String partnerKey;

	@Bean
	public WxMpMessageRouter wxMpMessageRouter(WxMpService wxMpService) {

		WxMpMessageRouter router = new WxMpMessageRouter(wxMpService);

		router.rule().async(false).msgType(WxConsts.XML_MSG_EVENT)
				.event(WxConsts.EVT_SUBSCRIBE)
				.handler(new SubscribeMessageHandler()).end();

		return router;
	}

	@Bean
	public WxMpService wxMpService(WxMpConfigStorage storage) {

		WxMpService srv = new WxMpServiceImpl();

		srv.setWxMpConfigStorage(storage);

		return srv;

	}
	
	@Bean
	public WxMpUserService wxMpUserService(WxMpService wxMpService){
		WxMpUserService wmup=new WxMpUserServiceImpl(wxMpService);
		return wmup;
	}
	

	@Bean
	public WxMpConfigStorage wxMpConfigStorage() {

		WxMpInMemoryConfigStorage storage = new WxMpInMemoryConfigStorage();

		storage.setAppId(this.appid);
		storage.setSecret(this.secret);
		storage.setToken(this.token);
		storage.setAesKey(this.aeskey);
		storage.setOauth2redirectUri(this.oauth2RedirectUrl);
		storage.setPartnerId(this.partnerId);
		storage.setPartnerKey(this.partnerKey);

		return storage;

	}

	public class SubscribeMessageHandler implements WxMpMessageHandler {

		@Override
		public WxMpXmlOutMessage handle(WxMpXmlMessage msg,
				Map<String, Object> ctx, WxMpService srv,
				WxSessionManager sessMgr) throws WxErrorException {

			WxMpXmlOutMessage outMsg = WxMpXmlOutMessage.TEXT()
					.fromUser(msg.getToUserName())
					.toUser(msg.getFromUserName()).content("欢迎你关注我").build();

			return outMsg;

		}

	}

}
