import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { Provider } from 'react-redux'
import store from './store/store.js'
import { createBrowserRouter, RouterProvider } from 'react-router'
import { Login,Home, Signup } from './components/index.js'
import SuperAdminDashboard from './components/SuperAdminDashboard/AnalyticsDashboard/SuperAdminDashboard.jsx';
import UserManagementDashboard from './components/SuperAdminDashboard/UserManagement/UserDashboard.jsx';
import InstituteAdminDashboard from './components/InstituteAdminDashboard/AnalyticsDashboard/InstituteAdminDashboard.jsx';
import CounsellorDashboard from './components/InstituteAdminDashboard/CounsellorManagement/CounsellorDashboard.jsx';
import LeadDashboard from './components/InstituteAdminDashboard/LeadManagement/LeadDashboard.jsx'
import Profile from './components/Profile.jsx';


const router = createBrowserRouter([
  {
    path:'/',
    element:<App/>,
  },
  {
    path:'signin',
    element : <Login/>
  },
  {
    path : 'signup',
    element : <Signup/>
  },
  {
    path : 'superadmin/dashboard',
    element : <SuperAdminDashboard />
  },
  {
    path: 'superadmin/user-management',
    element: <UserManagementDashboard />
  },
  {
    path: 'instituteadmin/dashboard',
    element: <InstituteAdminDashboard />
  },
  {
    path: 'instituteadmin/counsellor-management',
    element: <CounsellorDashboard />
  },
  {
    path: 'instituteadmin/lead-management',
    element: <LeadDashboard />
  },
  {
    path: 'profile',
    element: <Profile />   // ✅ New Profile route
  },
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Provider store={store}>
      <RouterProvider router={router}/>
    </Provider>
  </StrictMode>,
)
