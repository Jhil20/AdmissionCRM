import React from 'react';
import { HelpCircle, CheckCircle, XCircle } from 'lucide-react';

const QuerySelectionModal = ({ onYes, onNo, loading }) => {
    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
            <div className="bg-white rounded-lg shadow-xl max-w-md w-full animate-fadeIn">
                <div className="p-6">
                    {/* Icon */}
                    <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center mx-auto mb-4">
                        <HelpCircle className="w-8 h-8 text-green-600" />
                    </div>

                    {/* Title */}
                    <h2 className="text-xl font-bold text-gray-900 text-center mb-2">
                        Inquiry Submitted Successfully!
                    </h2>

                    {/* Message */}
                    <p className="text-gray-600 text-center mb-6">
                        Do you have any queries regarding courses, admission process, or scholarships?
                    </p>

                    {/* Buttons */}
                    <div className="grid grid-cols-2 gap-4">
                        <button
                            onClick={onNo}
                            // disabled={loading}
                            className="flex items-center justify-center space-x-2 px-6 py-3 border-2 border-gray-300 text-gray-700 rounded-lg hover:bg-gray-50 transition font-medium disabled:opacity-50 disabled:cursor-not-allowed"
                        >
                            <XCircle className="w-5 h-5" />
                            <span>No</span>
                        </button>
                        <button
                            onClick={onYes}
                            // disabled={loading}
                            className="flex items-center justify-center space-x-2 px-6 py-3 bg-green-500 hover:bg-green-600 text-white rounded-lg transition font-medium disabled:opacity-50 disabled:cursor-not-allowed"
                        >
                            <CheckCircle className="w-5 h-5" />
                            <span>Yes</span>
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default QuerySelectionModal;