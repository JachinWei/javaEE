package bean;

public class PageInfo {
	int indexPage;//当前页面
	int totalPages;//总页数
	int count;//行数目
	public final static int PAGE_SIZE = 10;//每页展现的数目
	public int getIndexPage() {
		return indexPage;
	}
	public void setIndexPage(int indexPage) {
		this.indexPage = indexPage;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
}
