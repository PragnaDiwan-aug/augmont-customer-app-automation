@echo off
setlocal enabledelayedexpansion

REM Docker Helper Scripts for Augmont Customer App (Windows)
REM This script provides easy commands to build and run the Docker container

set "IMAGE_NAME=augmont-customer-app"
set "CONTAINER_NAME=augmont-customer-app"
set "PG=RazorPay"
set "XML_FILES=testng.xml"

:show_usage
echo Usage: %0 [COMMAND] [OPTIONS]
echo.
echo Commands:
echo   build                    Build the Docker image
echo   run                      Run the container
echo   stop                     Stop the running container
echo   clean                    Remove container and image
echo   logs                     Show container logs
echo   shell                    Open shell in running container
echo   test                     Run tests with custom parameters
echo.
echo Options:
echo   --pg=PAYMENT_GATEWAY     Set payment gateway (default: RazorPay)
echo   --xml=XML_FILE           Set test suite XML file (default: testng.xml)
echo   --help                   Show this help message
echo.
echo Examples:
echo   %0 build
echo   %0 run --pg=Paytm --xml=regressionSuit.xml
echo   %0 test --pg=RazorPay --xml=puchaseGoldOneTime.xml
goto :eof

:build_image
echo [INFO] Building Docker image: %IMAGE_NAME%
docker build -t %IMAGE_NAME% .
if %errorlevel% equ 0 (
    echo [SUCCESS] Docker image built successfully!
) else (
    echo [ERROR] Failed to build Docker image
    exit /b 1
)
goto :eof

:run_container
echo [INFO] Starting container: %CONTAINER_NAME%
echo [INFO] Payment Gateway: %PG%
echo [INFO] Test Suite: %XML_FILES%

REM Stop existing container if running
docker ps -q -f name=%CONTAINER_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    echo [WARNING] Stopping existing container...
    docker stop %CONTAINER_NAME%
)

REM Remove existing container if exists
docker ps -aq -f name=%CONTAINER_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    docker rm %CONTAINER_NAME%
)

REM Run the container
docker run -d ^
    --name %CONTAINER_NAME% ^
    --privileged ^
    -p 4723:4723 ^
    -p 5900:5900 ^
    -v "%cd%\reports:/app/reports" ^
    -v "%cd%\test-output:/app/test-output" ^
    -v "%cd%\screenshots:/app/screenshots" ^
    -e PG="%PG%" ^
    -e xmlFiles="%XML_FILES%" ^
    --add-host=host.docker.internal:host-gateway ^
    %IMAGE_NAME%

if %errorlevel% equ 0 (
    echo [SUCCESS] Container started successfully!
    echo [INFO] Appium server available at: http://localhost:4723
    echo [INFO] VNC server available at: localhost:5900
    echo [INFO] View logs with: %0 logs
) else (
    echo [ERROR] Failed to start container
    exit /b 1
)
goto :eof

:stop_container
echo [INFO] Stopping container: %CONTAINER_NAME%
docker ps -q -f name=%CONTAINER_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    docker stop %CONTAINER_NAME%
    echo [SUCCESS] Container stopped successfully!
) else (
    echo [WARNING] Container is not running
)
goto :eof

:clean_up
echo [INFO] Cleaning up Docker resources...

REM Stop and remove container
docker ps -aq -f name=%CONTAINER_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    docker stop %CONTAINER_NAME% >nul 2>&1
    docker rm %CONTAINER_NAME%
    echo [SUCCESS] Container removed
)

REM Remove image
docker images -q %IMAGE_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    docker rmi %IMAGE_NAME%
    echo [SUCCESS] Image removed
)

echo [SUCCESS] Cleanup completed!
goto :eof

:show_logs
docker ps -q -f name=%CONTAINER_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    docker logs -f %CONTAINER_NAME%
) else (
    echo [ERROR] Container is not running
    exit /b 1
)
goto :eof

:open_shell
docker ps -q -f name=%CONTAINER_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    docker exec -it %CONTAINER_NAME% /bin/bash
) else (
    echo [ERROR] Container is not running
    exit /b 1
)
goto :eof

:run_tests
echo [INFO] Running tests with Payment Gateway: %PG% and Suite: %XML_FILES%

REM Stop existing container if running
docker ps -q -f name=%CONTAINER_NAME% >nul 2>&1
if %errorlevel% equ 0 (
    docker stop %CONTAINER_NAME%
    docker rm %CONTAINER_NAME%
)

REM Run tests
docker run --rm ^
    --name %CONTAINER_NAME% ^
    --privileged ^
    -p 4723:4723 ^
    -p 5900:5900 ^
    -v "%cd%\reports:/app/reports" ^
    -v "%cd%\test-output:/app/test-output" ^
    -v "%cd%\screenshots:/app/screenshots" ^
    -e PG="%PG%" ^
    -e xmlFiles="%XML_FILES%" ^
    --add-host=host.docker.internal:host-gateway ^
    %IMAGE_NAME%

if %errorlevel% equ 0 (
    echo [SUCCESS] Tests completed! Check reports folder for results.
) else (
    echo [ERROR] Tests failed
    exit /b 1
)
goto :eof

REM Parse command line arguments
set "COMMAND="
:parse_args
if "%~1"=="" goto :execute_command
if "%~1"=="build" set "COMMAND=build" & shift & goto :parse_args
if "%~1"=="run" set "COMMAND=run" & shift & goto :parse_args
if "%~1"=="stop" set "COMMAND=stop" & shift & goto :parse_args
if "%~1"=="clean" set "COMMAND=clean" & shift & goto :parse_args
if "%~1"=="logs" set "COMMAND=logs" & shift & goto :parse_args
if "%~1"=="shell" set "COMMAND=shell" & shift & goto :parse_args
if "%~1"=="test" set "COMMAND=test" & shift & goto :parse_args
if "%~1"=="--help" call :show_usage & exit /b 0

REM Parse options
if "%~1"=="" goto :parse_args
set "arg=%~1"
if "!arg:~0,5!"=="--pg=" (
    set "PG=!arg:~5!"
    shift
    goto :parse_args
)
if "!arg:~0,6!"=="--xml=" (
    set "XML_FILES=!arg:~6!"
    shift
    goto :parse_args
)

echo [ERROR] Unknown option: %~1
call :show_usage
exit /b 1

:execute_command
if "%COMMAND%"=="" (
    echo [ERROR] No command specified
    call :show_usage
    exit /b 1
)

if "%COMMAND%"=="build" call :build_image
if "%COMMAND%"=="run" call :run_container
if "%COMMAND%"=="stop" call :stop_container
if "%COMMAND%"=="clean" call :clean_up
if "%COMMAND%"=="logs" call :show_logs
if "%COMMAND%"=="shell" call :open_shell
if "%COMMAND%"=="test" call :run_tests

goto :eof
