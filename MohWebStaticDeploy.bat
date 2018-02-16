rem --- Deleting MohWeb all lib jar--------
cd C:\Users\tripotha\Documents\w4\MohWeb\target\MohWeb\WEB-INF\lib
del *.jar
rem --- copping MohWeb static content to Tomcat Root--------
xcopy /c /e/y /d C:\Users\tripotha\Documents\w4\MohWeb\target\MohWeb C:\apache-tomcat-7.0.57\webapps\ROOT