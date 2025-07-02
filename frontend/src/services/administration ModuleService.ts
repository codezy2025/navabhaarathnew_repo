/**
 * 🏗️  DEVELOPMENT GUIDE - Administration Module Service
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
 * - Add request/response interceptors for error handling
 * - Implement retry logic for failed requests
 * - Add caching layer (React Query, SWR)
 * - Include request cancellation support
 * - Add batch operations (bulkCreate, bulkUpdate)
 * - Implement optimistic updates
 * 
 * 💡 Methods to Consider Adding:
 * - search(query: string): Promise<Administration Module[]>
 * - bulkDelete(ids: string[]): Promise<void>
 * - export(): Promise<Blob>
 * - getStats(): Promise<{Administration ModuleStats}>
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
import { 
    AdministrationModule, 
    CreateAdministrationModuleDto, 
    UpdateAdministrationModuleDto 
} from '../types/AdministrationModuleTypes';

const API_BASE_URL = 'http://localhost:3000/api/administration-modules';

export const administrationModuleService = {
    getAll: async (): Promise<AdministrationModule[]> => {
        const response = await axios.get<AdministrationModule[]>(API_BASE_URL);
        return response.data;
    },

    create: async (data: CreateAdministrationModuleDto): Promise<AdministrationModule> => {
        const response = await axios.post<AdministrationModule>(API_BASE_URL, data);
        return response.data;
    },

    update: async (id: string, data: UpdateAdministrationModuleDto): Promise<AdministrationModule> => {
        const response = await axios.put<AdministrationModule>(`${API_BASE_URL}/${id}`, data);
        return response.data;
    },

    delete: async (id: string): Promise<void> => {
        await axios.delete(`${API_BASE_URL}/${id}`);
    }
};