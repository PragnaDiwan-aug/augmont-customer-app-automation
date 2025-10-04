# Docker Setup for Augmont Customer App

This project is now configured to run in Docker containers, making it easy to set up and run your mobile app testing environment consistently across different machines.

## 🚀 Quick Start

### Prerequisites
- Docker Desktop installed and running
- At least 4GB RAM allocated to Docker
- Android emulator or physical device connected (for testing)

### Option 1: Using Docker Compose (Recommended)

1. **Build and run the application:**
   ```bash
   cd customerapp
   docker-compose up --build
   ```

2. **Run tests with custom parameters:**
   ```bash
   docker-compose run --rm augmont-customer-app
   ```

3. **Stop the application:**
   ```bash
   docker-compose down
   ```

### Option 2: Using Helper Scripts

#### For Linux/Mac:
```bash
cd customerapp
chmod +x docker-run.sh

# Build the image
./docker-run.sh build

# Run the container
./docker-run.sh run

# Run tests with custom parameters
./docker-run.sh test --pg=Paytm --xml=regressionSuit.xml

# View logs
./docker-run.sh logs

# Stop container
./docker-run.sh stop
```

#### For Windows:
```cmd
cd customerapp

# Build the image
docker-run.bat build

# Run the container
docker-run.bat run

# Run tests with custom parameters
docker-run.bat test --pg=Paytm --xml=regressionSuit.xml

# View logs
docker-run.bat logs

# Stop container
docker-run.bat stop
```

## 📋 Available Commands

### Docker Compose Commands
- `docker-compose up --build` - Build and start the application
- `docker-compose up -d` - Start in background
- `docker-compose down` - Stop and remove containers
- `docker-compose logs -f` - Follow logs
- `docker-compose exec augmont-customer-app bash` - Open shell

### Helper Script Commands
- `build` - Build the Docker image
- `run` - Run the container in background
- `test` - Run tests and exit
- `stop` - Stop the running container
- `logs` - Show container logs
- `shell` - Open shell in running container
- `clean` - Remove container and image

## ⚙️ Configuration

### Environment Variables
- `PG` - Payment Gateway (default: RazorPay)
- `xmlFiles` - Test suite XML file (default: testng.xml)

### Available Test Suites
- `testng.xml` - Default test suite
- `regressionSuit.xml` - Regression tests
- `puchaseGoldOneTime.xml` - Gold purchase tests
- `purchaseSilverOnetime.xml` - Silver purchase tests
- `purchaseSip.xml` - SIP purchase tests

### Available Payment Gateways
- `RazorPay`
- `Paytm`
- `PhonePe`
- `UPI`

## 🔧 Docker Configuration

### Ports
- `4723` - Appium server port
- `5900` - VNC server port (for debugging)

### Volumes
- `./reports:/app/reports` - Test reports
- `./test-output:/app/test-output` - Test output files
- `./screenshots:/app/screenshots` - Screenshots

### Services

#### Main Application (`augmont-customer-app`)
- Java 17 + Maven
- Appium 2.17.1
- Android SDK
- TestNG framework
- Selenium WebDriver

#### Optional Android Emulator (`android-emulator`)
- Android 11 emulator
- Samsung Galaxy S10 profile
- VNC access enabled

## 🐛 Troubleshooting

### Common Issues

1. **Container fails to start**
   ```bash
   # Check Docker logs
   docker logs augmont-customer-app
   
   # Check if ports are available
   netstat -an | grep 4723
   ```

2. **Android emulator connection issues**
   ```bash
   # Ensure emulator is running on host
   adb devices
   
   # Connect to emulator from container
   adb connect host.docker.internal:5555
   ```

3. **Permission issues**
   ```bash
   # Make sure Docker has proper permissions
   sudo usermod -aG docker $USER
   ```

4. **Out of memory errors**
   - Increase Docker memory allocation to at least 4GB
   - Close other applications to free up system memory

### Debugging

1. **Access container shell:**
   ```bash
   docker exec -it augmont-customer-app /bin/bash
   ```

2. **Check Appium server:**
   ```bash
   curl http://localhost:4723/status
   ```

3. **View VNC (if needed):**
   - Connect to `localhost:5900` using VNC viewer
   - Password: (check container logs)

## 📁 Project Structure

```
customerapp/
├── Dockerfile              # Main Docker configuration
├── docker-compose.yml      # Docker Compose configuration
├── docker-run.sh          # Linux/Mac helper script
├── docker-run.bat         # Windows helper script
├── entrypoint.sh          # Container startup script
├── pom.xml                # Maven configuration
├── src/                   # Source code
├── config/                # Configuration files
├── testdata/              # Test data
├── xmlfiles/              # TestNG suite files
├── resource/              # APK files and resources
├── reports/               # Test reports (mounted volume)
├── test-output/           # Test output (mounted volume)
└── screenshots/           # Screenshots (mounted volume)
```

## 🔄 Development Workflow

1. **Make code changes** in your local IDE
2. **Build the Docker image** with your changes
3. **Run tests** to verify functionality
4. **Check reports** in the `reports/` folder
5. **Iterate** as needed

## 📊 Monitoring

- **Appium Server:** http://localhost:4723
- **VNC Server:** localhost:5900
- **Test Reports:** `./reports/` folder
- **Container Logs:** `docker logs augmont-customer-app`

## 🚀 Advanced Usage

### Custom Test Execution
```bash
# Run specific test suite with specific payment gateway
docker-compose run --rm -e PG=Paytm -e xmlFiles=regressionSuit.xml augmont-customer-app
```

### Multiple Test Runs
```bash
# Run multiple test suites
for suite in testng.xml regressionSuit.xml; do
  docker-compose run --rm -e xmlFiles=$suite augmont-customer-app
done
```

### CI/CD Integration
```yaml
# Example GitHub Actions workflow
- name: Run Tests
  run: |
    cd customerapp
    docker-compose run --rm augmont-customer-app
```

## 📝 Notes

- The container runs in privileged mode to access Android emulator
- Reports are automatically saved to the local `reports/` folder
- The container includes VNC server for debugging if needed
- All dependencies are pre-installed in the container
- The setup supports both physical devices and emulators

## 🆘 Support

If you encounter any issues:
1. Check the troubleshooting section above
2. Review container logs: `docker logs augmont-customer-app`
3. Ensure Docker Desktop is running and has sufficient resources
4. Verify Android emulator/device connectivity
