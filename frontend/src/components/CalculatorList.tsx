/**
 * 🏗️  DEVELOPMENT GUIDE - Calculator List Component
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
 * - Add search/filter functionality
 * - Implement sorting for all columns
 * - Add bulk operations (delete, update status)
 * - Include export functionality (CSV, PDF)
 * - Add infinite scrolling or virtual scrolling
 * - Implement row selection with checkboxes
 * 
 * 💡 Props to Consider Adding:
 * - searchTerm?: string
 * - filters?: Record<string, any>
 * - sortConfig?: { key: string, direction: 'asc' | 'desc' }
 * - isLoading?: boolean
 * - onBulkAction?: (action: string, ids: string[]) => void
 * 
 * 🔧 Libraries to Consider:
 * - @tanstack/react-table for advanced features
 * - react-window for virtualization
 * - fuse.js for fuzzy search
 */

import React from 'react';
import { Calculator } from '../types/CalculatorTypes';

interface CalculatorListProps {
  data: Calculator[];
  onEdit: (id: string) => void;
  onDelete: (id: string) => void;
}

const CalculatorList: React.FC<CalculatorListProps> = ({ data, onEdit, onDelete }) => {
  return (
    <table className="min-w-full divide-y divide-gray-200">
      <thead className="bg-gray-50">
        <tr>
          <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            ID
          </th>
          <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Name
          </th>
          <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">
            Actions
          </th>
        </tr>
      </thead>
      <tbody className="bg-white divide-y divide-gray-200">
        {data.map((calculator) => (
          <tr key={calculator.id}>
            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
              {calculator.id}
            </td>
            <td className="px-6 py-4 whitespace-nowrap text-sm text-gray-500">
              {calculator.name}
            </td>
            <td className="px-6 py-4 whitespace-nowrap text-sm font-medium">
              <button
                onClick={() => onEdit(calculator.id)}
                className="text-indigo-600 hover:text-indigo-900 mr-4"
              >
                Edit
              </button>
              <button
                onClick={() => onDelete(calculator.id)}
                className="text-red-600 hover:text-red-900"
              >
                Delete
              </button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};

export default CalculatorList;