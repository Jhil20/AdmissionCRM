import React from 'react';
import { Search, ChevronDown } from 'lucide-react';

const UserFilter = ({ searchTerm, setSearchTerm, selectedRole, setSelectedRole }) => (
  <div className="flex items-center space-x-4 mb-6">
    <div className="flex-1 relative">
      <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
      <input
        type="text"
        placeholder="Search users..."
        className="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 bg-white"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
    </div>

    <div className="relative">
      <select
        className="appearance-none bg-white border border-gray-300 rounded-lg px-4 py-3 pr-10 focus:ring-2 focus:ring-green-500 focus:border-green-500 min-w-[120px]"
        value={selectedRole}
        onChange={(e) => setSelectedRole(e.target.value)}
      >
        <option>All Roles</option>
        <option>Institutional Admin</option>
        <option>Counsellor</option>
      </select>
      <ChevronDown className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4 pointer-events-none" />
    </div>
  </div>
);

export default UserFilter;
