package entity;

import java.util.List;

public class PageInfo<T> {
	private List<T> pageList; // 该页所有数据
	private Integer count; // 总记录数
	private Integer totalPages; // 总页数
	private Integer pageIndex; // 当前页
	public final static int PAGE_SIZE = 5; // 常量：每页显示条数

	public PageInfo() {
	}

	public PageInfo(List<T> pageList, int count, int totalPages, int pageIndex) {
		super();
		this.pageList = pageList;
		this.count = count;
		this.totalPages = totalPages;
		this.pageIndex = pageIndex;
	}

	public List<T> getPageList() {
		return pageList;
	}

	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public static int getPageSize() {
		return PAGE_SIZE;
	}

}
