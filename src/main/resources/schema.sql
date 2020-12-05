DROP TABLE IF EXISTS specialoffers;
CREATE TABLE specialoffers (id SERIAL PRIMARY KEY , "name" VARCHAR(255), content VARCHAR(255), pharmacy_id VARCHAR(255));
DROP TABLE IF EXISTS hospitals;
CREATE TABLE hospitals (apiKey SERIAL PRIMARY KEY , "name" VARCHAR(255));
