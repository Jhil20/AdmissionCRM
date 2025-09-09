import React from 'react';
import Header from './Header';
import Sidebar from './SideBar'
import { Outlet } from 'react-router-dom';

const Layout = () => {
  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden">
      <Sidebar />
      <div className="flex-1 overflow-y-auto">
        <Header />
        <Outlet />
      </div>
    </div>
  );
};

export default Layout;
