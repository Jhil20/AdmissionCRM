import { Check, X } from 'lucide-react';

export default function SubmissionSuccess({ leadId = "", onSubmitAnother = () => {}, onClose = () => {} }) {
    const handleSubmitAnother = () => {
        if (onSubmitAnother) {
            onSubmitAnother();
        }
    };

    const handleClose = () => {
        if (onClose) {
            onClose();
        }
    };

    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center p-4 z-50">
            <div className="bg-white rounded-lg shadow-2xl p-8 max-w-md w-full text-center relative">
                {/* Close Button */}
                <button
                    onClick={handleClose}
                    className="absolute top-4 right-4 text-gray-400 hover:text-gray-600 transition-colors"
                >
                    <X className="w-6 h-6" />
                </button>

                {/* Success Icon */}
                <div className="flex justify-center mb-6">
                    <div className="bg-green-100 rounded-full p-4">
                        <Check className="w-12 h-12 text-green-600" strokeWidth={3} />
                    </div>
                </div>

                {/* Success Message */}
                <h1 className="text-3xl font-bold text-gray-900 mb-4">
                    Submission Successful!
                </h1>

                <p className="text-gray-600 mb-6">
                    Your admission inquiry has been submitted successfully. We will get back to you soon.
                </p>

                {/* Lead ID */}
                {leadId && (
                    <div className="bg-gray-50 border border-gray-200 rounded-lg p-4 mb-6">
                        <p className="text-sm text-gray-500 mb-1">Your Lead ID</p>
                        <p className="text-xl font-semibold text-gray-900 font-mono tracking-wider">
                            {leadId}
                        </p>
                    </div>
                )}

                {/* Submit Another Button */}
                <button
                    onClick={handleSubmitAnother}
                    className="w-full bg-green-500 hover:bg-green-600 text-white font-semibold py-3 px-6 rounded-lg transition-colors duration-200"
                >
                    Submit Another Inquiry
                </button>
            </div>
        </div>
    );
}