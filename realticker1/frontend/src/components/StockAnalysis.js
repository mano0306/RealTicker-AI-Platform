import React from 'react';
import { Line } from 'react-chartjs-2';
import {
    Chart as ChartJS,
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    Filler
} from 'chart.js';

ChartJS.register(
    CategoryScale,
    LinearScale,
    PointElement,
    LineElement,
    Title,
    Tooltip,
    Legend,
    Filler
);

const StockAnalysis = ({ data, ticker }) => {
    const chartData = {
        labels: data.history.map((_, index) => `Day ${index + 1}`),
        datasets: [
            {
                label: `${ticker} Price Trend (6 Months)`,
                data: data.history,
                borderColor: '#007bff',
                backgroundColor: 'rgba(0, 123, 255, 0.1)',
                fill: true,
                tension: 0.4,
                pointRadius: 0
            }
        ]
    };

    const options = {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
            legend: { position: 'top' },
            title: { display: true, text: `Price History for ${ticker}` }
        },
        scales: {
            y: { beginAtZero: false }
        }
    };

    // --- AI Text Parser Logic ---
    const getAnalysisText = () => {
        if (!data.analysis || data.analysis === "Click analyze for AI insights") {
            return "AI is analyzing the market trends... please wait.";
        }

        try {
            // Backend anuppura response-ah JSON-ah mathuroom
            const parsed = typeof data.analysis === 'string' ? JSON.parse(data.analysis) : data.analysis;

            // Hugging Face output array format-la irukkum [{generated_text: "..."}]
            if (Array.isArray(parsed) && parsed.length > 0 && parsed[0].generated_text) {
                const fullText = parsed[0].generated_text;
                
                // Prompt-ah thookittu badhil-ah mattum edukka "summary." vechu split pandroom
                const parts = fullText.split("summary.");
                return parts.length > 1 ? parts[1].trim() : fullText.trim();
            }
            
            return typeof data.analysis === 'string' ? data.analysis : JSON.stringify(data.analysis);
        } catch (e) {
            // Oru vela normal string-ah vandha adhaiye return pannum
            return data.analysis;
        }
    };

    return (
        <div style={{ marginTop: '30px', padding: '20px', border: '1px solid #ddd', borderRadius: '12px', backgroundColor: '#fff' }}>
            <h2 style={{ textAlign: 'center', color: '#333' }}>Market Analysis: {ticker}</h2>

            <div style={{ height: '350px', marginBottom: '30px' }}>
                <Line data={chartData} options={options} />
            </div>

            {/* --- AI Insights Section (Updated & Fixed) --- */}
            <div style={{ padding: '20px', backgroundColor: '#f0fdf4', borderRadius: '8px', borderLeft: '6px solid #22c55e' }}>
                <h4 style={{ margin: '0 0 10px 0', color: '#166534' }}>🤖 Real-Time AI Analysis (Hugging Face)</h4>

                <p style={{ margin: '0', color: '#333', lineHeight: '1.6', fontWeight: '500' }}>
                    {getAnalysisText()}
                </p>

                <p style={{ marginTop: '15px', fontSize: '11px', color: '#666', fontStyle: 'italic' }}>
                    {data.disclaimer ? data.disclaimer : "Disclaimer: AI insights are for educational purposes."}
                </p>
            </div>
        </div>
    );
};

export default StockAnalysis;