package com.dkv.distributedkvstore.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Configuration
@ConfigurationProperties("app.datasource")
public class ShardList {
    private List<ShardConfig> shards;

    public ShardList() {
    }

    public List<ShardConfig> getShards() {
        return shards;
    }

    public void setShards(List<ShardConfig> shards) {
        this.shards = shards;
    }
}
