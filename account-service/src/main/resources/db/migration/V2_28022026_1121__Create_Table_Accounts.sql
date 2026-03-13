CREATE TABLE IF NOT EXISTS accounts(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    account_number VARCHAR(100) NOT NULL UNIQUE,
    owner_name VARCHAR(255) NOT NULL,
    balance DECIMAL(19,2),
    status VARCHAR(10),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);