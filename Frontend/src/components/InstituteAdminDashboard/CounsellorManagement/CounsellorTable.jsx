import React from "react";
import { Eye, Edit, Trash2 } from "lucide-react";
import { useState } from "react";
import CounsellorDetailOverlay from "./CounsellorDetailOverlay";
import DeleteOverlay from "./DeleteOverlay"

const CounsellorTable = ({ filteredUsers = [], onDeleteUser }) => {
  const [selectedCounsellor, setSelectedCounsellor] = useState(null);
  const [userToDelete, setUserToDelete] = useState(null);


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
    <div className="bg-white rounded-lg shadow overflow-hidden">
      <table className="w-full text-left border-collapse">
        <thead className="bg-gray-50 border-b">
          <tr>
            <th className="px-6 py-3 text-sm font-medium text-gray-700 uppercase">Counsellor</th>
            <th className="px-6 py-3 text-sm font-medium text-gray-700 uppercase">Role</th>
            <th className="px-6 py-3 text-sm font-medium text-gray-700 uppercase">Contact</th>
            <th className="px-6 py-3 text-sm font-medium text-gray-700 uppercase">Status</th>
            <th className="px-6 py-3 text-sm font-medium text-gray-700 uppercase">Actions</th>
          </tr>
        </thead>

        <tbody className="bg-white divide-y divide-gray-200">
          {filteredUsers.length > 0 ? (
            filteredUsers.map((user, idx) => (
              <tr key={user.id} className="hover:bg-gray-50">
                {/* Name + Email */}
                <td className="px-6 py-4">
                  <div className="flex items-center">
                    {/* Avatar */}
                    <div className={`w-10 h-10 ${user.avatarBg} rounded-full flex items-center justify-center`}>
                      <span className="text-sm font-medium text-white">{user.avatar}</span>
                    </div>

                    {/* Name + Email */}
                    <div className="ml-4">
                      <div className="text-sm font-medium text-gray-900">{user.name}</div>
                      <div className="text-sm text-gray-500">{user.email}</div>
                    </div>
                  </div>
                </td>


                {/* Role - always Counsellor */}
                <td className="px-6 py-4">
                  <span className="inline-flex px-2 py-1 text-xs font-medium rounded-full bg-blue-100 text-blue-800">
                    Counsellor
                  </span>
                </td>

                {/* Contact */}
                <td className="px-6 py-4 text-gray-900">{user.contact}</td>

                {/* Status */}
                <td className="px-6 py-4">
                  <span
                    className={`inline-flex px-2 py-1 text-xs font-medium rounded-full ${user.status === "Active"
                      ? "bg-green-100 text-green-800"
                      : "bg-red-100 text-red-800"
                      }`}
                  >
                    {user.status ? "Active" : "Inactive"}
                  </span>
                </td>

                {/* Actions */}
                <td className="px-6 py-4">
                  <div className="flex items-center space-x-3">
                    <button className="text-green-600 hover:text-green-800"
                      onClick={() => setSelectedCounsellor(user)}>
                      <Eye className="w-4 h-4" />
                    </button>
                    <button className="text-blue-600 hover:text-blue-800">
                      <Edit className="w-4 h-4" />
                    </button>
                    <button 
                    onClick={()=>{handleDeleteClick(user)}}
                    className="text-red-600 hover:text-red-800">
                      <Trash2 className="w-4 h-4" />
                    </button>
                  </div>
                </td>
              </tr>
            ))
          ) : (
            <tr>
              <td colSpan={5} className="text-center py-6 text-gray-500">
                No counsellors found.
              </td>
            </tr>
          )}
        </tbody>
      </table>
      {selectedCounsellor && (
        <CounsellorDetailOverlay
          counsellor={selectedCounsellor}
          onClose={() => setSelectedCounsellor(null)}
        />
      )}

      {userToDelete && (
        <DeleteOverlay
          user={userToDelete}
          onClose={handleCloseDelete}
          onConfirm={handleConfirmDelete}
        />
      )}

    </div>
  );
};

export default CounsellorTable;
