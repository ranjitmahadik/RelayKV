package com.dkv.distributedkvstore.keyvaluestore.dto;

public record KeyExpireRequest(String key, Long ttl) {
}
