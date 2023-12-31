package com.dkv.distributedkvstore.keyvaluestore.repository;

public interface KeyValueDao {
    byte[] get(String key);

    String put(String key, byte[] value, Long ttl);

    String delete(String key);

    String expire(String key, Long ttl);
}
