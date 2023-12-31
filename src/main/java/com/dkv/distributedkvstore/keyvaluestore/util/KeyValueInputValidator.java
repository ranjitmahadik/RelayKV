package com.dkv.distributedkvstore.keyvaluestore.util;

import org.springframework.stereotype.Component;

@Component
public class KeyValueInputValidator {
    public boolean isKeyValid(String key) {
        return key != null && !key.isEmpty();
    }
}
