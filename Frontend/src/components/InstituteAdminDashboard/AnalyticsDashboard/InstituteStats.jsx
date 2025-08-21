import React from 'react';

const InstituteStats = () => {
  return (
    <div className="bg-white rounded-lg shadow-sm border p-4 sm:p-6 h-full">
      <h3 className="text-lg sm:text-xl font-bold text-gray-900 mb-4 sm:mb-6">Institute Distribution</h3>
      <div className="space-y-4 sm:space-y-6">
        <div className="flex justify-between items-center">
          <span className="text-gray-600 font-medium text-sm sm:text-base">Total Institutes</span>
          <span className="font-bold text-2xl sm:text-3xl text-gray-900">3</span>
        </div>
        <div className="flex justify-between items-center">
          <span className="text-gray-600 font-medium text-sm sm:text-base">Total Admins</span>
          <span className="font-bold text-2xl sm:text-3xl text-gray-900">1</span>
        </div>
        <div className="flex justify-between items-center">
          <span className="text-gray-600 font-medium text-sm sm:text-base">Total Counsellors</span>
          <span className="font-bold text-2xl sm:text-3xl text-gray-900">1</span>
        </div>
      </div>
    </div>
  );
};


export default InstituteStats;