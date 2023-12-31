package com.dkv.distributedkvstore.routing.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RelationDatabaseRoutingImpl implements IDatabaseRouting {

    @Autowired
    @Qualifier("multipleConnections")
    private List<JdbcTemplate> jdbcTemplateList;

    @Autowired
    private IRoutingStrategy routingStrategy;

    @Override
    public JdbcTemplate getTemplate(String key) {
        int index = routingStrategy.getShardIndex(key) % jdbcTemplateList.size();
        return jdbcTemplateList.get(index);
    }
}
