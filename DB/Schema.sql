CREATE TABLE employees (
    email CHAR(30) PRIMARY KEY,
    first_name CHAR(15) NOT NULL,
    last_name CHAR(15) NOT NULL,
    phone NUMERIC NOT NULL,
    gender CHAR(1) NOT NULL,
    birthday DATE NOT NULL,
    ssn INT NOT NULL,
    salary NUMERIC NOT NULL,
    title CHAR(10) NOT NULL,
    emp_password CHAR(20) NOT NULL
);

CREATE TABLE managers (
    manager_id SERIAL PRIMARY KEY,
    email CHAR(30) NOT NULL REFERENCES employees(email)
);

CREATE TABLE employee_manager (
	email CHAR(30) NOT NULL PRIMARY KEY REFERENCES employees(email),
    manager_id INT NOT NULL REFERENCES managers(manager_id)
);

CREATE TABLE requests (
    request_id SERIAL PRIMARY KEY,
    email CHAR(30) NOT NULL REFERENCES employees(email),
    manager_id INT REFERENCES managers(manager_id),
    amount NUMERIC NOT NULL,
    reciept CHAR NOT NULL,
    status CHAR(10) NOT NULL,
    dates DATE NOT NULL
);

-- Alter the sequences of the serial id's
ALTER SEQUENCE managers_manager_id_seq RESTART WITH 100 INCREMENT BY 1;
-- Only need to restart the requests id sequence in case I need to restart from 1
ALTER SEQUENCE requests_request_id_seq RESTART WITH 1 INCREMENT BY 1;

-- Only for major restructuring use only
DROP TABLE employees CASCADE;
DROP TABLE managers CASCADE;
DROP TABLE employee_manager;
DROP TABLE requests;
