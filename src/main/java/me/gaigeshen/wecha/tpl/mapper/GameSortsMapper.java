package me.gaigeshen.wecha.tpl.mapper;

import java.util.List;

import me.gaigeshen.wecha.tpl.model.vote.GameSorts;

public interface GameSortsMapper {
	
	public List<GameSorts> getGameSorts(Integer state);
	
	GameSorts getgamesortByRequireId(Integer require_id);
	
	public void addgamesort(GameSorts gamesorts);
	
	GameSorts getGameSortById(Integer id);
	
	public void deletesort(Integer require_id);

}
