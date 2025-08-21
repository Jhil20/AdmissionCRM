import React from 'react';
import { Search } from 'lucide-react';

const LeadHeader = ({ searchTerm, setSearchTerm, statusFilter, setStatusFilter }) => {
  return (
    <>
      {/* Title Section */}
      <div className="mb-6">
        <h1 className="text-2xl font-bold text-green-600">Lead Management</h1>
        <p className="text-gray-600">Manage student inquiries and assign to counsellors</p>
      </div>

      {/* Filters Section */}
      <div className="flex gap-4 mb-6">
        <div className="flex-1 relative">
          <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
          <input
            type="text"
            placeholder="Search leads..."
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
            className="w-full pl-10 pr-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 focus:border-transparent"
          />
        </div>
        <select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
          className="px-4 py-2 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-green-500 min-w-[150px]"
        >
          <option>All Status</option>
          <option>New</option>
          <option>Assigned</option>
          <option>Solved</option>
        </select>
      </div>
    </>
  );
};

export default LeadHeader;
