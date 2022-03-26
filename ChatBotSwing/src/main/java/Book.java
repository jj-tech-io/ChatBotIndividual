public class Book {
    private String title, genre, author;
    private int pages;
    private Library lib;
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", genre='" + genre + '\'' +
                ", author='" + author + '\'' +
                '}';
    }

    public Book(String title, String genre, String author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
    }
    public Book(String title, String genre, int pages, String author) {
        this.title = title;
        this.genre = genre;
        this.pages = pages;
        this.author = author;
    }
    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getAuthor() {
        return author;
    }

    public int getPages(){ return pages; }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getBookDetails(){
        return "Title: " + title + "\t\tGenre: " + genre + "\t\tAuthor: " + author;
    }
}
