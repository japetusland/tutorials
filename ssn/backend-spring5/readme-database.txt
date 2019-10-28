mysql> create database spring_ssn_db; -- Create the new database
mysql> create user 'ssnuser'@'%' identified by 'ssnpassword'; -- Creates the user
mysql> grant all on spring_ssn_db.* to 'ssnuser'@'%'; -- Gives all the privileges to the new user on the newly created database