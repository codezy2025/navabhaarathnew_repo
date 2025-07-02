/**
 * 🏗️  DEVELOPMENT GUIDE - Calculator Types
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
 * - Add validation schemas using Zod or Yup
 * - Create utility types for API responses (ApiResponse<Calculator>)
 * - Add enums for status fields or categories
 * - Consider adding computed fields or getters
 * - Add types for search/filter parameters
 * 
 * 💡 Example Extensions:
 * - export enum CalculatorStatus { ACTIVE = 'active', INACTIVE = 'inactive' }
 * - export type CalculatorSearchParams = Pick<Calculator, 'name' | 'status'>
 * - export type CalculatorUpdateData = Partial<Omit<Calculator, 'id' | 'createdAt'>>
 */

export interface Calculator {
  square: (num: number) => number;
  add: (num1: number, num2: number) => number;
}

export interface CalculatorFormData {
  firstNumber: string;
  secondNumber: string;
  result: string;
}