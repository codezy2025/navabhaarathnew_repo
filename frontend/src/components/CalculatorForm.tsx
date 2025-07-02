/**
 * 🏗️  DEVELOPMENT GUIDE - Calculator Form Component
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
 * - Add form validation with Zod/Yup schema
 * - Implement auto-save functionality
 * - Add file upload capabilities if needed
 * - Include conditional fields based on other inputs
 * - Add form steps/wizard for complex forms
 * - Implement real-time validation feedback
 * 
 * 💡 Props to Consider Adding:
 * - initialData?: Partial<Calculator> (for edit mode)
 * - onCancel?: () => void
 * - isLoading?: boolean
 * - validationSchema?: ZodSchema
 * 
 * 🔧 Libraries to Consider:
 * - @hookform/resolvers for validation
 * - react-hook-form-devtools for debugging
 */

import React from 'react';
import { useForm } from 'react-hook-form';
import { CalculatorFormData, CalculatorResult } from '../types/CalculatorTypes';

interface CalculatorFormProps {
  onSubmit: (data: CalculatorFormData) => CalculatorResult;
}

const CalculatorForm: React.FC<CalculatorFormProps> = ({ onSubmit }) => {
  const { register, handleSubmit, watch, formState: { errors } } = useForm<CalculatorFormData>();
  const [result, setResult] = React.useState<number | null>(null);

  const handleFormSubmit = (data: CalculatorFormData) => {
    const calculationResult = onSubmit(data);
    setResult(calculationResult.result);
  };

  return (
    <div className="calculator-form">
      <form onSubmit={handleSubmit(handleFormSubmit)}>
        <div className="form-group">
          <label htmlFor="number1">Number 1</label>
          <input
            id="number1"
            type="number"
            {...register('number1', { required: 'This field is required' })}
          />
          {errors.number1 && <span className="error">{errors.number1.message}</span>}
        </div>

        <div className="form-group">
          <label htmlFor="number2">Number 2 (for addition)</label>
          <input
            id="number2"
            type="number"
            {...register('number2')}
          />
        </div>

        <div className="button-group">
          <button type="submit" name="operation" value="square">
            Square Number 1
          </button>
          <button type="submit" name="operation" value="add">
            Add Numbers
          </button>
        </div>
      </form>

      {result !== null && (
        <div className="result-display">
          <h3>Result:</h3>
          <p>{result}</p>
        </div>
      )}

      <style jsx>{`
        .calculator-form {
          max-width: 400px;
          margin: 0 auto;
          padding: 20px;
          border: 1px solid #ddd;
          border-radius: 8px;
          background-color: #f9f9f9;
        }
        .form-group {
          margin-bottom: 15px;
        }
        label {
          display: block;
          margin-bottom: 5px;
          font-weight: bold;
        }
        input {
          width: 100%;
          padding: 8px;
          border: 1px solid #ddd;
          border-radius: 4px;
        }
        .error {
          color: red;
          font-size: 0.8rem;
          display: block;
          margin-top: 5px;
        }
        .button-group {
          display: flex;
          gap: 10px;
          margin-top: 20px;
        }
        button {
          padding: 10px 15px;
          background-color: #4CAF50;
          color: white;
          border: none;
          border-radius: 4px;
          cursor: pointer;
          flex: 1;
        }
        button:hover {
          background-color: #45a049;
        }
        .result-display {
          margin-top: 20px;
          padding: 10px;
          background-color: #e8f5e9;
          border-radius: 4px;
        }
        .result-display h3 {
          margin-top: 0;
        }
      `}</style>
    </div>
  );
};

export default CalculatorForm;