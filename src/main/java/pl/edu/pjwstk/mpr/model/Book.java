package pl.edu.pjwstk.mpr.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import pl.edu.pjwstk.mpr.model.enums.BookType;
import pl.edu.pjwstk.mpr.model.enums.LoanStatus;

@Entity
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String tile;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private List<Author> author;
    private Long isbn;
    private BookType bookType;
    private LoanStatus loanStatus;

    public Book() {
    }

    public Book(String tile, List<Author> author, Long isbn, BookType bookType) {
        this.tile = tile;
        this.author = author;
        this.isbn = isbn;
        this.bookType = bookType;
        this.loanStatus = LoanStatus.AVAILABLE;
    }

    public String getTile() {
        return tile;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public Long getIsbn() {
        return isbn;
    }

    public BookType getBookType() {
        return bookType;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }
}
