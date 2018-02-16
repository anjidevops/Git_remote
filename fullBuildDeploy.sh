echo "Svn update, Reset DB, clean and build. Tested on Ubuntu only."
echo "Press Ctrl - C now to cancel"
echo "${PWD##*/}"   "part of"  "${PWD}"

if [ "${PWD##*/}" != "starter" ]
then
  echo "not in starter, exiting"
  exit
else 
	echo "In correct folder."
fi

sleep 5

export starterHome=.
export tommyHome=/home/yashpal/apache-tomcat-8.0.5_1

export java=/usr/bin/java
export mysql=/opt/lampp/bin/mysql

export mysqlUsername=root
export mysqlUserpassword=root

echo "Taking update with HEAD revision" 

svn up -r HEAD --force .
if [ "$?" = "0" ]; then
   echo "Svn co done"
else
	echo "Svn checkout fail,Now existing" 1>&2
	exit 1
fi

echo "Stopping Tomcat for deployment"
$tommyHome/bin/shutdown.sh

sleep 5
echo "Running DB script"

$mysql -u $mysqlUsername -p$mysqlUserpassword < $starterHome/DB/mysql_script/0_ddl_db_create.sql
if [ "$?" = "0" ]; then
	   echo "Db 0"
else
	echo "Db delete failed" 1>&2
	exit 1
fi 

$mysql -u $mysqlUsername -p$mysqlUserpassword < $starterHome/DB/mysql_script/1_ddl_script.sql
if [ "$?" = "0" ]; then
		   echo "Db 1"
else
	echo "Db DDL 1 fail" 1>&2
	exit 1
fi

$mysql -u $mysqlUsername -p$mysqlUserpassword < $starterHome/DB/mysql_script/2_dml_script.sql
$mysql -u $mysqlUsername -p$mysqlUserpassword < $starterHome/DB/mysql_script/3_dml_script.sql
if [ "$?" = "0" ]; then
	   echo "Db 2"
else
	echo "Db dml 1 fail" 1>&2
	exit 1
fi

sleep 5
echo Building project

mvn clean install
if [ "$?" -ne 0 ]; then
   echo "Build failed, Now existing"
   exit "$?"
else
	echo "build success" 
fi

echo "Deploying build"
  
rm -Rf $tommyHome/webapps/ROOT
mkdir $tommyHome/webapps/ROOT
cp -Rf MohWeb/target/MohWeb/* $tommyHome/webapps/ROOT/

echo "Copying MohRptWeb.war into tomcat webapps"
rm -Rf $tommyHome/webapps/MohRptWeb.war
rm -Rf $tommyHome/webapps/MohRptWeb 
cp -Rf MohRptWeb/target/MohRptWeb.war $tommyHome/webapps/

echo "Deployment is done"

echo "Starting tomcat"
$tommyHome/bin/startup.sh

sleep 5
#open url in default web browser
xdg-open http://localhost:8080/