# DATABASE
mysql> CREATE DATABASE jee_ssn_db; -- Create the new database
mysql> CREATE USER 'ssnuser'@'%' IDENTIFIED BY 'ssnpassword'; -- Creates the user
mysql> GRANT ALL ON *.* TO 'ssnuser'@'%' WITH GRANT OPTION; -- Gives all the privileges to the new user on the newly created database
