$PostgresPort = 5432
$ContainerName = "notification-postgres"
$Db = "notifications"
$User = "user"
$Pass = "pass"

# Check if PostgreSQL is already running
try {
    $tcp = New-Object System.Net.Sockets.TcpClient("localhost", $PostgresPort)
    $tcp.Close()
    Write-Host "✅ PostgreSQL is already running on port $PostgresPort"
    exit
} catch {}

# Check if Docker is installed
if (-not (Get-Command docker -ErrorAction SilentlyContinue)) {
    Write-Host "❌ Docker is not installed. Please install Docker."
    exit 1
}

# Start PostgreSQL container
Write-Host "🚀 Starting PostgreSQL in Docker..."
docker run -d `
    --name $ContainerName `
    -e POSTGRES_DB=$Db `
    -e POSTGRES_USER=$User `
    -e POSTGRES_PASSWORD=$Pass `
    -p "$PostgresPort":5432 `
    postgres:15

# Wait for it to start
Write-Host "⏳ Waiting for PostgreSQL..."
while (-not (Test-NetConnection -ComputerName localhost -Port $PostgresPort).TcpTestSucceeded) {
    Start-Sleep -Seconds 1
}
Write-Host "✅ PostgreSQL is now running via Docker."
