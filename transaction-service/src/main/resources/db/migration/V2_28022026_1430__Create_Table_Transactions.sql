CREATE TABLE IF NOT EXISTS transactions(
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    from_account_id VARCHAR(100) NOT NULL,
    to_account_id VARCHAR(100) NOT NULL,
    amount DECIMAL(19,2) NOT NULL,
    transaction_status VARCHAR(10) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    completed_at TIMESTAMP
);