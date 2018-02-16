echo "Reset's db, svn update clean and build. Tested on Mac only."
echo "Powerful script that will delete current folder, use with caution"
echo "  *** *** "
echo "Press Ctrl - C now to cancel"
echo "  *** *** \n at "
echo "${PWD##*/}" " part of " "${PWD}"

# export is like set. # is comment if char 1
export mysql=/Applications/XAMPP/xamppfiles/bin/mysql
export tommyHome=/apps/tomcats/a

export java=/usr/bin/java
export starterHome=.
#/u/prog/wwrepos/assembla/nep7b/trunk/starter
 

#for some OS might need this
export starterDriveHome=d:

export mysqlUsername=root
export mysqlUserpassword=root


   echo "clearing "  $starterHome
# $starterDriveHome
cd $starterHome
if [ "$?" = "0" ]; then
	# rm -R *
	echo ""
else
	echo "Cannot change directory!" 1>&2
	exit 1
fi

#svn co -r HEAD --force https://subversion.assembla.com/svn/neptune7/trunk/starter .
svn up -r HEAD --force .
if [ "$?" = "0" ]; then
   echo "Svn co done"
else
	echo "Svn checkout fail" 1>&2
	exit 1
fi

# sleep 10

sh ./q.sh
echo "Code compile?"
if [ "$?" = "0" ]; then
	   echo "Code compile"
else
	echo "Code compile fail" 1>&2
	exit 1
fi

# sleep 10

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

 $mysql -u $mysqlUsername -p$mysqlUserpassword  < $starterHome/DB/mysql_script/2_dml_script.sql
 $mysql -u $mysqlUsername -p$mysqlUserpassword < $starterHome/DB/mysql_script/3_dml_script.sql
if [ "$?" = "0" ]; then
	   echo "Db 2"
else
	echo "Db dml 1 fail" 1>&2
	exit 1
fi
mvn install
$tommyHome/bin/shutdown.sh
sleep 4
rm -R $tommyHome/webapps/ROOT
mkdir $tommyHome/webapps/ROOT
rm -Rf $tommyHome/webapps/MohRptWeb.war
rm -Rf $tommyHome/webapps/MohRptWeb
echo "Copying MohRptWeb.war into tomcat webapps"
#cp -Rf MohRptWeb/target/MohRptWeb.war $tommyHome/webapps/
#exploded war
cp -R MohWeb/target/MohWeb/* $tommyHome/webapps/ROOT/
cp -R MohRptWeb/target/MohRptWeb.war $tommyHome/webapps/ROOT/


$tommyHome/bin/startup.sh
echo "Starting tommy and will open home page in 9+ seconds"
sleep 9
open http://localhost:8080/
#mac helper to forcefully kill tomcat process/ process using a particular port. On windows can use other tools
echo "sudo lsof -i tcp:8080 kill -15 <PID>"