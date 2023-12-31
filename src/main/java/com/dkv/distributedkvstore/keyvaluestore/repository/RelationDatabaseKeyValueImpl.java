package com.dkv.distributedkvstore.keyvaluestore.repository;

import com.dkv.distributedkvstore.keyvaluestore.exceptions.KeyNotFoundException;
import com.dkv.distributedkvstore.routing.db.IDatabaseRouting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RelationDatabaseKeyValueImpl implements KeyValueDao {

    @Autowired
    private IDatabaseRouting databaseRouting;

    @Override
    public byte[] get(String key) {
        JdbcTemplate jdbcTemplate = databaseRouting.getTemplate(key);
        String query = "SELECT value FROM store where key = ? and (ttl is null OR ttl > extract(epoch from now()));";
        List<byte[]> keyValueList = jdbcTemplate.query(query, new Object[]{key}, (rs, rowNum) -> rs.getBytes("value"));
        if (keyValueList.isEmpty()) {
            throw new KeyNotFoundException("Provided key doesn't exist!");
        }
        return keyValueList.get(0);
    }

    @Override
    public String put(String key, byte[] value, Long ttl) {
        JdbcTemplate jdbcTemplate = databaseRouting.getTemplate(key);
        String query = "INSERT INTO store(key, value, ttl) VALUES (?, ?, ?) ON CONFLICT (key) DO UPDATE SET value = ?, ttl = ? + extract(epoch from now());";
        jdbcTemplate.update(query, key, value, ttl, value, ttl);
        return "ok";
    }

    public String expireEntry(String key, Long ttl) {
        JdbcTemplate jdbcTemplate = databaseRouting.getTemplate(key);
        String query = "UPDATE store set ttl = -1 where key = ? and ttl > extract(epoch from now());";
        int rowsAffected = jdbcTemplate.update(query, key, ttl);
        if (rowsAffected == 0) {
            throw new KeyNotFoundException("Provided key doesn't exist!");
        }
        return "ok";
    }

    @Override
    public String delete(String key) {
        return this.expireEntry(key, -1L);
    }

    @Override
    public String expire(String key, Long ttl) {
        return this.expireEntry(key, ttl);
    }
}
