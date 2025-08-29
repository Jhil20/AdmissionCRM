import React from 'react';
import { Eye, UserPlus, Check } from 'lucide-react';

const LeadTable = ({ leads, onViewDetails, onAssignCounsellor, onMarkSolved }) => {
  const getStatusColor = (status) => {
    switch (status) {
      case 'new': return 'bg-blue-100 text-blue-800';
      case 'assigned': return 'bg-yellow-100 text-yellow-800';
      case 'solved': return 'bg-green-100 text-green-800';
      default: return 'bg-gray-100 text-gray-800';
    }
  };

  return (
    <div className="bg-white rounded-lg shadow-sm border border-gray-200">
      <table className="w-full table-fixed">
        {/* Table Header */}
        <thead className="bg-gray-50">
          <tr>
            <th className="text-left px-3 py-3 text-xs font-medium text-gray-700 border-b border-gray-200" style={{ width: '20%' }}>
              STUDENT
            </th>
            <th className="text-left px-3 py-3 text-xs font-medium text-gray-700 border-b border-gray-200" style={{ width: '12%' }}>
              SUBJECT
            </th>
            <th className="text-left px-3 py-3 text-xs font-medium text-gray-700 border-b border-gray-200" style={{ width: '16%' }}>
              INTERESTED COURSES
            </th>
            <th className="text-left px-3 py-3 text-xs font-medium text-gray-700 border-b border-gray-200" style={{ width: '10%' }}>
              STATUS
            </th>
            <th className="text-left px-3 py-3 text-xs font-medium text-gray-700 border-b border-gray-200" style={{ width: '20%' }}>
              ASSIGNED TO
            </th>
            <th className="text-left px-3 py-3 text-xs font-medium text-gray-700 border-b border-gray-200" style={{ width: '10%' }}>
              CREATED
            </th>
            <th className="text-left px-3 py-3 text-xs font-medium text-gray-700 border-b border-gray-200" style={{ width: '12%' }}>
              ACTIONS
            </th>
          </tr>
        </thead>

        {/* Table Body */}
        <tbody>
          {leads.map((lead) => (
            <tr key={lead.id} className="hover:bg-gray-50 border-b border-gray-100">
              <td className="px-3 py-3" style={{ width: '20%' }}>
                <div className="text-sm font-medium text-gray-900 truncate">{lead.studentName}</div>
                <div className="text-xs text-gray-500 truncate">{lead.email}</div>
              </td>
              
              <td className="px-3 py-3 text-xs text-gray-700" style={{ width: '12%' }}>
                <div className="truncate">{lead.subject}</div>
              </td>
              
              <td className="px-3 py-3 text-xs text-gray-700" style={{ width: '16%' }}>
                <div className="truncate">{lead.interestedCourses}</div>
              </td>
              
              <td className="px-3 py-3" style={{ width: '10%' }}>
                <span className={`px-2 py-1 rounded-full text-xs font-medium ${getStatusColor(lead.status)}`}>
                  {lead.status.charAt(0).toUpperCase() + lead.status.slice(1)}
                </span>
              </td>
              
              <td className="px-3 py-3" style={{ width: '20%' }}>
                {lead.assignedCounsellor ? (
                  <div>
                    <div className="text-sm font-medium text-gray-900 truncate">{lead.assignedCounsellor.name}</div>
                    <div className="text-xs text-gray-500 truncate">{lead.assignedCounsellor.email}</div>
                  </div>
                ) : (
                  <span className="text-xs text-gray-400">Not assigned</span>
                )}
              </td>
              
              <td className="px-3 py-3 text-xs text-gray-500" style={{ width: '10%' }}>
                <div className="truncate">{new Date(lead.createdAt).toLocaleDateString()}</div>
              </td>
              
              <td className="px-3 py-3" style={{ width: '12%' }}>
                <div className="flex gap-1">
                  <button
                    onClick={() => onViewDetails(lead)}
                    className="p-1 text-blue-500 hover:text-blue-700 hover:bg-blue-50 rounded transition-colors"
                    title="View Details"
                  >
                    <Eye className="w-3 h-3" />
                  </button>
                  
                  {lead.status === 'new' && (
                    <button
                      onClick={() => onAssignCounsellor(lead)}
                      className="p-1 text-green-500 hover:text-green-700 hover:bg-green-50 rounded transition-colors"
                      title="Assign Counsellor"
                    >
                      <UserPlus className="w-3 h-3" />
                    </button>
                  )}
                  
                  {lead.status === 'assigned' && (
                    <button
                      onClick={() => onMarkSolved(lead.id)}
                      className="p-1 text-emerald-500 hover:text-emerald-700 hover:bg-emerald-50 rounded transition-colors"
                      title="Mark as Solved"
                    >
                      <Check className="w-3 h-3" />
                    </button>
                  )}
                </div>
              </td>
            </tr>
          ))}
        </tbody>
      </table>

      {/* Empty State */}
      {leads.length === 0 && (
        <div className="text-center py-8 text-gray-500 text-sm">
          No leads found matching your criteria.
        </div>
      )}
    </div>
  );
};

export default LeadTable;