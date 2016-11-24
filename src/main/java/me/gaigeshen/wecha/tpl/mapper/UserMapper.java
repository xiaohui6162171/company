package me.gaigeshen.wecha.tpl.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import me.gaigeshen.wecha.tpl.model.vote.User;



public interface UserMapper {
	
	//根据号码获得用户信息
	public User findUserInfoByPhone(String userMoblie);
	
	public User findUserInfoById(Integer user_id);
	
	//登录
	public List<User> Login(@Param(value = "userMoblie") String userMoblie,@Param(value = "userPassword") String userPassword);
	
	//注册
	public void Register(User user);

	//根据用户名查询用户是否唯一
	public List<User> checkUserName(User user);
}
