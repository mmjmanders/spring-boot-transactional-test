CREATE TABLE "user" (
  id       BIGSERIAL PRIMARY KEY,
  username VARCHAR(255) NOT NULL UNIQUE,
  password VARCHAR(64)  NOT NULL
);