import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api/stocks';

// Stocks list-ah edukka
export const getTop10Stocks = async () => {
    try {
        const response = await axios.get(`${API_BASE_URL}/top10`);
        return response.data;
    } catch (error) {
        console.error("Error fetching stocks:", error);
        return [];
    }
};

// 👈 Indha pudhu function-ah ippo add pannunga (Chart-ku idhu thaan mukkiyam)
export const getStockHistory = async (ticker) => {
    try {
        const response = await axios.get(`${API_BASE_URL}/${ticker}/history`);
        return response.data;
    } catch (error) {
        console.error("Error fetching history:", error);
        return null;
    }
};