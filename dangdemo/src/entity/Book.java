package entity;

public class Book {
	private Integer id;
	private String author;
	private String publishing;
	private Long publish_time;
	private String word_number;
	private String which_edtion;
	private String total_page;
	private Long print_time;
	private String isbn;
	private String author_summary;
	private String catalogue;
	private Product product;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublishing() {
		return publishing;
	}

	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}

	public Long getPublish_time() {
		return publish_time;
	}

	public void setPublish_time(Long publish_time) {
		this.publish_time = publish_time;
	}

	public String getWord_number() {
		return word_number;
	}

	public void setWord_number(String word_number) {
		this.word_number = word_number;
	}

	public String getWhich_edtion() {
		return which_edtion;
	}

	public void setWhich_edtion(String which_edtion) {
		this.which_edtion = which_edtion;
	}

	public String getTotal_page() {
		return total_page;
	}

	public void setTotal_page(String total_page) {
		this.total_page = total_page;
	}

	public Long getPrint_time() {
		return print_time;
	}

	public void setPrint_time(Long print_time) {
		this.print_time = print_time;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor_summary() {
		return author_summary;
	}

	public void setAuthor_summary(String author_summary) {
		this.author_summary = author_summary;
	}

	public String getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(String catalogue) {
		this.catalogue = catalogue;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
