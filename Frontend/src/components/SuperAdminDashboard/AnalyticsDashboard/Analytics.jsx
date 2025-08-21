import React from 'react';
import { Users2, Clock, CheckCircle, TrendingUp } from 'lucide-react';
import StatsCard from './StatsCard';

const Analytics = () => {
  return (
    <div className="py-3 sm:py-4">
      <div className="flex flex-col sm:flex-row sm:justify-between sm:items-center mb-3 sm:mb-4 px-4 sm:px-6">
        <div className="mb-3 sm:mb-0  ">
          <h2 className="text-xl sm:text-2xl font-bold text-green-600 leading-tight ml-[-90px]  ">Analytics Dashboard</h2>
          <p className="text-gray-600 mt-1 text-sm sm:text-base">Monitor your admission process performance</p>
        </div>

        <button className="bg-green-500 text-white px-4 py-2 sm:px-6 sm:py-3 rounded-lg font-medium hover:bg-green-600 transition-colors text-sm sm:text-base">
          Export Report
        </button>
      </div>

      <div className="grid grid-cols-2 lg:grid-cols-4 gap-3 sm:gap-6 mb-3 sm:mb-4 px-4 sm:px-6">
        <StatsCard
          title="Total Leads"
          value="1,247"
          icon={Users2}
          color="bg-green-500"
          growth={{ icon: <TrendingUp className="w-3 h-3 sm:w-4 sm:h-4 mr-1" />, text: '+12%' }}
        />
        <StatsCard
          title="Pending Leads"
          value="89"
          icon={Clock}
          color="bg-orange-500"
        />
        <StatsCard
          title="Solved Leads"
          value="1,158"
          icon={CheckCircle}
          color="bg-green-500"
          growth={{ icon: <TrendingUp className="w-3 h-3 sm:w-4 sm:h-4 mr-1" />, text: '+8%' }}
        />
        <StatsCard
          title="Conversion Rate"
          value="92.9%"
          icon={TrendingUp}
          color="bg-purple-500"
          growth={{ icon: <TrendingUp className="w-3 h-3 sm:w-4 sm:h-4 mr-1" />, text: '+2.1%' }}
        />
      </div>
    </div>
  );
};



export default Analytics;