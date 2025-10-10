import React, { useState, useEffect } from 'react';
import { CheckCircle, XCircle, AlertCircle, Info, X } from 'lucide-react';

// Toast Component
const Toast = ({ message, type = 'info', duration = 5000, onClose }) => {
  const [isVisible, setIsVisible] = useState(true);
  const [isExiting, setIsExiting] = useState(false);

  useEffect(() => {
    const timer = setTimeout(() => {
      handleClose();
    }, duration);

    return () => clearTimeout(timer);
  }, [duration]);

  const handleClose = () => {
    setIsExiting(true);
    setTimeout(() => {
      setIsVisible(false);
      onClose();
    }, 300);
  };

  if (!isVisible) return null;

  const typeConfig = {
    success: {
      icon: CheckCircle,
      bgColor: 'bg-green-50',
      borderColor: 'border-green-500',
      textColor: 'text-green-800',
      iconColor: 'text-green-500',
    },
    error: {
      icon: XCircle,
      bgColor: 'bg-red-50',
      borderColor: 'border-red-500',
      textColor: 'text-red-800',
      iconColor: 'text-red-500',
    },
    warning: {
      icon: AlertCircle,
      bgColor: 'bg-yellow-50',
      borderColor: 'border-yellow-500',
      textColor: 'text-yellow-800',
      iconColor: 'text-yellow-500',
    },
    info: {
      icon: Info,
      bgColor: 'bg-blue-50',
      borderColor: 'border-blue-500',
      textColor: 'text-blue-800',
      iconColor: 'text-blue-500',
    },
  };

  const config = typeConfig[type] || typeConfig.info;
  const Icon = config.icon;

  return (
    <div
      className={`flex items-start gap-3 p-4 rounded-lg border-l-4 shadow-lg ${config.bgColor} ${config.borderColor} ${
        isExiting ? 'animate-slideOut' : 'animate-slideIn'
      }`}
      style={{
        minWidth: '320px',
        maxWidth: '500px',
      }}
    >
      <Icon className={`w-5 h-5 ${config.iconColor} flex-shrink-0 mt-0.5`} />
      <p className={`flex-1 text-sm font-medium ${config.textColor}`}>{message}</p>
      <button
        onClick={handleClose}
        className={`${config.textColor} hover:opacity-70 transition-opacity flex-shrink-0`}
        aria-label="Close"
      >
        <X className="w-4 h-4" />
      </button>
    </div>
  );
};

// Toast Container
export const ToastContainer = ({ toasts, removeToast }) => {
  return (
    <div className="fixed top-6 right-6 z-50 flex flex-col gap-3">
      {toasts.map((toast) => (
        <Toast
          key={toast.id}
          message={toast.message}
          type={toast.type}
          duration={toast.duration}
          onClose={() => removeToast(toast.id)}
        />
      ))}
    </div>
  );
};

// Custom Hook
export const useToast = () => {
  const [toasts, setToasts] = useState([]);

  const addToast = (message, type = 'info', duration = 5000) => {
    const id = Date.now() + Math.random();
    setToasts((prev) => [...prev, { id, message, type, duration }]);
  };

  const removeToast = (id) => {
    setToasts((prev) => prev.filter((toast) => toast.id !== id));
  };

  const showSuccess = (message, duration) => addToast(message, 'success', duration);
  const showError = (message, duration) => addToast(message, 'error', duration);
  const showWarning = (message, duration) => addToast(message, 'warning', duration);
  const showInfo = (message, duration) => addToast(message, 'info', duration);

  return {
    toasts,
    removeToast,
    showSuccess,
    showError,
    showWarning,
    showInfo,
  };
};
