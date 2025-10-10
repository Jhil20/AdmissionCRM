// QueryHeader.jsx
import React from 'react';
import { Plus } from 'lucide-react';

const QueryHeader = ({ onAddQuery }) => (
 <div className="flex items-center justify-between mb-4">
  <div className="flex flex-col items-start">
    <h2 className="text-2xl font-bold text-green-600">Queries</h2>
    <p className="text-gray-600 mt-1">Manage your course and admission queries</p>
  </div>
  <button 
    onClick={onAddQuery}
    className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg flex items-center space-x-2 transition-colors"
  >
    <Plus className="w-4 h-4" />
    <span>Ask Query</span>
  </button>
</div>
);

export default QueryHeader;