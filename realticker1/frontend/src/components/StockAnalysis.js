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

    return (
        <div style={{ marginTop: '30px', padding: '20px', border: '1px solid #ddd', borderRadius: '12px', backgroundColor: '#fff' }}>
            <h2 style={{ textAlign: 'center', color: '#333' }}>Market Analysis: {ticker}</h2>

            <div style={{ height: '350px', marginBottom: '30px' }}>
                <Line data={chartData} options={options} />
            </div>

            {/* --- AI Insights Section (Updated) --- */}
            <div style={{ padding: '20px', backgroundColor: '#f0fdf4', borderRadius: '8px', borderLeft: '6px solid #22c55e' }}>
                <h4 style={{ margin: '0 0 10px 0', color: '#166534' }}>🤖 Real-Time AI Analysis (Hugging Face)</h4>

                <p style={{ margin: '0', color: '#333', lineHeight: '1.6', fontWeight: '500' }}>
                    {/* Backend-la irundhu vara 'analysis' field-ah inga display pandrom */}
                    {data.analysis ? data.analysis : "AI is analyzing the market trends... please wait."}
                </p>

                <p style={{ marginTop: '15px', fontSize: '11px', color: '#666', fontStyle: 'italic' }}>
                    {/* Backend disclaimer-ah inga kaatureenga */}
                    {data.disclaimer ? data.disclaimer : "Disclaimer: AI insights are for educational purposes."}
                </p>
            </div>
        </div>
    );
};

export default StockAnalysis;