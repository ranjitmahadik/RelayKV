# Problem Statement
Design a Key Value Store on top of a SQL database. Exposes APIs to GET, PUT, DEL keys. Along with these core functionalities, there should be an API to set TTL to an existing key, post which the key is auto-deleted from the store.

### How to setup project?
- make sure you have docker installed.
- database setup
  add postgres username and password in file present inside db folder.
  path: `./db/.env`
  have provided example in `./db/example.env`
  ```
  # .env file for PostgreSQL containers
  POSTGRES_PASSWORD=user@123
  POSTGRES_USER=user
  POSTGRES_DB=kv  # do not change db name, else application won't work.
  ```
- now just run `docker compose up -d` to run all required containers.
  

### Learnings
- SQL Transactions.
- Pessimistic Locking.
- SQL DB horizontally Scaling by Manually Sharding Database (range based sharding).
  
