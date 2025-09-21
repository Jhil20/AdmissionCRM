import React from "react";
import { X, User, Phone, Mail, MapPin, Globe, GraduationCap, Trophy, Award } from "lucide-react";

const AdmissionForm = ({
  institutes,
  selectedProgram,
  setSelectedProgram,
  selectedInstituteForm,
  setSelectedInstituteForm,
  getCoursesForInstitute,
  setShowAdmissionForm,
}) => {
  const handleSubmit = () => {
    alert("Admission enquiry submitted successfully!");
  };

  const FormField = ({ icon: Icon, label, required, children, description }) => (
    <div className="space-y-2">
      <div className="flex items-center space-x-2">
        {Icon && <Icon className="w-4 h-4 text-blue-600" />}
        <label className="text-sm font-semibold text-gray-800">
          {label} {required && <span className="text-red-500">*</span>}
        </label>
      </div>
      {description && <p className="text-xs text-gray-500 ml-6">{description}</p>}
      <div className="ml-6">{children}</div>
    </div>
  );

  const inputClasses = "w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all duration-200 bg-gray-50 focus:bg-white text-gray-800 placeholder-gray-400";
  const selectClasses = "w-full px-4 py-3 border-2 border-gray-200 rounded-xl focus:outline-none focus:border-blue-500 focus:ring-2 focus:ring-blue-100 transition-all duration-200 bg-gray-50 focus:bg-white text-gray-800 cursor-pointer";

  return (
    <div className="bg-white rounded-2xl shadow-2xl border border-gray-100 max-w-2xl mx-auto">
      {/* Header */}
      <div className="bg-gradient-to-r from-green-500 to-emerald-600 p-6 rounded-t-2xl">
        <div className="flex justify-between items-center">
          <div>
            <h2 className="text-2xl font-bold text-white">Admission Enquiry</h2>
            <p className="text-blue-100 text-sm mt-1">Fill out your details to get started</p>
          </div>
          <button
            onClick={() => {
              setShowAdmissionForm(false);
              setSelectedProgram("");
              setSelectedInstituteForm("");
            }}
            className="text-white hover:text-blue-200 transition-colors p-2 rounded-full hover:bg-white hover:bg-opacity-20"
          >
            <X className="w-6 h-6" />
          </button>
        </div>
      </div>

      {/* Form Content */}
      <div className="p-8">
        <div className="space-y-8">
          {/* Personal Information Section */}
          <div className="space-y-6">
            <div className="border-b border-gray-200 pb-2">
              <h3 className="text-lg font-semibold text-gray-800 flex items-center">
                <User className="w-5 h-5 mr-2 text-blue-600" />
                Personal Information
              </h3>
            </div>

            <FormField icon={User} label="Full Name" required>
              <input
                type="text"
                required
                placeholder="Enter your full name"
                className={inputClasses}
              />
            </FormField>

            <FormField icon={Phone} label="Mobile Number" required>
              <input
                type="tel"
                required
                placeholder="Enter your mobile number"
                className={inputClasses}
              />
            </FormField>

            <FormField icon={Mail} label="Email Address" required>
              <input
                type="email"
                required
                placeholder="Enter your email address"
                className={inputClasses}
              />
            </FormField>
          </div>

          {/* Location Information Section */}
          <div className="space-y-6">
            <div className="border-b border-gray-200 pb-2">
              <h3 className="text-lg font-semibold text-gray-800 flex items-center">
                <MapPin className="w-5 h-5 mr-2 text-blue-600" />
                Location Details
              </h3>
            </div>

            <FormField icon={MapPin} label="City" required>
              <input
                type="text"
                required
                placeholder="Enter your city"
                className={inputClasses}
              />
            </FormField>

            <FormField label="State" required>
              <input
                type="text"
                required
                placeholder="Enter your state"
                className={inputClasses}
              />
            </FormField>

            <FormField icon={Globe} label="Country" required>
              <input
                type="text"
                required
                placeholder="Enter your country"
                className={inputClasses}
              />
            </FormField>
          </div>

          {/* Academic Information Section */}
          <div className="space-y-6">
            <div className="border-b border-gray-200 pb-2">
              <h3 className="text-lg font-semibold text-gray-800 flex items-center">
                <GraduationCap className="w-5 h-5 mr-2 text-blue-600" />
                Academic Details
              </h3>
            </div>

            <FormField 
              icon={Award} 
              label="12th Percentile" 
              description="Enter your 12th grade percentile (0-100)"
            >
              <input
                type="number"
                min="0"
                max="100"
                step="0.01"
                placeholder="Enter percentile (0-100)"
                className={inputClasses}
              />
            </FormField>

            <FormField 
              icon={Trophy} 
              label="GUJCET Marks"
              description="Gujarat Common Entrance Test marks"
            >
              <input
                type="number"
                min="0"
                placeholder="Enter GUJCET marks"
                className={inputClasses}
              />
            </FormField>

            <FormField 
              label="ACPC Rank"
              description="Admission Committee for Professional Courses rank"
            >
              <input
                type="number"
                min="1"
                placeholder="Enter ACPC rank"
                className={inputClasses}
              />
            </FormField>

            <FormField 
              label="JEE/NEET Marks"
              description="Joint Entrance Examination or National Eligibility Entrance Test marks"
            >
              <input
                type="number"
                min="0"
                placeholder="Enter JEE or NEET marks"
                className={inputClasses}
              />
            </FormField>
          </div>

          {/* Program Selection Section */}
          <div className="space-y-6">
            <div className="border-b border-gray-200 pb-2">
              <h3 className="text-lg font-semibold text-gray-800 flex items-center">
                <GraduationCap className="w-5 h-5 mr-2 text-blue-600" />
                Program Selection
              </h3>
            </div>

            <FormField label="Select Program" required>
              <select
                value={selectedProgram}
                onChange={(e) => {
                  setSelectedProgram(e.target.value);
                  setSelectedInstituteForm("");
                }}
                required
                className={selectClasses}
              >
                <option value="">Choose your program level</option>
                <option value="undergraduate">Under Graduate</option>
                <option value="postgraduate">Post Graduate</option>
                <option value="phd">PHD</option>
              </select>
            </FormField>

            {/* Institute Selection - Only for Undergraduate */}
            {selectedProgram === "undergraduate" && (
              <FormField label="Select Institute" required>
                <select
                  value={selectedInstituteForm}
                  onChange={(e) => setSelectedInstituteForm(e.target.value)}
                  required
                  className={selectClasses}
                >
                  <option value="">Choose your preferred institute</option>
                  {institutes.map((institute) => (
                    <option key={institute.id} value={institute.name}>
                      {institute.name} - {institute.fullName}
                    </option>
                  ))}
                </select>
              </FormField>
            )}

            {/* Course Selection - Only when Institute is selected */}
            {selectedProgram === "undergraduate" && selectedInstituteForm && (
              <FormField label="Select Course" required>
                <select
                  required
                  className={selectClasses}
                >
                  <option value="">Choose your course</option>
                  {getCoursesForInstitute(selectedInstituteForm).map((course, index) => (
                    <option key={index} value={course.name}>
                      {course.name}
                    </option>
                  ))}
                </select>
              </FormField>
            )}
          </div>

          {/* Submit Button */}
          <div className="pt-6 border-t border-gray-200">
            <button
              onClick={handleSubmit}
              className="w-full bg-gradient-to-r from-green-500 to-emerald-600 hover:from-green-600 hover:to-emerald-700 text-white py-4 px-8 rounded-xl font-semibold text-lg transition-all duration-300 transform hover:scale-105 hover:shadow-xl focus:outline-none focus:ring-4 focus:ring-green-200"
            >
              Submit Enquiry
            </button>
            <p className="text-center text-sm text-gray-500 mt-3">
              We'll get back to you within 24 hours
            </p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AdmissionForm;