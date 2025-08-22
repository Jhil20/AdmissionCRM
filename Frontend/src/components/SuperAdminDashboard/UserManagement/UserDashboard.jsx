import React, { useState, useEffect } from 'react';
import UserHeader from './UserHeader';
import UserFilter from './UserFilter';
import UserList from './UserList';
import AddUserForm from './AddUserForm';
import RoleSelectionModal from './RoleSelectionModal';
import Sidebar from '../SideBar';
import Header from '../Header';

const API_BASE_URL = "http://localhost:8000/api/auth/admin/getAlliAdminCounsellor";

const UserDashboard = () => {
  const [selectedRole, setSelectedRole] = useState('All Roles');
  const [searchTerm, setSearchTerm] = useState('');
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isFormOpen, setIsFormOpen] = useState(false);
  const [roleForNewUser, setRoleForNewUser] = useState('');

  useEffect(() => {
    fetchUsers();
  }, [selectedRole, searchTerm]);

  const fetchUsers = async () => {
    setLoading(true);
    try {
      let url = `${API_BASE_URL}?page=0&size=10`;

      if (selectedRole !== 'All Roles') {
        const roleParam =
          selectedRole === 'Institutional Admin'
            ? 'INSTITUTE_ADMIN'
            : selectedRole.toUpperCase();
        url += `&role=${roleParam}`;
      }

      if (searchTerm) {
        url += `&search=${encodeURIComponent(searchTerm)}`;
      }

      const token = localStorage.getItem('Token');
      if (!token) {
        console.error("No auth token found in localStorage");
        setLoading(false);
        return;
      }

      const res = await fetch(url, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}`
        }
      });

      if (!res.ok) {
        throw new Error(`Error: ${res.status}`);
      }

      const data = await res.json();

      const mappedUsers = data.content.map((u, idx) => ({
        id: idx,
        name: u.firstName,
        username : u.username,
        email: u.email,
        role: u.role === 'INSTITUTE_ADMIN' ? 'Institutional Admin' : 'Counsellor',
        institute: u.instituteName || u.counsellorInstitute,
        contact: u.mobileNumber,
        status: u.active ? 'Active' : 'Inactive',
        avatar: u.firstName ? u.firstName.slice(0, 2).toUpperCase() : '',
        avatarBg: 'bg-green-500',
        specialization: u.specialization || 'N/A',
        designation: u.designation || 'N/A',
        alternateEmail: u.alternateEmail || 'N/A',
        bio: u.bio || 'N/A',
      }));

      setUsers(mappedUsers);
    } catch (err) {
      console.error("Error fetching users", err);
    }
    setLoading(false);
  };

  const filteredUsers = users.filter(user =>
      (selectedRole === 'All Roles' || user.role === selectedRole) &&
      (
          (user.firstName?.toLowerCase() || '').includes(searchTerm.toLowerCase()) ||
          (user.email?.toLowerCase() || '').includes(searchTerm.toLowerCase())
      )
  );


  const handleAddUserClick = () => {
    setIsModalOpen(true);
  };

  const handleRoleSelect = (role) => {
    setRoleForNewUser(role);
    setIsModalOpen(false);
    setIsFormOpen(true);
  };

  const handleFormCancel = () => {
    setIsFormOpen(false);
    setRoleForNewUser('');
  };

  const handleFormSubmit = async (formData) => {
  
     try {
    const token = localStorage.getItem('Token');
    if (!token) {
      console.error("No auth token found in localStorage");
      return;
    }

    let endpoint = '';
    if (roleForNewUser === 'INSTITUTE_ADMIN') {
      endpoint = 'http://localhost:8000/api/auth/admin/ia/create';
    } else if (roleForNewUser === 'COUNSELOR') {
      endpoint = 'http://localhost:8000/api/auth/admin/c/create';
    }

    const res = await fetch(endpoint, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
      },
      body: JSON.stringify(formData)
    });

    if (!res.ok) {
      const errorText = await res.text();
      throw new Error(`Error ${res.status}: ${errorText}`);
    }

    const data = await res.json();
    console.log("User created:", data);

    // Close form and refresh list
    setIsFormOpen(false);
    setRoleForNewUser('');
    await fetchUsers();
  } catch (error) {
    console.error('Error creating user:', error);
  }
  };

  const handleModalClose = () => {
    setIsModalOpen(false);
  };

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
      <div className="fixed left-0 top-0 h-full z-20">
        <Sidebar />
      </div>

      <div className="flex-1 flex flex-col min-w-0 ml-80">
        <Header />
        <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
          {!isFormOpen ? (
            <>
              <UserHeader onAddUser={handleAddUserClick} />
              <UserFilter
                searchTerm={searchTerm}
                setSearchTerm={setSearchTerm}
                selectedRole={selectedRole}
                setSelectedRole={setSelectedRole}
              />
              {loading ? (
                <div className="text-center py-10 text-gray-500">Loading...</div>
              ) : (
                <UserList filteredUsers={filteredUsers} />
              )}
            </>
          ) : (
            <AddUserForm
              selectedRole={roleForNewUser}
              onCancel={handleFormCancel}
              onSubmit={handleFormSubmit}
            />
          )}
        </div>
      </div>

      <RoleSelectionModal
        isOpen={isModalOpen}
        onClose={handleModalClose}
        onSelectRole={handleRoleSelect}
      />
    </div>
  );
};

export default UserDashboard;