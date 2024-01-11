CREATE SCHEMA IF NOT EXISTS demo;
CREATE TABLE IF NOT EXISTS demo.currency
(
id long(4) auto_increment primary key, 
code VARCHAR(3) NOT NULL, 
name VARCHAR(32) NOT NULL
);
