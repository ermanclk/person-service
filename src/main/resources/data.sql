DROP TABLE IF EXISTS person;

CREATE TABLE person (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  age INTEGER DEFAULT NULL,
  favorite_color VARCHAR(250) NOT NULL
);

INSERT INTO person (id,first_name, last_name, age,favorite_color) VALUES
  (1, 'tester_name', 'lastname',35,'BLUE');