import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import Sidebar from "../Sidebar";
import Header from "../Header";
import QueryAdmissionRequired from "./QueryAdmissionRequired";
import QueryEmptyTable from "./QueryEmptyTable";
import QueryForm from "./QueryForm";

const QueriesDashboard = () => {
  const [hasEnquiry, setHasEnquiry] = useState(null); // null = loading
  const [showQueryForm, setShowQueryForm] = useState(false);
  const selector = useSelector((state) => state.authSlice.auth); // Redux auth
  const token = selector?.userData?.jwtToken;

  // ✅ Fetch enquiry check from backend
  const checkAdmissionEnquiry = async () => {
    try {
      const res = await fetch("http://localhost:8086/api/Admission-Inquiry/checkEnquiry", {
        method: "GET",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${token}`,
        },
      });

      if (!res.ok) throw new Error(`Error: ${res.status}`);

      const data = await res.json();
      setHasEnquiry(data); // true / false
    } catch (err) {
      console.error("Error checking admission enquiry:", err);
    }
  };

  useEffect(() => {
    if (token) checkAdmissionEnquiry();
  }, [token]);

  // Callback to handle “Fill Enquiry” button
  const handleFillEnquiry = () => {
    // You can later navigate to Admission Form page
    console.log("Redirecting to Admission Form...");
  };

 const handleAskQuery = () => {
    setShowQueryForm(true); // 👈 show the form
  };

  const handleCloseQueryForm = () => {
    setShowQueryForm(false); // 👈 close form
  };


  // Loading state
  if (hasEnquiry === null) {
    return (
      <div className="flex h-screen items-center justify-center">
        <p className="text-gray-600 text-lg">Loading...</p>
      </div>
    );
  }

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
      {/* Sidebar */}
      <div className="fixed left-0 top-0 h-full z-20">
        <Sidebar />
      </div> 

      {/* Main Content */}
      <div className="flex-1 flex flex-col min-w-0 ml-80">
        {/* Header */}
        <Header />

        {/* Page Content */}
        <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
            {showQueryForm ? (
            <QueryForm onClose={handleCloseQueryForm} /> // 👈 show form
          ) : hasEnquiry ? (
            <QueryEmptyTable onAskQuery={handleAskQuery} />
          ) : (
            <QueryAdmissionRequired onFillEnquiry={handleFillEnquiry} />
          )}
        </div>
      </div>
    </div>
  );
};

export default QueriesDashboard;
