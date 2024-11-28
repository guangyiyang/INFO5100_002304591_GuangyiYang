package model;

import java.util.ArrayList;
import java.util.List;

public class BookShelf {
    private List<Book> books;

    // Constructor
    public BookShelf() {
        this.books = new ArrayList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }

    @Override
    public String toString() {
        return "BookShelf{" +
                "books=" + books +
                '}';
    }
}
