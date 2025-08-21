import React from 'react';
import { ArrowLeft, X, User, Mail, Phone, Building, Award, FileText } from 'lucide-react';

const UserDetailOverlay = ({ user, onClose }) => {
  if (!user) return null;

  return (
    <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4">
      <div className="bg-white rounded-lg shadow-xl max-w-6xl w-full max-h-[90vh] overflow-y-auto">
        {/* Header */}
        <div className="flex items-center justify-between p-6 border-b">
          <div className="flex items-center space-x-4">
            <button onClick={onClose} className="p-2 hover:bg-gray-100 rounded-lg transition-colors">
              <ArrowLeft className="w-5 h-5 text-gray-600" />
            </button>
            <div>
              <h2 className="text-xl font-semibold text-gray-800">User Details</h2>
              <p className="text-gray-600">View complete user information</p>
            </div>
          </div>
          <button onClick={onClose} className="p-2 hover:bg-gray-100 rounded-lg transition-colors">
            <X className="w-5 h-5 text-gray-600" />
          </button>
        </div>

        <div className="p-6">
          {/* Avatar & Basic Info */}
          <div className="flex items-center space-x-6 mb-8">
            <div className={`${user.avatarBg} rounded-full w-20 h-20 flex items-center justify-center text-2xl font-bold text-white`}>
              {user.avatar}
            </div>
            <div>
              <h3 className="text-2xl font-bold text-gray-800 mb-2">{user.name}</h3>
              <span className={`inline-flex px-3 py-1 text-sm font-medium rounded-full ${
                user.role === 'Institutional Admin' 
                  ? 'bg-green-100 text-green-800' 
                  : 'bg-blue-100 text-blue-800'
              }`}>
                {user.role}
              </span>
              <div className="flex items-center space-x-4 mt-2">
                <span className="inline-flex px-2 py-1 text-xs font-semibold rounded-full bg-green-100 text-green-800">
                  {user.status}
                </span>
                <span className="text-sm text-gray-600">{user.institute}</span>
              </div>
            </div>
          </div>

          {/* Info Sections */}
          <div className="grid grid-cols-2 gap-12">
            {/* Left - Basic Information */}
            <div className="space-y-6">
              <h4 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-2">Basic Information</h4>
              <div className="space-y-4">
                <InfoRow icon={<User className="w-5 h-5" />} label="First Name" value={user.firstName || user.name} />
                <InfoRow icon={<User className="w-5 h-5" />} label="Last Name" value={user.lastName || ''} />
                <InfoRow icon={<Mail className="w-5 h-5" />} label="Email" value={user.email} />
                <InfoRow icon={<Building className="w-5 h-5" />} label="Password" value="••••••••" />
                <InfoRow icon={<Phone className="w-5 h-5" />} label="Mobile Number" value={user.contact} />
                <InfoRow icon={<User className="w-5 h-5" />} label="Username" value={user.username || `${user.name.toLowerCase()}_${user.role.toLowerCase().replace(' ', '_')}`} />
              </div>
            </div>

            {/* Right - Role-Specific Details */}
            <div className="space-y-6">
              {user.role === "Institutional Admin" ? (
                <>
                  <h4 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-2">Administrative Details</h4>
                  <div className="space-y-4">
                    <InfoRow icon={<Award className="w-5 h-5" />} label="Designation" value={user.designation || 'Principal'} />
                    <InfoRow icon={<Mail className="w-5 h-5" />} label="Alternate Email" value={user.alternateEmail || `${user.name.toLowerCase()}.admin@${user.institute.toLowerCase()}.edu`} />
                    <InfoRow icon={<Building className="w-5 h-5" />} label="Institute Name" value={user.institute || 'Charotar University of Science and Technology'} />
                  </div>
                </>
              ) : (
                <>
                  <h4 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-2">Counsellor Details</h4>
                  <div className="space-y-4">
                    <InfoRow icon={<Award className="w-5 h-5" />} label="Specialization" value={user.specialization || 'Career Guidance & Academic Planning'} />
                    <InfoRow 
                      icon={<FileText className="w-5 h-5" />} 
                      label="Bio" 
                      value={user.bio || 'Experienced educational counsellor focused on student guidance and academic planning.'} 
                      isMultiline={true}
                    />
                    <InfoRow icon={<Building className="w-5 h-5" />} label="Institute Name" value={user.instituteName || 'Charotar University of Science and Technology'} />
                  </div>
                </>
              )}
            </div>
          </div>

          {/* Close Button */}
          <div className="flex justify-center mt-8 pt-6 border-t border-gray-200">
            <button 
              onClick={onClose} 
              className="px-8 py-2 bg-green-500 text-white font-medium rounded-lg hover:bg-green-600 transition-colors"
            >
              Close
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

const InfoRow = ({ icon, label, value, isMultiline = false }) => (
  <div className="flex space-x-3">
    <div className="text-gray-400 mt-0.5 flex-shrink-0">{icon}</div>
    <div className="flex-grow min-w-0">
      <label className="block text-sm font-medium text-gray-600 mb-1 text-left">{label}</label>
      <p className={`text-gray-800 text-sm text-left ${isMultiline ? 'leading-relaxed' : ''}`}>
        {value || 'N/A'}
      </p>
    </div>
  </div>
);

export default UserDetailOverlay;