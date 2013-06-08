CREATE DATABASE domain_logic_patterns;

USE domain_logic_patterns;

CREATE TABLE product (ID int AUTO_INCREMENT, name varchar(30), type varchar(30), PRIMARY KEY (id)) ENGINE=MyISAM;
CREATE TABLE contract (ID int primary key, product int, revenue decimal, date_signed date);
CREATE TABLE revenue_recognition (contract int, amount decimal, recognized_on date, PRIMARY KEY(contract, recognized_on));

-- SHOW databases
-- SHOW tables
-- DESC [table_name]