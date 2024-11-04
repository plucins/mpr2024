package pl.edu.pjwstk.mpr.repository;

import java.util.HashMap;
import java.util.Map;

import pl.edu.pjwstk.mpr.model.Book;

public class BookRepository implements RepositoryInterface<Book> {

    //CRUD = Create | Read | Update | Delete
    private final Map<Long, Book> books = new HashMap<>();

    @Override
    public Map<Long, Book> getDataBase() {
        return books;
    }

    public Book createBook(Book book){
        books.put(RepoUtils.getNextId(this), book);
        System.out.println(books.keySet());
        return books.get(RepoUtils.getHighestId(this));
    }

    public Book getBookById(Long id){
        return books.get(id);
    }

    public boolean isBookExists(Long id){
        return books.containsKey(id);
    }

    public Book updateBook(Long actualBookId, Book updatedBook){
        books.replace(actualBookId, updatedBook);
        return getBookById(actualBookId);
    }

    public boolean deleteBookById(Long id){
        return books.remove(id) != null;
    }
}
