package me.gaigeshen.wecha.tpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.gaigeshen.wecha.tpl.mapper.GameWorksMapper;
import me.gaigeshen.wecha.tpl.model.vote.GameWorks;
import me.gaigeshen.wecha.tpl.service.GameWorksService;

@Service
@Transactional
public class GameWorksServiceImpl implements GameWorksService{
	
	@Resource
	private GameWorksMapper gameworksmapper;

	
	public void insert_GameWorks(GameWorks gWorks) {
		
		gameworksmapper.insert_GameWorks(gWorks);
		
	}


	public GameWorks get_gameworksById(Integer id) {
		
		return gameworksmapper.get_gameworksById(id);
	}


	public List<GameWorks> getworks(GameWorks gWorks) {
		
		return gameworksmapper.getworks(gWorks);
	}


	@Override
	public int getcountBySorId(Integer sort_id) {
		// TODO Auto-generated method stub
		return gameworksmapper.getcountBySorId(sort_id);
	}


	@Override
	public int getworksCount() {
		// TODO Auto-generated method stub
		return  gameworksmapper.getworksCount();
	}


	@Override
	public List<GameWorks> getallwork() {
		// TODO Auto-generated method stub
		return gameworksmapper.getallwork();
	}


	@Override
	public void update_gameworks(GameWorks gWorks) {
		// TODO Auto-generated method stub
		gameworksmapper.update_gameworks(gWorks);
	}
	

}
