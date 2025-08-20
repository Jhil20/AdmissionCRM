// import React from 'react';
// import { Search } from 'lucide-react';

// const SearchFilterBar = ({ 
//   searchTerm, 
//   setSearchTerm, 
//   statusFilter, 
//   setStatusFilter, 
//   totalCount, 
//   filteredCount 
// }) => {
//   return (
//     <div className="px-8 py-6">
//       <div className="flex items-center justify-between gap-4">
//         <div className="relative flex-1 max-w-md">
//           <Search className="absolute left-4 top-1/2 transform -translate-y-1/2 text-gray-400 w-5 h-5" />
//           <input
//             type="text"
//             placeholder="Search counsellors..."
//             value={searchTerm}
//             onChange={(e) => setSearchTerm(e.target.value)}
//             className="w-full pl-12 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 outline-none transition-colors bg-gray-50"
//           />
//         </div>
//         <select 
//           value={statusFilter}
//           onChange={(e) => setStatusFilter(e.target.value)}
//           className="px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 outline-none bg-gray-50 transition-colors min-w-[160px] font-medium text-gray-700"
//         >
//           <option>All Status</option>
//           <option>Active</option>
//           <option>Inactive</option>
//         </select>
//       </div>
      
//       {/* Results Count */}
//       <div className="mt-6">
//         <p className="text-sm text-gray-600 font-medium">
//           Showing {filteredCount} of {totalCount} counsellors
//         </p>
//       </div>
//     </div>
//   );
// };

// export default SearchFilterBar;
import React from "react";
import { Search, ChevronDown } from "lucide-react";

const CounsellorFilter = ({
  searchTerm,
  setSearchTerm,
  selectedStatus,
  setSelectedStatus,
}) => (
  <div className="flex items-center space-x-4 mb-6">
    {/* Search */}
    <div className="flex-1 relative">
      <Search className="absolute left-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4" />
      <input
        type="text"
        placeholder="Search counsellors..."
        className="w-full pl-10 pr-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-green-500 focus:border-green-500 bg-white"
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
    </div>

    {/* Status Filter */}
    <div className="relative">
      <select
        className="appearance-none bg-white border border-gray-300 rounded-lg px-4 py-3 pr-10 focus:ring-2 focus:ring-green-500 focus:border-green-500 min-w-[120px]"
        value={selectedStatus}
        onChange={(e) => setSelectedStatus(e.target.value)}
      >
        <option value="All">All Status</option>
        <option value="Active">Active</option>
        <option value="Inactive">Inactive</option>
      </select>
      <ChevronDown className="absolute right-3 top-1/2 transform -translate-y-1/2 text-gray-400 w-4 h-4 pointer-events-none" />
    </div>
  </div>
);

export default CounsellorFilter;