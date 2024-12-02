package pl.edu.pjwstk.mpr.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.websocket.server.PathParam;
import pl.edu.pjwstk.mpr.model.Loan;
import pl.edu.pjwstk.mpr.model.enums.LoanStatus;
import pl.edu.pjwstk.mpr.service.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping("")
    public ResponseEntity<List<Loan>> getAllLoansByStatus(@PathParam("loanStatus") LoanStatus loanStatus){
        return ResponseEntity.ok(loanService.getAllLoansByStatus(loanStatus));
    }

    @PostMapping("")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan){
        return ResponseEntity.ok(loanService.createLoan(loan));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Loan> updateLoan(@RequestBody Loan updatedLoan, @PathVariable("id") Long id){
        return ResponseEntity.ok(loanService.updateLoan(updatedLoan, id));
    }
}
