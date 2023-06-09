CREATE TABLE IF NOT EXISTS users (
	username VARCHAR (64) NOT NULL,
	password VARCHAR (500) NOT NULL
);

CREATE TABLE IF NOT EXISTS brand(
   id INT NOT NULL,
   brandid VARCHAR(2) NOT NULL PRIMARY KEY,
   brandname VARCHAR(15) NOT NULL
);

CREATE TABLE IF NOT EXISTS bicycles(
   id INT NOT NULL PRIMARY KEY,
   bikename VARCHAR(200) NOT NULL,
   brandid VARCHAR(2) NOT NULL,
   size VARCHAR(10) NOT NULL,
   color VARCHAR(20) NOT NULL,
   value INT NOT NULL,
   imageurl VARCHAR(100) NOT NULL,
   INDEX bra_index(brandid),
   FOREIGN KEY fk_brand(brandid) REFERENCES brand(brandid)
);

CREATE TABLE IF NOT EXISTS shops(
   id INT NOT NULL PRIMARY KEY,
   shopname VARCHAR(50) NOT NULL,
   bikeid VARCHAR(100) NOT NULL 
);