@echo off
setlocal
if "%JAVA_HOME%"=="" (
  echo JAVA_HOME not set; relying on PATH
) else (
  set "PATH=%JAVA_HOME%\bin;%PATH%"
)
if not exist out mkdir out
javac -d out src\*.java test\*.java
if errorlevel 1 exit /b 1
java -ea -cp out LabTestRunner
