#!/usr/bin/env bash
set -euo pipefail

# Simply run the fat JAR with any args forwarded
java -jar library-app.jar "$@"
