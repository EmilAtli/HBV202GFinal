#!/usr/bin/env bash
set -euo pipefail

echo "🔨 Cleaning & packaging fat JAR…"
mvn clean package

# Grab whichever fat‑jar you produced:
# (adjust the pattern if you switched to <classifier>all</classifier> in your POM)
SRC=$(ls target/*-all-jar-with-dependencies.jar | head -n1)

if [[ ! -f "$SRC" ]]; then
  echo "❌ Could not find the fat‑jar in target/"; exit 1
fi

cp "$SRC" library-app.jar
echo "✅ Created library-app.jar"
