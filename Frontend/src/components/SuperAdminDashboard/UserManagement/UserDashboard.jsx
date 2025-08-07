import React, { useState } from 'react';
import UserHeader from './UserHeader';
import UserFilter from './UserFilter';
import UserList from './UserList';
import Sidebar from '../../SideBar';
import Header from '../../Header';

const users = [
  {
    id: 1,
    name: 'Dr. Rajesh Sharma',
    email: 'rajesh@mit.edu',
    role: 'Institutional Admin',
    institute: 'MIT Engineering College',
    contact: '+91-9876543210',
    status: 'Active',
    avatar: 'DRS',
    avatarBg: 'bg-green-500',
  },
  {
    id: 2,
    name: 'Priya Patel',
    email: 'priya@mit.edu',
    role: 'Counsellor',
    institute: 'MIT Engineering College',
    contact: '+91-9876543211',
    status: 'Active',
    avatar: 'PP',
    avatarBg: 'bg-green-500',
  }
];

const UserDashboard = () => {
  const [selectedRole, setSelectedRole] = useState('All Roles');
  const [searchTerm, setSearchTerm] = useState('');

  const filteredUsers = users.filter(user =>
    (selectedRole === 'All Roles' || user.role === selectedRole) &&
    (user.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
     user.email.toLowerCase().includes(searchTerm.toLowerCase()))
  );

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
      <div className="fixed left-0 top-0 h-full z-20">
        <Sidebar />
      </div>

      <div className="flex-1 flex flex-col min-w-0 ml-80">
        <Header />
        <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
          <UserHeader />
          <UserFilter
            searchTerm={searchTerm}
            setSearchTerm={setSearchTerm}
            selectedRole={selectedRole}
            setSelectedRole={setSelectedRole}
          />
          <UserList filteredUsers={filteredUsers} />
        </div>
      </div>
    </div>
  );
};

export default UserDashboard;
