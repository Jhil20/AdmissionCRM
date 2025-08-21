import React from 'react';
import ProgressBar from './ProgressBar';

const LeadOverview = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm border p-4 sm:p-6 h-full">
      <h3 className="text-lg sm:text-xl font-bold text-gray-900 mb-4 sm:mb-6">Lead Status Overview</h3>
      <div className="space-y-4 sm:space-y-6">
        <ProgressBar label="Solved Leads" percentage={92} color="bg-green-500" />
        <ProgressBar label="Pending Leads" percentage={71} color="bg-red-500" />
      </div>
    </div>
  );
};
export default LeadOverview;