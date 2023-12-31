package com.dkv.distributedkvstore.routing.db;

public interface IRoutingStrategy {
    int getShardIndex(String key);
}
