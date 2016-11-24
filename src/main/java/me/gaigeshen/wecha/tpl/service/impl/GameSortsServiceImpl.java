package me.gaigeshen.wecha.tpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.gaigeshen.wecha.tpl.mapper.GameSortsMapper;
import me.gaigeshen.wecha.tpl.model.vote.GameSorts;
import me.gaigeshen.wecha.tpl.service.GameSortsService;

@Service
@Transactional
public class GameSortsServiceImpl implements GameSortsService{
	
	@Resource
	private GameSortsMapper gameSortsMapper;

	@Override
	public List<GameSorts> getGameSorts(Integer state) {		
		return gameSortsMapper.getGameSorts(state);
	}

	@Override
	public GameSorts getgamesortByRequireId(Integer require_id) {
		// TODO Auto-generated method stub
		return gameSortsMapper.getgamesortByRequireId(require_id);
	}

	@Override
	public void addgamesort(GameSorts gamesorts) {
		// TODO Auto-generated method stub
		gameSortsMapper.addgamesort(gamesorts);
	}

	@Override
	public GameSorts getGameSortById(Integer id) {
		// TODO Auto-generated method stub
		return gameSortsMapper.getGameSortById(id);
	}

	@Override
	public void deletesort(Integer require_id) {
		// TODO Auto-generated method stub
		gameSortsMapper.deletesort(require_id);
	}

}
