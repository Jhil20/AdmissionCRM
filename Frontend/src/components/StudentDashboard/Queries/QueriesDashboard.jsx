import React, { useEffect, useState } from "react";
import { useSelector } from "react-redux";
import Sidebar from "../Sidebar";
import Header from "../Header";
import QueryAdmissionRequired from "./QueryAdmissionRequired";
import ListQueries from "./ListQueries";
import QueryForm from "./QueryForm";

const QueriesDashboard = () => {
  const [hasEnquiry, setHasEnquiry] = useState(null); // null = loading
  const [showQueryForm, setShowQueryForm] = useState(false);
  const [refreshQueries, setRefreshQueries] = useState(0); // To trigger refresh after form submission
  
  const selector = useSelector((state) => state.authSlice.auth);
  const token = selector?.userData?.jwtToken;

  // Check if user has filled admission enquiry
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
      setHasEnquiry(false);
    }
  };

  useEffect(() => {
    if (token) checkAdmissionEnquiry();
  }, [token]);

  // Handle "Fill Enquiry" button click
  const handleFillEnquiry = () => {
    console.log("Redirecting to Admission Form...");
    // You can add navigation logic here, e.g.:
    // navigate('/admission-form');
  };

  // Handle "Ask Query" button click
  const handleAskQuery = () => {
    setShowQueryForm(true);
  };

  // Handle closing query form and refresh queries list
  const handleCloseQueryForm = (shouldRefresh = false) => {
    setShowQueryForm(false);
    if (shouldRefresh) {
      // Trigger refresh of queries list
      setRefreshQueries(prev => prev + 1);
    }
  };

  // Loading state
  if (hasEnquiry === null) {
    return (
      <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
        <div className="fixed left-0 top-0 h-full z-20">
          <Sidebar />
        </div>
        <div className="flex-1 flex flex-col min-w-0 ml-80">
          <Header />
          <div className="flex-1 flex items-center justify-center mt-[4.5rem]">
            <p className="text-gray-600 text-lg">Loading...</p>
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

      {/* Main Content */}
      <div className="flex-1 flex flex-col min-w-0 ml-80">
        {/* Header */}
        <Header />

        {/* Page Content */}
        <div className="flex-1 overflow-auto mt-[4.5rem]">
          {!hasEnquiry ? (
            // Show admission enquiry required message
            <QueryAdmissionRequired onFillEnquiry={handleFillEnquiry} />
          ) : showQueryForm ? (
            // Show query form
            <QueryForm onClose={handleCloseQueryForm} />
          ) : (
            // Show queries list (empty or with data)
            <ListQueries onAskQuery={handleAskQuery} key={refreshQueries} />
          )}
        </div>
      </div>
    </div>
  );
};

export default QueriesDashboard;