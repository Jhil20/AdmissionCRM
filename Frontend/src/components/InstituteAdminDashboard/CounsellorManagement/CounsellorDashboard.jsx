import React, { useState, useEffect } from "react";
import CounsellorHeader from "./CounsellorHeader";
import CounsellorFilter from "./Filter";
import CounsellorList from "./CounsellorTable";
import AddCounsellorForm from "./AddCounsellorForm";
import Sidebar from "../SideBar";
import Header from "../Header";

const API_URL = "http://localhost:8000/api/auth/admin/getCounsellorByInstitute";
const CREATE_URL = "http://localhost:8000/api/auth/admin/c/create";

const CounsellorDashboard = () => {
  const [counsellors, setCounsellors] = useState([]);
  const [loading, setLoading] = useState(false);
  const [searchTerm, setSearchTerm] = useState("");
  const [selectedStatus, setSelectedStatus] = useState("All");
  const [isFormOpen, setIsFormOpen] = useState(false);

  // Fetch all counsellors
  const fetchCounsellors = async () => {
    setLoading(true);
    try {
      const token = localStorage.getItem("Token");
      if (!token) return console.error("No auth token found");

      const res = await fetch(`${API_URL}?page=0&size=50`, {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      if (!res.ok) throw new Error(`Error: ${res.status}`);

      const data = await res.json();

      const mapped = data.content.map((u, idx) => ({
        id: idx,
        name: u.firstName,
        username: u.username,
        email: u.email,
        role: "COUNSELLOR",
        institute: u.instituteName || u.counsellorInstitute,
        contact: u.mobileNumber,
        status: u.active ? "Active" : "Inactive",
        avatar: u.firstName ? u.firstName.slice(0, 2).toUpperCase() : "",
        avatarBg: "bg-green-500",
        specialization: u.specialization || "N/A",
        designation: u.designation || "N/A",
        alternateEmail: u.alternateEmail || "N/A",
        bio: u.bio || "N/A",
      }));

      setCounsellors(mapped);
    } catch (err) {
      console.error("Error fetching counsellors:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCounsellors();
  }, []);

  // Filtered counsellors
  const filteredCounsellors = counsellors.filter((user) => {
    const name = (user.name || "").toLowerCase();
    const email = (user.email || "").toLowerCase();

    const matchesSearch =
      name.includes(searchTerm.toLowerCase()) ||
      email.includes(searchTerm.toLowerCase());

    const matchesStatus =
      selectedStatus === "All" || user.status === selectedStatus;

    return matchesSearch && matchesStatus;
  });


  const handleAddClick = () => setIsFormOpen(true);
  const handleFormCancel = () => setIsFormOpen(false);

  const handleFormSubmit = async (formData) => {
    try {
      const token = localStorage.getItem("Token");
      if (!token) return console.error("No auth token found");

      const res = await fetch(CREATE_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
        body: JSON.stringify(formData),
      });

      if (!res.ok) throw new Error("Failed to create counsellor");

      const data = await res.json();
      console.log("Counsellor created:", data);

      await fetchCounsellors();
      setIsFormOpen(false);

      // Map new counsellor to same structure
      // const newCounsellor = {
      //   id: counsellors.length + 1,
      //   name: data.firstName,
      //   username: data.username,
      //   email: data.email,
      //   role: "COUNSELLOR",
      //   institute: data.instituteName || data.counsellorInstitute,
      //   contact: data.mobileNumber,
      //   status: data.active ? "Active" : "Inactive",
      //   avatar: data.firstName ? data.firstName.slice(0, 2).toUpperCase() : "",
      //   avatarBg: "bg-green-500",
      //   specialization: data.specialization || "N/A",
      //   designation: data.designation || "N/A",
      //   alternateEmail: data.alternateEmail || "N/A",
      //   bio: data.bio || "N/A",
      // };

      // setCounsellors((prev) => [...prev, newCounsellor]);
      // setIsFormOpen(false);
    } catch (err) {
      console.error(err);
      alert("Error creating counsellor. Please try again.");
    }
  };

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
      <div className="fixed left-0 top-0 h-full z-20">
        <Sidebar />
      </div>

      <div className="flex-1 flex flex-col min-w-0 ml-80">
        <Header />

        <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
          {isFormOpen ? (
            <AddCounsellorForm
              selectedRole="COUNSELLOR"
              onCancel={handleFormCancel}
              onSubmit={handleFormSubmit}
            />
          ) : (
            <>
              <CounsellorHeader onAddCounsellor={handleAddClick} />
              <CounsellorFilter
                searchTerm={searchTerm}
                setSearchTerm={setSearchTerm}
                selectedStatus={selectedStatus}
                setSelectedStatus={setSelectedStatus}
              />
              {loading ? (
                <p className="text-gray-500">Loading counsellors...</p>
              ) : (
                <CounsellorList filteredUsers={filteredCounsellors} />
              )}
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default CounsellorDashboard;
