package com.dkv.distributedkvstore.keyvaluestore.service;

import com.dkv.distributedkvstore.keyvaluestore.repository.KeyValueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyValueService implements IKeyValueService {
    @Autowired
    private KeyValueDao keyValueDao;

    public byte[] get(String key) {
        return keyValueDao.get(key);
    }

    public String put(String key, byte[] value, Long ttl) {
        return keyValueDao.put(key, value, ttl);
    }

    public String delete(String key) {
        return keyValueDao.delete(key);
    }

    public String expire(String key, Long ttl) {
        return keyValueDao.expire(key, ttl);
    }
}
