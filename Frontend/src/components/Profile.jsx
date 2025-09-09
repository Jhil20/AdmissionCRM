import React, { useState, useEffect } from "react";
import { ArrowLeft, Edit3, Save, User, Mail, Lock, Phone, X } from "lucide-react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const Profile = () => {
  const navigate = useNavigate();
  const [isEditing, setIsEditing] = useState(false);
  const [loading, setSaving] = useState(false);
  const selector = useSelector((state) => state.authSlice.auth);

  // ✅ Fetch role from localStorage (set after login)
  const storedRole = selector.userData.role; 
  // Example: "SUPER_ADMIN" | "INSTITUTE_ADMIN" | "COUNSELLOR"

  // ✅ In real app, you should fetch this user data from backend using token
  const [userData, setUserData] = useState({
    firstName: "Kush",
    username: "kush123",
    email: "kush@gmail.com",
    mobileNumber: "8967564590",
    password: "********",
    role: storedRole , // fallback
    designation: "HOD",
    alternateEmail: "kush0@gmail.com",
    instituteName: "IIIM",
    specialization: "Career Counselling",
    bio: "Helping students choose the right career path.",
  });

  const [editData, setEditData] = useState(userData);

  // ✅ Keep role synced if localStorage changes
  useEffect(() => {
    setUserData((prev) => ({ ...prev, role: storedRole }));
  }, [storedRole]);

  const handleInputChange = (field, value) => {
    setEditData((prev) => ({ ...prev, [field]: value }));
  };

  const handleSave = () => {
    setSaving(true);
    setTimeout(() => {
      setUserData(editData);
      setIsEditing(false);
      setSaving(false);
      alert("Profile updated!");
    }, 1000);
  };

  const handleCancel = () => {
    setEditData(userData); // Reset to original data
    setIsEditing(false);
  };

  const ProfileField = ({ icon: Icon, label, value, field, type = "text", isTextarea = false }) => (
    <div className="flex items-start space-x-3 mb-6">
      <Icon size={20} className="text-gray-500 mt-1 flex-shrink-0" />
      <div className="flex-1">
        <label className="block text-sm font-medium text-gray-700 mb-1 text-left">
          {label}
        </label>
        {isEditing ? (
          isTextarea ? (
            <textarea
              value={editData[field]}
              onChange={(e) => handleInputChange(field, e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent text-left"
              rows="3"
            />
          ) : (
            <input
              type={type}
              value={editData[field]}
              onChange={(e) => handleInputChange(field, e.target.value)}
              className="w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-blue-500 focus:border-transparent text-left"
            />
          )
        ) : (
          <p className="text-gray-900 text-left">{value}</p>
        )}
      </div>
    </div>
  );

  return (
    <div className="p-6 space-y-6">
      {/* Header */}
      <div className="flex items-center justify-between">
        <button
          onClick={() => navigate(-1)}
          className="p-2 hover:bg-gray-100 rounded-lg"
        >
          <ArrowLeft size={20} className="text-gray-600" />
        </button>
        <h1 className="text-2xl font-bold">User Profile</h1>
        {!isEditing ? (
          <button
            onClick={() => setIsEditing(true)}
            className="bg-green-500 text-white px-4 py-2 rounded-lg flex items-center hover:bg-green-600 transition-colors"
          >
            <Edit3 size={16} className="mr-2" /> Edit
          </button>
        ) : (
          <div className="flex space-x-2">
            <button
              onClick={handleCancel}
              className="bg-gray-500 text-white px-4 py-2 rounded-lg flex items-center hover:bg-gray-600 transition-colors"
            >
              <X size={16} className="mr-2" />
              Cancel
            </button>
            <button
              onClick={handleSave}
              className="bg-green-500 text-white px-4 py-2 rounded-lg flex items-center hover:bg-green-600 transition-colors"
              disabled={loading}
            >
              <Save size={16} className="mr-2" />
              {loading ? "Saving..." : "Save"}
            </button>
          </div>
        )}
      </div>

      {/* Profile Content */}
      <div className="bg-white rounded-lg shadow-sm border border-gray-200">
        {/* Two Column Layout */}
        <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 p-8">
          {/* Left Column - Basic Information */}
          <div>
            <h3 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-3 mb-6 text-left">
              Basic Information
            </h3>
            
            <ProfileField
              icon={User}
              label="First Name"
              value={userData.firstName}
              field="firstName"
            />
            
            <ProfileField
              icon={Mail}
              label="Email"
              value={userData.email}
              field="email"
              type="email"
            />
            
            <ProfileField
              icon={Lock}
              label="Password"
              value={userData.password}
              field="password"
              type="password"
            />
            
            <ProfileField
              icon={Phone}
              label="Mobile Number"
              value={userData.mobileNumber}
              field="mobileNumber"
              type="tel"
            />
            
            <ProfileField
              icon={User}
              label="Username"
              value={userData.username}
              field="username"
            />
          </div>

          {/* Right Column - Role Specific Information */}
          <div>
            {userData.role === "INSTITUTE_ADMIN" && (
              <>
                <h3 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-3 mb-6 text-left">
                  Administrative Details
                </h3>
                
                <ProfileField
                  icon={User}
                  label="Designation"
                  value={userData.designation}
                  field="designation"
                />
                
                <ProfileField
                  icon={Mail}
                  label="Alternate Email"
                  value={userData.alternateEmail}
                  field="alternateEmail"
                  type="email"
                />
                
                <div className="flex items-start space-x-3 mb-6">
                  <User size={20} className="text-gray-500 mt-1 flex-shrink-0" />
                  <div className="flex-1">
                    <label className="block text-sm font-medium text-gray-700 mb-1 text-left">
                      Institute Name
                    </label>
                    <p className="text-gray-900 text-left">{userData.instituteName} </p>
                  </div>
                </div>
              </>
            )}

            {userData.role === "COUNSELLOR" && (
              <>
                <h3 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-3 mb-6 text-left">
                  Counsellor Details
                </h3>
                
                <ProfileField
                  icon={User}
                  label="Specialization"
                  value={userData.specialization}
                  field="specialization"
                />
                
                <ProfileField
                  icon={User}
                  label="Bio"
                  value={userData.bio}
                  field="bio"
                  isTextarea={true}
                />
                
                <div className="flex items-start space-x-3 mb-6">
                  <User size={20} className="text-gray-500 mt-1 flex-shrink-0" />
                  <div className="flex-1">
                    <label className="block text-sm font-medium text-gray-700 mb-1 text-left">
                      Institute Name
                    </label>
                    <p className="text-gray-900 text-left">{userData.instituteName} </p>
                  </div>
                </div>
              </>
            )}

            {userData.role === "SUPER_ADMIN" && (
              <>
                <h3 className="text-lg font-semibold text-gray-800 border-b border-gray-200 pb-3 mb-6 text-left">
                  Administrator Details
                </h3>
                <div className="flex items-center justify-center h-32 text-gray-500">
                  <p>Super Admin - No additional details required</p>
                </div>
              </>
            )}
          </div>
        </div>
      </div>
    </div>
  );
};

export default Profile