package com.example;

import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    @Qualifier("blackList")
    IMap<String, Boolean> blackList;

    @GetMapping
    Boolean getUserId(@RequestParam String id) {
       if (blackList.get(id) != null) {
           return true;
       }
        return false;
    }

    @PostMapping
    void addUserId(@RequestParam String id) {
        blackList.put(id, true);
    }
}
