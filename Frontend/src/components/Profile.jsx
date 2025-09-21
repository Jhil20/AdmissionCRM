import React, { useState, useEffect } from "react";
import {
    ArrowLeft,
    Edit3,
    Save,
    User,
    Mail,
    Lock,
    Phone,
    X,
    Loader2,
} from "lucide-react";
import { useNavigate } from "react-router-dom";
import { useSelector } from "react-redux";

const Profile = () => {
    const navigate = useNavigate();
    const [isEditing, setIsEditing] = useState(false);
    const [loading, setLoading] = useState(true); // fetching profile
    const [saving, setSaving] = useState(false); // updating profile
    const selector = useSelector((state) => state.authSlice.auth);

    const token = selector.userData.jwtToken;
    const storedRole = selector.userData.role;

    const [userData, setUserData] = useState(null);
    const [editData, setEditData] = useState(null);

    // ✅ Fetch profile from API
    const fetchProfile = async () => {
        try {
            setLoading(true);
            const response = await fetch("http://localhost:8000/auth/profile", {
                method: "GET",
                headers: {
                    Authorization: `Bearer ${token}`,
                    "Content-Type": "application/json",
                },
            });

            if (!response.ok) throw new Error("Failed to fetch profile");

            const data = await response.json();
            console.log("Fetched profile data:", data);
            const mappedData = {
                isActive: data.isActive,
                firstName: data.firstName || "",
                lastName: data.lastName || "",
                username: data.username || "",
                email: data.email || "",
                mobileNumber: data.mobileNumber || "",
                password: "********", // placeholder
                role: data.role || storedRole,
                designation: data.designation || "",
                alternateEmail: data.alternateEmail || "",
                instituteName: data.instituteName || "",
                specialization: data.specialization || "",
                bio: data.bio || "",
            };

            setUserData(mappedData);
            setEditData(mappedData);
        } catch (err) {
            console.error(err);
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchProfile();
    }, [storedRole]);

    // ✅ Handle input
    const handleInputChange = (field, value) => {
        setEditData((prev) => ({ ...prev, [field]: value, }));
    };

    // ✅ Update profile API
    const handleSave = async () => {
        try {
            setSaving(true);
            const response = await fetch(
                "http://localhost:8000/auth/profile/update",
                {
                    method: "PUT",
                    headers: {
                        Authorization: `Bearer ${token}`,
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify(editData),
                }
            );

            if (!response.ok) throw new Error("Failed to update profile");

            const updatedData = await response.json();

            const mappedData = {
                firstName: updatedData.firstName || "",
                lastName: updatedData.lastName || "",
                username: updatedData.username || "",
                email: updatedData.email || "",
                mobileNumber: updatedData.mobileNumber || "",
                role: updatedData.role || storedRole,
                designation: updatedData.designation || "",
                alternateEmail: updatedData.alternateEmail || "",
                instituteName: updatedData.instituteName || "",
                specialization: updatedData.specialization || "",
                bio: updatedData.bio || "",
            };

            setUserData(mappedData);
            setEditData(mappedData);
            setIsEditing(false);
            alert("Profile updated!");
        } catch (err) {
            console.error(err);
            alert("Error updating profile");
        } finally {
            setSaving(false);
        }
    };

    const handleCancel = () => {
        setEditData(userData);
        setIsEditing(false);
    };

    // ✅ Reusable ProfileField
    const ProfileField = ({
                              icon: Icon,
                              label,
                              value,
                              field,
                              type = "text",
                              isTextarea = false
                          }) => (
        <div className="flex items-start space-x-3 mb-6">
            <Icon size={20} className="text-gray-500 mt-1 flex-shrink-0" />
            <div className="flex-1">
                <label className="block text-sm font-medium text-gray-700 mb-1 text-left">
                    {label}
                </label>
                {isEditing ? (
                    isTextarea ? (
                        <textarea
                            value={editData?.[field] ?? ""}
                            onChange={(e) => handleInputChange(field, e.target.value)}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-green-600 focus:border-transparent text-left"
                            rows="3"
                        />
                    ) : (
                        <input
                            type={type}
                            value={editData?.[field] ?? ""}
                            onChange={(e) => handleInputChange(field, e.target.value)}
                            className="w-full p-2 border border-gray-300 rounded-md focus:ring-2 focus:ring-green-600 focus:border-transparent text-left"
                        />
                    )
                ) : (
                    <p className="text-gray-900 text-left">{value || "-"}</p>
                )}
            </div>
        </div>
    );

    // ✅ Loading state when fetching profile
    if (loading) {
        return (
            <div className="flex items-center justify-center h-screen">
                <Loader2 size={40} className="animate-spin text-green-600" />
                <span className="ml-3 text-lg text-gray-600">Loading profile...</span>
            </div>
        );
    }

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
                            className={`px-4 py-2 rounded-lg flex items-center transition-colors ${
                                saving
                                    ? "bg-green-400 cursor-not-allowed"
                                    : "bg-green-500 hover:bg-green-600"
                            } text-white`}
                            disabled={saving}
                        >
                            {saving ? (
                                <>
                                    <Loader2 size={16} className="mr-2 animate-spin" />
                                    Saving...
                                </>
                            ) : (
                                <>
                                    <Save size={16} className="mr-2" />
                                    Save
                                </>
                            )}
                        </button>
                    </div>
                )}
            </div>

            {/* Profile Content */}
            {userData && (
                <div className="bg-white rounded-lg shadow-sm border border-gray-200 relative">
                    {/* Overlay loader when saving */}
                    {saving && (
                        <div className="absolute inset-0 bg-white/70 flex items-center justify-center rounded-lg z-10">
                            <Loader2 size={30} className="animate-spin text-green-600" />
                            <span className="ml-3 text-gray-700 font-medium">
                Updating profile...
              </span>
                        </div>
                    )}

                    {/* Profile Header */}
                    <div className="flex items-center p-8 border-b border-gray-200 bg-gradient-to-r from-green-50 to-blue-50 rounded-t-lg">
                        {/* Avatar */}
                        <div className="w-20 h-20 rounded-full bg-gradient-to-br from-green-500 to-green-600 flex items-center justify-center text-white text-2xl font-bold shadow-md">
                            {userData.firstName
                                ? userData.firstName[0].toUpperCase() +
                                (userData.lastName ? userData.lastName[0].toUpperCase() : "")
                                : "U"}
                        </div>

                        {/* Info */}
                        <div className="ml-6">
                            <h2 className="text-2xl font-bold text-gray-800">
                                {userData.firstName + " " + userData.lastName}
                            </h2>

                            <div className="flex flex-wrap items-center gap-3 mt-2">
                <span className="px-3 py-1 rounded-full bg-blue-100 text-blue-700 text-sm font-semibold shadow-sm">
                  {userData.role || "Role"}
                </span>

                                <span
                                    className={`px-3 py-1 rounded-full text-sm font-medium shadow-sm ${
                                        userData.isActive
                                            ? "bg-green-100 text-green-700"
                                            : "bg-red-100 text-red-700"
                                    }`}
                                >
                  {userData.isActive ? "Active" : "Inactive"}
                </span>
                            </div>

                            <p className="mt-2 text-gray-600 text-sm font-medium">
                                {userData.instituteName || "Institute"}
                            </p>
                        </div>
                    </div>

                    {/* Details */}
                    <div className="grid grid-cols-1 lg:grid-cols-2 gap-8 p-8">
                        {/* Left Column */}
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
                                icon={User}
                                label="Last Name"
                                value={userData.lastName}
                                field="lastName"
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

                        {/* Right Column */}
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
                                        <User
                                            size={20}
                                            className="text-gray-500 mt-1 flex-shrink-0"
                                        />
                                        <div className="flex-1">
                                            <label className="block text-sm font-medium text-gray-700 mb-1 text-left">
                                                Institute Name
                                            </label>
                                            <p className="text-gray-900 text-left">
                                                {userData.instituteName}
                                            </p>
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
                                        isTextarea
                                    />
                                    <div className="flex items-start space-x-3 mb-6">
                                        <User
                                            size={20}
                                            className="text-gray-500 mt-1 flex-shrink-0"
                                        />
                                        <div className="flex-1">
                                            <label className="block text-sm font-medium text-gray-700 mb-1 text-left">
                                                Institute Name
                                            </label>
                                            <p className="text-gray-900 text-left">
                                                {userData.instituteName}
                                            </p>
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
            )}
        </div>
    );
};

export default Profile;
