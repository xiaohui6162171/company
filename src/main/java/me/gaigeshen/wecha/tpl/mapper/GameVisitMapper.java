package me.gaigeshen.wecha.tpl.mapper;

import me.gaigeshen.wecha.tpl.model.vote.GameVisit;

public interface GameVisitMapper {
	
	public void insetGameVisit(GameVisit gameVisit);
	
	public int getVisitCount();

}
