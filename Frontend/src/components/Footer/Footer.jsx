import React from 'react'
import { 
  Users, 
  TrendingUp, 
  Zap, 
  Shield, 
  Clock, 
  CheckCircle, 
  Star,
  ArrowRight,
  Menu,
  X,
  BarChart3,
  Database,
  GraduationCap,
  Globe,
  Award,
  BookOpen,
  Settings,
  ChevronRight,
  Building,
  Mail,
  Phone
} from 'lucide-react';

function Footer() {
  return (
    <footer className="relative z-10 bg-gradient-to-br from-gray-900 via-gray-800 to-gray-900 text-white">
      {/* Main Footer */}
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-16">
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-5 gap-8 lg:gap-12">
          {/* Brand Section */}
          <div className="lg:col-span-2">
            <div className="flex items-center space-x-3 mb-6">
              <div className="w-12 h-12 bg-gradient-to-br from-emerald-500 to-teal-600 rounded-xl flex items-center justify-center">
                <GraduationCap className="h-7 w-7 text-white" />
              </div>
              <div>
                <h3 className="text-2xl font-bold text-white">EduCRM</h3>
                <p className="text-sm text-gray-400">Admission Suite</p>
              </div>
            </div>
            <p className="text-gray-300 text-lg leading-relaxed mb-8 max-w-md">
              Streamline your admission process with our comprehensive platform designed for educational institutions worldwide.
            </p>
            
            {/* Contact Info */}
            <div className="space-y-4">
              <div className="flex items-center space-x-3">
                <Mail className="h-5 w-5 text-emerald-400" />
                <span className="text-gray-300">hello@educrm.com</span>
              </div>
              <div className="flex items-center space-x-3">
                <Phone className="h-5 w-5 text-emerald-400" />
                <span className="text-gray-300">+1 (555) 123-4567</span>
              </div>
            </div>
          </div>

          {/* Platform Links */}
          <div>
            <h4 className="text-white font-semibold text-lg mb-6">Platform</h4>
            <ul className="space-y-4">
              <li><a href="#applications" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Application Management</a></li>
              <li><a href="#portal" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Student Portal</a></li>
              <li><a href="#analytics" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Analytics Dashboard</a></li>
              <li><a href="#communication" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Communication Hub</a></li>
              <li><a href="#documents" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Document Center</a></li>
            </ul>
          </div>

          {/* Solutions Links */}
          <div>
            <h4 className="text-white font-semibold text-lg mb-6">Solutions</h4>
            <ul className="space-y-4">
              <li><a href="#universities" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Universities</a></li>
              <li><a href="#colleges" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Colleges</a></li>
              <li><a href="#schools" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Schools</a></li>
              <li><a href="#training" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Training Centers</a></li>
              <li><a href="#online" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Online Education</a></li>
            </ul>
          </div>

          {/* Company Links */}
          <div>
            <h4 className="text-white font-semibold text-lg mb-6">Company</h4>
            <ul className="space-y-4">
              <li><a href="#about" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">About Us</a></li>
              <li><a href="#careers" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Careers</a></li>
              <li><a href="#help" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Help Center</a></li>
              <li><a href="#contact" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Contact</a></li>
              <li><a href="#partners" className="text-gray-400 hover:text-emerald-400 transition-colors duration-200">Partners</a></li>
            </ul>
          </div>
        </div>
      </div>

      {/* Bottom Section */}
      <div className="border-t border-gray-700">
        <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
          <div className="flex flex-col lg:flex-row justify-between items-center space-y-4 lg:space-y-0">
            <p className="text-gray-400 text-sm">
              &copy; 2024 EduCRM. All rights reserved. Built with ❤️ for educators worldwide.
            </p>
            <div className="flex items-center space-x-8">
              <a href="#privacy" className="text-gray-400 hover:text-emerald-400 text-sm transition-colors">
                Privacy Policy
              </a>
              <a href="#terms" className="text-gray-400 hover:text-emerald-400 text-sm transition-colors">
                Terms of Service
              </a>
              <a href="#security" className="text-gray-400 hover:text-emerald-400 text-sm transition-colors">
                Security
              </a>
            </div>
          </div>
        </div>
      </div>
    </footer>
  );
}


export default Footer