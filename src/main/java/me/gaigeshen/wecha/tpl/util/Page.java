package me.gaigeshen.wecha.tpl.util;




/**
 * ┣描述：分页类<br>
 * ┣@author 王岩斌<br>
 * ┣@ClassName Page<br>
 * ┣@date 2016-5-26上午10:31:08<br>
 * ┣@Version 10.6
 */
public class Page {

	private Integer pageSize; // 显示个数
	private Integer count;// 总记录数
    // 当前页的记录集合
	private Integer pageIndex;// 当前页号
	private Integer totalPages;// 总页数

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
		this.totalPages = this.count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
	}



	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
}
