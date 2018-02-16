rem --- Deleting MohRptWeb all lib jar--------
cd C:\Users\tripotha\Documents\w4\MohRptWeb\target\MohRptWeb\WEB-INF\lib
del *.jar

rem --- copping MohWeb static content to Tomcat Root-------
cd C:\apache-tomcat-7.0.57\webapps
rmdir /s /q MohRptWeb
mkdir MohRptWeb 
xcopy /c /e/y /d C:\Users\tripotha\Documents\w4\MohRptWeb\target\MohRptWeb C:\apache-tomcat-7.0.57\webapps\MohRptWeb