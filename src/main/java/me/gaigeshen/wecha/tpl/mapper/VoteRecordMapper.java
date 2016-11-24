package me.gaigeshen.wecha.tpl.mapper;

import me.gaigeshen.wecha.tpl.model.vote.VoteRecord;

public interface VoteRecordMapper {
	
	//增加投票记录
	public void insertRecord(VoteRecord record);
	
	//根据openid判断是否投过票
	public VoteRecord getRecordByOpenid(VoteRecord record);

    public int getRecordCount();
}
