#! /bin/sh
exec javac -d `dirname $0`/bin $(find `dirname $0`/src/ -type f | grep \.java$ ) -nowarn
