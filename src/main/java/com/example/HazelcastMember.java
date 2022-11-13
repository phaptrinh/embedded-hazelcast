package com.example;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.hazelcast.replicatedmap.ReplicatedMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastMember {
    @Bean
    @Qualifier("hz")
    HazelcastInstance hazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }

    @Bean
    @Qualifier("blackList")
    ReplicatedMap<String, Boolean> blackList(@Qualifier("hz") HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getReplicatedMap("blackList");
    }

    @Bean
    @Qualifier("trackingList")
    ReplicatedMap<String, Boolean> trackingList(@Qualifier("hz") HazelcastInstance hazelcastInstance) {
        return hazelcastInstance.getReplicatedMap("trackingList");
    }
}
