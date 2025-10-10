import React, { useState, useEffect } from "react";
import { User, X, MapPin, Globe, GraduationCap, Trophy, Award, ChevronRight, ChevronLeft, Check, Calendar, BookOpen } from "lucide-react";
import { useSelector } from "react-redux";
import { ToastContainer, useToast } from "../../Toast/ToastSystem";

const AdmissionForm = () => {
  const [selectedCategory, setSelectedCategory] = useState("");
  
  const [selectedInstituteForm, setSelectedInstituteForm] = useState("");
  const [currentStep, setCurrentStep] = useState(0);
  const [sampleInstitutes, setSampleInstitutes] = useState([]);
  const [sampleCourses, setSampleCourses] = useState([]);
  const [loading, setLoading] = useState(false);

  const {toasts, removeToast, showSuccess, showError, showWarning } = useToast();

  const [formData, setFormData] = useState({
    firstName: '',
    middleName: '',
    lastName: '',
    city: '',
    state: '',
    country: '',
    gender: '',
    dob: '',
    gujcetMarks: '',
    gujcetPercentileRank: '',
    acpcRank: '',
    twelfthPercentileRank: '',
    instituteName: '',
    instituteId: '',
    courseName: '',
    courseId: '',
    category: '',
    program: '',
  });

  const steps = [
    {
      title: "Personal Information",
      subtitle: "Basic details about you",
      icon: User,
      color: "from-green-500 to-emerald-600"
    },
    {
      title: "Location Details",
      subtitle: "Where are you from?",
      icon: MapPin,
      color: "from-green-500 to-emerald-600"
    },
    {
      title: "Academic Details",
      subtitle: "Your academic performance",
      icon: GraduationCap,
      color: "from-green-500 to-emerald-600"
    },
    {
      title: "Program & Institute",
      subtitle: "Choose your educational path",
      icon: Trophy,
      color: "from-green-500 to-emerald-600"
    }
  ];

  const categoryOptions = [
    "ENGINEERING",
    "PHARMACY",
    "COMPUTER_APPLICATION",
    "BUSINESS_MANAGEMENT",
    "APPLIED_SCIENCE",
  ];

  const programOptions = ["UNDERGRADUATE", "POSTGRADUATE", "DIPLOMA", "PHD"];

  // Global location data
  const locationData = {
    "India": {
      "Gujarat": ["Ahmedabad", "Surat", "Vadodara", "Rajkot", "Bhavnagar", "Jamnagar", "Junagadh", "Gandhinagar", "Anand", "Morbi"],
      "Maharashtra": ["Mumbai", "Pune", "Nagpur", "Nashik", "Aurangabad", "Solapur", "Amravati", "Kolhapur"],
      "Karnataka": ["Bangalore", "Mysore", "Hubli", "Mangalore", "Belgaum", "Gulbarga", "Davanagere"],
      "Tamil Nadu": ["Chennai", "Coimbatore", "Madurai", "Tiruchirappalli", "Salem", "Tirunelveli"]
    },
    "United States": {
      "California": ["Los Angeles", "San Francisco", "San Diego", "Sacramento"],
      "Texas": ["Houston", "Dallas", "San Antonio", "Austin"],
      "Florida": ["Jacksonville", "Miami", "Tampa", "Orlando"],
      "New York": ["New York City", "Buffalo", "Rochester", "Yonkers"]
    }
  };

  const countries = Object.keys(locationData);
  const genderOptions = ["MALE", "FEMALE", "OTHER"];
  const selector = useSelector((state) => state.authSlice.auth);
  const token = selector?.userData?.jwtToken;

  // Fetch institutions based on category and program
  useEffect(() => {
    const fetchInstitutions = async () => {
      if (formData.program && formData.category) {
        setLoading(true);
        try {
          // setLoadingInstitutes(true);

          const response = await fetch(
            `http://localhost:8000/api/institutions/filterInstitution?category=${formData.category}&program=${formData.program}`,
            {
              method: "GET",
              headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
              }
            }
          );
          const data = await response.json();
          setSampleInstitutes(Array.isArray(data) ? data : []);
        } catch (error) {
          // console.error("Error fetching institutions:", error);
          showError(error.error || "Error fetching institutions");
          setSampleInstitutes([]);
        } finally {
          setLoading(false);
        }
      } else {
        setSampleInstitutes([]);
      }
    };

    fetchInstitutions();
  }, [formData.program, formData.category, token]);

  // Fetch courses based on selected institute
  useEffect(() => {
    const fetchCourses = async () => {
      if (formData.instituteId) {

        try {
          // setLoadingCourses(true);
          const config = {
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          };

          const response = await fetch(
            `http://localhost:8000/api/courses/institutions/${formData.instituteId}`,
            {
              method: "GET",
              headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json",
              },
            }
          );
          const data = await response.json();
          setSampleCourses(Array.isArray(data) ? data : []);
        } catch (error) {
          // console.error("Error fetching courses:", error);
          showError(error.error || "Error fetching courses");
          setSampleCourses([]);
        } finally {
          setLoading(false);
        }
      } else {
        setSampleCourses([]);
      }
    };

    fetchCourses();
  }, [formData.instituteId, token]);

  const getStatesForCountry = (country) => {
    return country ? Object.keys(locationData[country] || {}) : [];
  };

  const getCitiesForState = (country, state) => {
    return country && state ? locationData[country]?.[state] || [] : [];
  };

  const handleInputChange = (field, value) => {
    setFormData(prev => ({ ...prev, [field]: value }));

    if (field === 'country') {
      setFormData(prev => ({ ...prev, state: '', city: '' }));
    } else if (field === 'state') {
      setFormData(prev => ({ ...prev, city: '' }));
    }
  };

  const handleNext = () => {
    if (currentStep < steps.length - 1) {
      setCurrentStep(currentStep + 1);
    }
  };

  const handlePrevious = () => {
    if (currentStep > 0) {
      setCurrentStep(currentStep - 1);
    }
  };

  const handleSubmit = async () => {
    // setLoading(true);
    try {
      const response = await fetch('http://localhost:8086/api/Admission-Inquiry/create', {
        method: 'POST',
        headers: {
          Authorization: `Bearer ${token}`,
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(formData),
      });

      if (response.ok) {
        const result = await response.json();
        console.log("Form Data:", result);
        // alert("Admission enquiry submitted successfully!");
        showSuccess("Admission enquiry submitted successfully!");
        // Reset form after successful submission
        setCurrentStep(0);
        setFormData({
          firstName: '',
          middleName: '',
          lastName: '',
          city: '',
          state: '',
          country: '',
          gender: '',
          dob: '',
          gujcetMarks: '',
          gujcetPercentileRank: '',
          acpcRank: '',
          twelfthPercentileRank: '',
          instituteName: '',
          instituteId: '',
          courseName: '',
          courseId: '',
          category: '',
          program: '',
        });
        // setSelectedProgram("");
        setSelectedInstituteForm("");
      } else {
        const error = await response.json();
        console.error("Error submitting form:", error);
        showWarning(error.error || "Error submitting form");
        // alert(error.error);
      }
    } catch (error) {
      showWarning(error.error);
    } finally {
      setLoading(false);
    }
  };

  const handleClose = () => {
    if (window.confirm("Are you sure you want to close the form? Your progress will be lost.")) {
      setCurrentStep(0);
      setFormData({
        firstName: '',
        middleName: '',
        lastName: '',
        city: '',
        state: '',
        country: '',
        gender: '',
        dob: '',
        gujcetMarks: '',
        gujcetPercentileRank: '',
        acpcRank: '',
        twelfthPercentileRank: '',
        instituteName: '',
        instituteId: '',
        courseName: '',
        courseId: '',
        category: '',
        program: '',
      });
      // setSelectedProgram("");
      setSelectedInstituteForm("");
    }
  };

  const isStepValid = () => {
    if (currentStep === 0) {
      return formData.firstName.trim() && formData.lastName.trim() && formData.gender && formData.dob;
    } else if (currentStep === 1) {
      return formData.city && formData.state && formData.country;
    } else if (currentStep === 2) {
      return formData.gujcetMarks && formData.gujcetPercentileRank && formData.twelfthPercentileRank;
    } else if (currentStep === 3) {
      return formData.program && formData.instituteName && formData.courseName && formData.category;
    }
    return true;
  };

  const inputClasses = "w-full px-3 py-2 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-green-500 focus:ring-1 focus:ring-green-100 transition-all duration-200 bg-white text-gray-800 placeholder-gray-500 text-sm";
  const selectClasses = "w-full px-3 py-2 border-2 border-gray-200 rounded-lg focus:outline-none focus:border-green-500 focus:ring-1 focus:ring-green-100 transition-all duration-200 bg-white text-gray-800 cursor-pointer text-sm";

  const renderStepContent = () => {
    const currentStepData = steps[currentStep];

    return (
      <div className="h-full flex flex-col">
        {/* Step Header */}
        <div className="text-center mb-4">
          <div className={`w-12 h-12 mx-auto rounded-full bg-gradient-to-r ${currentStepData.color} flex items-center justify-center mb-3 shadow-lg`}>
            <currentStepData.icon className="w-6 h-6 text-white" />
          </div>
          <h2 className="text-xl font-bold text-gray-800 mb-1">{currentStepData.title}</h2>
          <p className="text-gray-600 text-sm">{currentStepData.subtitle}</p>
        </div>

        {/* Step Content */}
        <div className="flex-1 flex items-center justify-center">
          <div className="w-full max-w-3xl">
            {currentStep === 0 && (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    First Name <span className="text-red-500">*</span>
                  </label>
                  <div className="relative">
                    <User className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <input
                      type="text"
                      value={formData.firstName}
                      onChange={(e) => handleInputChange('firstName', e.target.value)}
                      placeholder="Enter first name"
                      className={`${inputClasses} pl-8`}
                      maxLength={50}
                    />
                  </div>
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Middle Name
                  </label>
                  <input
                    type="text"
                    value={formData.middleName}
                    onChange={(e) => handleInputChange('middleName', e.target.value)}
                    placeholder="Enter middle name"
                    className={inputClasses}
                    maxLength={50}
                  />
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Last Name <span className="text-red-500">*</span>
                  </label>
                  <input
                    type="text"
                    value={formData.lastName}
                    onChange={(e) => handleInputChange('lastName', e.target.value)}
                    placeholder="Enter last name"
                    className={inputClasses}
                    maxLength={50}
                  />
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Gender <span className="text-red-500">*</span>
                  </label>
                  <select
                    value={formData.gender}
                    onChange={(e) => handleInputChange('gender', e.target.value)}
                    className={selectClasses}
                  >
                    <option value="">Select gender</option>
                    {genderOptions.map((gender) => (
                      <option key={gender} value={gender}>{gender.charAt(0) + gender.slice(1).toLowerCase()}</option>
                    ))}
                  </select>
                </div>

                <div className="md:col-span-2">
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Date of Birth <span className="text-red-500">*</span>
                  </label>
                  <div className="relative max-w-xs mx-auto">
                    <Calendar className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <input
                      type="date"
                      value={formData.dob}
                      onChange={(e) => handleInputChange('dob', e.target.value)}
                      className={`${inputClasses} pl-8`}
                      max={new Date().toISOString().split('T')[0]}
                    />
                  </div>
                </div>
              </div>
            )}

            {currentStep === 1 && (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Country <span className="text-red-500">*</span>
                  </label>
                  <div className="relative">
                    <Globe className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <select
                      value={formData.country}
                      onChange={(e) => handleInputChange('country', e.target.value)}
                      className={`${selectClasses} pl-8`}
                    >
                      <option value="">Select country</option>
                      {countries.map((country) => (
                        <option key={country} value={country}>{country}</option>
                      ))}
                    </select>
                  </div>
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    State <span className="text-red-500">*</span>
                  </label>
                  <div className="relative">
                    <MapPin className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <select
                      value={formData.state}
                      onChange={(e) => handleInputChange('state', e.target.value)}
                      disabled={!formData.country}
                      className={`${selectClasses} pl-8 ${!formData.country ? 'opacity-50 cursor-not-allowed' : ''}`}
                    >
                      <option value="">Select state</option>
                      {getStatesForCountry(formData.country).map((state) => (
                        <option key={state} value={state}>{state}</option>
                      ))}
                    </select>
                  </div>
                </div>

                <div className="md:col-span-2">
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    City <span className="text-red-500">*</span>
                  </label>
                  <div className="relative max-w-md mx-auto">
                    <MapPin className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <select
                      value={formData.city}
                      onChange={(e) => handleInputChange('city', e.target.value)}
                      disabled={!formData.state}
                      className={`${selectClasses} pl-8 ${!formData.state ? 'opacity-50 cursor-not-allowed' : ''}`}
                    >
                      <option value="">Select city</option>
                      {getCitiesForState(formData.country, formData.state).map((city) => (
                        <option key={city} value={city}>{city}</option>
                      ))}
                    </select>
                  </div>
                </div>
              </div>
            )}

            {currentStep === 2 && (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    GUJCET Marks <span className="text-red-500">*</span>
                  </label>
                  <div className="relative">
                    <Trophy className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <input
                      type="number"
                      value={formData.gujcetMarks}
                      onChange={(e) => handleInputChange('gujcetMarks', e.target.value)}
                      placeholder="GUJCET marks"
                      className={`${inputClasses} pl-8`}
                      min="0"
                      step="0.01"
                    />
                  </div>
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    GUJCET Percentile <span className="text-red-500">*</span>
                  </label>
                  <div className="relative">
                    <Award className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <input
                      type="number"
                      value={formData.gujcetPercentileRank}
                      onChange={(e) => handleInputChange('gujcetPercentileRank', e.target.value)}
                      placeholder="Percentile (0-100)"
                      className={`${inputClasses} pl-8`}
                      min="0"
                      max="100"
                      step="0.01"
                    />
                  </div>
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    ACPC Rank
                  </label>
                  <input
                    type="number"
                    value={formData.acpcRank}
                    onChange={(e) => handleInputChange('acpcRank', e.target.value)}
                    placeholder="ACPC rank (optional)"
                    className={inputClasses}
                    min="1"
                  />
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    12th Percentile <span className="text-red-500">*</span>
                  </label>
                  <div className="relative">
                    <Award className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <input
                      type="number"
                      value={formData.twelfthPercentileRank}
                      onChange={(e) => handleInputChange('twelfthPercentileRank', e.target.value)}
                      placeholder="12th percentile (0-100)"
                      className={`${inputClasses} pl-8`}
                      min="0"
                      max="100"
                      step="0.01"
                    />
                  </div>
                </div>
              </div>
            )}

            {currentStep === 3 && (
              <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Program <span className="text-red-500">*</span>
                  </label>
                  <select
                    value={formData.program}
                    onChange={(e) => {
                      handleInputChange('program', e.target.value);
                      setSelectedInstituteForm("");
                      handleInputChange('instituteName', '');
                      handleInputChange('instituteId', '');
                      handleInputChange('courseName', '');
                      handleInputChange('courseId', '');
                    }}
                    className={selectClasses}
                  >
                    <option value="">Choose program</option>
                    {programOptions.map((program) => (
                      <option key={program} value={program}>
                        {program.charAt(0) + program.slice(1).toLowerCase()}
                      </option>
                    ))}
                  </select>
                </div>

                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Category <span className="text-red-500">*</span>
                  </label>
                  <select
                    value={formData.category}
                    onChange={(e) => {
                      handleInputChange('category', e.target.value);
                      setSelectedInstituteForm("");
                      handleInputChange('instituteName', '');
                      handleInputChange('instituteId', '');
                      handleInputChange('courseName', '');
                      handleInputChange('courseId', '');
                    }}
                    className={selectClasses}
                  >
                    <option value="">Select category</option>
                    {categoryOptions.map((category) => (
                      <option key={category} value={category}>{category}</option>
                    ))}
                  </select>
                </div>
                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Institute <span className="text-red-500">*</span>
                  </label>
                  <div className="relative">
                    <BookOpen className="absolute left-2 top-1/2 transform -translate-y-1/2 w-4 h-4 text-gray-400" />
                    <select
                      value={formData.instituteName}
                      onChange={(e) => {
                        const selectedInstitute = sampleInstitutes?.find(
                          (inst) => inst.instituteName === e.target.value
                        );

                        handleInputChange("instituteName", e.target.value);
                        handleInputChange("instituteId", selectedInstitute?.id || "");
                        setSelectedInstituteForm(e.target.value);

                        // Reset dependent fields
                        handleInputChange("courseName", "");
                        handleInputChange("courseId", "");
                      }}
                      disabled={!formData.program || !formData.category || loading}
                      className={`${selectClasses} pl-8 ${!formData.program || !formData.category || loading
                          ? "opacity-50 cursor-not-allowed"
                          : ""
                        }`}
                    >
                      <option value="">Choose institute</option>
                      {loading ? (
                        <option value="">Loading...</option>
                      ) : (
                        sampleInstitutes.map((institute) => (
                          <option key={institute.id} value={institute.instituteName}>
                            {institute.instituteName}
                          </option>
                        ))
                      )}
                    </select>
                  </div>
                </div>


                <div>
                  <label className="block text-sm font-semibold text-gray-700 mb-1">
                    Course <span className="text-red-500">*</span>
                  </label>
                  <select
                    value={formData.courseName}
                    onChange={(e) => {
                      const selectedCourse = sampleCourses?.find(course => course.name === e.target.value);
                      handleInputChange('courseName', e.target.value);
                      handleInputChange('courseId', selectedCourse?.id || e.target.value);
                    }}
                    disabled={!formData.instituteId || loading}
                    className={`${selectClasses} ${(!formData.instituteId || loading) ? 'opacity-50 cursor-not-allowed' : ''}`}
                  >
                    <option value="">Select course</option>
                    {loading ? (
                      <option value="">Loading...</option>
                    ) : (
                      sampleCourses.map((course) => (
                        <option key={course.id} value={course.name}>
                          {course.name}
                        </option>
                      ))
                    )}
                  </select>
                </div>
              </div>
            )}
          </div>
        </div>
      </div>
    );
  };

  return (
    <div className="fixed top-20 left-100 w-[calc(100%-26rem)] h-[calc(100vh-5rem)] bg-transparent overflow-hidden">
      <div className="h-full flex flex-col p-4 relative">
        {/* Close Button */}
        <button
          onClick={handleClose}
          className="absolute top-6 right-6 text-gray-400 hover:text-gray-700 transition-colors duration-200"
          aria-label="Close"
        >
          <X size={22} strokeWidth={2} />
        </button>

        {/* Header */}
        <div className="text-center py-2">
          <h1 className="text-2xl font-bold text-gray-800 mb-1">
            Admission Enquiry Form
          </h1>
          <p className="text-gray-600 text-sm">
            Take the first step towards your academic journey
          </p>
        </div>

        {/* Progress Steps */}
        <div className="py-3">
          <div className="flex items-center justify-center space-x-6">
            {steps.map((step, index) => (
              <div key={index} className="flex items-center">
                <div className={`flex items-center justify-center w-8 h-8 rounded-full border-2 transition-all duration-300 ${index <= currentStep
                  ? 'bg-green-500 border-green-500 text-white'
                  : 'bg-white border-gray-300 text-gray-400'
                  }`}>
                  {index < currentStep ? (
                    <Check className="w-4 h-4" />
                  ) : (
                    <span className="font-bold text-xs">{index + 1}</span>
                  )}
                </div>
                {index < steps.length - 1 && (
                  <div className={`w-12 h-0.5 mx-2 transition-all duration-300 ${index < currentStep ? 'bg-green-500' : 'bg-gray-300'
                    }`} />
                )}
              </div>
            ))}
          </div>
          <div className="flex justify-center mt-1">
            <p className="text-gray-600 text-xs">
              Step {currentStep + 1} of {steps.length}
            </p>
          </div>
        </div>

        {/* Form Content */}
        <div className="flex-1 bg-white rounded-xl shadow-lg p-4 flex flex-col overflow-hidden min-h-0">
          <div className="flex-1 overflow-y-auto overflow-x-hidden px-2" style={{ maxHeight: 'calc(100vh - 20rem)' }}>
            {renderStepContent()}
          </div>

          {/* Navigation Buttons */}
          <div className="flex justify-between items-center pt-3 border-t border-gray-200">
            <button
              onClick={handlePrevious}
              disabled={currentStep === 0}
              className={`flex items-center px-3 py-2 rounded-lg font-semibold transition-all duration-200 text-sm ${currentStep === 0
                ? 'bg-gray-100 text-gray-400 cursor-not-allowed'
                : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                }`}
            >
              <ChevronLeft className="w-4 h-4 mr-1" />
              Previous
            </button>

            {currentStep === steps.length - 1 ? (
              <button
                onClick={handleSubmit}
                disabled={!isStepValid() || loading}
                className={`flex items-center px-4 py-2 rounded-lg font-semibold transition-all duration-200 text-sm ${isStepValid() && !loading
                  ? 'bg-gradient-to-r from-green-500 to-emerald-600 text-white hover:from-green-600 hover:to-emerald-700 shadow-lg'
                  : 'bg-gray-300 text-gray-500 cursor-not-allowed'
                  }`}
              >
                {loading ? 'Submitting...' : 'Submit Application'}
                <Trophy className="w-4 h-4 ml-1" />
              </button>
            ) : (
              <button
                onClick={handleNext}
                disabled={!isStepValid()}
                className={`flex items-center px-3 py-2 rounded-lg font-semibold transition-all duration-200 text-sm ${isStepValid()
                  ? 'bg-gradient-to-r from-green-500 to-emerald-600 text-white hover:from-green-600 hover:to-emerald-700 shadow-lg'
                  : 'bg-gray-300 text-gray-500 cursor-not-allowed'
                  }`}
              >
                Next
                <ChevronRight className="w-4 h-4 ml-1" />
              </button>
            )}
          </div>
        </div>
      </div>
       <ToastContainer toasts={toasts} removeToast={removeToast} />
    </div>
   
  );
};

export default AdmissionForm;