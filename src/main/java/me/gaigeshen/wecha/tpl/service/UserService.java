package me.gaigeshen.wecha.tpl.service;



import me.gaigeshen.wecha.tpl.model.vote.User;


public interface UserService {
	
	//根据号码获得用户信息
	User findUserInfoByPhone(String userMoblie);
	
	//根据id获得用户信息
	public User findUserInfoById(Integer user_id);
	
	//登录
	public User Login(String userMoblie, String userPassword);
	
	//注册
	public void Register(User user);

	//根据用户名查询用户是否唯一
	public int checkUserName(User user);

}
