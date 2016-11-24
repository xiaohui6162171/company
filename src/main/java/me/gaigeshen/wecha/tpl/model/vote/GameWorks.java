package me.gaigeshen.wecha.tpl.model.vote;



public class GameWorks{
	
	private Integer id; 
	private String work_num;
	private Integer userid;//用户ID
	private String username;
	private String main_photo;//主视觉图
	private String mainthum_photo;//主视觉缩略图 
	private String works_name;///作品名
	private Integer sort_id;//作品分类的ID
	private String works_introduction;//作品介绍
	private Integer vote_count;//投票数量
	private String createtime;//上传时间
	private Integer state;//状态(现有0、原创1)
	private String email;//邮箱
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getWork_num() {
		return work_num;
	}
	public void setWork_num(String work_num) {
		this.work_num = work_num;
	}
	public Integer getUserid() {
		return userid;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getMain_photo() {
		return main_photo;
	}
	public void setMain_photo(String main_photo) {
		this.main_photo = main_photo;
	}
	
	public String getMainthum_photo() {
		return mainthum_photo;
	}
	public void setMainthum_photo(String mainthum_photo) {
		this.mainthum_photo = mainthum_photo;
	}
	public String getWorks_name() {
		return works_name;
	}
	public void setWorks_name(String works_name) {
		this.works_name = works_name;
	}
	public Integer getSort_id() {
		return sort_id;
	}
	public void setSort_id(Integer sort_id) {
		this.sort_id = sort_id;
	}
	public String getWorks_introduction() {
		return works_introduction;
	}
	public void setWorks_introduction(String works_introduction) {
		this.works_introduction = works_introduction;
	}
	public Integer getVote_count() {
		return vote_count;
	}
	public void setVote_count(Integer vote_count) {
		this.vote_count = vote_count;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
