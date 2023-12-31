CREATE TABLE IF NOT EXISTS store(
    key varchar(16) primary key,
    value bytea,
    ttl bigint,
);

CREATE INDEX IF NOT EXISTS ttl_index on store(ttl);