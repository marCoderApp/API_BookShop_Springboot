package com.project.apibookshop.controller;

import com.project.apibookshop.dto.LoanDTO;
import com.project.apibookshop.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {


    @Autowired
    private LoanService loanService;

    //GET ALL LOANS
    @GetMapping
    public ResponseEntity<List<LoanDTO>> getAllLoans(){
        return ResponseEntity.ok(loanService.getAllLoans());
    }

    //GET LOAN BY ID
    @GetMapping("/{id}")
    public ResponseEntity<LoanDTO> getLoanById(@PathVariable Long id){
        return ResponseEntity.ok(loanService.getLoanById(id));
    }

    //GET LOANS BY ID BOOK
    @GetMapping("/book/{id}")
    public ResponseEntity<List<LoanDTO>> getLoansByBookId(@PathVariable Long id){
        return ResponseEntity.ok(loanService.getLoansByBookId(id));
    }

    //GET LOANS BY BOOK TITLE
    @GetMapping("/title/{title}")
    public ResponseEntity<List<LoanDTO>> getLoansByBookTitle(@PathVariable String title){
        return ResponseEntity.ok(loanService.getLoansByBookTitle(title));
    }

    //GET LOANS BY BOOK AUTHOR
    @GetMapping("/author_surname/{author}")
    public ResponseEntity<List<LoanDTO>> getLoansByBookAuthor(@PathVariable String author){
        return ResponseEntity.ok(loanService.getLoansByBookAuthor(author));
    }

    //GET LOANS BY BOOK GENRE
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<LoanDTO>> getLoansByBookGenre(@PathVariable String genre){
        return ResponseEntity.ok(loanService.getLoansByBookGenre(genre));
    }

    //GET LOANS BY STATUS
    @GetMapping("/status/{status}")
    public ResponseEntity<List<LoanDTO>> getLoansByStatus(@PathVariable String status){
        return ResponseEntity.ok(loanService.getLoansByStatus(status));
    }

    //GET LOANS BY START DATE
    @GetMapping("/start_date/{startDate}")
    public ResponseEntity<List<LoanDTO>> getLoansByStartDate(@PathVariable String startDate){
        return ResponseEntity.ok(loanService.getLoansByStartDate(startDate));
    }

    //GET LOANS BY USER EMAIL
    @GetMapping("/user/{email}")
    public ResponseEntity<List<LoanDTO>> getLoansByUserEmail(@PathVariable String email){
        return ResponseEntity.ok(loanService.getLoansByUserEmail(email));
    }

    //SAVE LOAN
    @PostMapping
    public ResponseEntity<LoanDTO> saveLoan(@RequestBody LoanDTO loanDTO){
        return ResponseEntity.ok(loanService.saveLoan(loanDTO));
    }

    //UPDATE LOAN BY ID
    @PatchMapping("/update_loan/{id}")
    public ResponseEntity<LoanDTO> updateLoan(@PathVariable Long id, @RequestBody LoanDTO loanDTO){
        return ResponseEntity.ok(loanService.updateLoan(id, loanDTO));
    }

    //DELETE LOAN BY ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLoanById(@PathVariable Long id){
        loanService.deleteLoanById(id);
        return ResponseEntity.ok("Loan with ID: "+ id +" has been deleted!");
    }
    
}
