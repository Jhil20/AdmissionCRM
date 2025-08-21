import React, { useState } from "react";
import LeadHeader from "./LeadHeader";
import LeadTable from "./LeadTable";
import LeadModal from "./LeadModal";
import Sidebar from "../SideBar";
import Header from "../Header";

const LeadDashboard = () => {
  const [leads, setLeads] = useState([
    {
      id: 1,
      studentName: "Rahul Sharma",
      email: "rahul.sharma@example.com",
      subject: "Course Inquiry",
      interestedCourses: "Computer Science, Data Science",
      description:
        "Interested in understanding the curriculum and career prospects for CS programs. Looking for detailed information about placement statistics.",
      status: "new",
      assignedCounsellor: null,
      createdAt: "2025-08-20T10:30:00Z",
    },
    {
      id: 2,
      studentName: "Priya Patel",
      email: "priya.patel@example.com",
      subject: "Admission Process",
      interestedCourses: "MBA, Marketing",
      description:
        "Need information about admission requirements and fee structure for MBA program. Also interested in scholarship opportunities.",
      status: "assigned",
      assignedCounsellor: {
        id: 1,
        name: "Alice Johnson",
        email: "alice@example.com",
      },
      createdAt: "2025-08-19T14:20:00Z",
    },
    {
      id: 3,
      studentName: "Amit Kumar",
      email: "amit.kumar@example.com",
      subject: "Scholarship Information",
      interestedCourses: "Engineering, Mechanical",
      description:
        "Looking for scholarship opportunities for mechanical engineering course. Family income is below threshold.",
      status: "solved",
      assignedCounsellor: {
        id: 3,
        name: "David Patel",
        email: "david@example.com",
      },
      createdAt: "2025-08-18T09:15:00Z",
    },
    {
      id: 4,
      studentName: "Sneha Singh",
      email: "sneha.singh@example.com",
      subject: "Course Duration",
      interestedCourses: "Web Development, Full Stack",
      description:
        "Want to know about part-time course options and flexible schedules as I am currently working full-time.",
      status: "new",
      assignedCounsellor: null,
      createdAt: "2025-08-21T08:45:00Z",
    },
  ]);

  const [searchTerm, setSearchTerm] = useState("");
  const [statusFilter, setStatusFilter] = useState("All Status");
  const [showModal, setShowModal] = useState(null);
  const [selectedLead, setSelectedLead] = useState(null);

  const counsellors = [
    {
      id: 1,
      name: "Alice Johnson",
      email: "alice@example.com",
      specialization: "Computer Science & Technology",
    },
    {
      id: 2,
      name: "Bob Singh",
      email: "bob@example.com",
      specialization: "Business & Management",
    },
    {
      id: 3,
      name: "David Patel",
      email: "david@example.com",
      specialization: "Engineering & Sciences",
    },
    {
      id: 4,
      name: "Sarah Wilson",
      email: "sarah@example.com",
      specialization: "Arts & Design",
    },
    {
      id: 5,
      name: "Michael Brown",
      email: "michael@example.com",
      specialization: "Finance & Accounting",
    },
  ];

  const filteredLeads = leads.filter((lead) => {
    const matchesSearch =
      lead.studentName.toLowerCase().includes(searchTerm.toLowerCase()) ||
      lead.email.toLowerCase().includes(searchTerm.toLowerCase()) ||
      lead.subject.toLowerCase().includes(searchTerm.toLowerCase());

    const matchesStatus =
      statusFilter === "All Status" ||
      lead.status === statusFilter.toLowerCase();

    return matchesSearch && matchesStatus;
  });

  const handleAssignLead = (leadId, counsellorId) => {
    const counsellor = counsellors.find((c) => c.id === counsellorId);
    setLeads(
      leads.map((lead) =>
        lead.id === leadId
          ? { ...lead, status: "assigned", assignedCounsellor: counsellor }
          : lead
      )
    );
    setShowModal(null);
    setSelectedLead(null);
  };

  const handleMarkSolved = (leadId) => {
    setLeads(
      leads.map((lead) =>
        lead.id === leadId ? { ...lead, status: "solved" } : lead
      )
    );
  };

  const openModal = (type, lead) => {
    setShowModal(type);
    setSelectedLead(lead);
  };

  const closeModal = () => {
    setShowModal(null);
    setSelectedLead(null);
  };

  return (
    <div className="flex h-screen bg-gray-50 overflow-hidden m-0 p-0">
      {/* Sidebar */}
      <div className="fixed left-0 top-0 h-full z-20">
        <Sidebar />
      </div>

      {/* Main content */}
      <div className="flex-1 flex flex-col min-w-0 ml-80">
        {/* Fixed Header */}
        <Header />

        {/* Page content */}
        <div className="flex-1 overflow-auto mt-[4.5rem] p-6">
          <LeadHeader
            searchTerm={searchTerm}
            setSearchTerm={setSearchTerm}
            statusFilter={statusFilter}
            setStatusFilter={setStatusFilter}
          />

          <LeadTable
            leads={filteredLeads}
            onViewDetails={(lead) => openModal("details", lead)}
            onAssignCounsellor={(lead) => openModal("assign", lead)}
            onMarkSolved={handleMarkSolved}
          />

          {showModal && selectedLead && (
            <LeadModal
              type={showModal}
              lead={selectedLead}
              counsellors={counsellors}
              onAssign={handleAssignLead}
              onClose={closeModal}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default LeadDashboard;
