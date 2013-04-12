@echo off
Title Compiler
IF EXIST "%~dp0/bin" (GOTO COMPILE)
mkdir "%~dp0/bin"
IF EXIST "%~dp0/bin/sign" (GOTO COMPILE)
mkdir "%~dp0/bin/sign"
IF EXIST "%~dp0/bin/util" (GOTO COMPILE)
mkdir "%~dp0/bin/util"
:COMPILE
javac -Xlint:unchecked -d bin -sourcepath src src/*.java
pause