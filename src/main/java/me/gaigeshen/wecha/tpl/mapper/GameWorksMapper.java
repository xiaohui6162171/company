package me.gaigeshen.wecha.tpl.mapper;

import java.util.List;

import me.gaigeshen.wecha.tpl.model.vote.GameWorks;

public interface GameWorksMapper {
	
	//增加活动作品
	public void insert_GameWorks(GameWorks gWorks);
	
	//查询所有活动作品
	public List<GameWorks> getworks(GameWorks gWorks);
	
	public List<GameWorks> getallwork();
	
	//根据作品信息查询
	public GameWorks get_gameworksById(Integer id);
	
	//根据分类id查询作品数量
	public int getcountBySorId(Integer sort_id);
	
	public int getworksCount();
	
	public void update_gameworks(GameWorks gWorks);
	

}
