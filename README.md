
# 🚨 Incident Management and Reporting (IMR)

**IMR** is a microservice-driven system that helps detect, manage, and notify stakeholders of various types of incidents in real time. It integrates **monitoring, alerting, and notification delivery** across infrastructure, applications, and business processes.

---

## ✅ Use Cases

IMR addresses three core categories of incidents:

- **Infrastructure Monitoring**
  - Examples: High CPU, memory, disk usage
  - Tool: Prometheus

- **Application Errors**
  - Examples: API exceptions, service downtimes
  - Tool: Custom error-reporting from Spring Boot apps

- **Business Events**
  - Examples: Order failures, payment issues
  - Tool: Business logic triggers that post to the Notification Service

---

## 🧩 System Architecture Overview

```
                        +-------------------+
                        |   Prometheus      |
                        | (Infra Metrics)   |
                        +-------------------+
                                 |
                             [ALERTS]
                                 |
                        +-------------------+
                        |  Alertmanager     |
                        | (Rules & Routing) |
                        +-------------------+
                                 |
                                 v
                 +-------------------------------+
                 | Spring Boot Notification API  |
                 |  /notify endpoint (webhook)   |
                 +-------------------------------+
                  /         |          |         \
      [Slack]   [Email]   [SMS]   [Custom Dashboard]

+----------------------+         +------------------------+
| Spring Boot Apps     |         | Business Event Service |
| (App logs, errors)   |         | (Order/Payment errors) |
+----------------------+         +------------------------+
              \                        /
               \     [HTTP POST]     /
                \------------------/
                      Notification API
```

---

## 🔔 Notification Service

📍 **Path:** [`notification-service/`](./notification-service/README.md)

The Notification Service receives structured alert payloads and dispatches messages via different channels.

### Features:

- Multi-channel delivery: Email, Slack, SMS, Webhooks
- Retry and backoff on failures
- Format templates for alerts
- Multi-tenant support
- Encryption/decryption of alert payloads
- Logging and audit of notifications

### Technologies:

- Java Spring Boot
- PostgreSQL
- RabbitMQ (optional for queuing)
- Kubernetes + Helm

---

## ⚠️ Alert Manager

The Alertmanager component handles all routing and filtering of incoming alerts from Prometheus.

### Features:

- Receives alerts from Prometheus via `/api/v1/alerts`
- Routes alerts to Notification Service
- Groups similar alerts
- Deduplicates and suppresses noise
- Supports silences and escalation

### Configuration:

- `alertmanager/config.yaml` includes:
  - Routing rules per use case
  - Receiver endpoints
  - Slack/email integration

---

## 📦 Deployment Options

- **Docker Compose** (local development)
- **Kubernetes + Helm**
- Integrated with:
  - Prometheus Operator
  - External services like Twilio, SMTP, etc.

---

## 📂 Repository Structure

```
.
├── alertmanager/               # Alertmanager rules & config
├── notification-service/       # Notification microservice (Spring Boot)
├── prometheus/                 # Prometheus config & alert rules
├── init-scripts/               # SQL scripts for tenant DBs
├── charts/                     # Helm charts
├── docker-compose.yml          # Local stack setup
└── README.md                   # This file
```

---



- [Notification Service README](./notification-service/README.md)
- [Prometheus Docs](https://prometheus.io/docs/introduction/overview/)
- [Alertmanager Docs](https://prometheus.io/docs/alerting/latest/alertmanager/)
- [Spring Boot](https://spring.io/projects/spring-boot)

---

## 🛠️ Future Enhancements

- Tenant-specific throttling and quotas
- UI for alert configuration and silence management
- Support for Teams, Telegram, WhatsApp integrations
- GraphQL API support

---

## 📄 License

MIT License — [View License](LICENSE)
