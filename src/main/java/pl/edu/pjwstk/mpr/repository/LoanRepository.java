package pl.edu.pjwstk.mpr.repository;

import java.util.HashMap;
import java.util.Map;

import pl.edu.pjwstk.mpr.model.Loan;

public class LoanRepository implements RepositoryInterface<Loan> {
    private final Map<Long, Loan> loans = new HashMap<>();

    public Loan createLoan(Loan loan){
        loans.put(RepoUtils.getNextId(this), loan);
        return loans.get(RepoUtils.getHighestId(this));
    }


    @Override
    public Map<Long, Loan> getDataBase() {
        return loans;
    }
}
