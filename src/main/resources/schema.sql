DROP SCHEMA public CASCADE;

CREATE SEQUENCE seq1 START WITH 1;
CREATE SEQUENCE seq2 START WITH 1;

CREATE TABLE ORDERS (
   id BIGINT NOT NULL PRIMARY KEY,
   orderNumber VARCHAR(255) NOT NULL
);

CREATE TABLE ORDER_ROWS (
   id BIGINT NOT NULL FOREIGN KEY,
   row_id BIGINT NOT NULL PRIMARY KEY,
   itemName VARCHAR(255),
   quantity INTEGER,
   price INTEGER
);