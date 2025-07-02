/**
 * 🏗️  DEVELOPMENT GUIDE - Calculator Service
 * 
 * 📋 Original Requirements: Create a React calculator component with TypeScript that implements the following functionality from calculator.py:
1. A square function that takes a number and returns its square
2. An add function that takes two numbers and returns their sum
3. Input fields for numbers
4. Buttons to trigger calculations
5. Display area for results
6. Proper TypeScript typing for all props and state
7. Clean UI with proper styling
 * 
 * 🚀 Enhancement Ideas:
 * - Add request/response interceptors for error handling
 * - Implement retry logic for failed requests
 * - Add caching layer (React Query, SWR)
 * - Include request cancellation support
 * - Add batch operations (bulkCreate, bulkUpdate)
 * - Implement optimistic updates
 * 
 * 💡 Methods to Consider Adding:
 * - search(query: string): Promise<Calculator[]>
 * - bulkDelete(ids: string[]): Promise<void>
 * - export(): Promise<Blob>
 * - getStats(): Promise<{CalculatorStats}>
 * 
 * 🔧 Error Handling:
 * - Create custom error classes
 * - Add request/response logging
 * - Implement exponential backoff for retries
 * 
 * 🚀 Performance:
 * - Add request deduplication
 * - Implement response caching
 * - Consider using React Query for state management
 */

import axios from 'axios';
import { Calculator, CalculatorInput } from '../types/CalculatorTypes';

const API_BASE_URL = 'http://localhost:3000/api/calculators';

const getAll = async (): Promise<Calculator[]> => {
    const response = await axios.get(API_BASE_URL);
    return response.data;
};

const create = async (calculatorData: CalculatorInput): Promise<Calculator> => {
    const response = await axios.post(API_BASE_URL, calculatorData);
    return response.data;
};

const update = async (id: string, calculatorData: CalculatorInput): Promise<Calculator> => {
    const response = await axios.put(`${API_BASE_URL}/${id}`, calculatorData);
    return response.data;
};

const deleteById = async (id: string): Promise<void> => {
    await axios.delete(`${API_BASE_URL}/${id}`);
};

export const calculatorService = {
    getAll,
    create,
    update,
    delete: deleteById
};