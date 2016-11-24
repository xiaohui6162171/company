package me.gaigeshen.wecha.tpl.model.vote;

public class GameSorts {
	
	private Integer id;
	private String sort_name;//分类名
	private Integer state;//状态
	private Integer require_id;//需求id
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSort_name() {
		return sort_name;
	}
	public void setSort_name(String sort_name) {
		this.sort_name = sort_name;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Integer getRequire_id() {
		return require_id;
	}
	public void setRequire_id(Integer require_id) {
		this.require_id = require_id;
	}
	

}
