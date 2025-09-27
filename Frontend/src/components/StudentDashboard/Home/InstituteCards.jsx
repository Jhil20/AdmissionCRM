import React from "react";

const InstituteCards = ({ institutes, setSelectedInstitute, setShowAdmissionForm }) => {
  return (
    <>
      <div className="flex justify-between items-center mb-6">
        <div className="text-left">
          <h1 className="text-2xl font-bold text-green-600 mb-2">Institute Management</h1>
          <p className="text-gray-600">Manage institutional admins and counsellors</p>
        </div>
        <button
          onClick={() => setShowAdmissionForm(true)}
          className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-lg font-semibold transition-colors"
        >
          + Admission Enquiry
        </button>
      </div>

      <div className="space-y-6">
        {institutes.map((institute) => (
          <div
            key={institute.id}
            className="bg-white rounded-lg shadow-sm border border-gray-200 p-6"
          >
            <div className="flex items-start space-x-4 text-left">
              <div className="text-4xl">{institute.logo}</div>
              <div>
                <h3 className="text-xl font-bold text-gray-800 mb-1">{institute.instituteName}</h3>
                <h4 className="text-lg text-gray-700 mb-2">{institute.instituteFullform}</h4>
                <p className="text-gray-600 text-sm mb-4">{institute.description}</p>
                <button
                  onClick={() => setSelectedInstitute(institute)}
                  className="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md font-medium transition-colors"
                >
                  More Info →
                </button>
              </div>
            </div>
          </div>
        ))}
      </div>
    </>
  );
};

export default InstituteCards;
