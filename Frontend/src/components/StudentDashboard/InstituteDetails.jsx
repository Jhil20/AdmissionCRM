import React, { useState, useEffect } from 'react';
import { X } from 'lucide-react';
import { useSelector } from 'react-redux';
import CourseDetail from './CourseDetail';  

const InstituteDetails = ({ institute, onClose, setShowAdmissionForm }) => {
  const selector = useSelector((state) => state.authSlice.auth);
  const [courses, setCourses] = useState([]);
  const [loading, setLoading] = useState(false);
  const [selectedCourse, setSelectedCourse] = useState(null);

  // Fetch courses from backend
  const fetchCourses = async () => {
    if (!institute.id) return;
    setLoading(true);

    try {
      const token = selector.userData.jwtToken;
      const res = await fetch(`http://localhost:8000/api/courses/institutions/${institute.id}`, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${token}`,
        },
      });

      if (!res.ok) throw new Error(`Error: ${res.status}`);
      const data = await res.json();
      setCourses(data);
    } catch (err) {
      console.error('Error fetching courses:', err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchCourses();
  }, [institute]);

  const handleMoreInfo = (course) => {
    setSelectedCourse(course);
  };

  const handleBackToCourses = () => {
    setSelectedCourse(null);
  };

  // If a course is selected, show the course details page
  if (selectedCourse) {
    return (
      <CourseDetail 
        courseData={selectedCourse} 
        onBack={handleBackToCourses}
      />
    );
  }

  return (
    <div className="bg-white rounded-lg shadow-md border border-gray-200">
      <div className="p-6 border-b border-gray-200">
        <div className="flex items-center justify-between">
          <div>
            <h2 className="text-2xl font-bold text-gray-800 mb-1">
              {institute.instituteName || 'CSPIT University'}
            </h2>
            <p className="text-gray-600">
              {institute.description ||
                'Chandubhai S Patel Institute of Technology, known for excellence in engineering education.'}
            </p>
          </div>
          <button
            onClick={onClose}
            className="text-gray-400 hover:text-gray-600 transition-colors"
          >
            <X className="w-6 h-6" />
          </button>
        </div>
      </div>

      <div className="p-4">
        {/* Wider Table */}
        {loading ? (
          <p className="text-gray-500 text-center">Loading courses...</p>
        ) : courses.length === 0 ? (
          <p className="text-gray-500 text-center">No courses available</p>
        ) : (
          <div className="overflow-x-auto rounded-lg shadow-sm border border-gray-200">
            <table className="w-full bg-white">
              <thead>
                <tr className="bg-gradient-to-r from-green-50 to-blue-50 border-b border-gray-200">
                  <th className="px-8 py-4 text-left text-sm font-semibold text-gray-700 border-r border-gray-200">
                    Course Name
                  </th>
                  <th className="px-8 py-4 text-center text-sm font-semibold text-gray-700 border-r border-gray-200 w-40">
                    Duration
                  </th>
                  <th className="px-8 py-4 text-center text-sm font-semibold text-gray-700 border-r border-gray-200">
                    Total Seats
                  </th>
                  <th className="px-8 py-4 text-center text-sm font-semibold text-gray-700 border-r border-gray-200">
                    Fees
                  </th>
                  <th className="px-8 py-4 text-center text-sm font-semibold text-gray-700">
                    Actions
                  </th>
                </tr>
              </thead>
              <tbody>
                {courses.map((course, index) => (
                  <tr
                    key={index}
                    className={`border-b border-gray-200 hover:bg-gradient-to-r hover:from-green-50 hover:to-blue-50 transition-all duration-200 ${
                      index % 2 === 0 ? 'bg-white' : 'bg-gray-50'
                    }`}
                  >
                    <td className="px-8 py-4 border-r border-gray-200 text-left">
                      <div>
                        <div className="text-sm font-bold text-gray-900 mb-2">
                          {course.name}
                        </div>
                        <div className="flex items-center">
                          <span
                            className={`inline-flex items-center px-3 py-1 text-xs font-semibold rounded-full shadow-sm ${
                              course.admissionStatus === true
                                ? 'bg-green-100 text-green-700'
                                : 'bg-red-100 text-red-700'
                            }`}
                          >
                            <span
                              className={`w-2 h-2 rounded-full mr-2 ${
                                course.admissionStatus === true
                                  ? 'bg-green-500'
                                  : 'bg-red-500'
                              }`}
                            ></span>
                            Applications{' '}
                            {course.admissionStatus === true ? 'Open' : 'Closed'}
                          </span>
                        </div>
                      </div>
                    </td>
                    <td className="px-8 py-4 text-sm text-center text-gray-700 border-r border-gray-200 w-40">
                      <div className="font-medium flex flex-col items-center">
                        <div>{course.years} Years</div>
                        <div>{course.semester} Semesters</div>
                      </div>
                    </td>
                    <td className="px-8 py-4 text-center border-r border-gray-200">
                      <div className="font-bold text-gray-800 text-lg">
                        {course.totalSeatsAcpc +
                          course.totalSeatsManagement +
                          course.totalSeatsNri}
                      </div>
                    </td>
                    <td className="px-8 py-4 text-center font-semibold text-green-600 text-base border-r border-gray-200">
                      ₹{course.fee.toLocaleString()}
                    </td>
                    <td className="px-8 py-4 text-center">
                      <button 
                        onClick={() => handleMoreInfo(course)}
                        className="whitespace-nowrap bg-gradient-to-r from-green-500 to-green-600 hover:from-green-600 hover:to-green-700 text-white px-5 py-2 rounded-lg text-sm font-semibold shadow-md transition-all duration-200 transform hover:scale-105"
                      >
                        More Info
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}

        <div className="mt-6 text-center">
          <button
            onClick={() => setShowAdmissionForm(true)}
            className="bg-green-500 hover:bg-green-600 text-white px-10 py-3 rounded-lg font-semibold transition-colors"
          >
            Admission Enquiry
          </button>
        </div>
      </div>
    </div>
  );
};

export default InstituteDetails;