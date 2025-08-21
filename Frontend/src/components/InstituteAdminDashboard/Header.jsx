import React from 'react';
import { Bell, User } from 'lucide-react';

const Header = () => {
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

        {/* Right: Notification and User Info */}
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
            <span className="text-gray-700 font-medium text-base hidden sm:block">Institute Admin</span>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Header;
