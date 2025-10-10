import React, { useState, useEffect } from 'react';
import { MessageSquare, Plus, Eye, Users, Calendar, ArrowLeft } from 'lucide-react';
import { useSelector } from 'react-redux';
import QueryHeader from './QueryHeader'; // Import the new header component

const ListQueries = ({ onAskQuery }) => {
  const [queries, setQueries] = useState([]);
  const [selectedQuery, setSelectedQuery] = useState(null);
  const [loading, setLoading] = useState(true);

  const selector = useSelector((state) => state.authSlice.auth);
  const token = selector?.userData?.jwtToken;

  useEffect(() => {
    const fetchQueries = async () => {
      if (!token) return;

      try {
        setLoading(true);
        const res = await fetch('http://localhost:8086/api/lead/student/list', {
          method: 'GET',
          headers: {
            'Authorization': `Bearer ${token}`,
            'Content-Type': 'application/json',
          },
        });

        if (!res.ok) throw new Error('Failed to fetch queries');

        const data = await res.json();
        setQueries(data?.data?.Leads || []);
      } catch (err) {
        console.error('Error fetching queries:', err);
        setQueries([]);
      } finally {
        setLoading(false);
      }
    };

    fetchQueries();
  }, [token]);

  const getStatusColor = (status) => {
    switch (status?.toUpperCase()) {
      case 'RESOLVED':
      case 'ANSWERED':
        return 'bg-green-100 text-green-800';
      case 'IN_PROGRESS':
      case 'PENDING':
        return 'bg-blue-100 text-blue-800';
      case 'SUBMITTED':
      case 'ASSIGNED':
        return 'bg-yellow-100 text-yellow-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  const formatStatus = (status) => {
    if (!status) return 'Submitted';
    return status.replace(/_/g, ' ').replace(/\b\w/g, l => l.toUpperCase());
  };

  const formatDate = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleDateString('en-IN', {
      year: 'numeric',
      month: 'short',
      day: 'numeric'
    });
  };

  const formatDateTime = (dateString) => {
    if (!dateString) return 'N/A';
    const date = new Date(dateString);
    return date.toLocaleString('en-IN', {
      year: 'numeric',
      month: 'short',
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    });
  };

  const truncateText = (text, wordCount = 3) => {
    if (!text) return '';
    const words = text.split(' ');
    if (words.length <= wordCount) return text;
    return words.slice(0, wordCount).join(' ') + '...';
  };

  if (selectedQuery) {
    return (
      <div className="flex-1 bg-gray-50 overflow-auto">
       <div className="flex items-start justify-between p-6 bg-transparent">
  <div className="flex items-start space-x-4">
    <button
      onClick={() => setSelectedQuery(null)}
      className="text-gray-500 hover:text-gray-700 mt-1"
    >
      <ArrowLeft className="w-5 h-5" />
    </button>
    <div className="flex flex-col items-start">
      <h2 className="text-2xl font-bold text-green-600">Query Details</h2>
      <p className="text-gray-600 mt-1">View detailed information about the query</p>
    </div>
  </div>
</div>

        <div className="p-6">
          <div className="max-w-4xl bg-white rounded-lg shadow-sm border border-gray-200">
            <div className="p-8">
              <div className="flex items-start justify-between mb-8 pb-6 border-b border-gray-200">
                <div className="flex-1 text-left">
                  <h2 className="text-xl font-bold text-gray-900 mb-3">
                    {selectedQuery.leadSubject?.replace(/_/g, ' ')}
                  </h2>
                  <div className="flex items-center space-x-3 text-sm text-gray-600">
                    <span>Lead ID: #{selectedQuery.leadId}</span>
                    <span>•</span>
                    <span>{formatDateTime(selectedQuery.date)}</span>
                  </div>
                </div>
                <span className={`inline-flex items-center px-4 py-1.5 rounded-md text-sm font-medium ${getStatusColor(selectedQuery.leadStatus)}`}>
                  {formatStatus(selectedQuery.leadStatus)}
                </span>
              </div>

              <div className="space-y-6 text-left">
                {selectedQuery.studentName && (
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Student Name</label>
                    <p className="text-base text-gray-900">{selectedQuery.studentName}</p>
                  </div>
                )}

                {selectedQuery.email && (
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Email</label>
                    <p className="text-base text-gray-900">{selectedQuery.email}</p>
                  </div>
                )}

                {selectedQuery.phone && (
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">Phone</label>
                    <p className="text-base text-gray-900">{selectedQuery.phone}</p>
                  </div>
                )}

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">Institute</label>
                  <p className="text-base text-gray-900">{selectedQuery.instituteName || 'N/A'}</p>
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">Assigned To</label>
                  <p className="text-base text-gray-900 flex items-center">
                    <Users className="w-4 h-4 mr-2 text-gray-400" />
                    {selectedQuery.assignedTo || 'Pending Assignment'}
                  </p>
                </div>

                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">Date</label>
                  <p className="text-base text-gray-900 flex items-center">
                    <Calendar className="w-4 h-4 mr-2 text-gray-400" />
                    {formatDate(selectedQuery.date)}
                  </p>
                </div>

                <div className="pt-2">
                  <label className="block text-sm font-medium text-gray-700 mb-2">Query Description</label>
                  <div className="bg-gray-50 rounded-lg p-5 border border-gray-200">
                    <p className="text-base text-gray-900 whitespace-pre-wrap leading-relaxed">
                      {selectedQuery.leadDescription || 'No description provided'}
                    </p>
                  </div>
                </div>

                {selectedQuery.response ? (
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-2">Response</label>
                    <div className="bg-green-50 border border-green-200 rounded-lg p-5">
                      <p className="text-base text-gray-900 whitespace-pre-wrap leading-relaxed">
                        {selectedQuery.response}
                      </p>
                    </div>
                  </div>
                ) : (
                  <div className="bg-yellow-50 border border-yellow-200 rounded-lg p-5">
                    <p className="text-base text-yellow-800">
                      This query is awaiting review and response from the assigned counselor.
                    </p>
                  </div>
                )}
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }

  if (loading) {
    return (
      <div className="flex-1 bg-gray-50 overflow-auto p-6">
        <QueryHeader onAddQuery={onAskQuery} />
        <div className="flex items-center justify-center min-h-[calc(100vh-200px)]">
          <p className="text-gray-600 text-lg">Loading queries...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="flex-1 bg-gray-50 overflow-auto p-6">
      {/* Header matching User Management style */}
      <QueryHeader onAddQuery={onAskQuery} />

      {/* Table matching User Management style */}
      <div className="bg-white rounded-lg shadow overflow-hidden">
        {queries.length === 0 ? (
          <div className="text-center py-16 px-6">
            <div className="w-16 h-16 bg-gray-100 rounded-full flex items-center justify-center mx-auto mb-6">
              <MessageSquare className="w-8 h-8 text-gray-400" />
            </div>
            <h3 className="text-lg font-medium text-gray-900 mb-2">No Queries Yet</h3>
            <p className="text-gray-600 mb-6">You haven't submitted any queries yet.</p>
            <button
              onClick={onAskQuery}
              className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-lg font-medium transition-colors inline-flex items-center space-x-2"
            >
              <Plus className="w-5 h-5" />
              <span>Ask Your First Query</span>
            </button>
          </div>
        ) : (
          <table className="w-full">
            <thead className="bg-gray-50 border-b">
              <tr>
                <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">SUBJECT</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">ASSIGNED TO</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">INSTITUTE</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">DATE</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">STATUS</th>
                <th className="text-left px-6 py-4 text-sm font-medium text-gray-700 uppercase tracking-wider">ACTIONS</th>
              </tr>
            </thead>
            <tbody className="bg-white divide-y divide-gray-200">
              {queries.map((query) => (
                <tr key={query.id} className="hover:bg-gray-50">
                  <td className="px-6 py-4 align-top text-left">
                    <div className="text-sm font-medium text-gray-900">
                      {truncateText(query.leadSubject?.replace(/_/g, ' '), 4)}
                    </div>
                    <div className="text-sm text-gray-500">
                      {truncateText(query.leadDescription, 5)}
                    </div>
                  </td>
                  <td className="px-6 py-4 text-sm text-gray-900">
                    {query.assignedTo || 'Pending'}
                  </td>
                  <td className="px-6 py-4 text-sm text-gray-900">
                    {query.instituteName || 'N/A'}
                  </td>
                  <td className="px-6 py-4 text-sm text-gray-900">
                    {formatDate(query.date)}
                  </td>
                  <td className="px-6 py-4">
                    <span className={`inline-flex px-3 py-1 text-xs font-medium rounded-full ${getStatusColor(query.leadStatus)}`}>
                      {formatStatus(query.leadStatus)}
                    </span>
                  </td>
                  {/* <td className="px-6 py-4">
                    <div className="flex items-center space-x-3">
                      <button
                        onClick={() => setSelectedQuery(query)}
                        className="text-green-600 hover:text-green-800 transition-colors"
                      >
                        <Eye className="w-4 h-4" />
                      </button>
                    </div>
                  </td> */}
                  <td className="px-6 py-4 text-left">
                    <button
                      onClick={() => setSelectedQuery(query)}
                      className="flex items-center gap-2 text-green-600 hover:text-green-800 font-medium"
                    >
                      <span>View</span>
                      <Eye className="w-4 h-4" />
                    </button>
                  </td>

                </tr>
              ))}
            </tbody>
          </table>
        )}
      </div>
    </div>
  );
};

export default ListQueries;