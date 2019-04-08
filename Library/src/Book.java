
public class Book {
	
	private String title;
	private String author;
	private String publisher;
	private int publishYear;
	private String location;
	private boolean isAvailable;
	
	public Book(String title,String author,String publisher,int publishYear,String loaction){
		this.title=title;
		this.author=author;
		this.publishYear=publishYear;
		this.publisher=publisher;
		this.location=location;
		this.isAvailable=true;
	}

	@Override
	public String toString() {
		return "Book [title=" + title + ", author=" + author + ", publisher=" + publisher + ", publishYear="
				+ publishYear + ", location=" + location + ", isAvailable=" + isAvailable + "]";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
