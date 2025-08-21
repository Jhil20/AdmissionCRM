import React from 'react';
import Sidebar from '../Sidebar';
import Header from '../Header';
import Analytics from './Analytics';
import LeadOverview from './LeadOverview';
import InstituteStats from './InstituteStats';

const InstituteAdminDashboard = () => {
  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
      {/* Sidebar */}
      <div className="fixed left-0 top-0 h-full z-20">
        <Sidebar />
      </div>

      {/* Main Content Area */}
      <div className="flex-1 flex flex-col min-w-0 ml-80">
        {/* Fixed Header */}
        <Header />

        {/* Scrollable Content below Header */}
        <div className="flex-1 overflow-auto mt-[4.5rem]">
          <Analytics />
          <div className="grid grid-cols-2 gap-6 px-6 pb-4">
            <LeadOverview />
            <InstituteStats />
          </div>
        </div>
      </div>
    </div>
  );
};

export default InstituteAdminDashboard;
