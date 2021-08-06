package com.bitgo.blockExplorer.service.impl;

import com.bitgo.blockExplorer.model.Transaction;
import com.bitgo.blockExplorer.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BlockServiceImpl implements BlockService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${block.host}")
    private String blockHost;

    @Override
    public String getBlock(int height) {
        String hash = restTemplate.getForObject(blockHost + "/block-height/" + height, String.class);
        return hash;
    }

    @Override
    public List<String> getTransactions(String hash) {
        List<String> txs = new ArrayList<>();
        if (hash == null) {
            return txs;
        }
        //String[] txtArray  = restTemplate.getForObject(blockHost, String[].class);
        String[] txtArray = restTemplate.getForObject(blockHost + "/block/" + hash + "/txids", String[].class);
        return Arrays.asList(txtArray);
    }

    @Override
    public List<String> getTransactionsByHeight(int height) {
        String hash = getBlock(height);
        return getTransactions(hash);
    }
}
