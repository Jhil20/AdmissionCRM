import React from 'react';
import { X } from 'lucide-react';

const RoleSelectionModal = ({ isOpen, onClose, onSelectRole }) => {
  if (!isOpen) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg p-6 w-96 mx-4">
        <div className="flex items-center justify-between mb-4">
          <h3 className="text-lg font-semibold text-gray-900">Select User Role</h3>
          <button onClick={onClose} className="text-gray-400 hover:text-gray-600">
            <X className="w-5 h-5" />
          </button>
        </div>
        <p className="text-gray-600 mb-6">Choose the role for the new user</p>
        <div className="space-y-3">
          <button
            onClick={() => onSelectRole('INSTITUTE_ADMIN')}
            className="w-full p-4 text-left border border-gray-200 rounded-lg hover:border-green-500 hover:bg-green-50 transition-colors"
          >
            <div className="font-medium text-gray-900">Institute Admin</div>
            <div className="text-sm text-gray-500 mt-1">Manage institutional operations and settings</div>
          </button>
          <button
            onClick={() => onSelectRole('COUNSELOR')}
            className="w-full p-4 text-left border border-gray-200 rounded-lg hover:border-green-500 hover:bg-green-50 transition-colors"
          >
            <div className="font-medium text-gray-900">Counselor</div>
            <div className="text-sm text-gray-500 mt-1">Provide counseling and student support services</div>
          </button>
        </div>
      </div>
    </div>
  );
};

export default RoleSelectionModal;