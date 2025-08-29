import React, { useState } from 'react';
import { X } from 'lucide-react';

const DeleteOverlay = ({ user, onClose, onConfirm }) => {
  const [inputValue, setInputValue] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleConfirm = async () => {
    if (inputValue === 'DELETE') {
      setIsLoading(true);
      try {
        // Call the onConfirm function passed from parent
        await onConfirm(user.id);
      } catch (error) {
        console.error('Error deleting user:', error);
        // You could show an error message here
      } finally {
        setIsLoading(false);
        // Close the overlay after successful deletion
        onClose();
      }
    }
  };

  const handleKeyPress = (e) => {
    if (e.key === 'Enter' && inputValue === 'DELETE' && !isLoading) {
      handleConfirm();
    }
  };

  const isDeleteEnabled = inputValue === 'DELETE';

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
      <div className="bg-white rounded-lg max-w-md w-full mx-4 p-6 shadow-2xl">
        <div className="flex justify-between items-center mb-4">
          <h3 className="text-lg font-semibold text-gray-900">Delete User</h3>
          <button 
            onClick={onClose}
            className="text-gray-400 hover:text-gray-600 transition-colors"
            disabled={isLoading}
          >
            <X className="w-5 h-5" />
          </button>
        </div>
        
        <div className="mb-6">
          <div className="flex items-center mb-4">
            <div className={`w-12 h-12 ${user.avatarBg || 'bg-gray-500'} rounded-full flex items-center justify-center`}>
              <span className="text-sm font-medium text-white">{user.avatar}</span>
            </div>
            <div className="ml-3">
              <div className="text-sm font-medium text-gray-900">{user.name}</div>
              <div className="text-sm text-gray-500">{user.email}</div>
            </div>
          </div>
          
          <div className="bg-red-50 border-l-4 border-red-400 p-4 mb-4">
            <div className="flex">
              <div className="ml-3">
                <p className="text-sm text-red-700">
                  <strong>Warning:</strong> This action cannot be undone. This will permanently delete the user account and remove all associated data.
                </p>
              </div>
            </div>
          </div>
          
          <div className="mb-4">
            <label htmlFor="deleteConfirmation" className="block text-sm font-medium text-gray-700 mb-2">
              Please type <strong className="text-red-600">DELETE</strong> to confirm:
            </label>
            <input
              id="deleteConfirmation"
              type="text"
              value={inputValue}
              onChange={(e) => setInputValue(e.target.value)}
              onKeyPress={handleKeyPress}
              className="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-red-500 focus:border-red-500"
              placeholder="Type DELETE here"
              disabled={isLoading}
              autoComplete="off"
            />
          </div>
        </div>
        
        <div className="flex justify-end space-x-3">
          <button
            onClick={onClose}
            className="px-4 py-2 text-sm font-medium text-gray-700 bg-white border border-gray-300 rounded-md hover:bg-gray-50 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-gray-500 transition-colors"
            disabled={isLoading}
          >
            Cancel
          </button>
          <button
            onClick={handleConfirm}
            disabled={!isDeleteEnabled || isLoading}
            className={`px-4 py-2 text-sm font-medium text-white rounded-md focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-red-500 transition-colors ${
              isDeleteEnabled && !isLoading
                ? 'bg-red-600 hover:bg-red-700' 
                : 'bg-gray-300 cursor-not-allowed'
            }`}
          >
            {isLoading ? (
              <div className="flex items-center">
                <div className="w-4 h-4 border-2 border-white border-t-transparent rounded-full animate-spin mr-2"></div>
                Deleting...
              </div>
            ) : (
              'Delete User'
            )}
          </button>
        </div>
      </div>
    </div>
  );
};

export default DeleteOverlay;