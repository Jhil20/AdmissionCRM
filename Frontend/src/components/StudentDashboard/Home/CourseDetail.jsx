import React from 'react';
import { ArrowLeft, Calendar, Users, BookOpen, GraduationCap, CheckCircle, AlertCircle } from 'lucide-react';

const CourseDetail = ({ courseData, onBack }) => {
  // Calculate total seats and available seats
  const totalSeats = courseData.totalSeatsAcpc + courseData.totalSeatsManagement + courseData.totalSeatsNri;
  const totalAvailableSeats = courseData.availableSeatsAcpc + courseData.availableSeatsManagement + courseData.availableSeatsNri;

  // Parse eligibility criteria into bullet points
  const parseEligibilityCriteria = (criteriaText) => {
    // Split by semicolon or common separators and clean up
    const points = criteriaText
      .split(/[;,]/)
      .map(point => point.trim())
      .filter(point => point.length > 0);
    
    return points;
  };

  // Format dates
  const formatDate = (dateString) => {
    return new Date(dateString).toLocaleDateString('en-IN', {
      day: 'numeric',
      month: 'long', 
      year: 'numeric'
    });
  };

  // Check if admissions are open based on admissionStatus field
  const isAdmissionOpen = courseData.admissionStatus === true;

  return (
    <div className="min-h-screen bg-gray-50">
      {/* Header */}
      <div className="bg-white shadow-sm border-b">
        <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8">
          <div className="flex items-center justify-between py-4">
            <button
              onClick={onBack}
              className="flex items-center text-gray-600 hover:text-gray-900 transition-colors"
            >
              <ArrowLeft className="w-5 h-5 mr-2" />
              Back to Courses
            </button>
            
            <span className={`inline-flex items-center text-sm font-semibold px-3 py-1 rounded-full ${
              isAdmissionOpen 
                ? 'text-green-700 bg-green-100' 
                : 'text-red-700 bg-red-100'
            }`}>
              {isAdmissionOpen ? (
                <>
                  <CheckCircle className="w-3 h-3 mr-1" />
                  Admissions Open
                </>
              ) : (
                <>
                  <AlertCircle className="w-3 h-3 mr-1" />
                  Admissions Closed
                </>
              )}
            </span>
          </div>
        </div>
      </div>

      <div className="max-w-6xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        {/* Course Header */}
        <div className="bg-gradient-to-r from-green-50 to-blue-50 p-6 rounded-xl mb-6">
          <div>
            <h1 className="text-3xl font-bold text-gray-800 mb-3">{courseData.name}</h1>
            <div className="flex items-center space-x-4">
              <span className="text-sm font-semibold text-blue-600 bg-blue-100 px-3 py-1 rounded-full">
                {courseData.courseCode}
              </span>
              <span className={`text-sm font-semibold px-3 py-1 rounded-full ${
                courseData.degreeType === 'UNDERGRADUATE' 
                  ? 'text-green-700 bg-green-100' 
                  : 'text-purple-700 bg-purple-100'
              }`}>
                {courseData.degreeType}
              </span>
            </div>
          </div>
        </div>

        <div className="space-y-6">
          {/* Course Overview */}
          <div className="bg-gray-50 p-5 rounded-lg">
            <h3 className="text-lg font-semibold text-gray-800 mb-3 flex items-center">
              <BookOpen className="w-5 h-5 mr-2 text-blue-600" />
              Course Overview
            </h3>
            <p className="text-gray-700 leading-relaxed">{courseData.description}</p>
          </div>

          {/* Program Details */}
          <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
            <div className="bg-white border border-gray-200 p-5 rounded-lg shadow-sm">
              <h3 className="text-lg font-semibold text-gray-800 mb-4 flex items-center">
                <GraduationCap className="w-5 h-5 mr-2 text-green-600" />
                Program Structure
              </h3>
              <div className="space-y-3">
                <div className="flex justify-between items-center">
                  <span className="text-gray-600">Duration:</span>
                  <span className="font-semibold text-gray-800">{courseData.years} Years</span>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-gray-600">Total Semesters:</span>
                  <span className="font-semibold text-gray-800">{courseData.semester} Semesters</span>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-gray-600">Course Fee:</span>
                  <span className="font-bold text-green-600 text-lg">₹{courseData.fee.toLocaleString()}</span>
                </div>
              </div>
            </div>

            <div className="bg-white border border-gray-200 p-5 rounded-lg shadow-sm">
              <h3 className="text-lg font-semibold text-gray-800 mb-4 flex items-center">
                <Calendar className="w-5 h-5 mr-2 text-red-600" />
                Admission Timeline
              </h3>
              <div className="space-y-3">
                <div className="flex justify-between items-center">
                  <span className="text-gray-600">Start Date:</span>
                  <span className="font-semibold text-gray-800">{formatDate(courseData.admissionStartDate)}</span>
                </div>
                <div className="flex justify-between items-center">
                  <span className="text-gray-600">End Date:</span>
                  <span className="font-semibold text-gray-800">{formatDate(courseData.admissionEndDate)}</span>
                </div>
              </div>
            </div>
          </div>

          {/* Eligibility Criteria */}
          <div className="bg-blue-50 border border-blue-200 p-5 rounded-lg">
            <h3 className="text-lg font-semibold text-gray-800 mb-4 flex items-center">
              <CheckCircle className="w-5 h-5 mr-2 text-blue-600" />
              Eligibility Criteria
            </h3>
            <ul className="space-y-3">
              {parseEligibilityCriteria(courseData.eligibilityCriteria).map((criterion, index) => (
                <li key={index} className="flex items-start">
                  <CheckCircle className="w-4 h-4 text-green-500 mt-0.5 mr-3 flex-shrink-0" />
                  <span className="text-gray-700">{criterion}</span>
                </li>
              ))}
            </ul>
          </div>

          {/* Seat Information */}
          <div className="bg-white border border-gray-200 rounded-lg shadow-sm overflow-hidden">
            <div className="bg-gradient-to-r from-green-50 to-blue-50 p-4 border-b border-gray-200">
              <h3 className="text-lg font-semibold text-gray-800 flex items-center">
                <Users className="w-5 h-5 mr-2 text-green-600" />
                Seat Information
              </h3>
            </div>
            
            <div className="p-5">
              {/* Summary Row */}
              <div className="grid grid-cols-3 gap-4 mb-6 p-4 bg-gray-50 rounded-lg">
                <div className="text-center">
                  <div className="text-2xl font-bold text-green-600">{totalSeats}</div>
                  <div className="text-sm text-gray-600">Total Seats</div>
                </div>
                <div className="text-center">
                  <div className="text-2xl font-bold text-blue-600">{totalAvailableSeats}</div>
                  <div className="text-sm text-gray-600">Available Seats</div>
                </div>
                <div className="text-center">
                  <div className="text-2xl font-bold text-red-600">{totalSeats - totalAvailableSeats}</div>
                  <div className="text-sm text-gray-600">Filled Seats</div>
                </div>
              </div>

              {/* Category Table */}
              <div className="overflow-x-auto">
                <table className="min-w-full">
                  <thead>
                    <tr className="bg-gray-50 border-b border-gray-200">
                      <th className="px-4 py-3 text-left text-sm font-semibold text-gray-700">Category</th>
                      <th className="px-4 py-3 text-center text-sm font-semibold text-gray-700">Total Seats</th>
                      <th className="px-4 py-3 text-center text-sm font-semibold text-gray-700">Available Seats</th>
                      <th className="px-4 py-3 text-center text-sm font-semibold text-gray-700">Filled Seats</th>
                      <th className="px-4 py-3 text-center text-sm font-semibold text-gray-700">Status</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr className="border-b border-gray-100">
                      <td className="px-4 py-3 font-medium text-gray-800">ACPC</td>
                      <td className="px-4 py-3 text-center text-gray-700">{courseData.totalSeatsAcpc}</td>
                      <td className="px-4 py-3 text-center font-semibold text-blue-600">{courseData.availableSeatsAcpc}</td>
                      <td className="px-4 py-3 text-center font-semibold text-gray-700">{courseData.totalSeatsAcpc - courseData.availableSeatsAcpc}</td>
                      <td className="px-4 py-3 text-center">
                        <span className={`px-2 py-1 text-xs font-semibold rounded-full ${
                          courseData.availableSeatsAcpc > 0 
                            ? 'bg-green-100 text-green-700' 
                            : 'bg-red-100 text-red-700'
                        }`}>
                          {courseData.availableSeatsAcpc > 0 ? 'Available' : 'Full'}
                        </span>
                      </td>
                    </tr>
                    <tr className="border-b border-gray-100">
                      <td className="px-4 py-3 font-medium text-gray-800">Management</td>
                      <td className="px-4 py-3 text-center text-gray-700">{courseData.totalSeatsManagement}</td>
                      <td className="px-4 py-3 text-center font-semibold text-green-600">{courseData.availableSeatsManagement}</td>
                      <td className="px-4 py-3 text-center font-semibold text-gray-700">{courseData.totalSeatsManagement - courseData.availableSeatsManagement}</td>
                      <td className="px-4 py-3 text-center">
                        <span className={`px-2 py-1 text-xs font-semibold rounded-full ${
                          courseData.availableSeatsManagement > 0 
                            ? 'bg-green-100 text-green-700' 
                            : 'bg-red-100 text-red-700'
                        }`}>
                          {courseData.availableSeatsManagement > 0 ? 'Available' : 'Full'}
                        </span>
                      </td>
                    </tr>
                    <tr>
                      <td className="px-4 py-3 font-medium text-gray-800">NRI</td>
                      <td className="px-4 py-3 text-center text-gray-700">{courseData.totalSeatsNri}</td>
                      <td className="px-4 py-3 text-center font-semibold text-purple-600">{courseData.availableSeatsNri}</td>
                      <td className="px-4 py-3 text-center font-semibold text-gray-700">{courseData.totalSeatsNri - courseData.availableSeatsNri}</td>
                      <td className="px-4 py-3 text-center">
                        <span className={`px-2 py-1 text-xs font-semibold rounded-full ${
                          courseData.availableSeatsNri > 0 
                            ? 'bg-green-100 text-green-700' 
                            : 'bg-red-100 text-red-700'
                        }`}>
                          {courseData.availableSeatsNri > 0 ? 'Available' : 'Full'}
                        </span>
                      </td>
                    </tr>
                  </tbody>
                </table>
              </div>
            </div>
          </div>

          {/* Action Buttons */}
          <div className="flex justify-center space-x-4 pt-4">
            <button 
              className={`px-8 py-3 rounded-lg font-semibold transition-colors ${
                isAdmissionOpen && totalAvailableSeats > 0
                  ? 'bg-green-500 hover:bg-green-600 text-white'
                  : 'bg-gray-300 text-gray-500 cursor-not-allowed'
              }`}
              disabled={!isAdmissionOpen || totalAvailableSeats === 0}
            >
              {totalAvailableSeats === 0 ? 'All Seats Filled' : 'Apply Now'}
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CourseDetail;