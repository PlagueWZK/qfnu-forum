#!/bin/bash

echo "Starting QFNU Forum Frontend..."
echo "================================"
echo ""

# Check if Node.js is installed
if ! command -v node &> /dev/null; then
    echo "Error: Node.js is not installed."
    echo "Please install Node.js first:"
    echo "  Ubuntu/Debian: curl -fsSL https://deb.nodesource.com/setup_18.x | sudo -E bash - && sudo apt install nodejs"
    echo "  macOS: brew install node"
    exit 1
fi

# Navigate to frontend directory
cd "$(dirname "$0")/frontend" || exit

# Install dependencies if node_modules doesn't exist
if [ ! -d "node_modules" ]; then
    echo "Installing dependencies..."
    npm install
    echo ""
fi

# Start the application
echo "Starting Vue development server..."
echo "Frontend will be available at: http://localhost:5173"
echo ""

npm run dev
