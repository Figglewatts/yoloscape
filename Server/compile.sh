#! /bin/sh
exec javac -classpath "deps/*" -d `dirname $0`/bin $(find `dirname $0`/src/server -type f | grep \.java$ ) -nowarn
