import React from 'react';
import { MessageSquare, Plus, AlertCircle } from 'lucide-react';

const QueryAdmissionRequired = ({ onFillEnquiry }) => {
  return (
    <div className="flex-1 bg-gray-50 overflow-auto">
      {/* Header */}
      <div className="bg-white border-b border-gray-200">
        <div className="flex items-center justify-between p-6">
          <div>
            <h1 className="text-2xl font-bold text-gray-900">Queries</h1>
            <p className="text-gray-600 mt-1">Ask questions about courses and admissions</p>
          </div>
          <div className="w-10 h-10 bg-green-500 rounded-full flex items-center justify-center">
            <MessageSquare className="w-5 h-5 text-white" />
          </div>
        </div>
      </div>

      {/* Enquiry Form Required Message */}
      <div className="flex items-center justify-center min-h-[calc(100vh-200px)] p-6">
        <div className="bg-white rounded-lg shadow-sm border border-gray-200 max-w-md w-full">
          <div className="text-center py-12 px-6">
            <div className="w-16 h-16 bg-orange-100 rounded-full flex items-center justify-center mx-auto mb-6">
              <AlertCircle className="w-8 h-8 text-orange-600" />
            </div>
            <h2 className="text-xl font-semibold text-gray-900 mb-2">
              Admission Enquiry Required
            </h2>
            <p className="text-gray-600 mb-8">
              You must submit an admission enquiry form before you can ask queries about courses and admissions.
            </p>
            <button
              onClick={onFillEnquiry}
              className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-lg font-medium transition-colors inline-flex items-center space-x-2"
            >
              <Plus className="w-5 h-5" />
              <span>Fill Admission Enquiry Form</span>
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default QueryAdmissionRequired;