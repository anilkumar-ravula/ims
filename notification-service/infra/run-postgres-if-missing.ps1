#!/bin/bash

set -e

# Configuration
POSTGRES_CONTAINER_NAME="notification-postgres"
POSTGRES_DB="notifications"
POSTGRES_USER="user"
POSTGRES_PASSWORD="pass"
POSTGRES_PORT="5432"

# Step 1: Check if PostgreSQL is running locally
if nc -z localhost "$POSTGRES_PORT"; then
  echo "✅ PostgreSQL is already running on port $POSTGRES_PORT"
  exit 0
fi

# Step 2: Check if Docker is installed
if ! command -v docker &> /dev/null; then
  echo "❌ PostgreSQL not found and Docker is not installed."
  echo "💡 Please install Docker or run PostgreSQL manually."
  exit 1
fi

# Step 3: Run PostgreSQL using Docker
echo "🚀 Starting PostgreSQL in Docker..."
docker run -d \
  --name "$POSTGRES_CONTAINER_NAME" \
  -e POSTGRES_DB="$POSTGRES_DB" \
  -e POSTGRES_USER="$POSTGRES_USER" \
  -e POSTGRES_PASSWORD="$POSTGRES_PASSWORD" \
  -p "$POSTGRES_PORT":5432 \
  postgres:15

echo "⏳ Waiting for PostgreSQL to become available..."
until nc -z localhost "$POSTGRES_PORT"; do
  sleep 1
done

echo "✅ PostgreSQL is now running via Docker."
