package me.gaigeshen.wecha.tpl.model.vote;

public class User {
	
	private Integer userId;// 用户编号
	private String userName;// 用户名
	private String userPassword;// 密码
	private String userMoblie;// 手机号
	private String userRealname;// 真实姓名
	private String userBirthday;// 出生日期
	private String userSex;// 性别
	private String userAge;// 年龄
	private String userProfile;// 头像
	private String userBackground;// 个人中心背景图
	private String userCurrent;// 头衔
	private String userIntroduction;// 个人简介
	private String userAddress;// 详细地址
	private String userUniversity;// 毕业院校
	private String userIdea;// 设计理念
	private String userAchievement;// 所获奖项或成就
	private Float userRank;// 设计师星级由1-5决定
	private Integer userAuthority;// 用户权限 0、普通用户 1、设计师 2、设计团队
	private Integer userOne;// 封神设计师
	private Integer stylistState; // 设计师开启或关闭预约
	private Integer userBannedstate;// 是否禁言 0、正常 9、禁言
	private String userCreatetime;// 创建时间
	private String userUpdatetime;// 创建时间
	private Integer userSealstate;// 是否封号
	private Integer userStyliststate;// 申请成为设计师
	private String userUploadzip;// 附件
	private String achievement;// 换行使用的成就
	private String idea;// 换行使用的理念
	private String video;//用户视频
	private Integer designerSort; //用户排序
	private String userOpenid;// openid
	public Integer getDesignerSort() {
		return designerSort;
	}

	public void setDesignerSort(Integer designerSort) {
		this.designerSort = designerSort;
	}


	
	public String getVideo() {
		if (this.video == null) {
			return "";
		}
		return this.video.replaceAll("& gt;", ">").replaceAll("& lt;", "<")
				.replaceAll("& #40;", "(").replaceAll("& #41;", ")");
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserMoblie() {
		return userMoblie;
	}

	public void setUserMoblie(String userMoblie) {
		this.userMoblie = userMoblie;
	}

	public String getUserRealname() {
		return userRealname;
	}

	public void setUserRealname(String userRealname) {
		this.userRealname = userRealname;
	}

	public String getUserBirthday() {
		return userBirthday;
	}

	public void setUserBirthday(String userBirthday) {
		this.userBirthday = userBirthday;
	}

	public String getUserSex() {
		return userSex;
	}

	public void setUserSex(String userSex) {
		this.userSex = userSex;
	}

	public String getUserAge() {
		return userAge;
	}

	public void setUserAge(String userAge) {
		this.userAge = userAge;
	}

	public String getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(String userProfile) {
		this.userProfile = userProfile;
	}

	public String getUserCurrent() {
		return userCurrent;
	}

	public void setUserCurrent(String userCurrent) {
		this.userCurrent = userCurrent;
	}

	public String getUserIntroduction() {
		return userIntroduction;
	}

	public void setUserIntroduction(String userIntroduction) {
		this.userIntroduction = userIntroduction;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public String getUserUniversity() {
		return userUniversity;
	}

	public void setUserUniversity(String userUniversity) {
		this.userUniversity = userUniversity;
	}

	public String getUserAchievement() {
		return userAchievement;
	}

	public void setUserAchievement(String userAchievement) {
		this.userAchievement = userAchievement;
	}

	public Float getUserRank() {
		return userRank;
	}

	public void setUserRank(Float userRank) {
		this.userRank = userRank;
	}

	public Integer getStylistState() {
		return stylistState;
	}

	public void setStylistState(Integer stylistState) {
		this.stylistState = stylistState;
	}

	public Integer getUserAuthority() {
		return userAuthority;
	}

	public void setUserAuthority(Integer userAuthority) {
		this.userAuthority = userAuthority;
	}

	public Integer getUserOne() {
		return userOne;
	}

	public void setUserOne(Integer userOne) {
		this.userOne = userOne;
	}

	public Integer getUserBannedstate() {
		return userBannedstate;
	}

	public void setUserBannedstate(Integer userBannedstate) {
		this.userBannedstate = userBannedstate;
	}

	public String getUserCreatetime() {
		return userCreatetime;
	}

	public void setUserCreatetime(String userCreatetime) {
		this.userCreatetime = userCreatetime;
	}

	public String getUserUpdatetime() {
		return userUpdatetime;
	}

	public void setUserUpdatetime(String userUpdatetime) {
		this.userUpdatetime = userUpdatetime;
	}

	public Integer getUserSealstate() {
		return userSealstate;
	}

	public void setUserSealstate(Integer userSealstate) {
		this.userSealstate = userSealstate;
	}

	public Integer getUserStyliststate() {
		return userStyliststate;
	}

	public void setUserStyliststate(Integer userStyliststate) {
		this.userStyliststate = userStyliststate;
	}

	public String getUserUploadzip() {
		return userUploadzip;
	}

	public String getUserIdea() {
		return userIdea;
	}

	public void setUserIdea(String userIdea) {
		this.userIdea = userIdea;
	}

	public void setUserUploadzip(String userUploadzip) {
		this.userUploadzip = userUploadzip;
	}

	public String getAchievement() {
		return achievement;
	}

	public void setAchievement(String achievement) {
		this.achievement = achievement;
	}

	public String getIdea() {
		return idea;
	}

	public void setIdea(String idea) {
		this.idea = idea;
	}

	public String getUserBackground() {
		return userBackground;
	}

	public void setUserBackground(String userBackground) {
		this.userBackground = userBackground;
	}



	public String getUserOpenid() {
		return userOpenid;
	}

	public void setUserOpenid(String userOpenid) {
		this.userOpenid = userOpenid;
	}
	

}
