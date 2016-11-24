package me.gaigeshen.wecha.tpl.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.gaigeshen.wecha.tpl.mapper.VoteRecordMapper;
import me.gaigeshen.wecha.tpl.model.vote.VoteRecord;
import me.gaigeshen.wecha.tpl.service.VoteRecordService;


@Service
@Transactional
public class VoteRecordServiceImpl implements VoteRecordService{
	
	@Resource
	private VoteRecordMapper votemapper;

	@Override
	public void insertRecord(VoteRecord record) {
		votemapper.insertRecord(record);
		
	}

	@Override
	public VoteRecord getRecordByOpenid(VoteRecord record) {
		// TODO Auto-generated method stub
		return votemapper.getRecordByOpenid(record);
	}

	@Override
	public int getRecordCount() {
		// TODO Auto-generated method stub
		return votemapper.getRecordCount();
	}

}
