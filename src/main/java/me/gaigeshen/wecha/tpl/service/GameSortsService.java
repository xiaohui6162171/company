package me.gaigeshen.wecha.tpl.service;

import java.util.List;

import me.gaigeshen.wecha.tpl.model.vote.GameSorts;

public interface GameSortsService {
	
	public List<GameSorts> getGameSorts(Integer state);
	
	GameSorts getgamesortByRequireId(Integer require_id);
	
	public void addgamesort(GameSorts gamesorts);
	
	GameSorts getGameSortById(Integer id);
    
	public void deletesort(Integer require_id);

}
