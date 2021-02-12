CREATE TABLE "employees" (
    "emp_no" SERIAL PRIMARY KEY,
    "first_name" CHAR   NOT NULL,
    "last_name" CHAR   NOT NULL,
    "email" CHAR   NOT NULL,
    "phone" NUMERIC   NOT NULL,
    "gender" CHAR   NOT NULL,
    "birthday" DATE   NOT NULL,
    "ssn" INT   NOT NULL,
    "salary" NUMERIC   NOT NULL,
    "title" CHAR   NOT NULL,
    "password" CHAR   NOT NULL
);

CREATE TABLE "managers" (
    "manager_no" SERIAL PRIMARY KEY,
    "emp_no" INT NOT NULL
);

CREATE TABLE "employee_manager" (
    "emp_man" SERIAL PRIMARY KEY,
    "manager_no" INT NOT NULL,
    "emp_no" INT NOT NULL
);

CREATE TABLE "requests" (
    "request_no" SERIAL PRIMARY KEY,
    "emp_no" INT   NOT NULL,
    "amount" NUMERIC   NOT NULL,
    "reciept" CHAR   NOT NULL,
    "status" CHAR NOT NULL
);

ALTER TABLE "managers" ADD CONSTRAINT "fk_managers_emp_no" FOREIGN KEY("emp_no")
REFERENCES "employees" ("emp_no");

ALTER TABLE "employee_manager" ADD CONSTRAINT "fk_employee_manager_manager_no" FOREIGN KEY("manager_no")
REFERENCES "managers" ("manager_no");

ALTER TABLE "employee_manager" ADD CONSTRAINT "fk_employee_manager_emp_no" FOREIGN KEY("emp_no")
REFERENCES "employees" ("emp_no");

ALTER TABLE "requests" ADD CONSTRAINT "fk_requests_emp_no" FOREIGN KEY("emp_no")
REFERENCES "employees" ("emp_no");