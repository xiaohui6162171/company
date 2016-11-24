package me.gaigeshen.wecha.tpl.service;

import java.util.List;

import me.gaigeshen.wecha.tpl.model.vote.GameWorksDetail;

public interface GameWorksDetailService {
	
	//根据作品id查询作品详情
	List<GameWorksDetail> getworksdetailsById(Integer gameworks_id);
	
	//增加作品图片
	void insertworksdetail(GameWorksDetail detail);

}
