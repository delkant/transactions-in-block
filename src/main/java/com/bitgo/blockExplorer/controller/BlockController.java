package com.bitgo.blockExplorer.controller;

import com.bitgo.blockExplorer.model.Transaction;
import com.bitgo.blockExplorer.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/client/v1/")
public class BlockController {
    @Autowired
    BlockService blockService;

    @GetMapping(path = "/transactions/{height}")
    ResponseEntity<List<String>> getTransactions(@PathVariable int height) {
        try {
            List<String> transactions = blockService.getTransactionsByHeight(height);
            if (transactions.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok().body(transactions);

        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
