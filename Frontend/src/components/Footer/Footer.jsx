import React, { useState, useEffect } from 'react'
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
  Phone,
  MapPin,
  Send,
  Facebook,
  Twitter,
  Linkedin,
  Youtube,
  Instagram,
  Heart,
  ExternalLink
} from 'lucide-react';

function Footer() {
  const [showBottomFooter, setShowBottomFooter] = useState(false);
  const [email, setEmail] = useState('');

  useEffect(() => {
    const handleScroll = () => {
      const windowHeight = window.innerHeight;
      const documentHeight = document.documentElement.scrollHeight;
      const scrollTop = window.pageYOffset || document.documentElement.scrollTop;
      
      // Show bottom footer when user is within 150px of the bottom
      const isNearBottom = scrollTop + windowHeight >= documentHeight - 150;
      setShowBottomFooter(isNearBottom);
    };

    window.addEventListener('scroll', handleScroll);
    handleScroll(); // Check initial position
    
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  const handleSubscribe = () => {
    // Handle newsletter subscription
    console.log('Subscribing:', email);
    setEmail('');
  };

  return (
    <>
      {/* Main Footer - Full Screen Height with new design */}
      <div className="relative w-full min-h-screen bg-gradient-to-br from-slate-900 via-gray-900 to-slate-800 text-white overflow-hidden" 
           style={{marginLeft: 'calc(-50vw + 50%)', marginRight: 'calc(-50vw + 50%)', width: '100vw'}}>
        
        {/* Animated Background Elements */}
        <div className="absolute inset-0">
          <div className="absolute top-0 left-0 w-full h-full bg-[radial-gradient(ellipse_at_top_left,_var(--tw-gradient-stops))] from-emerald-900/20 via-transparent to-transparent"></div>
          <div className="absolute bottom-0 right-0 w-full h-full bg-[radial-gradient(ellipse_at_bottom_right,_var(--tw-gradient-stops))] from-teal-900/20 via-transparent to-transparent"></div>
          
          {/* Floating particles */}
          <div className="absolute inset-0">
            <div className="absolute top-20 left-20 w-2 h-2 bg-emerald-400/60 rounded-full animate-ping"></div>
            <div className="absolute top-40 right-32 w-1 h-1 bg-teal-400/40 rounded-full animate-ping" style={{animationDelay: '2s'}}></div>
            <div className="absolute bottom-40 left-1/3 w-1.5 h-1.5 bg-blue-400/50 rounded-full animate-ping" style={{animationDelay: '4s'}}></div>
            <div className="absolute top-1/3 right-1/4 w-1 h-1 bg-emerald-300/30 rounded-full animate-ping" style={{animationDelay: '6s'}}></div>
            <div className="absolute bottom-20 right-20 w-2 h-2 bg-teal-300/40 rounded-full animate-ping" style={{animationDelay: '8s'}}></div>
          </div>
          
          {/* Grid pattern overlay */}
          <div className="absolute inset-0 bg-[linear-gradient(rgba(255,255,255,0.01)_1px,transparent_1px),linear-gradient(90deg,rgba(255,255,255,0.01)_1px,transparent_1px)] bg-[size:100px_100px]"></div>
        </div>

        <div className="relative z-10 max-w-[120rem] mx-auto px-6 sm:px-8 lg:px-10 py-16 min-h-screen flex flex-col justify-center">
          
          {/* Main Content Grid */}
          <div className="grid grid-cols-1 lg:grid-cols-12 gap-12 mb-16">
            
            {/* Brand & Contact Section */}
            <div className="lg:col-span-4">
              <div className="flex items-center space-x-3 mb-8">
                <div className="relative">
                  <div className="w-16 h-16 bg-gradient-to-br from-emerald-500 via-teal-500 to-blue-500 rounded-2xl flex items-center justify-center shadow-2xl">
                    <GraduationCap className="h-9 w-9 text-white" />
                  </div>
                  <div className="absolute -top-1 -right-1 w-5 h-5 bg-gradient-to-r from-yellow-400 to-orange-500 rounded-full flex items-center justify-center">
                    <Star className="h-2.5 w-2.5 text-white fill-current" />
                  </div>
                </div>
                <div>
                  <h3 className="text-3xl font-bold bg-gradient-to-r from-white via-emerald-200 to-teal-200 bg-clip-text text-transparent">
                    Pravesh Path
                  </h3>
                  <p className="text-base text-gray-400 font-medium">Step In, Stand Out</p>
                </div>
              </div>
              
              <p className="text-lg text-gray-300 leading-relaxed mb-10 max-w-lg">
                Revolutionizing education technology with innovative solutions that empower institutions worldwide to streamline their admission processes and enhance student experiences.
              </p>
              
              {/* Contact Cards */}
              <div className="space-y-4">
                <div className="group flex items-center space-x-3 p-3 bg-white/5 backdrop-blur-sm rounded-xl border border-white/10 hover:bg-white/10 transition-all duration-300">
                  <div className="w-12 h-12 bg-emerald-500/20 rounded-lg flex items-center justify-center group-hover:scale-110 transition-transform">
                    <Mail className="h-6 w-6 text-emerald-400" />
                  </div>
                  <div>
                    <p className="text-gray-400 text-xs font-medium">Email Support</p>
                    <span className="text-white text-base font-semibold">query@praveshpath.com</span>
                  </div>
                </div>
                
                <div className="group flex items-center space-x-3 p-3 bg-white/5 backdrop-blur-sm rounded-xl border border-white/10 hover:bg-white/10 transition-all duration-300">
                  <div className="w-12 h-12 bg-teal-500/20 rounded-lg flex items-center justify-center group-hover:scale-110 transition-transform">
                    <Phone className="h-6 w-6 text-teal-400" />
                  </div>
                  <div>
                    <p className="text-gray-400 text-xs font-medium">24/7 Hotline</p>
                    <span className="text-white text-base font-semibold">+91 9327955353</span>
                  </div>
                </div>

                <div className="group flex items-center space-x-3 p-3 bg-white/5 backdrop-blur-sm rounded-xl border border-white/10 hover:bg-white/10 transition-all duration-300">
                  <div className="w-12 h-12 bg-blue-500/20 rounded-lg flex items-center justify-center group-hover:scale-110 transition-transform">
                    <MapPin className="h-6 w-6 text-blue-400" />
                  </div>
                  <div>
                    <p className="text-gray-400 text-xs font-medium">Headquarters</p>
                    <span className="text-white text-base font-semibold">Gujarat, India</span>
                  </div>
                </div>
              </div>
            </div>

            {/* Navigation Links */}
            <div className="lg:col-span-5">
              <div className="grid grid-cols-1 md:grid-cols-3 gap-10">
                
                {/* Platform Links */}
                <div>
                  <h4 className="text-white font-bold text-xl mb-6 flex items-center">
                    <Database className="h-5 w-5 mr-2 text-emerald-400" />
                    Platform
                  </h4>
                  <ul className="space-y-3">
                    {[
                      'Application Management',
                      'Student Portal',
                      'Analytics Dashboard',
                      'Communication Hub',
                      'Document Center',
                      'API Integration',
                      'Mobile App'
                    ].map((item, index) => (
                      <li key={index}>
                        <a href="#" className="group flex items-center text-gray-400 hover:text-emerald-400 transition-colors duration-200 text-base">
                          <ChevronRight className="h-3 w-3 mr-2 group-hover:translate-x-1 transition-transform" />
                          {item}
                        </a>
                      </li>
                    ))}
                  </ul>
                </div>

                {/* Solutions Links */}
                <div>
                  <h4 className="text-white font-bold text-xl mb-6 flex items-center">
                    <Building className="h-5 w-5 mr-2 text-teal-400" />
                    Solutions
                  </h4>
                  <ul className="space-y-3">
                    {[
                      'Universities',
                      'Colleges',
                      'K-12 Schools',
                      'Training Centers',
                      'Online Education',
                      'Enterprise',
                      'Government'
                    ].map((item, index) => (
                      <li key={index}>
                        <a href="#" className="group flex items-center text-gray-400 hover:text-teal-400 transition-colors duration-200 text-base">
                          <ChevronRight className="h-3 w-3 mr-2 group-hover:translate-x-1 transition-transform" />
                          {item}
                        </a>
                      </li>
                    ))}
                  </ul>
                </div>

                {/* Company Links */}
                <div>
                  <h4 className="text-white font-bold text-xl mb-6 flex items-center">
                    <Globe className="h-5 w-5 mr-2 text-blue-400" />
                    Company
                  </h4>
                  <ul className="space-y-3">
                    {[
                      'About Us',
                      'Careers',
                      'Press Kit',
                      'Help Center',
                      'Contact',
                      'Partners',
                      'Blog'
                    ].map((item, index) => (
                      <li key={index}>
                        <a href="#" className="group flex items-center text-gray-400 hover:text-blue-400 transition-colors duration-200 text-base">
                          <ChevronRight className="h-3 w-3 mr-2 group-hover:translate-x-1 transition-transform" />
                          {item}
                        </a>
                      </li>
                    ))}
                  </ul>
                </div>
              </div>
            </div>

            {/* Newsletter & Social - Made Bigger */}
            <div className="lg:col-span-3">
              <div className="bg-gradient-to-br from-white/10 to-white/5 backdrop-blur-sm rounded-2xl p-8 border border-white/20 h-full">
                <h4 className="text-white font-bold text-2xl mb-6">Stay Connected</h4>
                <p className="text-gray-300 mb-8 leading-relaxed text-base">
                  Get the latest updates, features, and educational insights delivered to your inbox.
                </p>
                
                {/* Newsletter Form */}
                <div className="mb-10">
                  <div className="relative">
                    <input 
                      type="email" 
                      value={email}
                      onChange={(e) => setEmail(e.target.value)}
                      placeholder="Enter your email"
                      className="w-full px-4 py-4 bg-black/20 border border-white/30 rounded-xl text-white placeholder-gray-400 focus:outline-none focus:border-emerald-400 focus:ring-2 focus:ring-emerald-400/20 transition-all text-base"
                    />
                    <button 
                      onClick={handleSubscribe}
                      className="absolute right-2 top-2 p-2 bg-gradient-to-r from-emerald-500 to-teal-500 rounded-lg hover:from-emerald-600 hover:to-teal-600 transition-all duration-300 group"
                    >
                      <Send className="h-5 w-5 text-white group-hover:translate-x-0.5 transition-transform" />
                    </button>
                  </div>
                </div>

                {/* Social Media */}
                <div>
                  <h5 className="text-white font-semibold text-lg mb-4">Follow Us</h5>
                  <div className="flex space-x-3">
                    {[
                      { icon: Facebook, color: 'hover:bg-blue-600', name: 'Facebook' },
                      { icon: Twitter, color: 'hover:bg-sky-500', name: 'Twitter' },
                      { icon: Linkedin, color: 'hover:bg-blue-700', name: 'LinkedIn' },
                      { icon: Youtube, color: 'hover:bg-red-600', name: 'YouTube' },
                      { icon: Instagram, color: 'hover:bg-pink-600', name: 'Instagram' }
                    ].map((social, index) => {
                      const Icon = social.icon;
                      return (
                        <a 
                          key={index}
                          href="#" 
                          className={`w-12 h-12 bg-white/10 ${social.color} rounded-lg flex items-center justify-center transition-all duration-300 hover:scale-110 group border border-white/20`}
                          title={social.name}
                        >
                          <Icon className="h-5 w-5 text-white" />
                        </a>
                      );
                    })}
                  </div>
                </div>
              </div>
            </div>
          </div>

          {/* Stats & Certifications */}
          <div className="grid grid-cols-2 md:grid-cols-4 gap-6 mb-16">
            {[
              { number: '500K+', label: 'Students Managed', icon: Users },
              { number: '99.9%', label: 'Uptime SLA', icon: Shield },
              { number: '1000+', label: 'Institutions', icon: Building },
              { number: '150+', label: 'Countries', icon: Globe }
            ].map((stat, index) => {
              const Icon = stat.icon;
              return (
                <div key={index} className="text-center group">
                  <div className="inline-flex items-center justify-center w-16 h-16 bg-gradient-to-br from-emerald-500/20 to-teal-500/20 rounded-xl mb-3 group-hover:scale-110 transition-transform duration-300 border border-emerald-500/30">
                    <Icon className="h-8 w-8 text-emerald-400" />
                  </div>
                  <div className="text-3xl font-bold text-white mb-1">{stat.number}</div>
                  <div className="text-gray-400 text-base font-medium">{stat.label}</div>
                </div>
              );
            })}
          </div>

          {/* Simple Footer Text */}
        
        </div>
      </div>

      {/* Bottom Fixed Footer */}
      <div className={`fixed bottom-0 left-0 right-0 w-full z-50 bg-slate-900/95 backdrop-blur-lg border-t border-white/20 ${
        showBottomFooter ? '' : 'hidden'
      }`}>
        <div className="max-w-[120rem] mx-auto px-6 sm:px-8 lg:px-10 py-5">
          <div className="flex flex-col lg:flex-row justify-between items-center space-y-4 lg:space-y-0">
            <div className="flex items-center space-x-6">
              <p className="text-gray-400 text-sm">
                &copy; 2024 Pravesh Path. All rights reserved.
              </p>
              <div className="flex items-center space-x-1 text-emerald-400 text-sm">
                <Zap className="h-4 w-4" />
                <span>Version 3.2.1</span>
              </div>
            </div>
            
            <div className="flex items-center space-x-8">
              <a href="#privacy" className="text-gray-400 hover:text-emerald-400 text-sm transition-colors flex items-center">
                Privacy Policy
                <ExternalLink className="h-3 w-3 ml-1" />
              </a>
              <a href="#terms" className="text-gray-400 hover:text-emerald-400 text-sm transition-colors flex items-center">
                Terms of Service
                <ExternalLink className="h-3 w-3 ml-1" />
              </a>
              <a href="#security" className="text-gray-400 hover:text-emerald-400 text-sm transition-colors flex items-center">
                Security
                <ExternalLink className="h-3 w-3 ml-1" />
              </a>
              <div className="flex items-center space-x-2 px-3 py-1 bg-emerald-500/20 rounded-full border border-emerald-500/30">
                <div className="w-2 h-2 bg-emerald-400 rounded-full animate-pulse"></div>
                <span className="text-emerald-400 text-xs font-medium">All Systems Operational</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}

export default Footer