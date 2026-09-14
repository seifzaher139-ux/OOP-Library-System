package librarySystem;

public class Book extends LibraryItem {

    // ==================== Fields ====================

    private String author;
    private int pages;


    // ==================== Constructor ====================

    public Book(String title, String author, int pages) {
        super(title);
        this.author = author;
        this.pages = pages;
    }


    // ==================== Getters and Setters ====================

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }


    // ==================== Overridden Methods ====================

    @Override
    public int getLoanPeriodDays() {
        return 21;
    }

    @Override
    public String getType() {
        return "Book";
    }
}