/**
 * 🏗️  DEVELOPMENT GUIDE - Administration Module Types
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
 * - Add validation schemas using Zod or Yup
 * - Create utility types for API responses (ApiResponse<Administration Module>)
 * - Add enums for status fields or categories
 * - Consider adding computed fields or getters
 * - Add types for search/filter parameters
 * 
 * 💡 Example Extensions:
 * - export enum Administration ModuleStatus { ACTIVE = 'active', INACTIVE = 'inactive' }
 * - export type Administration ModuleSearchParams = Pick<Administration Module, 'name' | 'status'>
 * - export type Administration ModuleUpdateData = Partial<Omit<Administration Module, 'id' | 'createdAt'>>
 */

export interface User {
  id: string;
  username: string;
  passwordHash: string;
  userType: 'admin' | 'teacher' | 'staff';
  createdAt: Date;
  updatedAt: Date;
}

export interface UserFormData {
  username: string;
  password: string;
  userType: 'admin' | 'teacher' | 'staff';
}

export interface SchoolInfo {
  id: string;
  name: string;
  address: string;
  contactEmail: string;
  contactPhone: string;
  principal: string;
  updatedAt: Date;
}

export interface SchoolInfoFormData {
  name: string;
  address: string;
  contactEmail: string;
  contactPhone: string;
  principal: string;
}

export interface LoginRecord {
  id: string;
  userId: string;
  username: string;
  action: 'login' | 'logout';
  ipAddress: string;
  timestamp: Date;
}

export interface LoginTrackingFilter {
  username?: string;
  action?: 'login' | 'logout';
  dateFrom?: Date;
  dateTo?: Date;
}

export interface AdminOptions {
  userManagement: boolean;
  schoolInfoManagement: boolean;
  loginTracking: boolean;
  systemSettings: boolean;
}