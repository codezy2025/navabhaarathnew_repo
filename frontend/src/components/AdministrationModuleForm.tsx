/**
 * 🏗️  DEVELOPMENT GUIDE - Administration Module Form Component
 * 
 * 📋 Original Requirements: Generate React TSX files for the following components based on the provided documentation:

1. User Management:
   - UserList (Form26) - A table displaying all users with edit/delete actions
   - UserForm (Form27) - A form for adding/editing users with fields for username, password, and user type

2. School Information Management (Form22):
   - A form to edit school name, address, contact info, and principal

3. Login Tracking (Form25):
   - A table showing login/logout history with filtering capabilities

4. Administrator Options Panel (Form23):
   - A dashboard with buttons linking to all admin functions

Include proper TypeScript interfaces for all data structures and implement the described functionality while addressing the security concerns mentioned (like plaintext password storage).
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
 * - initialData?: Partial<Administration Module> (for edit mode)
 * - onCancel?: () => void
 * - isLoading?: boolean
 * - validationSchema?: ZodSchema
 * 
 * 🔧 Libraries to Consider:
 * - @hookform/resolvers for validation
 * - react-hook-form-devtools for debugging
 */

import React from 'react';
import { useForm, SubmitHandler } from 'react-hook-form';
import { AdministrationModuleFormData } from '../types/AdministrationModuleTypes';

interface AdministrationModuleFormProps {
  onSubmit: SubmitHandler<AdministrationModuleFormData>;
}

const AdministrationModuleForm: React.FC<AdministrationModuleFormProps> = ({ onSubmit }) => {
  const { register, handleSubmit, formState: { errors } } = useForm<AdministrationModuleFormData>();

  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      {/* User Management Fields */}
      <div>
        <label htmlFor="username">Username</label>
        <input
          id="username"
          type="text"
          {...register('username', { required: 'Username is required' })}
        />
        {errors.username && <span>{errors.username.message}</span>}
      </div>

      <div>
        <label htmlFor="password">Password</label>
        <input
          id="password"
          type="password"
          {...register('password', { required: 'Password is required' })}
        />
        {errors.password && <span>{errors.password.message}</span>}
      </div>

      <div>
        <label htmlFor="userType">User Type</label>
        <select
          id="userType"
          {...register('userType', { required: 'User type is required' })}
        >
          <option value="">Select user type</option>
          <option value="admin">Admin</option>
          <option value="teacher">Teacher</option>
          <option value="student">Student</option>
        </select>
        {errors.userType && <span>{errors.userType.message}</span>}
      </div>

      {/* School Information Fields */}
      <div>
        <label htmlFor="schoolName">School Name</label>
        <input
          id="schoolName"
          type="text"
          {...register('schoolName')}
        />
      </div>

      <div>
        <label htmlFor="address">Address</label>
        <input
          id="address"
          type="text"
          {...register('address')}
        />
      </div>

      <div>
        <label htmlFor="contactInfo">Contact Info</label>
        <input
          id="contactInfo"
          type="text"
          {...register('contactInfo')}
        />
      </div>

      <div>
        <label htmlFor="principal">Principal</label>
        <input
          id="principal"
          type="text"
          {...register('principal')}
        />
      </div>

      <button type="submit">Submit</button>
    </form>
  );
};

export default AdministrationModuleForm;