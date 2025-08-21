import React, { useState } from 'react';

const LeadModal = ({ type, lead, counsellors, onAssign, onClose }) => {
  const [selectedCounsellor, setSelectedCounsellor] = useState(null);

  const getStatusColor = (status) => {
    switch (status) {
      case 'new': return 'bg-blue-100 text-blue-800';
      case 'assigned': return 'bg-yellow-100 text-yellow-800';
      case 'solved': return 'bg-green-100 text-green-800';
      default: return 'bg-gray-100 text-gray-800';
    }
  };

  const handleAssign = () => {
    if (selectedCounsellor) {
      onAssign(lead.id, selectedCounsellor.id);
    }
  };

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-full max-w-2xl mx-4">
        {type === 'assign' ? (
          <>
            <h2 className="text-xl font-bold text-gray-800 mb-4">Assign Counsellor</h2>
            
            <div className="mb-4 p-4 bg-gray-50 rounded-lg">
              <h3 className="font-medium text-gray-800">Lead Details:</h3>
              <p className="text-sm text-gray-600">Student: {lead.studentName}</p>
              <p className="text-sm text-gray-600">Subject: {lead.subject}</p>
              <p className="text-sm text-gray-600">Courses: {lead.interestedCourses}</p>
            </div>

            <h3 className="font-medium text-gray-800 mb-3">Select Counsellor:</h3>
            
            <div className="space-y-3 mb-6 max-h-60 overflow-y-auto">
              {counsellors.map((counsellor) => (
                <div
                  key={counsellor.id}
                  className={`p-4 border rounded-lg cursor-pointer transition-colors ${
                    selectedCounsellor?.id === counsellor.id
                      ? 'border-green-500 bg-green-50'
                      : 'border-gray-200 hover:border-green-300'
                  }`}
                  onClick={() => setSelectedCounsellor(counsellor)}
                >
                  <div className="flex items-center justify-between">
                    <div>
                      <h4 className="font-medium text-gray-800">{counsellor.name}</h4>
                      <p className="text-sm text-gray-600">{counsellor.email}</p>
                      <p className="text-sm text-green-600 font-medium">{counsellor.specialization}</p>
                    </div>
                    <div className="w-4 h-4">
                      {selectedCounsellor?.id === counsellor.id && (
                        <div className="w-4 h-4 bg-green-500 rounded-full"></div>
                      )}
                    </div>
                  </div>
                </div>
              ))}
            </div>

            <div className="flex gap-3 justify-end">
              <button 
                onClick={onClose} 
                className="px-4 py-2 text-gray-600 border border-gray-300 rounded-lg hover:bg-gray-50 transition-colors"
              >
                Cancel
              </button>
              <button
                onClick={handleAssign}
                disabled={!selectedCounsellor}
                className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-colors disabled:opacity-50 disabled:cursor-not-allowed"
              >
                Assign Counsellor
              </button>
            </div>
          </>
        ) : (
          <>
            <h2 className="text-xl font-bold text-gray-800 mb-6 text-left">Lead Details</h2>
            
            <div className="space-y-4 text-left">
              <div className="flex gap-8">
                <div className="flex-1">
                  <label className="block text-sm font-medium text-gray-700 mb-1">Student Name</label>
                  <p className="text-gray-800">{lead.studentName}</p>
                </div>
                <div className="flex-1">
                  <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
                  <p className="text-gray-800">{lead.email}</p>
                </div>
              </div>
              
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Subject</label>
                <p className="text-gray-800">{lead.subject}</p>
              </div>
              
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Interested Courses</label>
                <p className="text-gray-800">{lead.interestedCourses}</p>
              </div>
              
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">Description</label>
                <p className="text-gray-800 bg-gray-50 p-3 rounded-lg">{lead.description}</p>
              </div>
              
              <div className="flex gap-8">
                <div className="flex-1">
                  <label className="block text-sm font-medium text-gray-700 mb-1">Status</label>
                  <span className={`inline-block px-2 py-1 rounded-full text-xs font-medium ${getStatusColor(lead.status)}`}>
                    {lead.status.charAt(0).toUpperCase() + lead.status.slice(1)}
                  </span>
                </div>
                <div className="flex-1">
                  <label className="block text-sm font-medium text-gray-700 mb-1">Created Date</label>
                  <p className="text-gray-800">{new Date(lead.createdAt).toLocaleDateString()}</p>
                </div>
              </div>
              
              {lead.assignedCounsellor && (
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">Assigned Counsellor</label>
                  <div className="bg-gray-50 p-3 rounded-lg">
                    <p className="font-medium text-gray-800">{lead.assignedCounsellor.name}</p>
                    <p className="text-sm text-gray-600">{lead.assignedCounsellor.email}</p>
                  </div>
                </div>
              )}
            </div>

            <div className="flex justify-end mt-6">
              <button 
                onClick={onClose} 
                className="px-4 py-2 bg-green-600 text-white rounded-lg hover:bg-green-700 transition-colors"
              >
                Close
              </button>
            </div>
          </>
        )}
      </div>
    </div>
  );
};

export default LeadModal;