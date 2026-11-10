@echo off
setlocal
cd /d "%~dp0"
if exist out rmdir /s /q out
mkdir out
javac -d out src\*.java test\*.java
if errorlevel 1 (
    echo COMPILE FAILED
    exit /b 1
)
java -ea -cp out LabTestRunner
set RC=%errorlevel%
if exist out rmdir /s /q out
exit /b %RC%
