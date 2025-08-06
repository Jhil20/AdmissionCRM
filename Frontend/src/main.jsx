import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import App from './App.jsx'
import { Provider } from 'react-redux'
import store from './store/store.js'
import { createBrowserRouter, RouterProvider } from 'react-router'
import { Login,Home, Signup } from './components/index.js'

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
  }
])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Provider store={store}>
      <RouterProvider router={router}/>
    </Provider>
  </StrictMode>,
)
