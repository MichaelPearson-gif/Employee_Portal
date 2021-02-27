CREATE TABLE employees (
    email VARCHAR PRIMARY KEY,
    first_name VARCHAR NOT NULL,
    last_name VARCHAR NOT NULL,
    phone NUMERIC NOT NULL,
    gender VARCHAR NOT NULL,
    birthday DATE NOT NULL,
    ssn INT NOT NULL,
    salary NUMERIC NOT NULL,
    title VARCHAR NOT NULL,
    emp_password VARCHAR NOT NULL
);

CREATE TABLE managers (
    manager_id INT PRIMARY KEY,
    email VARCHAR NOT NULL REFERENCES employees(email)
);

CREATE TABLE employee_manager (
	id INT PRIMARY KEY,
	email VARCHAR REFERENCES employees(email),
    manager_id INT REFERENCES managers(manager_id)
);

CREATE TABLE requests (
    request_id SERIAL PRIMARY KEY,
    email VARCHAR NOT NULL REFERENCES employees(email),
    manager_id INT REFERENCES managers(manager_id),
    amount NUMERIC NOT NULL,
    reciept VARCHAR,
    status VARCHAR NOT NULL,
    dates DATE NOT NULL
);

-- Alter the sequences of the serial id's
-- Only need to restart the requests id sequence in case I need to restart from 1
ALTER SEQUENCE requests_request_id_seq RESTART WITH 1 INCREMENT BY 1;

-- Only for major restructuring use only
DROP TABLE employees CASCADE;
DROP TABLE managers CASCADE;
DROP TABLE employee_manager;
DROP TABLE requests;
