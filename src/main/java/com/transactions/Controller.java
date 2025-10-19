package com.transactions;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/transactions")
public class Controller {

    public static final Logger logger = LoggerFactory.getLogger(Controller.class);

    private final TransactionService transactionService;

    public Controller(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Trans>> list() {
        List<Trans> listaTrans = transactionService.findAll();
        if (listaTrans.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaTrans);
    }

    @GetMapping("/{transaction_id}")
    public ResponseEntity<?> listOne(@PathVariable("transaction_id") long transaction_id) {
        Optional<Trans> opt = transactionService.findById(transaction_id);
        if (!opt.isPresent()) {
            return new ResponseEntity<>(
                    new CustomErrorType("404", "Transação não Encontrada!"),
                    HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(opt.get());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TransRequest request, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Transaction request: {}", request);

        Trans trans = new Trans();
        trans.setPayeeId(request.getPayee_id());
        trans.setPayerId(request.getPayer_id());
        trans.setValue(request.getValue());

        Trans created = transactionService.create(trans);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/transactions/{id}").buildAndExpand(created.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

}