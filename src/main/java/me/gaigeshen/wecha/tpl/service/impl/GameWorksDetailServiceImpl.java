package me.gaigeshen.wecha.tpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.gaigeshen.wecha.tpl.mapper.GameWorksDetailMapper;
import me.gaigeshen.wecha.tpl.model.vote.GameWorksDetail;
import me.gaigeshen.wecha.tpl.service.GameWorksDetailService;

@Service
@Transactional
public class GameWorksDetailServiceImpl implements GameWorksDetailService{
	
	@Resource
	private GameWorksDetailMapper gameworksdetailmapper;

	
	public List<GameWorksDetail> getworksdetailsById(Integer gameworks_id) {
		
		return gameworksdetailmapper.getworksdetailsById(gameworks_id);
	}

	
	public void insertworksdetail(GameWorksDetail detail) {
		
		gameworksdetailmapper.insertworksdetail(detail);
	}

}
