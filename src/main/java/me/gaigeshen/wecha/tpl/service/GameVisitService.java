package me.gaigeshen.wecha.tpl.service;

import me.gaigeshen.wecha.tpl.model.vote.GameVisit;

public interface GameVisitService {
	
	public void insetGameVisit(GameVisit gameVisit);
	
	public int getVisitCount();


}
