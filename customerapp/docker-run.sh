#!/bin/bash

# Docker Helper Scripts for Augmont Customer App
# This script provides easy commands to build and run the Docker container

set -e

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Default values
IMAGE_NAME="augmont-customer-app"
CONTAINER_NAME="augmont-customer-app"
PG="RazorPay"
XML_FILES="testng.xml"

# Function to print colored output
print_info() {
    echo -e "${BLUE}[INFO]${NC} $1"
}

print_success() {
    echo -e "${GREEN}[SUCCESS]${NC} $1"
}

print_warning() {
    echo -e "${YELLOW}[WARNING]${NC} $1"
}

print_error() {
    echo -e "${RED}[ERROR]${NC} $1"
}

# Function to show usage
show_usage() {
    echo "Usage: $0 [COMMAND] [OPTIONS]"
    echo ""
    echo "Commands:"
    echo "  build                    Build the Docker image"
    echo "  run                      Run the container"
    echo "  stop                     Stop the running container"
    echo "  clean                    Remove container and image"
    echo "  logs                     Show container logs"
    echo "  shell                    Open shell in running container"
    echo "  test                     Run tests with custom parameters"
    echo ""
    echo "Options:"
    echo "  --pg=PAYMENT_GATEWAY     Set payment gateway (default: RazorPay)"
    echo "  --xml=XML_FILE           Set test suite XML file (default: testng.xml)"
    echo "  --help                   Show this help message"
    echo ""
    echo "Examples:"
    echo "  $0 build"
    echo "  $0 run --pg=Paytm --xml=regressionSuit.xml"
    echo "  $0 test --pg=RazorPay --xml=puchaseGoldOneTime.xml"
}

# Function to build Docker image
build_image() {
    print_info "Building Docker image: $IMAGE_NAME"
    docker build -t $IMAGE_NAME .
    print_success "Docker image built successfully!"
}

# Function to run container
run_container() {
    print_info "Starting container: $CONTAINER_NAME"
    print_info "Payment Gateway: $PG"
    print_info "Test Suite: $XML_FILES"
    
    # Stop existing container if running
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        print_warning "Stopping existing container..."
        docker stop $CONTAINER_NAME
    fi
    
    # Remove existing container if exists
    if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
        docker rm $CONTAINER_NAME
    fi
    
    # Run the container
    docker run -d \
        --name $CONTAINER_NAME \
        --privileged \
        -p 4723:4723 \
        -p 5900:5900 \
        -v "$(pwd)/reports:/app/reports" \
        -v "$(pwd)/test-output:/app/test-output" \
        -v "$(pwd)/screenshots:/app/screenshots" \
        -e PG="$PG" \
        -e xmlFiles="$XML_FILES" \
        --add-host=host.docker.internal:host-gateway \
        $IMAGE_NAME
    
    print_success "Container started successfully!"
    print_info "Appium server available at: http://localhost:4723"
    print_info "VNC server available at: localhost:5900"
    print_info "View logs with: $0 logs"
}

# Function to stop container
stop_container() {
    print_info "Stopping container: $CONTAINER_NAME"
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        docker stop $CONTAINER_NAME
        print_success "Container stopped successfully!"
    else
        print_warning "Container is not running"
    fi
}

# Function to clean up
clean_up() {
    print_info "Cleaning up Docker resources..."
    
    # Stop and remove container
    if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
        docker stop $CONTAINER_NAME 2>/dev/null || true
        docker rm $CONTAINER_NAME
        print_success "Container removed"
    fi
    
    # Remove image
    if [ "$(docker images -q $IMAGE_NAME)" ]; then
        docker rmi $IMAGE_NAME
        print_success "Image removed"
    fi
    
    print_success "Cleanup completed!"
}

# Function to show logs
show_logs() {
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        docker logs -f $CONTAINER_NAME
    else
        print_error "Container is not running"
        exit 1
    fi
}

# Function to open shell
open_shell() {
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        docker exec -it $CONTAINER_NAME /bin/bash
    else
        print_error "Container is not running"
        exit 1
    fi
}

# Function to run tests
run_tests() {
    print_info "Running tests with Payment Gateway: $PG and Suite: $XML_FILES"
    
    # Stop existing container if running
    if [ "$(docker ps -q -f name=$CONTAINER_NAME)" ]; then
        docker stop $CONTAINER_NAME
        docker rm $CONTAINER_NAME
    fi
    
    # Run tests
    docker run --rm \
        --name $CONTAINER_NAME \
        --privileged \
        -p 4723:4723 \
        -p 5900:5900 \
        -v "$(pwd)/reports:/app/reports" \
        -v "$(pwd)/test-output:/app/test-output" \
        -v "$(pwd)/screenshots:/app/screenshots" \
        -e PG="$PG" \
        -e xmlFiles="$XML_FILES" \
        --add-host=host.docker.internal:host-gateway \
        $IMAGE_NAME
    
    print_success "Tests completed! Check reports folder for results."
}

# Parse command line arguments
COMMAND=""
while [[ $# -gt 0 ]]; do
    case $1 in
        build|run|stop|clean|logs|shell|test)
            COMMAND="$1"
            shift
            ;;
        --pg=*)
            PG="${1#*=}"
            shift
            ;;
        --xml=*)
            XML_FILES="${1#*=}"
            shift
            ;;
        --help)
            show_usage
            exit 0
            ;;
        *)
            print_error "Unknown option: $1"
            show_usage
            exit 1
            ;;
    esac
done

# Execute command
case $COMMAND in
    build)
        build_image
        ;;
    run)
        run_container
        ;;
    stop)
        stop_container
        ;;
    clean)
        clean_up
        ;;
    logs)
        show_logs
        ;;
    shell)
        open_shell
        ;;
    test)
        run_tests
        ;;
    "")
        print_error "No command specified"
        show_usage
        exit 1
        ;;
    *)
        print_error "Unknown command: $COMMAND"
        show_usage
        exit 1
        ;;
esac
