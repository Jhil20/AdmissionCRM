import AddCounsellorForm from "./AddCounsellorForm";
import React, { useState, useMemo } from "react";
import CounsellorHeader from "./CounsellorHeader";
import CounsellorFilter from "./Filter";
import CounsellorList from "./CounsellorTable";
import Sidebar from "../SideBar";
import Header from "../Header";

const CounsellorDashboard = () => {
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedStatus, setSelectedStatus] = useState("All");
  const [showAddForm, setShowAddForm] = useState(false); // ✅ Added missing state
  const [counsellors, setCounsellors] = useState([
    {
      id: 1,
      name: "Alice Johnson",
      email: "alice@example.com",
      role: "Counsellor",
      contact: "9876543210",
      status: "Active",
    },
    {
      id: 2,
      name: "Bob Singh",
      email: "bob@example.com",
      role: "Counsellor",
      contact: "9123456780",
      status: "Inactive",
    },
    {
      id: 4,
      name: "David Patel",
      email: "david@example.com",
      role: "Counsellor",
      contact: "9988776655",
      status: "Active",
    },
  ]);

  // Filtering logic - filter by search and status only
  const filteredUsers = useMemo(() => {
    return counsellors.filter((user) => {
      const matchesSearch =
        user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
        user.email.toLowerCase().includes(searchTerm.toLowerCase());

      const matchesStatus =
        selectedStatus === "All" || user.status === selectedStatus;

      return matchesSearch && matchesStatus;
    });
  }, [searchTerm, selectedStatus, counsellors]);

  const handleAddCounsellor = () => {
    setShowAddForm(true);
  };

  const handleCancelForm = () => {
    setShowAddForm(false);
  };

  const handleSubmitForm = (formData) => {
    // Create new counsellor object
    const newCounsellor = {
      id: counsellors.length + 1,
      name: `${formData.firstName} ${formData.lastName}`,
      email: formData.email,
      role: "Counsellor",
      contact: formData.mobileNumber,
      status: "Active",
    };

    // ✅ Update state correctly
    setCounsellors((prev) => [...prev, newCounsellor]);

    // Hide the form
    setShowAddForm(false);

    console.log("New counsellor added:", newCounsellor);
  };

  if (showAddForm) {
    return (
      <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
        {/* Sidebar */}
        <div className="fixed left-0 top-0 h-full z-20">
          <Sidebar />
        </div>

        {/* Main content */}
        <div className="flex-1 flex flex-col min-w-0 ml-80">
          {/* Fixed Header */}
          <Header />

          <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
            {/* ✅ Corrected form name */}
            <AddCounsellorForm
              selectedRole="COUNSELLOR"
              onCancel={handleCancelForm}
              onSubmit={handleSubmitForm}
            />
          </div>
        </div>
      </div>
    );
  }

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
      {/* Sidebar */}
      <div className="fixed left-0 top-0 h-full z-20">
        <Sidebar />
      </div>

      {/* Main content */}
      <div className="flex-1 flex flex-col min-w-0 ml-80">
        {/* Fixed Header */}
        <Header />

        <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
          <CounsellorHeader onAddCounsellor={handleAddCounsellor} /> {/* ✅ Pass handler */}
          <CounsellorFilter
            searchTerm={searchTerm}
            setSearchTerm={setSearchTerm}
            selectedStatus={selectedStatus}
            setSelectedStatus={setSelectedStatus}
          />
          <CounsellorList filteredUsers={filteredUsers} />
        </div>
      </div>
    </div>
  );
};

export default CounsellorDashboard;
