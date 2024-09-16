CREATE TABLE IF NOT EXISTS "transaction" (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_document VARCHAR NOT NULL,
    credit_card_token VARCHAR NOT NULL,
    transaction_value BIGINT NOT NULL
);