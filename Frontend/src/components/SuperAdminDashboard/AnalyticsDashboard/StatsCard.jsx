import React from 'react';
import { 
  BarChart3,
  Users,
  Building2,
  GraduationCap,
  MessageSquare,
  Bell,
  User,
  Users2,
  Clock,
  CheckCircle,
  TrendingUp
} from 'lucide-react';

const StatsCard = ({ title, value, icon: Icon, color, growth }) => {
  return (
    <div className="bg-white rounded-lg shadow-sm border p-6">
      <div className="text-center">
        <p className="text-gray-600 text-sm font-medium mb-2">{title}</p>
        <div className="flex items-center justify-center mb-2">
          <p className="text-3xl font-bold text-gray-900 mr-3">{value}</p>
          <div className={`rounded-lg p-2 ${color}`}>
            <Icon className="w-5 h-5 text-white" />
          </div>
        </div>
        {growth && (
          <div className="flex items-center justify-center text-sm text-green-600">
            {growth.icon}
            <span>{growth.text}</span>
          </div>
        )}
      </div>
    </div>
  );
};

export default StatsCard;