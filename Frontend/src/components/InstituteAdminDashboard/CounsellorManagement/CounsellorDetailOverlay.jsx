import React from "react";
import { ArrowLeft, X, User, Mail, Phone, Building, Award, FileText } from "lucide-react";

const CounsellorDetailOverlay = ({ counsellor, onClose }) => {
  if (!counsellor) return null;

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
              <h2 className="text-xl font-semibold text-gray-800">Counsellor Details</h2>
              <p className="text-gray-600">View complete counsellor information</p>
            </div>
          </div>
          <button onClick={onClose} className="p-2 hover:bg-gray-100 rounded-lg transition-colors">
            <X className="w-5 h-5 text-gray-600" />
          </button>
        </div>

        <div className="p-6">
          {/* Avatar & Basic Info */}
          <div className="flex items-center space-x-6 mb-8">
            <div className={`${counsellor.avatarBg} rounded-full w-20 h-20 flex items-center justify-center text-2xl font-bold text-white`}>
              {counsellor.avatar}
            </div>
            <div>
              <h3 className="text-2xl font-bold text-gray-800 mb-2">{counsellor.name}</h3>
              <span className="inline-flex px-3 py-1 text-sm font-medium rounded-full bg-blue-100 text-blue-800">
                Counsellor
              </span>
              <div className="flex items-center space-x-4 mt-2">
                <span className={`inline-flex px-2 py-1 text-xs font-semibold rounded-full ${counsellor.status === "Active"
                  ? "bg-green-100 text-green-800"
                  : "bg-red-100 text-red-800"}`}>
                  {counsellor.status}
                </span>
                <span className="text-sm text-gray-600">{counsellor.institute}</span>
              </div>
            </div>
          </div>

          {/* Counsellor Info */}
          <div className="grid grid-cols-2 gap-12">
            {/* Left */}
            <div className="space-y-6">
              <h4 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-2">Basic Information</h4>
              <div className="space-y-4">
                <InfoRow icon={<User className="w-5 h-5" />} label="First Name" value={counsellor.name} />
                <InfoRow icon={<Mail className="w-5 h-5" />} label="Email" value={counsellor.email} />
                <InfoRow icon={<Phone className="w-5 h-5" />} label="Mobile Number" value={counsellor.contact} />
                <InfoRow icon={<User className="w-5 h-5" />} label="Username" value={counsellor.username || "N/A"} />
              </div>
            </div>

            {/* Right */}
            <div className="space-y-6">
              <h4 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-2">Counsellor Details</h4>
              <div className="space-y-4">
                <InfoRow icon={<Award className="w-5 h-5" />} label="Specialization" value={counsellor.specialization || "N/A"} />
                <InfoRow 
                  icon={<FileText className="w-5 h-5" />} 
                  label="Bio" 
                  value={counsellor.bio || "N/A"} 
                  isMultiline={true}
                />
                <InfoRow icon={<Building className="w-5 h-5" />} label="Institute Name" value={counsellor.institute || "N/A"} />
              </div>
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
      <p className={`text-gray-800 text-sm text-left ${isMultiline ? "leading-relaxed" : ""}`}>
        {value || "N/A"}
      </p>
    </div>
  </div>
);

export default CounsellorDetailOverlay;
