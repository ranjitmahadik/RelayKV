package com.dkv.distributedkvstore.keyvaluestore.exceptions;

public class KeyNotFoundException extends RuntimeException{
    public KeyNotFoundException(String msg) {
        super(msg);
    }
}
