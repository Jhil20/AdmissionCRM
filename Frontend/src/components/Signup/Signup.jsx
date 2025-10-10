import { useEffect, useState } from 'react';
import { UserPlus, Lock, ArrowRight, Eye, EyeOff, Mail, User, Phone } from 'lucide-react';
import { ToastContainer, useToast } from '../Toast/ToastSystem.jsx'

export default function Signup({ onSignup }) {
  const [email, setEmail] = useState('');
  // const [password, setPassword] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  // const [username, setUsername] = useState('');
  const [mobileNumber, setMobileNumber] = useState('');
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  // const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const { toasts, removeToast, showSuccess, showError, showWarning } = useToast();


  const handleSubmit = async () => {
    setError('');
    setSuccess('');
    setIsLoading(true);

    try {
      const res = await fetch('http://localhost:8000/auth/student/register', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          "mobileNumber": mobileNumber,
          "email": email,
          // "username": username,
          "firstName": firstName,
          "lastName": lastName,
          // "password": password
        }),
      });

      if (!res.ok) {
        // throw new Error('Signup failed');
        showError('Signup failed. Please check your details and try again.');
        return
      }
      // setSuccess('Signup successful! You are logged in.');
      const data = await res.json();
      showSuccess('Signup successful! You are logged in.');

       if (onSignup) {
      onSignup(data);
    }
}

    // You can handle the successful signup data here
   catch (err) {
    showError(err.message);
    // setError(err.message);
  } finally {
    setIsLoading(false);
  }
};

useEffect(() => {
  if (error) {
    showError(error);
  }
}, [error])

useEffect(() => {
  if (success) {
    showSuccess(success);
  }
}, [success])

return (
  <div className="h-screen w-full flex overflow-hidden fixed inset-0">
    {/* Left Side - Welcome with Geometric Shapes */}
    <div className="w-full md:w-3/5 h-full bg-gradient-to-br from-emerald-500 via-teal-500 to-cyan-600 relative overflow-hidden flex flex-col justify-center">
      {/* Geometric Shapes Layer */}
      <div className="absolute inset-0">
        {/* Top Layer Shapes */}
        <div className="absolute top-10 left-10 w-96 h-32 bg-gradient-to-r from-emerald-400/30 to-transparent rounded-3xl transform rotate-12 blur-2xl"></div>
        <div className="absolute top-20 right-20 w-80 h-80 bg-gradient-to-l from-teal-400/20 to-transparent rounded-full transform -rotate-12 blur-3xl"></div>

        {/* Middle Layer Shapes */}
        <div className="absolute top-1/3 left-1/4 w-72 h-72 bg-gradient-to-tr from-cyan-400/25 to-transparent rounded-full blur-2xl"></div>
        <div className="absolute top-1/2 right-1/3 w-64 h-64 bg-gradient-to-bl from-emerald-300/30 to-transparent rounded-full blur-2xl"></div>

        {/* Bottom Layer Shapes */}
        <div className="absolute bottom-20 left-20 w-80 h-40 bg-gradient-to-r from-teal-300/40 to-transparent rounded-2xl transform -rotate-6 blur-2xl"></div>
        <div className="absolute bottom-40 right-10 w-96 h-32 bg-gradient-to-l from-cyan-400/30 to-transparent rounded-3xl transform rotate-12 blur-2xl"></div>

        {/* Floating Accent Shapes */}
        <div className="absolute top-1/4 right-1/4 w-24 h-24 bg-white/10 rounded-full blur-xl animate-pulse"></div>
        <div className="absolute bottom-1/4 left-1/3 w-32 h-32 bg-white/10 rounded-full blur-xl animate-pulse" style={{ animationDelay: '1s' }}></div>
      </div>

      {/* Content - Fixed Position */}
      <div className="relative z-10 px-8 md:px-16 max-w-2xl mx-auto">
        <h1 className="text-5xl md:text-6xl font-bold mb-6 leading-tight text-white">
          Join
          <br />
          <span className="text-emerald-100">Pravesh Path</span>
          <br />
          <span className="text-teal-100">Community</span>
        </h1>
        <p className="text-xl md:text-2xl text-emerald-50 leading-relaxed font-light mb-8">
          Create your account and start your educational journey with our comprehensive CRM platform.
        </p>

        {/* Features - Fixed Grid */}
        <div className="grid grid-cols-2 gap-8 mb-8">
          <div>
            <div className="text-2xl font-bold text-white mb-2">✨ Easy Setup</div>
            <div className="text-emerald-200 text-sm">Get started in minutes</div>
          </div>
          <div>
            <div className="text-2xl font-bold text-white mb-2">🔒 Secure</div>
            <div className="text-emerald-200 text-sm">Your data is protected</div>
          </div>
          <div>
            <div className="text-2xl font-bold text-white mb-2">📚 Comprehensive</div>
            <div className="text-emerald-200 text-sm">All tools in one place</div>
          </div>
          <div>
            <div className="text-2xl font-bold text-white mb-2">🚀 Fast</div>
            <div className="text-emerald-200 text-sm">Lightning quick access</div>
          </div>
        </div>
      </div>

      {/* Footer - Fixed Position */}
      <div className="absolute bottom-6 left-8 text-white/60 text-sm">
        © 2025 Pravesh Path. All rights reserved.
      </div>
    </div>

    {/* Right Side - Signup Form with Light Gradient */}
    <div className="w-full md:w-2/5 h-full bg-gradient-to-br from-gray-50 via-emerald-50/30 to-teal-50/20 flex items-center justify-center p-6 md:p-8 relative overflow-y-auto">
      {/* Subtle background shapes for right side */}
      <div className="absolute inset-0 overflow-hidden">
        <div className="absolute top-20 right-10 w-32 h-32 bg-emerald-100/20 rounded-full blur-xl"></div>
        <div className="absolute bottom-20 left-10 w-24 h-24 bg-teal-100/15 rounded-full blur-lg"></div>
        <div className="absolute top-1/2 right-20 w-16 h-16 bg-cyan-100/25 rounded-full blur-md"></div>
      </div>

      <div className="w-full max-w-sm relative z-10 my-8">
        {/* Header */}
        <div className="text-center mb-6">
          <div className="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-br from-emerald-500 to-teal-600 rounded-2xl mb-4 shadow-lg">
            <UserPlus className="h-8 w-8 text-white" />
          </div>
          <h2 className="text-2xl font-bold text-gray-800 mb-2">CREATE ACCOUNT</h2>
          <p className="text-gray-600 text-sm">Join our educational platform</p>
        </div>

        {/* Signup Form */}
        <div className="bg-white/80 backdrop-blur-sm rounded-2xl p-6 shadow-xl border border-white/50">
          <div className="space-y-4">
            {/* Name Fields Row */}
            <div className="grid grid-cols-2 gap-3">
              <input
                type="text"
                placeholder="First Name"
                value={firstName}
                onChange={e => setFirstName(e.target.value)}
                required
                className="w-full px-3 py-2.5 bg-gray-50/50 border border-gray-200/50 rounded-xl focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 focus:bg-white outline-none transition-all duration-200 text-gray-700 placeholder:text-gray-400 text-sm"
              />
              <input
                type="text"
                placeholder="Last Name"
                value={lastName}
                onChange={e => setLastName(e.target.value)}
                required
                className="w-full px-3 py-2.5 bg-gray-50/50 border border-gray-200/50 rounded-xl focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 focus:bg-white outline-none transition-all duration-200 text-gray-700 placeholder:text-gray-400 text-sm"
              />
            </div>


            {/* Email Field */}
            <div className="relative">
              <Mail className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-gray-400" />
              <input
                type="email"
                placeholder="Email address"
                value={email}
                onChange={e => setEmail(e.target.value)}
                required
                className="w-full px-3 py-2.5 pl-10 bg-gray-50/50 border border-gray-200/50 rounded-xl focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 focus:bg-white outline-none transition-all duration-200 text-gray-700 placeholder:text-gray-400 text-sm"
              />
            </div>

            {/* Mobile Number Field */}
            <div className="relative">
              <Phone className="absolute left-3 top-1/2 -translate-y-1/2 h-4 w-4 text-gray-400" />
              <input
                type="tel"
                placeholder="Mobile Number"
                value={mobileNumber}
                onChange={e => setMobileNumber(e.target.value)}
                required
                className="w-full px-3 py-2.5 pl-10 bg-gray-50/50 border border-gray-200/50 rounded-xl focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 focus:bg-white outline-none transition-all duration-200 text-gray-700 placeholder:text-gray-400 text-sm"
              />
            </div>


            {/* Error Message */}
            {/* {error && (
                <div className="bg-red-50/80 border border-red-200/50 text-red-600 px-4 py-3 rounded-xl text-sm backdrop-blur-sm">
                  {error}
                </div>
              )} */}



            {/* Success Message */}
            {/* {success && (
                <div className="bg-green-50/80 border border-green-200/50 text-green-600 px-4 py-3 rounded-xl text-sm backdrop-blur-sm">
                  {success}
                </div>
              )} */}

            {/* Terms Agreement */}
            <div className="flex items-start text-xs text-gray-600">
              <input
                type="checkbox"
                required
                className="rounded mr-2 mt-0.5 focus:ring-emerald-500 text-emerald-600"
              />
              <span>
                I agree to the{' '}
                <button type="button" className="text-emerald-600 hover:text-emerald-700 font-medium">
                  Terms of Service
                </button>
                {' '}and{' '}
                <button type="button" className="text-emerald-600 hover:text-emerald-700 font-medium">
                  Privacy Policy
                </button>
              </span>
            </div>

            {/* Signup Button */}
            <button
              onClick={handleSubmit}
              disabled={isLoading}
              className="w-full bg-gradient-to-r from-emerald-500 to-teal-600 text-white py-3 rounded-xl font-semibold hover:from-emerald-600 hover:to-teal-700 transition-all duration-200 shadow-lg hover:shadow-xl transform hover:-translate-y-0.5 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none flex items-center justify-center text-sm"
            >
              {isLoading ? (
                <>
                  <div className="w-4 h-4 border-2 border-white/30 border-t-white rounded-full animate-spin mr-2"></div>
                  Creating Account...
                </>
              ) : (
                <>
                  CREATE ACCOUNT
                  <ArrowRight className="ml-2 h-4 w-4" />
                </>
              )}
            </button>
          </div>
        </div>

        {/* Login Link */}
        <div className="text-center mt-4">
          <p className="text-gray-600 text-sm">
            Already have an account?{' '}
            <button
              type="button"
              onClick={() => {
                // Handle navigation to login - you can customize this
                if (window.location) {
                  window.location.href = '/signin';
                }
              }}
              className="font-semibold text-emerald-600 hover:text-emerald-700 transition-colors"
            >
              Sign in
            </button>
          </p>
        </div>
      </div>
    </div>
    <ToastContainer toasts={toasts} removeToast={removeToast} />
  </div>
);
}