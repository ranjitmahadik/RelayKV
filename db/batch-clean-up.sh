#!/bin/bash

DB_USER="$POSTGRES_USER"
DB_PASS="$POSTGRES_PASSWORD"
DB_NAME="$POSTGRES_DB"
ROWS_LIMIT=1000

psql -U "$DB_USER" -d "$DB_NAME" -c "WITH rows_to_delete AS (
    SELECT key FROM store WHERE ttl < EXTRACT(epoch FROM now()) LIMIT $ROWS_LIMIT
)
DELETE FROM store WHERE key IN (SELECT key FROM rows_to_delete);"
