import React from 'react';

const StockTable = ({ stocks, onStockClick }) => {
    return (
        <div style={{ padding: '20px' }}>
            <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '20px' }}>
                <thead>
                    <tr style={{ backgroundColor: '#007bff', color: 'white', textAlign: 'left' }}>
                        <th style={{ padding: '12px' }}>Ticker (Click to Analyze)</th>
                        <th style={{ padding: '12px' }}>Company Name</th>
                        <th style={{ padding: '12px' }}>Price</th>
                        <th style={{ padding: '12px' }}>Change</th>
                        <th style={{ padding: '12px' }}>Volume</th>
                    </tr>
                </thead>
                <tbody>
                    {stocks.map((stock) => (
                        <tr key={stock.ticker} style={{ borderBottom: '1px solid #ddd' }}>
                            <td
                                onClick={() => onStockClick(stock.ticker)}
                                style={{
                                    padding: '12px',
                                    fontWeight: 'bold',
                                    color: '#007bff',
                                    cursor: 'pointer',
                                    textDecoration: 'underline'
                                }}
                            >
                                {stock.ticker}
                            </td>
                            <td style={{ padding: '12px' }}>{stock.companyName}</td>

                            {/* Backend-la "price" nu varadhala, inga stock.price nu kudunga */}
                            <td style={{ padding: '12px' }}>
                                ${stock.price}
                            </td>

                            <td style={{ padding: '12px', color: stock.dailyChange >= 0 ? 'green' : 'red' }}>
                                {stock.dailyChange}%
                            </td>

                            {/* Backend-la String-ah varadhala direct-ah display pannunga */}
                            <td style={{ padding: '12px' }}>{stock.volume}</td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
};

export default StockTable;