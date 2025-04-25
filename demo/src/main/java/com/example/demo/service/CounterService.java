package com.example.demo.service;

import org.apache.zookeeper.ZooKeeper;

public class CounterService {
    private static final String COUNTER_PATH = "/counter";
    private static final int RANGE_SIZE = 100000;

    private final ZooKeeper zooKeeper;
    private long currentCounter;
    private long maxCounter;

    public CounterService(ZooKeeper zooKeeper){
        this.zooKeeper=zooKeeper;
        initializeCounter();
    }

    private void initializeCounter(){
        try{
            Stat
        }
    }
}
