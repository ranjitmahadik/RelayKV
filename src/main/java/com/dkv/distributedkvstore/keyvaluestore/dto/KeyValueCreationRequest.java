package com.dkv.distributedkvstore.keyvaluestore.dto;

public record KeyValueCreationRequest(String key, byte[] value, Long ttl) {
}
