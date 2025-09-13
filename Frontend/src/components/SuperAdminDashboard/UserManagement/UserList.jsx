import React, { useState } from 'react';
import { Eye, Edit, Trash2 } from 'lucide-react';
import DeleteOverlay from './DeleteOverlay';
import UserDetailOverlay from './UserDetailOverlay';

const UserList = ({ filteredUsers, onDeleteUser }) => {
  const [selectedUser, setSelectedUser] = useState(null);
  const [userToDelete, setUserToDelete] = useState(null);

  const handleViewUser = (user) => {
    setSelectedUser(user);
  };

  const handleCloseDetail = () => {
    setSelectedUser(null);
  };

  const handleDeleteClick = (user) => {
    setUserToDelete(user);
  };

  const handleCloseDelete = () => {
    setUserToDelete(null);
  }

  const handleConfirmDelete = async (userId) => {
    if (userToDelete && onDeleteUser) {
      try {
        // Call the delete API or function here
        if (onDeleteUser) {
          await onDeleteUser(userId);
        }
        setUserToDelete(null);
      } catch (error) {
        console.error('Error deleting user:', error);
      }
    }
  };

  return (
    <>
      <div className="bg-white rounded-lg shadow overflow-hidden">
        <table className="w-full">
          <thead className="bg-gray-50 border-b">
            <tr>
              <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">USER</th>
              <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">ROLE</th>
              <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">INSTITUTE</th>
              <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">CONTACT</th>
              <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">STATUS</th>
              <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">ACTIONS</th>
            </tr>
          </thead>
          <tbody className="bg-white divide-y divide-gray-200">
            {filteredUsers.map((user,idx) => (
              <tr key={user.id} className="hover:bg-gray-50">
                <td className="px-6 py-4">
                  <div className="flex items-center">
                    <div className={`w-10 h-10 ${user.avatarBg} rounded-full flex items-center justify-center`}>
                      <span className="text-sm font-medium text-white">{user.avatar}</span>
                    </div>
                    <div className="ml-4">
                      <div className="text-sm font-medium text-gray-900">{user.firstName}</div>
                      <div className="text-sm text-gray-500">{user.email}</div>
                    </div>
                  </div>
                </td>
                <td className="px-6 py-4">
                  <span className={`inline-flex px-3 py-1 text-xs font-medium rounded-full ${
                    user.role === 'Institutional Admin' 
                      ? 'bg-green-100 text-green-800' 
                      : 'bg-blue-100 text-blue-800'
                  }`}>
                    {user.role}
                  </span>
                </td>
                <td className="px-6 py-4 text-sm text-gray-900">{user.institute}</td>
                <td className="px-6 py-4 text-sm text-gray-900">{user.contact}</td>
                <td className="px-6 py-4">
                  <span className="inline-flex px-3 py-1 text-xs font-medium rounded-full bg-green-100 text-green-800">
                    {user.status}
                  </span>
                </td>
                <td className="px-6 py-4">
                  <div className="flex items-center space-x-3">
                    <button 
                      onClick={() => handleViewUser(user)}
                      className="text-green-600 hover:text-green-800 transition-colors"
                    >
                      <Eye className="w-4 h-4" />
                    </button>
                    <button className="text-blue-600 hover:text-blue-800 transition-colors">
                      <Edit className="w-4 h-4" />
                    </button>
                    <button 
                    onClick={()=>{handleDeleteClick(user)}}
                    className="text-red-600 hover:text-red-800 transition-colors">
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </div>
                </td>
              </tr>
            ))}
          </tbody>
        </table>

        {filteredUsers.length === 0 && (
          <div className="text-center py-12">
            <div className="text-gray-500">No users found matching your criteria.</div>
          </div>
        )}
      </div>

      {/* User Detail Overlay */}
      {selectedUser && (
        <UserDetailOverlay user={selectedUser} onClose={handleCloseDetail} />
      )}

      {userToDelete && (
        <DeleteOverlay
          user={userToDelete}
          onClose={handleCloseDelete}
          onConfirm={handleConfirmDelete}
        />
      )}
    </>
  );
};

export default UserList;