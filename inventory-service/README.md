# Smart Email Assistant Backend

A Spring Boot backend service to generate professional email replies using Gemini Language Model API.

---

## Features

* Accepts an email content and optional tone.
* Sends prompt to Gemini API for generating reply content.
* Returns generated email reply as a response.

---

## Technologies Used

* Java 17+
* Spring Boot
* Spring WebFlux (WebClient)
* Lombok
* Jackson (JSON processing)

---

## Setup & Running

### 1. Clone the repository

```bash
git clone <repo-url>
cd <repo-root>
```

---

### 2. Configure API Key

The app uses Google Gemini Language Model API. You need to provide your API key securely.

#### Option 1: Use `secrets.properties` (recommended)

Create a file named `secrets.properties` inside `src/main/resources` with the content:

```properties
API_KEY=YOUR_ACTUAL_API_KEY_HERE
```

The main `application.properties` already imports this file optionally:

```properties
spring.config.import=optional:classpath:secrets.properties
```

So, the placeholder `${API_KEY}` in

```properties
gemini.api.key=${API_KEY}
```

will be replaced at runtime.

---

#### Option 2: Set environment variable (alternative)

Set the environment variable `API_KEY` before running the app:

```bash
export API_KEY=YOUR_ACTUAL_API_KEY_HERE   # Linux/Mac
set API_KEY=YOUR_ACTUAL_API_KEY_HERE      # Windows CMD
```

Make sure to update `application.properties` to:

```properties
gemini.api.key=${API_KEY}
```

---

### 3. Build and run the app

Using Maven:

```bash
./mvnw clean package
java -jar target/your-app-name.jar
```

Or run from your IDE directly.

---

### 4. API Usage

Send a POST request to:

```
POST /api/email/generate
Content-Type: application/json
```

Sample request body:

```json
{
  "emailContent": "Hi team, can you please provide the status update?",
  "tone": "friendly"
}
```

Sample response:

```json
"Hi, thanks for reaching out! The status update is as follows..."
```

---

## Project Structure

```
src/
├─ main/
│  ├─ java/lk/email/assistant/
│  │  ├─ controller/EmailGenerator.java
│  │  ├─ service/GenerateEmailService.java
│  │  └─ entity/EmailRequest.java
│  └─ resources/
│     ├─ application.properties
│     └─ secrets.properties (not committed, for API key)
```

---

## Notes

* Make sure to keep `secrets.properties` **out of version control** for security.
* You can enhance this service by adding authentication, validation, or logging.

