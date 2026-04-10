import React, { useState, useEffect } from 'react';
import './App.css';

function App() {
  const [stocks, setStocks] = useState([]);
  const [selectedStock, setSelectedStock] = useState(null);
  const [analysis, setAnalysis] = useState("");
  const [loading, setLoading] = useState(true);
  const [aiLoading, setAiLoading] = useState(false);

  // 1. Dashboard-kaaga 10 Stocks-ah Fetch pandrom
  useEffect(() => {
    fetch('http://localhost:8080/api/stocks/top10')
      .then(res => res.json())
      .then(data => {
        setStocks(data);
        setLoading(false);
      })
      .catch(err => console.error("Error:", err));
  }, []);

  // 2. Click panna trigger aagura function
  const handleTickerClick = async (ticker) => {
    setAiLoading(true);
    setAnalysis("AI is thinking...");

    try {
      // Step A: Fetch History (GET)
      const historyRes = await fetch(`http://localhost:8080/api/stocks/${ticker}/history`);
      const historyData = await historyRes.json();
      setSelectedStock(historyData);

      // Step B: Trigger AI Analysis (POST)
      const aiRes = await fetch(`http://localhost:8080/api/stocks/${ticker}/analyze`, {
        method: 'POST'
      });
      const aiData = await aiRes.json();
      setAnalysis(aiData.analysis);
    } catch (error) {
      setAnalysis("AI is busy. Please try again.");
    } finally {
      setAiLoading(false);
    }
  };

  return (
    <div className="App" style={{ padding: '20px', fontFamily: 'Arial', backgroundColor: '#f4f7f6', minHeight: '100vh' }}>
      <h1 style={{ textAlign: 'center', color: '#333', marginBottom: '30px', fontSize: '2.5rem' }}>RealTicker AI Dashboard</h1>

      {loading ? <h2 style={{ textAlign: 'center' }}>Loading Market Data...</h2> : (
        <div style={{ display: 'flex', gap: '20px', flexDirection: 'column', alignItems: 'center' }}>

          {/* STOCK TABLE */}
          <div style={{ width: '90%', overflowX: 'auto', borderRadius: '12px', boxShadow: '0 8px 16px rgba(0,0,0,0.1)' }}>
            <table style={{ width: '100%', borderCollapse: 'collapse', backgroundColor: 'white' }}>
              <thead style={{ backgroundColor: '#007bff', color: 'white' }}>
                <tr>
                  <th style={{ padding: '15px' }}>Ticker (Click)</th>
                  <th style={{ padding: '15px' }}>Company</th>
                  <th style={{ padding: '15px' }}>Price</th>
                  <th style={{ padding: '15px' }}>Change</th>
                  <th style={{ padding: '15px' }}>Volume</th> {/* Volume Header Add Panniyachu */}
                </tr>
              </thead>
              <tbody>
                {stocks.map(stock => (
                  <tr key={stock.ticker} style={{ borderBottom: '1px solid #eee', textAlign: 'center' }} className="table-row">
                    <td style={{ padding: '12px' }}>
                      <button
                        onClick={() => handleTickerClick(stock.ticker)}
                        style={{ background: 'none', border: 'none', color: '#007bff', textDecoration: 'underline', cursor: 'pointer', fontWeight: 'bold', fontSize: '1.1rem' }}
                      >
                        {stock.ticker}
                      </button>
                    </td>
                    <td style={{ padding: '12px', color: '#555' }}>{stock.companyName}</td>

                    {/* FIX 1: currentPrice-ah 'price' nu maathiyachu */}
                    <td style={{ padding: '12px', fontWeight: '500' }}>
                        ${stock.price ? stock.price.toFixed(2) : "0.00"}
                    </td>

                    <td style={{ padding: '12px', color: stock.dailyChange >= 0 ? '#28a745' : '#dc3545', fontWeight: 'bold' }}>
                      {stock.dailyChange}%
                    </td>

                    {/* FIX 2: Volume column add panniyachu */}
                    <td style={{ padding: '12px', color: '#555' }}>{stock.volume}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>

          {/* ANALYSIS SECTION */}
          {selectedStock && (
            <div style={{
              marginTop: '40px',
              padding: '35px',
              background: 'linear-gradient(135deg, #e0f2ff 0%, #ffffff 100%)',
              borderRadius: '20px',
              width: '85%',
              boxShadow: '0 15px 30px rgba(0,0,0,0.1)',
              border: '1px solid #b3d7ff',
              borderLeft: '10px solid #007bff',
              textAlign: 'center'
            }}>
              <h2 style={{ color: '#0056b3', marginBottom: '20px', fontSize: '1.8rem', fontWeight: '700' }}>
                Analysis for {selectedStock.ticker}
              </h2>

              <div style={{
                backgroundColor: 'rgba(255, 255, 255, 0.8)',
                padding: '25px',
                borderRadius: '15px',
                fontSize: '1.2rem',
                lineHeight: '1.7',
                color: '#2c3e50',
                boxShadow: 'inset 0 2px 5px rgba(0,0,0,0.05)',
                minHeight: '100px',
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center'
              }}>
                {aiLoading ? (
                  <div style={{ display: 'flex', flexDirection: 'column', alignItems: 'center', gap: '15px' }}>
                     <div className="spinner"></div>
                     <strong style={{ color: '#007bff' }}>AI is deeply analyzing 6 months of trends...</strong>
                  </div>
                ) : (
                  <p style={{ margin: 0, fontWeight: '500' }}>{analysis}</p>
                )}
              </div>

              <p style={{ fontSize: '13px', color: '#7f8c8d', marginTop: '20px', fontWeight: 'bold', borderTop: '1px solid #d1e9ff', paddingTop: '15px' }}>
                * This is AI-generated analysis and not financial advice.
              </p>
            </div>
          )}
        </div>
      )}
    </div>
  );
}

export default App;
