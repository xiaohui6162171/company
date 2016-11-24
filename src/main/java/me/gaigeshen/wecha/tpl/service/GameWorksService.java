package me.gaigeshen.wecha.tpl.service;

import java.util.List;

import me.gaigeshen.wecha.tpl.model.vote.GameWorks;

public interface GameWorksService {
	
	    //增加活动作品
		void insert_GameWorks(GameWorks gWorks);
		
		//查询所有活动作品
		List<GameWorks> getworks(GameWorks gWorks);
		
	   List<GameWorks> getallwork();
		
		//根据作品信息查询
		GameWorks get_gameworksById(Integer id);
		
		//根据分类id查询作品数量
		int getcountBySorId(Integer sort_id);
		
	    int getworksCount();
	    
		public void update_gameworks(GameWorks gWorks);
}
