# Base image: Maven + Java 17
FROM maven:3.9.6-eclipse-temurin-17

# Install basic tools, Node.js 22, Appium, ADB
RUN apt-get update && apt-get install -y \
     curl unzip git wget gnupg apt-transport-https \
     android-tools-adb net-tools xvfb x11vnc \
     ca-certificates \
  && curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
  && apt-get install -y nodejs \
  && npm install -g appium@2.17.1 \
  && appium driver install --source=npm appium-uiautomator2-driver@4.2.8 \
  && apt-get clean \
  && rm -rf /var/lib/apt/lists/*

# Install Android SDK Command-line tools
RUN mkdir -p /opt/android-sdk/cmdline-tools \
    && cd /opt/android-sdk/cmdline-tools \
    && wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O cmdline-tools.zip \
    && unzip cmdline-tools.zip -d /opt/android-sdk/cmdline-tools \
    && rm cmdline-tools.zip \
    && mkdir -p /opt/android-sdk/cmdline-tools/latest \
    && mv /opt/android-sdk/cmdline-tools/cmdline-tools/* /opt/android-sdk/cmdline-tools/latest/ \
    && rmdir /opt/android-sdk/cmdline-tools/cmdline-tools \
    && yes | /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --licenses \
    && /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.2"

# Environment variables
ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH=$PATH:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin

# Set working directory
WORKDIR /app

# Copy pom.xml first for better layer caching
COPY pom.xml /app/
RUN mvn dependency:go-offline -B

# Copy project files
COPY . /app
RUN mkdir -p /app/reports && chmod -R 777 /app/reports

# Expose Appium server port
EXPOSE 4723

# Copy entrypoint
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Default Payment Gateway (can be overridden)
ENV PG=RazorPay

ENTRYPOINT ["/entrypoint.sh"]

# Optional volume for reports
VOLUME ["/app/reports"]
