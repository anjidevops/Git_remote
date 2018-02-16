rem --- Maven clean install----------
echo off
cd C:\Users\tripotha\Documents\w4
start mvn clean install

echo press the any key after compilation is Successs.
pause
cd  C:\Users\tripotha\Documents\w4\MohWeb\target
IF EXIST MohWeb.war (
 echo MohWeb is there
) ELSE (
 echo MohWeb  missing
)



rem --- copping MohWeb lib to Tomcat lib--------
xcopy /c /e/y /d C:\Users\tripotha\Documents\w4\MohWeb\target\MohWeb\WEB-INF\lib C:\apache-tomcat-7.0.57\lib
rem --- Deleting MohWeb all lib jar--------
cd C:\Users\tripotha\Documents\w4\MohWeb\target\MohWeb\WEB-INF\lib
del *.jar
rem --- copping MohWeb static content to Tomcat Root--------
xcopy /c /e/y /d C:\Users\tripotha\Documents\w4\MohWeb\target\MohWeb C:\apache-tomcat-7.0.57\webapps\ROOT

rem --- copping MohRptWeb lib to Tomcat lib--------
xcopy /c /e/y /d C:\Users\tripotha\Documents\w4MohRptWeb\target\MohRptWeb\WEB-INF\lib C:\apache-tomcat-7.0.57\lib
rem --- Deleting MohRptWeb all lib jar--------
cd C:\Users\tripotha\Documents\w4\MohRptWeb\target\MohRptWeb\WEB-INF\lib
del *.jar

rem --- copping MohWeb static content to Tomcat Root-------
cd C:\apache-tomcat-7.0.57\webapps
rmdir /s /q MohRptWeb
mkdir MohRptWeb 
xcopy /c /e/y /d C:\Users\tripotha\Documents\w4\MohRptWeb\target\MohRptWeb C:\apache-tomcat-7.0.57\webapps\MohRptWeb

echo Deployment is Done.Script is existing now
timeout 5




