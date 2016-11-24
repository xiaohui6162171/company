package me.gaigeshen.wecha.tpl.model.vote;

public class GameWorksDetail {
	
	private Integer id;
	private Integer gameworks_id;//作品ID
	private String work_url;//作品url
	private String workthum_url;//作品缩略图url
	private Integer state;//状态
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGameworks_id() {
		return gameworks_id;
	}
	public void setGameworks_id(Integer gameworks_id) {
		this.gameworks_id = gameworks_id;
	}
	public String getWork_url() {
		return work_url;
	}
	public void setWork_url(String work_url) {
		this.work_url = work_url;
	}
	public String getWorkthum_url() {
		return workthum_url;
	}
	public void setWorkthum_url(String workthum_url) {
		this.workthum_url = workthum_url;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	
	
	

}
