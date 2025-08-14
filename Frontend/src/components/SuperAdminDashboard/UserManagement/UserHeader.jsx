import React from 'react';
import { Plus } from 'lucide-react';

const UserHeader = ({ onAddUser }) => (
  <div className="flex items-center justify-between mb-4">
    <div>
      <h2 className="text-2xl font-bold text-green-600 ml-[-106px]">User Management</h2>
      <p className="text-gray-600 mt-1">Manage institutional admins and counsellors</p>
    </div>
    <button 
      onClick={onAddUser}
      className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg flex items-center space-x-2 transition-colors"
    >
      <Plus className="w-4 h-4" />
      <span>Add User</span>
    </button>
  </div>
);

export default UserHeader;