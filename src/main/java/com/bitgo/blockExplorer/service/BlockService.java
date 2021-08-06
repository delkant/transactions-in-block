package com.bitgo.blockExplorer.service;

import com.bitgo.blockExplorer.model.Block;
import com.bitgo.blockExplorer.model.Transaction;

import java.util.List;

public interface BlockService {
    String getBlock(int height);

    List<String> getTransactions(String hash);

    List<String> getTransactionsByHeight(int height);
}
