package me.gaigeshen.wecha.tpl.model.vote;


public class VoteRecord {
	
	private Integer id;
	private String openid;//投票用户的openid
	private Integer gamework_id;//投票的作品id
	private String createtime;//投票时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public Integer getGamework_id() {
		return gamework_id;
	}
	public void setGamework_id(Integer gamework_id) {
		this.gamework_id = gamework_id;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	

}
