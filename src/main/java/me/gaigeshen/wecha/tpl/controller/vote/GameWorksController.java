package me.gaigeshen.wecha.tpl.controller.vote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import me.gaigeshen.wecha.tpl.model.vote.GameSorts;
import me.gaigeshen.wecha.tpl.model.vote.GameWorks;
import me.gaigeshen.wecha.tpl.model.vote.GameWorksDetail;
import me.gaigeshen.wecha.tpl.model.vote.User;
import me.gaigeshen.wecha.tpl.service.GameSortsService;
import me.gaigeshen.wecha.tpl.service.GameWorksDetailService;
import me.gaigeshen.wecha.tpl.service.GameWorksService;
import me.gaigeshen.wecha.tpl.service.UserService;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/gameworks")
public class GameWorksController {

	@Resource
	private GameWorksService gameWorksService;

	@Resource
	private GameWorksDetailService detailService;

    @Resource
    private UserService userService;
    
    @Resource
    private GameSortsService sortsService;

	// 根据分类查询作品列表
	@ResponseBody
	@RequestMapping("/getGameWorksBySortsId")
	public List<GameWorks> getGameWorksBySortsId(Integer sortid, Integer state,
			Model model) {
		GameWorks works = new GameWorks();
		works.setSort_id(sortid);
		works.setState(state);
		List<GameWorks> gameworks = gameWorksService.getworks(works);
		return gameworks;

	}
	
	@ResponseBody
	@RequestMapping("/GameWorksVote")
   public List<GameWorks> getGameWorks(Integer flag){
		GameWorks works = new GameWorks();
		List<GameWorks> gameworks=new ArrayList<GameWorks>()	;
		if(flag==0){
			gameworks = gameWorksService.getworks(works);  //根据時閒
		}
		if(flag==1){
			gameworks=gameWorksService.getallwork();//根据投票数量
		}
		return gameworks;
   }

	// 根据作品Id查询作品详情
	@ResponseBody
	@RequestMapping("/getGameWorksDetail")
	public Map<String, Object> getGameWorksDetailByWorksId(Integer worksid) {
		Map<String, Object> map = new HashMap<String, Object>();
		GameWorks gameWorks = gameWorksService.get_gameworksById(worksid);
		List<GameWorksDetail> details = detailService
				.getworksdetailsById(worksid);
		List<GameWorksDetail> detail = new ArrayList<GameWorksDetail>();
		for (GameWorksDetail d : details) {
			String st = d.getWork_url().substring(d.getWork_url().indexOf("."));
			if (st.equals(".mp4")) {
				detail.add(d);
			}
		}
		for (GameWorksDetail d : details) {
			String st = d.getWork_url().substring(d.getWork_url().indexOf("."));
			if (!st.equals(".mp4")) {
				detail.add(d);
			}
		}
		GameSorts sort=sortsService.getGameSortById(gameWorks.getSort_id());
		map.put("sortname", sort.getSort_name());
		map.put("gameWorks", gameWorks);
		map.put("details", detail);
		return map;
	}


	
	

}
