package br.com.bibliotech.services;

import br.com.bibliotech.convertes.LoanResponseConverter;
import br.com.bibliotech.dtos.LoanDTO;
import br.com.bibliotech.entities.*;
import br.com.bibliotech.repositories.CopyRepository;
import br.com.bibliotech.repositories.ItemRepository;
import br.com.bibliotech.repositories.LoanRepository;
import br.com.bibliotech.repositories.StudentRepository;
import br.com.bibliotech.responses.LoanResponse;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    private final LoanResponseConverter loanResponseConverter = new LoanResponseConverter();

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CopyRepository copyRepository;


    @Transactional
    public LoanResponse start(LoanDTO loanDTO){
        Loan loan = createLoan(loanDTO);
        List<Copy> copies = findEachCopyById(loanDTO.copiesIds());
        createItems(loan, copies);
        disableCopies(copies);

        log.info("Loan started: " + loan);
        return loanResponseConverter.convert(loan);
    }

    @Transactional
    public LoanResponse finish(Long id){
        Loan loan = loanRepository.findById(id).orElseThrow();
        loan.setFinished(true);
        loan.setFinishedDate(LocalDate.now());
        ableCopies(loan.getItems());

        log.info("Loan finished: " + loan);
        return loanResponseConverter.convert(loan);
    }

    public LoanResponse getById(Long id){
        Loan loan = loanRepository.findById(id).orElseThrow();
        return loanResponseConverter.convert(loan);
    }

    public List<LoanResponse> getAll(){
        List<Loan> loans = loanRepository.findAll();
        return loanResponseConverter.convertEach(loans);
    }

    private Loan createLoan(LoanDTO loanDTO){
        Student student = getStudentByRm(loanDTO.rm());
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = generateEndDate(startDate);

        Loan loan = new Loan(startDate, endDate, student);
        loanRepository.save(loan);

        return loan;
    }

    private Student getStudentByRm(String rm){
        return studentRepository.findByRm(rm).orElseThrow();
    }

    private LocalDate generateEndDate(LocalDate startDate){
        return startDate.plusDays(7);
    }

    private List<Copy> findEachCopyById(List<Long> copiesId){
        List<Copy> copies = new ArrayList<>();

        copiesId.forEach(copyId -> {
            Copy copy = copyRepository.findById(copyId).orElseThrow();
            copies.add(copy);
        });

        return copies;
    }

    private void createItems(Loan loan, List<Copy> copies){
        saveItems(loan, copies);
    }

    private void saveItems(Loan loan, List<Copy> copies){
        copies.forEach(copy -> {
            Item item = new Item(new ItemId(loan.getId(), copy.getId()), loan, copy);
            itemRepository.save(item);
            log.info("Item created: " + item);
        });
    }

    private void disableCopies(List<Copy> copies){
        copies.forEach(copy -> {
            copy.setAvailable(false);
            log.info("Copy disabled: " + copy);
        });
    }

    private void ableCopies(List<Item> items){
        items.forEach(item -> {
            item.getCopy().setAvailable(true);
            log.info("Copy available: " + item.getCopy());
        });
    }

}
