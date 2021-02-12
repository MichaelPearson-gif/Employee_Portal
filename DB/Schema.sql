CREATE TABLE employees (
    emp_no SERIAL PRIMARY KEY,
    first_name CHAR(15) NOT NULL,
    last_name CHAR(15) NOT NULL,
    email CHAR(30) NOT NULL,
    phone NUMERIC NOT NULL,
    gender CHAR(1) NOT NULL,
    birthday DATE NOT NULL,
    ssn INT NOT NULL,
    salary NUMERIC NOT NULL,
    title CHAR(10) NOT NULL,
    emp_password CHAR(20) NOT NULL
);

CREATE TABLE managers (
    manager_no SERIAL PRIMARY KEY,
    emp_no INT NOT NULL REFERENCES employees(emp_no)
);

CREATE TABLE employee_manager (
    emp_man SERIAL PRIMARY KEY,
    manager_no INT NOT NULL REFERENCES managers(manager_no),
    emp_no INT NOT NULL REFERENCES employees(emp_no)
);

CREATE TABLE requests (
    request_no SERIAL PRIMARY KEY,
    emp_no INT NOT NULL REFERENCES employees(emp_no),
    amount NUMERIC NOT NULL,
    reciept CHAR NOT NULL,
    status CHAR(10) NOT NULL,
    dates DATE NOT NULL
);

-- Alter the sequences of the serial id's
ALTER SEQUENCE employees_emp_no_seq RESTART WITH 1000 INCREMENT BY 1;
ALTER SEQUENCE managers_manager_no_seq RESTART WITH 2000 INCREMENT BY 1;
ALTER SEQUENCE employee_manager_emp_man_seq RESTART WITH 3000 INCREMENT BY 1;
-- Only need to restart the requests id sequence in case I need to restart from 1
ALTER SEQUENCE requests_request_no_seq RESTART WITH 1 INCREMENT BY 1;

-- Only for major restructuring use only
DROP TABLE employees CASCADE;
DROP TABLE managers CASCADE;
DROP TABLE employee_manager;
DROP TABLE requests;
