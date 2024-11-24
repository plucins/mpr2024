package pl.edu.pjwstk.mpr.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import pl.edu.pjwstk.mpr.model.enums.LoanStatus;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime loanStartDate;
    private LocalDateTime expectedLoanEndDate;
    private LocalDateTime actualLoanEndDate;
    private LoanStatus loanStatus;
    @OneToOne
    private User loanTo;
    @OneToOne
    private User employee;
    @OneToOne
    private Book loanBook;

    public Loan() {
    }

    public Loan(User loanTo, User employee, Book loanBook) {
        this.loanStartDate = LocalDateTime.now();
        this.expectedLoanEndDate = LocalDateTime.now().plusDays(7);
        this.loanStatus = LoanStatus.DURING_LOAN;
        this.loanTo = loanTo;
        this.employee = employee;
        this.loanBook = loanBook;
    }

    public LocalDateTime getLoanStartDate() {
        return loanStartDate;
    }

    public LocalDateTime getExpectedLoanEndDate() {
        return expectedLoanEndDate;
    }

    public LocalDateTime getActualLoanEndDate() {
        return actualLoanEndDate;
    }

    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public User getLoanTo() {
        return loanTo;
    }

    public User getEmployee() {
        return employee;
    }

    public Book getLoanBook() {
        return loanBook;
    }
}
