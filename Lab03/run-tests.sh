#!/usr/bin/env bash
set -e
cd "$(dirname "$0")"
rm -rf out
mkdir out
javac -d out src/*.java test/*.java
java -ea -cp out LabTestRunner
rm -rf out
