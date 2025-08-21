import React from 'react';
import {
  BarChart3,
  Users,
  Building2,
  GraduationCap,
  MessageSquare
} from 'lucide-react';
import { useNavigate, useLocation } from 'react-router-dom';

const Sidebar = () => {
  const navigate = useNavigate();
  const location = useLocation();

  const menuItems = [
    { id: 'analytics', icon: BarChart3, label: 'Analytics Dashboard', route: '/instituteadmin/dashboard' },
    { id: 'users', icon: Users, label: 'Counsellor Management', route: '/instituteadmin/counsellor-management' },
    { id: 'leads', icon: Users, label: 'Lead Management', route: '/instituteadmin/lead-management' },
  ];

  return (
    <div className="w-80 bg-white shadow-lg flex flex-col flex-shrink-0 fixed h-full">
      <div className="p-6 border-b">
        <div className="flex items-center space-x-3">
          <div className="bg-green-500 rounded-lg p-2">
            <span className="text-white font-bold text-lg">E</span>
          </div>
          <span className="text-xl font-bold text-gray-900">EduCRM</span>
        </div>
      </div>

      <div className="flex-1 p-6 overflow-y-auto">
        <div className="space-y-2">
          {menuItems.map((item) => {
            const isActive = location.pathname === item.route;
            return (
              <div
                key={item.id}
                onClick={() => navigate(item.route)}
                className={`p-4 flex items-center space-x-3 rounded-lg cursor-pointer transition-colors ${
                  isActive ? 'bg-green-500 text-white' : 'text-gray-600 hover:bg-gray-50'
                }`}
              >
                <item.icon className="w-5 h-5" />
                <span className="font-medium">{item.label}</span>
              </div>
            );
          })}
        </div>
      </div>

      <div className="p-6 border-t">
        <div className="flex items-center space-x-3">
          <div className="bg-gray-400 rounded-full w-10 h-10 flex items-center justify-center">
            <span className="text-white font-bold">A</span>
          </div>
          <div>
            <div className="font-medium text-gray-900">Admin User</div>
            <div className="text-sm text-gray-500">Institute Admin</div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Sidebar;
