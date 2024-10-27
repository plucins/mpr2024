package service;

import model.Book;
import repository.BookRepository;

public class BookService {
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(Book book) {
        if (book.getIsbn() == null) {
            throw new IllegalArgumentException("ISBN is required field");
        }
        return bookRepository.createBook(book);
    }

    public Book getBookById(Long id) {
        if (!bookRepository.isBookExists(id)) {
            throw new IllegalArgumentException("Could not find Book by ID: " + id);
        }
        return bookRepository.getBookById(id);
    }

    public Book updateBook(Long actualBookId, Book updatedBook) {
        Book actualBook = getBookById(actualBookId);

        if (!actualBook.getLoanStatus().equals(updatedBook.getLoanStatus())) {
            actualBook.setLoanStatus(updatedBook.getLoanStatus());
        }

        if (!actualBook.getBookType().equals(updatedBook.getBookType()) && updatedBook.getBookType() != null) {
            actualBook.setBookType(updatedBook.getBookType());
        }

        return bookRepository.updateBook(actualBookId, actualBook);
    }

    public void deleteBook(Long id){
        getBookById(id);
        bookRepository.deleteBookById(id);
    }
}
