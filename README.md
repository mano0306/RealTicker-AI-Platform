RealTicker - AI-Powered Stock Insights Platform

Overview::

RealTicker is a full-stack application developed for the SORIM AI Hackathon Technical Assessment. The platform provides users with real-time stock price visualization and leverages Large Language Models (LLMs) to provide human-readable investment insights.

Key Objectives:

1.Market Tracking: Fetches daily stock price data and identifies the Top 10 stocks based on volume and growth.

2.AI Analysis: Uses a Hugging Face LLM to analyze 6 months of historical price trends.

Investment Guidance: Provides trend identification, risk levels, and suggested actions for beginners.



Tech Stack:

Frontend: React.js
Backend: Java (Spring Boot)
AI Integration: Hugging Face Inference API
LLM Used: mistralai/Mistral-7B-Instruct-v0.3



Core Features:

1.Top 10 Stocks Dashboard: Displays Ticker, Company Name, Current Price, Change %, and Volume.
2.Interactive Charting: Visual representation of 6 months of historical data.
3.AI-Generated Insights: Detailed trend analysis (Upward/Downward), Risk Level assessment, and Suggested Actions.
4.Professional Disclaimer: "This is AI-generated analysis and not financial advice."



API Design:

/api/stocks/top10	               GET	   Fetches the Top 10 performing stocks

/api/stocks/{ticker}/history	   GET    	Fetches 6 months history for a specific ticker

/api/stocks/{ticker}/analyze	   GET/POST	   Triggers Hugging Face AI to analyze trends



Setup Instructions :

Prerequisites:

Java 17+

Node.js & npm

Hugging Face Access Token



Backend Setup:

Navigate to the realticker1 directory.

Open src/main/resources/application.properties.

Add your Hugging Face Token: huggingface.token=your_token_here

Run the Spring Boot application: mvn spring-boot:run




Frontend Setup:
Navigate to the frontend directory.

Install dependencies:npm install

Start the React development server: npm start




Submission Details :

Developer: Manojkumar


Task: Full-Stack AI Integration Assessment 


Architecture: React Frontend + Spring Boot REST API + Hugging Face AI


