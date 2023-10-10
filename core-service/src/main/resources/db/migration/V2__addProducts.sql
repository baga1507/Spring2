DROP TABLE products;
CREATE TABLE IF NOT EXISTS products (id bigserial primary key, title varchar(255), cost int);
INSERT INTO products (title, cost) VALUES
    ('Cucumber', 35),
    ('Orange', 50),
    ('Turkey', 90),
    ('Butter', 90),
    ('Mango', 185),
    ('Alpen Gold', 70),
    ('Milk', 75),
    ('Muffins', 60),
    ('Beef', 300),
    ('Pelmeni', 315),
    ('Cheese', 130),
    ('Trout', 324),
    ('Barilla Girandole', 80),
    ('Big Bon', 60),
    ('Harris American Sandwich', 105),
    ('Cake', 90),
    ('Kvas', 40),
    ('Coffee', 250),
    ('Tea', 95),
    ('Lemonade', 35)
