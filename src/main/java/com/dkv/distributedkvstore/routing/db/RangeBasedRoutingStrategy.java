package com.dkv.distributedkvstore.routing.db;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class RangeBasedRoutingStrategy implements IRoutingStrategy {
    @Override
    public int getShardIndex(String key) {
        char start = key.toLowerCase().charAt(0);
        if (start >= 'a' && start <= 'm') {
            return 0;
        } else {
            return 1;
        }
    }
}
