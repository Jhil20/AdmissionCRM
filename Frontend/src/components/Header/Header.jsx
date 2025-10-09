import React, { useState, useEffect } from 'react'
import {
  GraduationCap,
  Menu,
  X,
} from 'lucide-react'
import { Link, NavLink, useLocation } from 'react-router-dom'

function Header() {
  const [isMenuOpen, setIsMenuOpen] = useState(false);
  const [scrolled, setScrolled] = useState(false);

  useEffect(() => {
    const handleScroll = () => {
      setScrolled(window.scrollY > 50);
    };
    window.addEventListener('scroll', handleScroll);
    return () => window.removeEventListener('scroll', handleScroll);
  }, []);

  return (
    <header className={`fixed left-1/2 -translate-x-1/2 w-full top-0 z-50 transition-all duration-300 ${
      scrolled ? 'bg-white/95 backdrop-blur-md shadow-lg' : 'bg-transparent'
    }`}>
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex items-center justify-between h-20">
          {/* Logo Section - Fixed width */}
          <div className="flex items-center space-x-3 flex-shrink-0">
            <div className="relative">
              <div className="w-12 h-12 bg-gradient-to-br from-emerald-500 to-teal-600 rounded-xl flex items-center justify-center shadow-lg">
                <GraduationCap className="h-7 w-7 text-white" />
              </div>
              <div className="absolute -top-1 -right-1 w-4 h-4 bg-orange-500 rounded-full animate-pulse"></div>
            </div>
            <div>
              <h1 className="text-2xl font-bold bg-gradient-to-r from-gray-900 to-gray-600 bg-clip-text text-transparent">
                Pravesh Path
              </h1>
              <p className="text-xs text-gray-500 font-medium">Step In, Stand Out</p>
            </div>
          </div>
          
          {/* Navigation - Centered */}
          <nav className="hidden lg:flex items-center justify-center flex-1">
            <div className="flex items-center space-x-8">
              {[
                { name: 'Solutions', href: '#solutions' },
                { name: 'Features', href: '#features' },
                { name: 'Pricing', href: '#pricing' },
                { name: 'Resources', href: '#resources' },
                { name: 'Company', href: '#company' }
              ].map((item) => (
                <a
                  key={item.name}
                  href={item.href}
                  className="text-gray-700 hover:text-emerald-600 font-medium transition-all duration-200 relative group py-2"
                >
                  {item.name}
                  <span className="absolute bottom-0 left-0 w-0 h-0.5 bg-emerald-500 group-hover:w-full transition-all duration-300"></span>
                </a>
              ))}
            </div>
          </nav>

          {/* CTA Buttons - Fixed width */}
          <div className="hidden lg:flex items-center space-x-4 flex-shrink-0">
            <NavLink to={"/signin"}>
            <button className="text-gray-700 hover:text-emerald-600 font-medium transition-colors">
              Sign In
            </button>
            </NavLink>
            <NavLink to={"/signin"}>
            <button className="bg-gradient-to-r from-emerald-500 to-teal-600 text-white px-6 py-3 rounded-xl font-semibold hover:from-emerald-600 hover:to-teal-700 transition-all duration-200 shadow-lg hover:shadow-xl transform hover:-translate-y-0.5">
              Get Started
            </button>
            </NavLink>
          </div>

          {/* Mobile menu button */}
          <button 
            className="lg:hidden p-2 text-gray-700 flex-shrink-0"
            onClick={() => setIsMenuOpen(!isMenuOpen)}
          >
            {isMenuOpen ? <X className="h-6 w-6" /> : <Menu className="h-6 w-6" />}
          </button>
        </div>
      </div>

      {/* Mobile menu */}
      {isMenuOpen && (
        <div className="lg:hidden bg-white/95 backdrop-blur-md border-t border-gray-200">
          <div className="px-4 py-6 space-y-4">
            {['Solutions', 'Features', 'Pricing', 'Resources', 'Company'].map((item) => (
              <a key={item} href={`#${item.toLowerCase()}`} className="block text-gray-700 hover:text-emerald-600 font-medium py-2">
                {item}
              </a>
            ))}
            <div className="pt-4 space-y-3 border-t border-gray-200">
              <Link to={"/signin"}>
              <button className="w-full text-left text-gray-700 hover:text-emerald-600 font-medium">Sign In</button>
              </Link>
              <NavLink to={"/signin"}>
              <button className="w-full bg-gradient-to-r from-emerald-500 to-teal-600 text-white py-3 rounded-xl font-semibold">
                Get Started
              </button>
              </NavLink>
            </div>
          </div>
        </div>
      )}
    </header>
  );
}

export default Header