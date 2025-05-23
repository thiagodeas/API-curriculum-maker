# Curriculum Maker API

A backend service for generating minimalist developer CVs in PDF format, powered by Spring Boot, Thymeleaf, and OpenHTMLtoPDF.

---

## ✨ Features

- 🧾 Generates professional-looking PDF resumes
- 🖋️ Template rendered using Thymeleaf
- 📄 PDF generation powered by OpenHTMLtoPDF (using PDFBox)

---

## 🛠️ Tech Stack

- Java 21
- Spring Boot
- Thymeleaf
- OpenHTMLtoPDF (with PDFBox)
- Lombok

---

## 📌 API Endpoint

### `POST /api/curriculum/generate`

Generate a PDF file based on the provided curriculum data.

#### 📥 Request Body (`application/json`)

Use JSON data like the example below in the request body:

```json
{
  "fullName": "John Doe",
  "jobTitle": "Full Stack Developer",
  "city": "São Paulo",
  "state": "SP",
  "country": "Brazil",
  "phone": "+55 11 91234-5678",
  "email": "john.doe@example.com",
  "linkedin": "https://linkedin.com/in/johndoe",
  "github": "https://github.com/johndoe",
  "aboutMe": "Passionate developer with experience in backend systems...",
  "experience": [
    {
      "position": "Backend Developer",
      "company": "TechCorp",
      "period": "2022 - Present",
      "description": "Developed REST APIs with Java and Spring Boot..."
    }
  ],
  "knowledgeByCategory": [
    {
      "categoryName": "Languages",
      "skills": ["Java", "JavaScript", "Python"]
    },
    {
      "categoryName": "Frameworks",
      "skills": ["Spring Boot", "React", "NestJS"]
    }
  ],
  "education": [
    {
      "name": "B.Sc. in Computer Science",
      "institution": "Estácio",
      "year": "2024"
    }
  ],
  "projects": [
    {
      "title": "Personal Portfolio",
      "description": "A web portfolio to showcase my work and blog posts."
    }
  ],
  "additionalActivities": [
    {
      "period": "2023",
      "description": "Volunteered in a community tech bootcamp."
    }
  ]
}
```

#### 📤 Response

- `200 OK`: Returns a PDF file as binary (`application/pdf`)
- Response Headers include:
  ```
  Content-Disposition: attachment; filename=curriculo.pdf
  Content-Type: application/pdf
  ```

---

## 🐳 How to Run with Docker

You can build and run the application easily using Docker — no need to install Maven or Java locally.

### 🧱 Build the Docker image

```bash
docker build -t curriculum-maker .
```

### Run the container

```bash
docker run -p 8080:8080 curriculum-maker
```

Your API will be available at: http://localhost:8080

---

## 🚀 How to Run Locally

1. Clone the repository:
   ```bash
   git clone https://github.com/thiagodeas/API-curriculum-maker.git
   cd curriculum-maker
   ```

2. Build and run the project:
   ```bash
   ./mvnw spring-boot:run
   ```

3. Prepare your JSON payload and test the API.

You can test the API in **two ways**:

- 1: Using a JSON file with curl:

Copy the JSON example above and save it as sample-curriculum.json in your project folder.

Then run:
```bash
   curl -X POST http://localhost:8080/api/curriculum/generate \
   -H "Content-Type: application/json" \
   --data @sample-curriculum.json --output curriculum.pdf
```
This command saves the generated PDF locally as curriculum.pdf.

OR

- 2: Copy and paste the JSON directly in Insomnia or Postman:

Open Insomnia or Postman, create a POST request to http://localhost:8080/api/curriculum/generate, set the body type to application/json, and paste the JSON example above directly into the request body.

Send the request and download the returned PDF.
