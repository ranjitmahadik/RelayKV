# Problem Statement
Design a Key Value Store on top of a SQL database. Exposes APIs to GET, PUT, DEL keys. Along with these core functionalities, there should be an API to set TTL to an existing key, post which the key is auto-deleted from the store.

### Learnings
- SQL Transactions.
- Pessimestic Locking.
- Scaling by Manually Sharding Database (range based sharding).
  
