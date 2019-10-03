DROP TABLE IF EXISTS cars;
CREATE TABLE cars(id serial PRIMARY KEY, make VARCHAR(20),  model VARCHAR(20), modelYear integer,
status VARCHAR(10));

DROP TABLE IF EXISTS accounting;
CREATE TABLE accounting(id serial PRIMARY KEY, car_id integer, price integer, sale_date date);
CREATE TABLE accounting(id serial PRIMARY KEY, car_id integer, price integer, sale_date date, Unique(car_id));