package pl.edu.pjwstk.mpr.model;

import java.time.LocalDateTime;
import pl.edu.pjwstk.mpr.model.enums.LoanStatus;

public class Loan {
    private LocalDateTime loanStartDate;
    private LocalDateTime expectedLoanEndDate;
    private LocalDateTime actualLoanEndDate;
    private LoanStatus loanStatus;
    private User loanTo;
    private User employee;
    private Book loanBook;

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
