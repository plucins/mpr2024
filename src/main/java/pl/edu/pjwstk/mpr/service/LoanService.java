package pl.edu.pjwstk.mpr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import pl.edu.pjwstk.mpr.model.Loan;
import pl.edu.pjwstk.mpr.model.enums.LoanStatus;
import pl.edu.pjwstk.mpr.repository.LoanRepository;

@Service
public class LoanService {

    private LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAllLoansByStatus(LoanStatus loanStatus) {
        return loanRepository.findAllByLoanStatus(loanStatus);
    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Optional<Loan> getLoanById(Long id){
        return loanRepository.findById(id);
    }

    public Loan updateLoan(Loan updatedLoan, Long id) {
        Loan actualLoan = getLoanById(id).get();

        if(!actualLoan.getLoanTo().getId().equals(updatedLoan.getLoanTo().getId())){
            actualLoan.setLoanTo(updatedLoan.getLoanTo());
        }

        if(!actualLoan.getActualLoanEndDate().equals(updatedLoan.getActualLoanEndDate())){
            actualLoan.setActualLoanEndDate(updatedLoan.getActualLoanEndDate());
        }

        if(!actualLoan.getLoanStatus().equals(updatedLoan.getLoanStatus())){
            actualLoan.setLoanStatus(updatedLoan.getLoanStatus());
        }

        return loanRepository.save(actualLoan);
    }
}
