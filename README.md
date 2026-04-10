# 🚀 RealTicker - AI-Powered Stock Insights Platform

## 📌 Overview

**RealTicker** is a full-stack application built for the **SORIM AI Hackathon Technical Assessment**.
It combines real-time stock data with **AI-powered insights** to help users understand market trends in a simple, human-readable way.

The platform leverages **Large Language Models (LLMs)** to transform complex financial data into beginner-friendly investment guidance.

---

## 🎯 Key Objectives

* 📊 **Market Tracking**
  Fetch daily stock data and identify **Top 10 stocks** based on volume and growth.

* 🤖 **AI Analysis**
  Analyze **6 months of historical data** using a Hugging Face LLM.

* 💡 **Investment Guidance**
  Provide:

  * Trend Identification (Upward / Downward)
  * Risk Level (Low / Medium / High)
  * Suggested Actions for beginners

---

## 🏗️ System Architecture

### 1. Client Layer

* Built with **React.js**
* Handles UI, user interaction, and data visualization

### 2. Server Layer

* Built with **Spring Boot (Java)**
* Manages business logic and API communication

### 3. AI Layer

* Uses **Hugging Face Inference API**
* Powered by **Mistral-7B-Instruct-v0.3**
* Generates intelligent financial insights

---

## 🛠️ Tech Stack

| Layer     | Technology                         |
| --------- | ---------------------------------- |
| Frontend  | React.js                           |
| Backend   | Java (Spring Boot)                 |
| AI        | Hugging Face Inference API         |
| LLM Model | mistralai/Mistral-7B-Instruct-v0.3 |

---

## ✨ Core Features

* 📈 **Top 10 Stocks Dashboard**

  * Ticker
  * Company Name
  * Current Price
  * Change %
  * Volume

* 📊 **Interactive Charting**

  * Visualize 6 months of historical stock data

* 🧠 **AI-Generated Insights**

  * Trend Analysis (Upward / Downward)
  * Risk Level Assessment
  * Suggested Investment Actions

* ⚠️ **Professional Disclaimer**

  > This is AI-generated analysis and not financial advice.

---

## 🔗 API Endpoints

| Endpoint                       | Method     | Description                    |
| ------------------------------ | ---------- | ------------------------------ |
| `/api/stocks/top10`            | GET        | Fetch Top 10 performing stocks |
| `/api/stocks/{ticker}/history` | GET        | Get 6-month historical data    |
| `/api/stocks/{ticker}/analyze` | GET / POST | Generate AI-based analysis     |

---

## ⚙️ Setup Instructions

### ✅ Prerequisites

* Java 17+
* Node.js & npm
* Hugging Face Access Token

---

### 🔧 Backend Setup

```bash
cd realticker1
```

1. Open:

```
src/main/resources/application.properties
```

2. Add your Hugging Face token:

```properties
huggingface.token=your_token_here
```

3. Run the application:

```bash
mvn spring-boot:run
```

---

### 🎨 Frontend Setup

```bash
cd frontend
npm install
npm start
```

---

## 📌 Project Highlights

* Full-stack integration (React + Spring Boot + AI)
* Real-world financial use case
* Clean REST API architecture
* LLM-powered insights generation
* Beginner-friendly investment explanations

---

## 👨‍💻 Developer

**Manojkumar**

---

## 🏁 Submission Details

* 🏆 **Assessment**: SORIM AI Hackathon
* 📂 **Category**: Full-Stack AI Integration
* 🚀 **Project**: RealTicker

---

## 📜 License

This project is developed for educational and assessment purposes.

---

⭐ If you found this project useful, feel free to star the repository!



Architecture: React Frontend + Spring Boot REST API + Hugging Face AI


