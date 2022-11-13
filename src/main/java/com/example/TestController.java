package com.example;

import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
public class TestController {

    @Autowired
    @Qualifier("blackList")
    IMap<String, Boolean> blackList;

    @GetMapping
    String getUserId(@RequestParam String id) {
       long start = System.currentTimeMillis();
       Boolean aBoolean = blackList.get(id);
       long getTime = System.currentTimeMillis() - start;
       if (aBoolean == true) {
           return String.valueOf(getTime);
       }
       return "false";

    }

    @PostMapping
    String addUserId(@RequestParam String id) {
        long start = System.currentTimeMillis();
        blackList.put(id, true);
        long getTime = System.currentTimeMillis() - start;
        return String.valueOf(getTime);
    }
}
