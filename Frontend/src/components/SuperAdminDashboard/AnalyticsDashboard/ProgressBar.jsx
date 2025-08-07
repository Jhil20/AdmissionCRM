import React from 'react';

const ProgressBar = ({ label, percentage, color }) => {
  return (
    <div className="mb-6 last:mb-0">
      <div className="flex justify-between items-center mb-2">
        <span className="text-gray-700 font-medium">{label}</span>
        <span className="font-bold text-gray-900">{percentage}%</span>
      </div>
      <div className="w-full bg-gray-200 rounded-full h-3">
        <div 
          className={`${color} h-3 rounded-full transition-all duration-300`}
          style={{ width: `${percentage}%` }}
        ></div>
      </div>
    </div>
  );
};
export default ProgressBar;