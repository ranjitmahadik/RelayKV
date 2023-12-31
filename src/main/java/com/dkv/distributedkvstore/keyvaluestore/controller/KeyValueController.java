package com.dkv.distributedkvstore.keyvaluestore.controller;

import com.dkv.distributedkvstore.keyvaluestore.dto.KeyExpireRequest;
import com.dkv.distributedkvstore.keyvaluestore.dto.KeyValueCreationRequest;
import com.dkv.distributedkvstore.keyvaluestore.service.IKeyValueService;
import com.dkv.distributedkvstore.keyvaluestore.util.KeyValueInputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/kv")
public class KeyValueController {
    @Autowired
    private IKeyValueService keyValueService;

    @Autowired
    private KeyValueInputValidator keyValueInputValidator;

    @PostMapping("")
    public ResponseEntity<?> put(@RequestBody KeyValueCreationRequest keyValueCreationRequest) {
        if (keyValueCreationRequest == null || !keyValueInputValidator.isKeyValid(keyValueCreationRequest.key())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        String status = keyValueService.put(keyValueCreationRequest.key(), keyValueCreationRequest.value(), keyValueCreationRequest.ttl());
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }

    @GetMapping("")
    public ResponseEntity<?> get(@RequestParam("key") String key) {
        if (!keyValueInputValidator.isKeyValid(key)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("key must be present!");
        }
        byte[] value = keyValueService.get(key);
        return ResponseEntity.ok(value);
    }

    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam("key") String key) {
        if (!keyValueInputValidator.isKeyValid(key)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("key must be present!");
        }
        String status = keyValueService.delete(key);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(status);
    }

    @PutMapping("")
    public ResponseEntity<?> expire(@RequestBody KeyExpireRequest keyExpireRequest) {
        if (keyExpireRequest == null || !keyValueInputValidator.isKeyValid(keyExpireRequest.key())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("key must be present!");
        }
        String status = keyValueService.expire(keyExpireRequest.key(), keyExpireRequest.ttl());
        return ResponseEntity.status(HttpStatus.CREATED).body(status);
    }
}
