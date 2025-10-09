import React from 'react';
import { MessageSquare, Plus } from 'lucide-react';

const QueryEmptyTable = ({ onAskQuery }) => {
  return (
    <div className="flex-1 bg-gray-50 overflow-auto">
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="flex items-center justify-between p-6">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Queries</h1>
            <p className="text-gray-600 mt-1">Manage your course and admission queries</p>
          </div>
          <button
            onClick={onAskQuery}
            className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg font-medium transition-colors inline-flex items-center space-x-2"
          >
            <Plus className="w-5 h-5" />
            <span>Ask Query</span>
          </button>
        </div>
      </div>

      {/* Empty Table */}
      <div className="p-6">
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 overflow-hidden">
          <div className="overflow-x-auto">
            <table className="w-full">
              <thead className="bg-gray-50 border-b border-gray-200">
                <tr>
                  <th className="text-left p-4 font-semibold text-gray-900">Subject</th>
                  <th className="text-left p-4 font-semibold text-gray-900">Assigned To</th>
                  <th className="text-left p-4 font-semibold text-gray-900">Status</th>
                  <th className="text-left p-4 font-semibold text-gray-900">Institute</th>
                  <th className="text-left p-4 font-semibold text-gray-900">Date</th>
                  <th className="text-left p-4 font-semibold text-gray-900">Action</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td colSpan="6" className="p-0">
                    <div className="text-center py-16 px-6">
                      <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-6">
                        <MessageSquare className="w-8 h-8 text-gray-400" />
                      </div>
                      <h3 className="text-lg font-medium text-gray-900 mb-2">No Queries Yet</h3>
                      <p className="text-gray-600 mb-6">You haven't submitted any queries yet.</p>
                      <button
                        onClick={onAskQuery}
                        className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-lg font-medium transition-colors inline-flex items-center space-x-2"
                      >
                        <Plus className="w-5 h-5" />
                        <span>Ask Your First Query</span>
                      </button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default QueryEmptyTable;