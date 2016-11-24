package me.gaigeshen.wecha.tpl.controller.vote;

import java.io.IOException;
import java.util.HashMap;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.chanjar.weixin.common.exception.WxErrorException;

import me.chanjar.weixin.mp.bean.result.WxMpUser;
import me.gaigeshen.wecha.tpl.controller.Authorizing;
import me.gaigeshen.wecha.tpl.model.vote.GameVisit;
import me.gaigeshen.wecha.tpl.model.vote.GameWorks;
import me.gaigeshen.wecha.tpl.model.vote.VoteRecord;
import me.gaigeshen.wecha.tpl.service.GameVisitService;
import me.gaigeshen.wecha.tpl.service.GameWorksService;
import me.gaigeshen.wecha.tpl.service.VoteRecordService;
import me.gaigeshen.wecha.tpl.util.DateUtil;
import me.gaigeshen.wecha.tpl.util.wechat.WechatAuthorized;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/votewx")
public class GameWorksWxController {
	

	
	@Resource
	private GameWorksService worksService;
	
	@Resource
	private GameVisitService visitService;
	
	@Resource
	private  VoteRecordService  recordService;
	
	@Authorizing
	@RequestMapping("/getallgameworks")
	public String getallGameWorks(WechatAuthorized authorized,HttpServletRequest request,HttpServletResponse response) throws IOException{
		WxMpUser wxuser=authorized.wxMpUser();
		request.getSession().setAttribute("openid", wxuser.getOpenId());
		GameVisit visit=new GameVisit();
		visit.setOpenid(wxuser.getOpenId());
		visit.setCreatetime(DateUtil.getNowStringTime());
		visitService.insetGameVisit(visit);
		response.sendRedirect("/game/vote/index.html");
		return "index";
	}
	
	@ResponseBody
	@RequestMapping("/getVoteInfo")
	public Map<String,Object> getvoteInfo(){
		Map<String,Object> map=new HashMap<String,Object>();
		int  joinnum=worksService.getworksCount();
		map.put("joinnum", joinnum);
		int votecount=recordService.getRecordCount();
		map.put("votecount", votecount);
		int visitnum=visitService.getVisitCount();
		map.put("visitnum", visitnum);
		return map;
	}
	
	
	
	@ResponseBody
	@RequestMapping("/addvote_count")
	public Map<String,Object> addvote_count(HttpServletRequest request,Integer gamework_id) throws WxErrorException{
		Map<String,Object> map=new HashMap<String,Object>();
		String openid=(String)request.getSession().getAttribute("openid");
		VoteRecord record=new VoteRecord();
		record.setOpenid(openid);
		record.setGamework_id(gamework_id);
		VoteRecord r=recordService.getRecordByOpenid(record);
		if(r!=null){
			map.put("msg", 2); //2为该用户当日已经给该作品投票
		}else{
		record.setCreatetime(DateUtil.getNowStringDate());
		recordService.insertRecord(record);
		GameWorks  works=worksService.get_gameworksById(gamework_id);
		works.setVote_count(works.getVote_count()+1);
		worksService.update_gameworks(works);
		map.put("msg", 0); //0为该用户未投票
		}
		return map;
	}
	
	@ResponseBody
	@RequestMapping("/checkvote")
	public Map<String,Object> checkisnotvote(HttpServletRequest request,Integer workid){
		Map<String,Object> map=new HashMap<String,Object>();
		String openid=(String)request.getSession().getAttribute("openid");
		VoteRecord r=new VoteRecord();
		r.setOpenid(openid);
		r.setGamework_id(workid);
		VoteRecord record=recordService.getRecordByOpenid(r);
		if(record==null){
			map.put("msg", 0);//0可以投票
		}else if(record.getGamework_id()==workid){
			map.put("msg", 1);//1已经投过
		}
		return map;
	}

}
