/**
 * 🏗️  DEVELOPMENT GUIDE - Administration Module Page Component
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
import { AdministrationModuleForm } from '../components/AdministrationModuleForm';
import { AdministrationModuleList } from '../components/AdministrationModuleList';
import { AdministrationModuleService } from '../services/AdministrationModuleService';
import { AdministrationModule, AdministrationModuleCreate } from '../types/AdministrationModuleTypes';

export const AdministrationModulePage: React.FC = () => {
  const [modules, setModules] = useState<AdministrationModule[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);
  const [selectedModule, setSelectedModule] = useState<AdministrationModule | null>(null);

  useEffect(() => {
    fetchModules();
  }, []);

  const fetchModules = async () => {
    try {
      setLoading(true);
      const data = await AdministrationModuleService.getAll();
      setModules(data);
      setError(null);
    } catch (err) {
      setError('Failed to fetch modules');
    } finally {
      setLoading(false);
    }
  };

  const handleCreate = async (moduleData: AdministrationModuleCreate) => {
    try {
      setLoading(true);
      await AdministrationModuleService.create(moduleData);
      await fetchModules();
      setError(null);
    } catch (err) {
      setError('Failed to create module');
    } finally {
      setLoading(false);
    }
  };

  const handleUpdate = async (id: string, moduleData: AdministrationModuleCreate) => {
    try {
      setLoading(true);
      await AdministrationModuleService.update(id, moduleData);
      await fetchModules();
      setSelectedModule(null);
      setError(null);
    } catch (err) {
      setError('Failed to update module');
    } finally {
      setLoading(false);
    }
  };

  const handleDelete = async (id: string) => {
    try {
      setLoading(true);
      await AdministrationModuleService.delete(id);
      await fetchModules();
      setError(null);
    } catch (err) {
      setError('Failed to delete module');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h1>Administration Module</h1>
      {error && <div className="error">{error}</div>}
      {loading && <div>Loading...</div>}
      <AdministrationModuleForm
        onSubmit={selectedModule ? (data) => handleUpdate(selectedModule.id, data) : handleCreate}
        initialData={selectedModule}
        onCancel={() => setSelectedModule(null)}
      />
      <AdministrationModuleList
        modules={modules}
        onEdit={setSelectedModule}
        onDelete={handleDelete}
      />
    </div>
  );
};