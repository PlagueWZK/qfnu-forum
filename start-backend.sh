#!/bin/bash

echo "Starting QFNU Forum Backend..."
echo "================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "Error: Maven is not installed."
    echo "Please install Maven first:"
    echo "  Ubuntu/Debian: sudo apt install maven"
    echo "  macOS: brew install maven"
    exit 1
fi

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "Error: Java is not installed."
    echo "Please install Java 17 or higher:"
    echo "  Ubuntu/Debian: sudo apt install openjdk-17-jdk"
    echo "  macOS: brew install openjdk@17"
    exit 1
fi

# Navigate to backend directory
cd "$(dirname "$0")/backend" || exit

# Start the application
echo "Building and starting Spring Boot application..."
echo "Backend will be available at: http://localhost:8080"
echo ""

mvn spring-boot:run
