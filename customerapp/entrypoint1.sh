#!/bin/bash
set -e

# Set default suite file if not provided
: "${xmlFiles:=testng.xml}"

echo "📡 Starting VNC server..."
Xvfb :99 -screen 0 1280x800x16 &
x11vnc -forever -usepw -create &

echo "⏳ Waiting for Android emulator..."
sleep 5

echo "🔌 Connecting to host emulator..."
adb connect host.docker.internal:5555 || echo "⚠️ Could not connect to emulator"

echo "🚀 Starting Appium server..."
appium --log-level info &
sleep 5

# Check if the suite file exists
if [ ! -f "/app/xmlfiles/$xmlFiles" ]; then
    echo "❌ Suite file /app/xmlfiles/$xmlFiles not found!"
    exit 1
fi

echo "🧪 Running Maven tests using suite: $xmlFiles"
mvn clean test -DsuiteXmlFile=/app/xmlfiles/$xmlFiles
