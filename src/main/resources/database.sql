-- Crear la tabla 'users'
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,  -- Para MySQL
    -- id BIGSERIAL PRIMARY KEY,           -- Para PostgreSQL
    sub VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    email_verified BOOLEAN NOT NULL DEFAULT FALSE,
    picture VARCHAR(255),
    given_name VARCHAR(255),
    family_name VARCHAR(255)
);

