/**
 * 🏗️  DEVELOPMENT GUIDE - Administration Module List Component
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
import { useTable } from 'react-table';
import { Module } from '../types/AdministrationModuleTypes';

interface ModuleListProps {
  data: Module[];
  onEdit: (id: string) => void;
  onDelete: (id: string) => void;
}

const ModuleList: React.FC<ModuleListProps> = ({ data, onEdit, onDelete }) => {
  const columns = React.useMemo(
    () => [
      {
        Header: 'Name',
        accessor: 'name',
      },
      {
        Header: 'Description',
        accessor: 'description',
      },
      {
        Header: 'Actions',
        Cell: ({ row }: { row: { original: Module } }) => (
          <div>
            <button onClick={() => onEdit(row.original.id)}>Edit</button>
            <button onClick={() => onDelete(row.original.id)}>Delete</button>
          </div>
        ),
      },
    ],
    [onEdit, onDelete]
  );

  const {
    getTableProps,
    getTableBodyProps,
    headerGroups,
    rows,
    prepareRow,
  } = useTable({ columns, data });

  return (
    <table {...getTableProps()}>
      <thead>
        {headerGroups.map(headerGroup => (
          <tr {...headerGroup.getHeaderGroupProps()}>
            {headerGroup.headers.map(column => (
              <th {...column.getHeaderProps()}>{column.render('Header')}</th>
            ))}
          </tr>
        ))}
      </thead>
      <tbody {...getTableBodyProps()}>
        {rows.map(row => {
          prepareRow(row);
          return (
            <tr {...row.getRowProps()}>
              {row.cells.map(cell => (
                <td {...cell.getCellProps()}>{cell.render('Cell')}</td>
              ))}
            </tr>
          );
        })}
      </tbody>
    </table>
  );
};

export default ModuleList;