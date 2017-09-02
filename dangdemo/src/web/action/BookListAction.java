package web.action;

import java.util.ArrayList;

import entity.Book;
import entity.CategoryCount;
import entity.PageInfo;
import service.BookService;
import service.ProductService;

public class BookListAction  {
	BookService bookService = new BookService();
	ProductService productService = new ProductService();
	
	private PageInfo<Book> pageInfo;
	private ArrayList<CategoryCount> cateArray;//当前页书籍列表，从数据库中取，传给前端
	private Integer pageIndex = 1;//当前页，前端
	private Integer category_id;//当前分类id，前后交流
	private Integer totals=0;//书本总数，从数据库中取，传给前端

	public String getList() {
		cateArray = productService.getProductCountByCate();
		for (CategoryCount obj : cateArray) {
			totals += (Integer)obj.getCount();
		}
		if (pageIndex == null || pageIndex <= 1 ) {
			pageIndex = 1;// 默认页1
		}
		pageInfo = new PageInfo<Book>();
		pageInfo.setPageIndex(pageIndex);
		
		bookService.findByPage(pageInfo, category_id);
		return "success";
	}

	public PageInfo<Book> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Book> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public ArrayList<CategoryCount> getCateArray() {
		return cateArray;
	}

	public void setCateArray(ArrayList<CategoryCount> cateArray) {
		this.cateArray = cateArray;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}

	public Integer getTotals() {
		return totals;
	}

	public void setTotals(Integer totals) {
		this.totals = totals;
	}
}
