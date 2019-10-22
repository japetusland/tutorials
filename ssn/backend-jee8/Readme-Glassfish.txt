# Libs
In domain's lib folder glassfish5.1/glassfish/domains/domain1/lib:
	copy Log4j libs:
		log4j-api-2.11.2.jar
		log4j-core-2.11.2.jar
	copy MySql connector lib:
		mysql-connector-java-8.0.15.jar

# Connection Pool
glassfish\bin> asadmin.bat create-jdbc-connection-pool --datasourceclassname com.mysql.cj.jdbc.MysqlDataSource --restype=javax.sql.DataSource --property user=ssnuser:password=ssnpassword:DatabaseName=jee_ssn_db:ServerName=localhost:port=3306:useSSL=false:allowPublicKeyRetrieval=true:serverTimezone=UTC jee-ssn-pool
glassfish\bin> asadmin.bat create-jdbc-resource --connectionpoolid jee-ssn-pool jdbc/jee-ssn-resource
