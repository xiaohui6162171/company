package me.gaigeshen.wecha.tpl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import me.gaigeshen.wecha.tpl.mapper.UserMapper;
import me.gaigeshen.wecha.tpl.model.vote.User;
import me.gaigeshen.wecha.tpl.service.UserService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Resource
	private UserMapper userMapper;

	
	public User findUserInfoByPhone(String userMoblie) {
		return userMapper.findUserInfoByPhone(userMoblie);
	}


	@Override
	public User findUserInfoById(Integer user_id) {
		// TODO Auto-generated method stub
		return userMapper.findUserInfoById(user_id);
	}


	@Override
	public User Login(String userMoblie, String userPassword) {
		// TODO Auto-generated method stub
		List<User> user = userMapper.Login(userMoblie, userPassword);
		if(user == null || user.size() != 1){
			return null;
		}
		return user.get(0);
	}


	@Override
	public void Register(User user) {
		// TODO Auto-generated method stub
		userMapper.Register(user);
	}


	@Override
	public int checkUserName(User user) {
		// TODO Auto-generated method stub
		return userMapper.checkUserName(user).size();
	}
	
	

}
