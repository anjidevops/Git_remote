// Mock_Startup  project SetUp

1. Database(MySql) and tomcat server setup
Install xammp software in your machine from https://www.apachefriends.org/download.html

2. Open phpmyadmin console in xammp, dump db using import option
import--> select 1_ddl_script.sql --> go
import--> select 2_dml_script.sql --> go

3. set up maven in your system from http://maven.apache.org/download.cgi

4. go to moh starter project top folder.
run maven command
>mvn clean install tomcat:run-war-only


mvn clean -- cleans all projects
mvn install -- compiles and runs test cases and generates jar files 
mvn tomcat:run-war-only  -- deploys the generated war file into tomcat

5. For uploads : App settings has been used to get settings like upload dir (using tomcat web apps folder so we can download from there, for added security can have other structure
'Res_Root_Absolute','/data/tomcat/' is the app settings that has path to tomcat. 

Copping directories from one to another.
------------------------------------------------------
C:\>xcopy /c /e/y /d source_path  destination_path

Example
C:\>xcopy /c /e/y /d E:\Trimurthulu\MOH\NeevMOHPRO\MohWeb\target\MohWeb C:\apache-tomcat-7.0.57\webapps\ROOT


Test Case for Transaction Roll Back Demo
--------------------------------------------------
AppointmentFacadeTest.testCreateUserAndBookAppointment() written to prove the concept of transaction rollback. With a clean database (similar to the database at step 2 of Mock_Startup  project SetUp) user_1 and appoinment_1 will be created. Since the same appointment is no more available appointment_2 will not be created and user_2 will be rolled back. Any subsequent runs of this test will not create user or appointment because the appointment is no more available.

1. go to moh starter\MohFacade project top folder.
run maven command:
mvn -Dtest=TransactionalBehaviorTest#testCreateUserAndBookAppointment test


Local Ehcache Configuration
---------------------------------
1. go to moh starter\MohService\src\main\resources folder.
2. Open and edit line 62(<beans:bean id='ehcache') of service-context.xml and change to p:configLocation='classpath:ehcache-service.xml'


Remote Ehcache Configuration
---------------------------------
1. go to moh starter\MohService\src\main\resources folder.
2. Open and edit line 4(<terracottaConfig rejoin="true" url="localhost:9510,localhost:9610"/>) of ehcache-service-remote.xml with remote server ip and port
3. Open and edit line 62(<beans:bean id='ehcache') of service-context.xml and change to p:configLocation='classpath:ehcache-service-remote.xml'

Clear Cache
----------------------------------------------
URL: http://localhost:8080/MohWeb/cacheManager.jsp

"Clear All Cache" button will clear entire cache. "Clear Cache" button will clear cache based on the cache name entered into the textbox. Valid cache names will be found in \MohService\src\main\resources\ehcache.xml, these are: "addressCache", "appointmentCache", "departmentCache", "doctorCache", "genderCache", "medicineInventoryCache", "patientCache" and "userCache".


Logging
-------------------
For logging, factory method pattern has been used. The log factory MohLogFactory
 returns the actual logger implementor class. As of now 2 custom implementors are 
 created MohSlf4jLoggerImpl for slf4j and MohLog4j2LoggerImpl for log4j2. 
 Currently the factory returns log4j2 implementor. 
 
 Various adapters are configured in pom for log4j 1, Apache commons, JUL and slf4j.
Logs of these frameworks will be captured by log4j2 implementor and placed into the
rolling file. Log4j2 specific configurations are done in log4j2.xml file.

To migrate from log4j2 to any new logging framework a new implementor class needs 
to be written and the log factory will return that class, if config points to it. For this migration 
no other change in the existing code base will be required.

Full Deployment
-------------------------
First configure path for tommyHome , java , mysql , mysqlUsername , mysqlUserpassword variable in below two script 
 1- fullBuildDeploy.sh for in Ubuntu
 2- bld.sh for Mac
 
 above two script does following tasks in order. 
 	svn update , stop tomcat , clean db , setup db with scripts(1,2,3 sql script), 
 	full maven build with testcase,start tomcat,
 	open tab in default browser

script will deploy MohWeb as "/" (inside ROOT directory) and MohRptWeb as "MohRptWeb" inside Tomcat.
 
