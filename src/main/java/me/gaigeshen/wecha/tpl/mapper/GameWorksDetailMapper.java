package me.gaigeshen.wecha.tpl.mapper;

import java.util.List;

import me.gaigeshen.wecha.tpl.model.vote.GameWorksDetail;

public interface GameWorksDetailMapper {
	
	//根据作品id查询作品详情
	public List<GameWorksDetail> getworksdetailsById(Integer gameworks_id);
	
	//增加作品图片
	public void insertworksdetail(GameWorksDetail detail);
	
	
	
	

}
