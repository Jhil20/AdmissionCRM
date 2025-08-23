import React from 'react';
import { Bell, User, LogOut } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

const Header = ({ onLogout }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    // Remove token from localStorage
    localStorage.removeItem('Token');
    localStorage.removeItem('Role')

    // Option 1: Simple redirect (works but causes full page reload)

    navigate("/signin",{replace:true});
    window.history.pushState(null, null, '/signin');

    // Option 2: React Router navigation (uncomment if using React Router)
    // navigate('/login');

    // Option 3: Parent component state update (uncomment if needed)
    // if (onLogout) {
    //   onLogout();
    // }

    console.log('User logged out successfully');
  };

  return (
      <div className="fixed top-0 left-80 w-[calc(100%-20rem)] bg-white shadow px-6 py-4 z-10">
        {/* <div className="px-4 sm:px-6"></div> */}
        <div className="flex items-center justify-between">
          {/* Left: Title and Subtitle */}
          <div className="text-left">
            <h1 className="text-xl sm:text-2xl font-bold text-gray-900">Dashboard</h1>
            <p className="text-gray-600 mt-1 text-sm sm:text-base">
              Welcome back, manage your educational platform
            </p>
          </div>

          {/* Right: Notification and Logout */}
          <div className="flex items-center space-x-4">
            <div className="relative">
              <Bell className="w-6 h-6 text-gray-600" />
              <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs rounded-full w-5 h-5 flex items-center justify-center">
              1
            </span>
            </div>
            <div className="flex items-center space-x-3">
              <div className="bg-gray-600 rounded-full w-10 h-10 flex items-center justify-center">
                <User className="w-5 h-5 text-white" />
              </div>
              <button
                  onClick={handleLogout}
                  className="flex items-center space-x-2 bg-red-500 hover:bg-red-600 text-white px-4 py-2 rounded-lg transition-colors duration-200 text-sm font-medium"
              >
                <LogOut className="w-4 h-4" />
                <span className="hidden sm:block">Logout</span>
              </button>
            </div>
          </div>
        </div>
      </div>
  );
};

export default Header;