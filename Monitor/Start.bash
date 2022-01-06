#! /bin/bash
java -jar C:\Users\81803\Documents\test\ServerClient\Monitor\app\build\libs\app-all.jar
pid=$!
touch $pid.pid
