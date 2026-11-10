@echo off
setlocal EnableDelayedExpansion
cd /d "%~dp0"

javac -version >nul 2>&1
if errorlevel 1 (
    set "JAVA_HOME=%USERPROFILE%\.vscode\extensions\redhat.java-1.56.0-win32-x64\jre\21.0.12.1-win32-x86_64"
    set "PATH=!JAVA_HOME!\bin;!PATH!"
)

if exist out rmdir /s /q out
mkdir out
javac -d out src\*.java test\*.java
if errorlevel 1 exit /b 1
java -ea -cp out LabTestRunner
