@echo off
title Yoloscape - Compiler
javac -Xlint:unchecked -d bin -cp ./deps/*; -sourcepath src src/server/ServerStart.java
javac -Xlint:unchecked -d bin -cp ./deps/*; -sourcepath src src/server/GUI.java
pause
