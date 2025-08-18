# ================================
# Base image: Maven + Java 17
# ================================
FROM maven:3.9.6-eclipse-temurin-17

# ================================
# Install basic tools, Node.js 22, and Appium
# ================================
RUN apt-get update && apt-get install -y \
    curl unzip git wget gnupg apt-transport-https \
    android-tools-adb \
    net-tools \
    && curl -fsSL https://deb.nodesource.com/setup_22.x | bash - \
    && apt-get install -y nodejs \
    && npm install -g appium@2.17.1 \
    && appium driver install uiautomator2 \
    && apt-get clean

# ================================
# Install Android SDK Command-line tools
# ================================
RUN mkdir -p /opt/android-sdk/cmdline-tools \
    && cd /opt/android-sdk/cmdline-tools \
    && wget https://dl.google.com/android/repository/commandlinetools-linux-9477386_latest.zip -O cmdline-tools.zip \
    && unzip cmdline-tools.zip -d /opt/android-sdk/cmdline-tools \
    && rm cmdline-tools.zip \
    # Move extracted tools into expected folder
    && mkdir -p /opt/android-sdk/cmdline-tools/latest \
    && mv /opt/android-sdk/cmdline-tools/cmdline-tools/* /opt/android-sdk/cmdline-tools/latest/ \
    && rmdir /opt/android-sdk/cmdline-tools/cmdline-tools \
    # Accept licenses and install required SDK components
    && yes | /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager --licenses \
    && /opt/android-sdk/cmdline-tools/latest/bin/sdkmanager "platform-tools" "platforms;android-33" "build-tools;33.0.2"

# ================================
# Environment variables
# ================================
ENV ANDROID_SDK_ROOT=/opt/android-sdk
ENV PATH=$PATH:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin

# ================================
# Set working directory
# ================================
WORKDIR /app

# ================================
# Copy pom.xml and download dependencies first (cache layer)
# ================================
COPY pom.xml /app/
RUN mvn dependency:go-offline -B

# ================================
# Copy rest of the project
# ================================
COPY . /app
COPY src ./src
COPY config /app/config
COPY testdata ./testdata
COPY xmlfiles ./xmlfiles
COPY resource ./resource

# ================================
# Entrypoint
# ================================
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Default Payment Gateway (can be overridden)
ENV PG=RazorPay

# Expose Appium server port
EXPOSE 4723

# Entrypoint
ENTRYPOINT ["/entrypoint.sh"]
