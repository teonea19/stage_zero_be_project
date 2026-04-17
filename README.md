# stage_zero_be_project
# 🚀 Gender Classification API (Spring Boot)

A RESTful API that predicts gender based on a given name using the Genderize API, processes the response, and returns a structured result with confidence evaluation.

---

## 📌 Live API

**Base URL:**

```
https://your-app.up.railway.app
```

**Endpoint:**

```
GET /api/classify?name=John
```

---

## 📥 Request

### Query Parameter

| Parameter | Type   | Required | Description      |
| --------- | ------ | -------- | ---------------- |
| name      | String | Yes      | Name to classify |

---

## 📤 Response

### ✅ Success Response

```json
{
  "status": "success",
  "data": {
    "name": "john",
    "gender": "male",
    "probability": 0.99,
    "sample_size": 1234,
    "is_confident": true,
    "processed_at": "2026-04-16T10:00:00Z"
  }
}
```

---

## 🧠 Processing Logic

* Extracts:

  * `gender`
  * `probability`
  * `count → renamed to sample_size`
* Computes:

  * `is_confident = true` if:

    * probability ≥ 0.7
    * sample_size ≥ 100
* Adds:

  * `processed_at` (UTC, ISO 8601 format)

---

## ⚠️ Error Handling

All errors follow this format:

```json
{
  "status": "error",
  "message": "Error message here"
}
```

---

### ❌ Possible Errors

| Scenario                   | Status Code |
| -------------------------- | ----------- |
| Missing or empty name      | 400         |
| Invalid (non-string) input | 422         |
| No prediction available    | 422         |
| External API failure       | 500 / 502   |

---

### 🚫 Edge Case Handling

If the external API returns:

* `gender = null`
* OR `count = 0`

Response:

```json
{
  "status": "error",
  "message": "No prediction available for the provided name"
}
```

---

## 🌐 CORS

CORS is enabled for all origins:

```
Access-Control-Allow-Origin: *
```

---

## ⚙️ Tech Stack

* Java 17
* Spring Boot
* Maven
* REST API
* External API Integration (Genderize)

---

## 🛠️ How to Run Locally

### 1. Clone the repository

```
git clone https://github.com/your-username/your-repo.git
cd your-repo
```

---

### 2. Build the project

```
mvn clean install
```

---

### 3. Run the application

```
java -jar target/classifier-0.0.1-SNAPSHOT.jar
```

---

### 4. Test locally

```
http://localhost:8080/api/classify?name=John
```

---

## 🚀 Deployment

This API is deployed on Railway.

---

## 📊 Performance

* Response time under 500ms (excluding external API latency)
* Handles multiple concurrent requests efficiently

---

## 📁 Project Structure

```
controller/   → Handles HTTP requests  
service/      → Business logic  
client/       → External API calls  
dto/          → Data models  
config/       → App configuration  
exception/    → Global error handling  
```

---

## 👤 Author

Developed as part of a backend API integration and data processing assessment.

---

## 📄 License

This project is for educational and assessment purposes.
