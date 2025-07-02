/**
 * 🏗️  DEVELOPMENT GUIDE - Calculator Page Component
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
 * - Add URL-based filtering and search
 * - Implement breadcrumb navigation
 * - Add export/import functionality
 * - Include real-time updates (WebSocket/SSE)
 * - Add keyboard shortcuts for common actions
 * - Implement undo/redo functionality
 * 
 * 💡 State Management Improvements:
 * - Use useReducer for complex state logic
 * - Add optimistic updates for better UX
 * - Implement proper error boundaries
 * - Add loading skeletons instead of spinners
 * 
 * 🔧 User Experience:
 * - Add confirmation dialogs for destructive actions
 * - Implement toast notifications for feedback
 * - Add drag-and-drop for reordering
 * - Include accessibility features (ARIA labels)
 * 
 * 📱 Responsive Design:
 * - Add mobile-specific components
 * - Implement swipe actions for mobile
 * - Consider drawer/modal layouts for small screens
 */

import React, { useState, useEffect } from 'react';
import CalculatorForm from '../components/CalculatorForm';
import CalculatorList from '../components/CalculatorList';
import calculatorService from '../services/calculatorService';
import { Calculation } from '../types/CalculatorTypes';

const CalculatorPage: React.FC = () => {
  const [calculations, setCalculations] = useState<Calculation[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    fetchCalculations();
  }, []);

  const fetchCalculations = async () => {
    try {
      setLoading(true);
      const data = await calculatorService.getAll();
      setCalculations(data);
      setError(null);
    } catch (err) {
      setError('Failed to fetch calculations');
    } finally {
      setLoading(false);
    }
  };

  const handleCreate = async (calculation: Omit<Calculation, 'id'>) => {
    try {
      setLoading(true);
      const newCalculation = await calculatorService.create(calculation);
      setCalculations([...calculations, newCalculation]);
      setError(null);
    } catch (err) {
      setError('Failed to create calculation');
    } finally {
      setLoading(false);
    }
  };

  const handleUpdate = async (id: string, calculation: Omit<Calculation, 'id'>) => {
    try {
      setLoading(true);
      const updatedCalculation = await calculatorService.update(id, calculation);
      setCalculations(calculations.map(c => c.id === id ? updatedCalculation : c));
      setError(null);
    } catch (err) {
      setError('Failed to update calculation');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: string) => {
    try {
      setLoading(true);
      await calculatorService.delete(id);
      setCalculations(calculations.filter(c => c.id !== id));
      setError(null);
    } catch (err) {
      setError('Failed to delete calculation');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Calculator</h1>
      {error && <div className="error">{error}</div>}
      <CalculatorForm onSubmit={handleCreate} loading={loading} />
      {loading ? (
        <div>Loading...</div>
      ) : (
        <CalculatorList 
          calculations={calculations} 
          onUpdate={handleUpdate} 
          onDelete={handleDelete} 
        />
      )}
    </div>
  );
};

export default CalculatorPage;