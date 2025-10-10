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
  const [errors, setErrors] = useState({});

  const selector = useSelector((state) => state.authSlice.auth);
  const token = selector?.userData?.jwtToken;

  // Fetch institutes where admission inquiry is done
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

  // Handle input change
  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({
      ...formData,
      [name]: value,
    });
    // Clear error for this field
    if (errors[name]) {
      setErrors({
        ...errors,
        [name]: "",
      });
    }
  };

  // Validate form
  const validateForm = () => {
    const newErrors = {};

    if (!formData.instituteName) {
      newErrors.instituteName = "Please select an institute";
    }
    if (!formData.querySubject) {
      newErrors.querySubject = "Please select a query subject";
    }
    if (!formData.query.trim()) {
      newErrors.query = "Please enter your query description";
    }

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  // Handle submit
  const handleSubmit = async () => {
    if (!validateForm()) {
      return;
    }

    const payload = {
      querySubject: formData.querySubject,
      query: formData.query.trim(),
      instituteName: formData.instituteName,
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

        try {
          const leadRes = await fetch("http://localhost:8086/api/lead/create", {
            method: "POST",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
            body: JSON.stringify({ queryId: data?.queryId }),
          });

          if (leadRes.ok) {
            console.log("✅ Lead created successfully for this query.");
          } else {
            console.error("❌ Failed to create lead entry.");
            console.log(await leadRes.text());
          }
        } catch (leadErr) {
          console.error("Error while creating lead:", leadErr);
        }




        // Reset form
        setFormData({
          instituteName: "",
          querySubject: "",
          query: "",
        });
        // Close form and trigger refresh
        onClose(true);
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
     <div className="flex items-center justify-between mb-4 p-6 bg-transparent">
  <div className="flex flex-col items-start">
    <h1 className="text-2xl font-bold text-green-600">Ask New Query</h1>
    <p className="text-gray-600 mt-1">
      Submit your question about courses and admissions
    </p>
  </div>
  <button
    onClick={() => onClose(false)}
    className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg flex items-center space-x-2 transition-colors"
  >
    <span>Back to Queries</span>
  </button>
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
                className={`w-full p-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 ${errors.instituteName ? 'border-red-500' : 'border-gray-300'
                  }`}
              >
                <option value="">Select an institute</option>
                {instituteList.map((inst, idx) => (
                  <option key={idx} value={inst.instituteName}>
                    {inst.instituteName}
                  </option>
                ))}
              </select>
              {errors.instituteName && (
                <p className="mt-1 text-sm text-red-600">{errors.instituteName}</p>
              )}
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
                className={`w-full p-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 ${errors.querySubject ? 'border-red-500' : 'border-gray-300'
                  }`}
              >
                <option value="">Select a subject</option>
                {querySubjects.map((subject, idx) => (
                  <option key={idx} value={subject}>
                    {subject.replaceAll("_", " ")}
                  </option>
                ))}
              </select>
              {errors.querySubject && (
                <p className="mt-1 text-sm text-red-600">{errors.querySubject}</p>
              )}
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
                className={`w-full p-3 border rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 ${errors.query ? 'border-red-500' : 'border-gray-300'
                  }`}
                placeholder="Describe your query in detail..."
              />
              {errors.query && (
                <p className="mt-1 text-sm text-red-600">{errors.query}</p>
              )}
            </div>

            {/* Submit Button */}
            <div className="flex justify-end space-x-3">
              <button
                onClick={() => onClose(false)}
                className="px-6 py-3 border border-gray-300 rounded-lg font-medium text-gray-700 hover:bg-gray-50 transition-colors"
              >
                Cancel
              </button>
              <button
                onClick={handleSubmit}
                disabled={loading}
                className={`${loading ? "bg-green-400 cursor-not-allowed" : "bg-green-500 hover:bg-green-600"
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