import React, { useEffect, useState } from "react";
import { Send } from "lucide-react";
import { useSelector } from "react-redux";

const querySubjects = [
  "ADMISSION_PROCESS",
  "ELIGIBILITY_CRITERIA",
  "ENTRANCE_EXAMS",
  "RESERVATION_AND_CATEGORY",
  "DOCUMENTS_REQUIRED",
  "FEES_STRUCTURE",
  "SCHOLARSHIPS_AND_FINANCIAL_AID",
  "PAYMENT_PROCESS",
  "COURSE_DETAILS",
  "COURSE_DURATION",
  "SPECIALIZATIONS",
  "FACULTY_DETAILS",
  "ACADEMIC_CALENDAR",
  "HOSTEL_AND_ACCOMMODATION",
  "TRANSPORT_AND_LOCATION",
  "CAMPUS_INFRASTRUCTURE",
  "EXTRACURRICULAR_ACTIVITIES",
  "PLACEMENTS_AND_INTERNSHIPS",
  "CAREER_GUIDANCE",
  "ALUMNI_NETWORK",
];

const QueryForm = ({ onClose }) => {
  const [formData, setFormData] = useState({
    instituteName: "",
    querySubject: "",
    query: "",
  });

  const [instituteList, setInstituteList] = useState([]);
  const [loading, setLoading] = useState(false);

  // ✅ Get token from Redux
  const selector = useSelector((state) => state.authSlice.auth);
  const token = selector?.userData?.jwtToken;

  // ✅ Fetch institutes (only those where admission inquiry is done)
  useEffect(() => {
    const fetchInstitutes = async () => {
      if (!token) return;
      try {
        const res = await fetch(
          "http://localhost:8086/api/Admission-Inquiry/user-Institutes",
          {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );
        if (!res.ok) throw new Error("Failed to fetch institutes");
        const data = await res.json();
        setInstituteList(data || []);
      } catch (err) {
        console.error("Error fetching institutes:", err);
      }
    };
    fetchInstitutes();
  }, [token]);

  // ✅ Handle input change
  const handleChange = (e) => {
    setFormData({
      ...formData,
      [e.target.name]: e.target.value,
    });
  };

  // ✅ Handle submit (real backend call)
  const handleSubmit = async () => {
    const { instituteName, querySubject, query } = formData;

    if (!instituteName || !querySubject || !query) {
      alert("Please fill all required fields.");
      return;
    }

    const payload = {
      querySubject,
      query,
      instituteName,
    };

    try {
      setLoading(true);
      const res = await fetch("http://localhost:8086/api/query/create", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      const data = await res.json();

      if (res.ok) {
        alert("✅ Query submitted successfully!");
        setFormData({
          instituteName: "",
          querySubject: "",
          query: "",
        });
      } else {
        alert(`❌ ${data.message || "Error submitting query"}`);
      }
    } catch (err) {
      console.error("Error:", err);
      alert("❌ Network error while submitting query.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="flex-1 bg-gray-50 overflow-auto">
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="flex items-center justify-between p-6">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Ask New Query</h1>
            <p className="text-gray-600 mt-1">
              Submit your question about courses and admissions
            </p>
          </div>
          <button
            onClick={onClose}
            className="text-gray-500 hover:text-gray-700 text-sm font-medium"
          >
            Back to Queries
          </button>
        </div>
      </div>

      {/* Query Form */}
      <div className="max-w-2xl mx-auto p-6">
        <div className="bg-white rounded-lg shadow-sm border border-gray-200">
          <div className="p-6 space-y-6">
            {/* Institute Dropdown */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Choose Institute *
              </label>
              <select
                name="instituteName"
                value={formData.instituteName}
                onChange={handleChange}
                className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
              >
                <option value="">Select an institute</option>
                {instituteList.map((inst, idx) => (
                  <option key={idx} value={inst.instituteName}>
                    {inst.instituteName}
                  </option>
                ))}
              </select>
            </div>

            {/* Query Subject Dropdown */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Query Subject *
              </label>
              <select
                name="querySubject"
                value={formData.querySubject}
                onChange={handleChange}
                className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
              >
                <option value="">Select a subject</option>
                {querySubjects.map((subject, idx) => (
                  <option key={idx} value={subject}>
                    {subject.replaceAll("_", " ")}
                  </option>
                ))}
              </select>
            </div>

            {/* Query Description */}
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-2">
                Description *
              </label>
              <textarea
                name="query"
                value={formData.query}
                onChange={handleChange}
                rows="4"
                className="w-full p-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500"
                placeholder="Describe your query in detail..."
              />
            </div>

            {/* Submit Button */}
            <div className="flex justify-end">
              <button
                onClick={handleSubmit}
                disabled={loading}
                className={`${
                  loading ? "bg-green-400" : "bg-green-500 hover:bg-green-600"
                } text-white px-6 py-3 rounded-lg font-medium transition-colors inline-flex items-center space-x-2`}
              >
                <Send className="w-5 h-5" />
                <span>{loading ? "Submitting..." : "Submit Query"}</span>
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default QueryForm;
