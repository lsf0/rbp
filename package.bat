cd %~dp0
set path=%MAVEN_HOME%\bin;%path%
call mvn clean package
pause