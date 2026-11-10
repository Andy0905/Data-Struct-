#!/usr/bin/env bash
set -euo pipefail
mkdir -p out
javac -d out src/*.java test/*.java
java -ea -cp out LabTestRunner
