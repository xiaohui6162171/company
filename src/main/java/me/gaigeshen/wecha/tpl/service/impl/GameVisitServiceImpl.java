package me.gaigeshen.wecha.tpl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.gaigeshen.wecha.tpl.mapper.GameVisitMapper;
import me.gaigeshen.wecha.tpl.model.vote.GameVisit;
import me.gaigeshen.wecha.tpl.service.GameVisitService;

@Service
@Transactional
public class GameVisitServiceImpl implements GameVisitService {
	
	@Resource
	private GameVisitMapper visitMapper;

	@Override
	public void insetGameVisit(GameVisit gameVisit) {
		// TODO Auto-generated method stub
		visitMapper.insetGameVisit(gameVisit);
	}

	@Override
	public int getVisitCount() {
		// TODO Auto-generated method stub
		return visitMapper.getVisitCount();
	}

}
