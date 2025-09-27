import React, { useState, useEffect } from "react";
import { useSelector } from "react-redux";
import Sidebar from "../Sidebar";
import Header from "../Header";
import InstituteCards from "./InstituteCards";
import InstituteDetails from "./InstituteDetails";
import AdmissionForm from "./AdmissionForm";

const StudentDashboard = () => {
  const [showAdmissionForm, setShowAdmissionForm] = useState(false);
  const [selectedInstitute, setSelectedInstitute] = useState(null);
  const [selectedProgram, setSelectedProgram] = useState("");
  const [selectedInstituteForm, setSelectedInstituteForm] = useState("");
  const [institutes, setInstitutes] = useState([]);
  const [loading, setLoading] = useState(false);
  const selector = useSelector((state) => state.authSlice.auth); // Access auth state from Redux

  // Sample institute data
const fetchInstitutions = async () => {
  setLoading(true);
  try {
    // get token from redux (or localStorage if you prefer)
    const token = selector.userData.jwtToken;
    if (!token) {
      console.error("No auth token found");
      setLoading(false);
      return;
    }

    const res = await fetch("http://localhost:8000/api/institutions/getAllInstitutes", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
    });

    if (!res.ok) throw new Error(`Error: ${res.status}`);

    const data = await res.json();
    console.log("Fetched institutions:", data);

    // directly set backend data (fields already match UI)
    setInstitutes(data);
  } catch (err) {
    console.error("Error fetching institutions:", err);
  } finally {
    setLoading(false);
  }
};

useEffect(() => {
  fetchInstitutions();
}, []);



  const getCoursesForInstitute = (instituteName) => {
    const institute = institutes.find((inst) => inst.name === instituteName);
    return institute ? institute.courses : [];
  };

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

        {/* Scrollable Page Content */}
        <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
          {showAdmissionForm ? (
            <AdmissionForm
              institutes={institutes}
              selectedProgram={selectedProgram}
              setSelectedProgram={setSelectedProgram}
              selectedInstituteForm={selectedInstituteForm}
              setSelectedInstituteForm={setSelectedInstituteForm}
              getCoursesForInstitute={getCoursesForInstitute}
              setShowAdmissionForm={setShowAdmissionForm}
            />
          ) : selectedInstitute ? (
            <InstituteDetails
              institute={selectedInstitute}
              onClose={()=> setSelectedInstitute(null)}
              setSelectedInstitute={setSelectedInstitute}
              setShowAdmissionForm={setShowAdmissionForm}
            />
          ) : (
            <InstituteCards
              institutes={institutes}
              setSelectedInstitute={setSelectedInstitute}
              setShowAdmissionForm={setShowAdmissionForm}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default StudentDashboard;
