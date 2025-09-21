import React from 'react';
import { X } from 'lucide-react';

const InstituteDetails = ({ institute, onClose, setShowAdmissionForm }) => {
  // Sample course data
  const sampleCourses = [
    {
      courseName: "B Tech. Computer Engineering",
      durationYears: "4 Years",
      durationSemesters: "8 Semesters",
      totalSeats: 240,
      fees: "6,00,000",
      applicationStatus: "Open"
    },
    {
      courseName: "B Tech. Mechanical Engineering",
      durationYears: "4 Years",
      durationSemesters: "8 Semesters",
      totalSeats: 180,
      fees: "5,80,000",
      applicationStatus: "Open"
    },
    {
      courseName: "B Tech. Electrical Engineering",
      durationYears: "4 Years",
      durationSemesters: "8 Semesters",
      totalSeats: 120,
      fees: "5,50,000",
      applicationStatus: "Closed"
    },
    {
      courseName: "B Tech. Civil Engineering",
      durationYears: "4 Years",
      durationSemesters: "8 Semesters",
      totalSeats: 120,
      fees: "5,20,000",
      applicationStatus: "Open"
    },
    {
      courseName: "B Tech. Information Technology",
      durationYears: "4 Years",
      durationSemesters: "8 Semesters",
      totalSeats: 200,
      fees: "6,00,000",
      applicationStatus: "Open"
    }
  ];

  return (
    <div className="bg-white rounded-lg shadow-md border border-gray-200">
      <div className="p-6 border-b border-gray-200">
        <div className="flex items-center justify-between">
          <div>
            <h2 className="text-2xl font-bold text-gray-800 mb-1">
              CSPIT University
            </h2>
            <p className="text-gray-600">
              Chandubhai S Patel Institute of Technology, known for excellence in engineering education.
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
              {sampleCourses.map((course, index) => (
                <tr
                  key={index}
                  className={`border-b border-gray-200 hover:bg-gradient-to-r hover:from-green-50 hover:to-blue-50 transition-all duration-200 ${
                    index % 2 === 0 ? 'bg-white' : 'bg-gray-50'
                  }`}
                >
                  <td className="px-8 py-4 border-r border-gray-200 text-left">
                    <div>
                      <div className="text-sm font-bold text-gray-900 mb-2">
                        {course.courseName}
                      </div>
                      <div className="flex items-center">
                        <span
                          className={`inline-flex items-center px-3 py-1 text-xs font-semibold rounded-full shadow-sm ${
                            course.applicationStatus === 'Open'
                              ? 'bg-green-100 text-green-700'
                              : 'bg-red-100 text-red-700'
                          }`}
                        >
                          <span
                            className={`w-2 h-2 rounded-full mr-2 ${
                              course.applicationStatus === 'Open'
                                ? 'bg-green-500'
                                : 'bg-red-500'
                            }`}
                          ></span>
                          Applications {course.applicationStatus}
                        </span>
                      </div>
                    </div>
                  </td>
                  <td className="px-8 py-4 text-sm text-center text-gray-700 border-r border-gray-200 w-40">
                    <div className="font-medium flex flex-col items-center">
                      <div>{course.durationYears}</div>
                      <div>{course.durationSemesters}</div>
                    </div>
                  </td>
                  <td className="px-8 py-4 text-center border-r border-gray-200">
                    <div className="font-bold text-gray-800 text-lg">
                      {course.totalSeats}
                    </div>
                  </td>
                  <td className="px-8 py-4 text-center font-semibold text-green-600 text-base border-r border-gray-200">
                    ₹{course.fees}
                  </td>
                  <td className="px-8 py-4 text-center">
                    <button className="whitespace-nowrap bg-gradient-to-r from-green-500 to-green-600 hover:from-green-600 hover:to-green-700 text-white px-5 py-2 rounded-lg text-sm font-semibold shadow-md transition-all duration-200 transform hover:scale-105">
                      More Info
                    </button>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>

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