package com.dkv.distributedkvstore.routing.db;

import org.springframework.jdbc.core.JdbcTemplate;

public interface IDatabaseRouting {
    JdbcTemplate getTemplate(String key);
}
