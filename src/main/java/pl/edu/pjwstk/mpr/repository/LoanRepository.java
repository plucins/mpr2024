package pl.edu.pjwstk.mpr.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pjwstk.mpr.model.Loan;
import pl.edu.pjwstk.mpr.model.enums.LoanStatus;

@Repository
public interface LoanRepository extends JpaRepository<Loan,Long > {
    List<Loan> findAllByLoanStatus(LoanStatus loanStatus);
}
