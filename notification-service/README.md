# Notification Service

A Spring Boot-based microservice for sending notifications via Email, Slack, SMS, and Calls. Designed for integration with systems like Alertmanager or internal services via REST.

## High Level Architecture

```
[Alertmanager / Other Services]
         │
[NotificationController]
         │
[NotificationService]
         │
┌────────┼──────────────┐
│ sender.com.imr.notification.EmailSenderService   │
│ SlackSenderService   │
│ SMSSenderService     │
│ CallSenderService    │
└────────┴──────────────┘
        │
[NotificationRepository]
        │
[PostgreSQL / JPA]
```
## ✨ Features

- Multi-channel notifications: Email, Slack, SMS, Call
- REST API to send notifications
- Retry and failure handling
- Notification tracking and persistence
- Dockerized with Helm chart for Kubernetes deployment

## 🚀 Tech Stack

- Java 8+
`- Spring Boot
- Maven
- PostgreSQL (for tracking notifications)
- Docker
- Helm (Kubernetes)
`
## 🧪 REST API

### POST `/api/notify`

**Request Body:**

```
{
  "channel": "EMAIL",
  "recipient": "user@example.com",
  "message": "Server down!",
  "incidentId": "1234"
}
```

## 🐳 Docker

Build and run:

```bash
mvn clean install
docker build -t imr/notification-service .
docker run -p 8080:8080 imr/notification-service
```

Build and run along with postgress:
```bash
mvn clean install
docker build -t imr/notification-service .
docker-compose up -d
```

Checking API is running:

curl --location 'http://localhost:8080/api/notify' 

Ideal Response if it is working : Welcome to notifications

## ☸️ Kubernetes (via Helm)

Build and push your Docker image:

```bash
docker build -t imr/notification-service:latest .
docker push imr/notification-service:latest
```

Deploy using Helm:

```bash
helm install notification-service ./helm/notification-service
```

## 🛠 Configuration

Edit `src/main/resources/application.yml` to configure database, mail server, and other settings.

## 📂 Project Structure

```
.
├── src
│   └── main
│       ├── java/com/example/notification
│       ├── resources
├── Dockerfile
├── helm
│   └── notification-service
├── pom.xml
```

## 📃 License

MIT
