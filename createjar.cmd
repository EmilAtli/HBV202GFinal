@echo off
mvn clean package && copy /B /Y target\*-all-jar-with-dependencies.jar library-app.jar && echo Created library-app.jar
