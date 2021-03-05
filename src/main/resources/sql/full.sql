-- SCHEMA: hibernate

-- DROP SCHEMA hibernate ;
set search_path to hibernate;
BEGIN;
CREATE TABLE IF NOT EXISTS products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('apple', 110),
('peach', 120),
('pear', 100),
('cherry', 150),
('blueberry', 500);

CREATE TABLE IF NOT EXISTS customers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO customers (name) VALUES
('Bob'),
('Sam'),
('Ann'),
('Kate');

CREATE TABLE IF NOT EXISTS orders (id bigserial PRIMARY KEY, id_customer bigserial, id_product bigserial, cost int,
FOREIGN KEY (id_customer)  REFERENCES customers (id), FOREIGN KEY (id_product)  REFERENCES products (id));
INSERT INTO orders (id_customer, id_product, cost) VALUES
(1, 1, 110),
(1, 2, 120),
(1, 5, 110),
(1, 4, 160),
(2, 1, 110),
(3, 2, 140);
COMMIT;