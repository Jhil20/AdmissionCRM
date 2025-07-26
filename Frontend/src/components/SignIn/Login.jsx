import { useState } from 'react';
import { Mail, Lock, ArrowRight, Eye, EyeOff } from 'lucide-react';

export default function Login({ onLogin }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [isLoading, setIsLoading] = useState(false);

  const handleSubmit = async () => {
    setError('');
    setIsLoading(true);
    
    try {
      const res = await fetch('http://localhost:8000/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ "identifier": email, "password": password }),
      });
      
      if (!res.ok) throw new Error('Invalid credentials');
      const data = await res.json();
      console.log(data);
      
      if (onLogin) onLogin(data.jwtToken);
    } catch (err) {
      setError(err.message);
    } finally {
      setIsLoading(false);
    }
  };

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
          <div className="absolute bottom-1/4 left-1/3 w-32 h-32 bg-white/10 rounded-full blur-xl animate-pulse" style={{animationDelay: '1s'}}></div>
        </div>

        {/* Content - Fixed Position */}
        <div className="relative z-10 px-8 md:px-16 max-w-2xl mx-auto">
          <h1 className="text-5xl md:text-6xl font-bold mb-6 leading-tight text-white">
            Welcome to
            <br />
            <span className="text-emerald-100">EduCRM</span>
            <br />
            <span className="text-teal-100">Admission Suite</span>
          </h1>
          <p className="text-xl md:text-2xl text-emerald-50 leading-relaxed font-light mb-8">
            Transform your institution's admission process with our comprehensive CRM platform.
          </p>
          
          {/* Stats - Fixed Grid */}
          <div className="grid grid-cols-3 gap-8 mb-8">
            <div>
              <div className="text-3xl font-bold text-white">200+</div>
              <div className="text-emerald-200 text-sm">Institutions</div>
            </div>
            <div>
              <div className="text-3xl font-bold text-white">98%</div>
              <div className="text-emerald-200 text-sm">Success Rate</div>
            </div>
            <div>
              <div className="text-3xl font-bold text-white">24/7</div>
              <div className="text-emerald-200 text-sm">Support</div>
            </div>
          </div>
        </div>

        {/* Footer - Fixed Position */}
        <div className="absolute bottom-6 left-8 text-white/60 text-sm">
          © 2025 EduCRM. All rights reserved.
        </div>
      </div>

      {/* Right Side - Login Form with Light Gradient */}
      <div className="w-full md:w-2/5 h-full bg-gradient-to-br from-gray-50 via-emerald-50/30 to-teal-50/20 flex items-center justify-center p-6 md:p-8 relative">
        {/* Subtle background shapes for right side */}
        <div className="absolute inset-0 overflow-hidden">
          <div className="absolute top-20 right-10 w-32 h-32 bg-emerald-100/20 rounded-full blur-xl"></div>
          <div className="absolute bottom-20 left-10 w-24 h-24 bg-teal-100/15 rounded-full blur-lg"></div>
          <div className="absolute top-1/2 right-20 w-16 h-16 bg-cyan-100/25 rounded-full blur-md"></div>
        </div>

        <div className="w-full max-w-sm relative z-10">
          {/* Header */}
          <div className="text-center mb-8">
            <div className="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-br from-emerald-500 to-teal-600 rounded-2xl mb-4 shadow-lg">
              <Mail className="h-8 w-8 text-white" />
            </div>
            <h2 className="text-2xl font-bold text-gray-800 mb-2">USER LOGIN</h2>
            <p className="text-gray-600 text-sm">Sign in to your account</p>
          </div>

          {/* Login Form */}
          <div className="bg-white/80 backdrop-blur-sm rounded-2xl p-6 shadow-xl border border-white/50">
            <div className="space-y-5">
              {/* Email Field */}
              <div>
                <input
                  type="email"
                  placeholder="Email address"
                  value={email}
                  onChange={e => setEmail(e.target.value)}
                  className="w-full px-4 py-3 bg-gray-50/50 border border-gray-200/50 rounded-xl focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 focus:bg-white outline-none transition-all duration-200 text-gray-700 placeholder:text-gray-400"
                />
              </div>

              {/* Password Field */}
              <div className="relative">
                <input
                  type={showPassword ? "text" : "password"}
                  placeholder="Password"
                  value={password}
                  onChange={e => setPassword(e.target.value)}
                  className="w-full px-4 py-3 pr-12 bg-gray-50/50 border border-gray-200/50 rounded-xl focus:ring-2 focus:ring-emerald-500/20 focus:border-emerald-500 focus:bg-white outline-none transition-all duration-200 text-gray-700 placeholder:text-gray-400"
                />
                <button
                  type="button"
                  onClick={() => setShowPassword(!showPassword)}
                  className="absolute right-4 top-1/2 -translate-y-1/2 text-gray-400 hover:text-gray-600 transition-colors"
                >
                  {showPassword ? <EyeOff className="h-5 w-5" /> : <Eye className="h-5 w-5" />}
                </button>
              </div>

              {/* Error Message */}
              {error && (
                <div className="bg-red-50/80 border border-red-200/50 text-red-600 px-4 py-3 rounded-xl text-sm backdrop-blur-sm">
                  {error}
                </div>
              )}

              {/* Login Button */}
              <button
                onClick={handleSubmit}
                disabled={isLoading}
                className="w-full bg-gradient-to-r from-emerald-500 to-teal-600 text-white py-3 rounded-xl font-semibold hover:from-emerald-600 hover:to-teal-700 transition-all duration-200 shadow-lg hover:shadow-xl transform hover:-translate-y-0.5 disabled:opacity-50 disabled:cursor-not-allowed disabled:transform-none flex items-center justify-center"
              >
                {isLoading ? (
                  <>
                    <div className="w-5 h-5 border-2 border-white/30 border-t-white rounded-full animate-spin mr-2"></div>
                    Signing In...
                  </>
                ) : (
                  <>
                    LOGIN
                    <ArrowRight className="ml-2 h-4 w-4" />
                  </>
                )}
              </button>

              {/* Additional Options */}
              <div className="flex items-center justify-between text-sm">
                <label className="flex items-center text-gray-600 cursor-pointer">
                  <input type="checkbox" className="rounded mr-2 focus:ring-emerald-500 text-emerald-600" />
                  Remember me
                </label>
                <button 
                  onClick={() => window.location.href = '/forgot-password'} 
                  className="text-emerald-600 hover:text-emerald-700 font-medium transition-colors"
                >
                  Forgot Password?
                </button>
              </div>
            </div>
          </div>

          {/* Sign Up Link */}
          <div className="text-center mt-6">
            <p className="text-gray-600 text-sm">
              New user?{' '}
              <button
                onClick={() => window.location.href = '/signup'}
                className="font-semibold text-emerald-600 hover:text-emerald-700 transition-colors"
              >
                Create account
              </button>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}