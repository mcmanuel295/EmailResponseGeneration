# AI-Powered Email Response Generator Backend

A robust, enterprise-ready Spring Boot microservice designed to ingest incoming emails, process text contextual parameters, and programmatically generate tailored responses based on specified tones (Professional, Casual, Friendly, Serious).

This backend acts as the core processing engine, easily extensible to interface with LLM Orchestration frameworks (such as Spring AI, LangChain4j, or direct LLM REST APIs) and relational databases for logging or auditing purposes.

---

## 🚀 Features

- **RESTful Architecture:** Clear, decoupled API endpoints for structural email ingestion and response delivery.
- **Tone Parameterization:** Robust handling of tone contexts using strongly typed enums (`PROFESSIONAL`, `CASUAL`, `FRIENDLY`, `SERIOUS`).
- **Input Validation:** Built-in verification using Java Bean Validation (`jakarta.validation`) to ensure incoming payloads are clean and non-empty.
- **Cross-Origin Resource Sharing (CORS):** Pre-configured CORS policies to seamlessly integrate with your frontend application.
- **Robust Exception Handling:** Global exception handling interceptors that return structured, client-friendly error JSON structures.

---

## 🛠️ Tech Stack & Ecosystem

- **Framework:** Spring Boot 3.x
- **Language:** Java 17 / 21
- **Build Tool:** Apache Maven
- **Core Dependencies:**
  - `spring-boot-starter-web` (REST APIs & embedded Tomcat server)
  - `spring-boot-starter-validation` (Payload safety constraints)
  - `lombok` (Boilerplate code reduction for clean POJOs)

---

## ⚙️ Configuration & Environment Setup

### Prerequisites
- **JDK 17** or higher installed.
- **Apache Maven 3.8+** installed.

---

## 🔌 API Documentation

### Generate Email Response
Exposes a secure POST endpoint to process a raw email body alongside an exact tone modifier.

- **URL:** `/api/v1/emails/generate`
- **Method:** `POST`
- **Headers:** `Content-Type: application/json`

#### Request Body Schema (`EmailRequestDTO`)
```json
{
  "receivedEmail": "Hi team, can we push our weekly sync to tomorrow afternoon? Let me know if that works.",
  "tone": "PROFESSIONAL"