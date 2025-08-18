#!/bin/bash
set -e

# Default Payment Gateway (can be overridden at docker run time)
: "${PG:=Paytm}"

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
appium --use-drivers uiautomator2 --log-level info &
sleep 10

# Check if the suite file exists
if [ ! -f "/app/xmlfiles/$xmlFiles" ]; then
    echo "❌ Suite file /app/xmlfiles/$xmlFiles not found!"
    exit 1
fi

echo "🧪 Running Maven tests using suite: $xmlFiles with PG=$PG"
mvn clean test \
  -DPG="$PG" \
  -DsuiteXmlFile="/app/xmlfiles/$xmlFiles"
