// import React from 'react';
// import { Plus } from 'lucide-react';

// const CounsellorHeader = ({ onAddCounsellor }) => {
//   return (
//     <div className="px-8 py-6">
//       <div className="flex items-center justify-between">
//         <div>
//           <h2 className="text-2xl font-bold text-green-600 mb-2">Counsellor Management</h2>
//           <p className="text-gray-600">Manage counsellors for your institute</p>
//         </div>
//         <button 
//           onClick={onAddCounsellor}
//           className="bg-green-500 hover:bg-green-600 text-white px-6 py-3 rounded-lg flex items-center space-x-2 transition-colors font-medium"
//         >
//           <Plus className="w-5 h-5" />
//           <span>Add Counsellor</span>
//         </button>
//       </div>
//     </div>
//   );
// };

// export default CounsellorHeader;
import React from 'react';
import { Plus } from 'lucide-react';

const CounsellorHeader = ({ onAddCounsellor }) => (
  <div className="flex items-center justify-between mb-4">

    <div className="mb-3 sm:mb-0  ">
      <h2 className="text-xl sm:text-2xl font-bold text-green-600 leading-tight ml-[-44px]  ">Counsellor Management</h2>
      <p className="text-gray-600 mt-1 text-sm sm:text-base">Manage institutional admins and counsellors</p>
    </div>

    <button
      onClick={onAddCounsellor}
      className="bg-green-500 hover:bg-green-600 text-white px-4 py-2 rounded-lg flex items-center space-x-2 transition-colors"
    >
      <Plus className="w-4 h-4" />
      <span>Add Counsellor</span>
    </button>
  </div>
);

export default CounsellorHeader;