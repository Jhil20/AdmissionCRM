// AdmissionFlowContainer.jsx
import React, { useState } from 'react';
import { useSelector } from 'react-redux';
import QuerySelectionModal from './QuerySelectionModal';
import AdmissionForm from "./AdmissionForm.jsx";

const AdmissionFlowContainer = () => {
    const [step, setStep] = useState('admission'); // 'admission', 'querySelection', 'queryForm'
    const [admissionData, setAdmissionData] = useState(null);
    const [loading, setLoading] = useState(false);

    const selector = useSelector((state) => state.authSlice.auth);
    const token = selector?.userData?.jwtToken;

    // Handle admission inquiry submission
    const handleAdmissionSubmit = (data) => {
        setAdmissionData(data);
        setStep('querySelection');
    };

    // User selects "Yes" to having queries
    const handleQueryYes = () => {
        setStep('queryForm');
    };

    // User selects "No" to having queries - directly call Lead API
    const handleQueryNo = async () => {
        setLoading(true);
        try {
            await callLeadAPI(null);
            // Redirect or show success message
            alert('Admission inquiry submitted successfully!');
            // Reset flow or redirect to dashboard
            setStep('admission');
            setAdmissionData(null);
        } catch (error) {
            console.error('Error calling Lead API:', error);
            alert('Failed to complete submission. Please try again.');
        } finally {
            setLoading(false);
        }
    };


    // Handle back from query form
    const handleBackToSelection = () => {
        setStep('querySelection');
    };


    // Call Lead API
    const callLeadAPI = async (queryData) => {
        try {
            // Replace with your actual Lead API endpoint
            const leadPayload = {
                firstName: admissionData.firstName,
                lastName: admissionData.lastName,
                email: admissionData.email,
                phone: admissionData.phone,
                institute: admissionData.institute,
                course: admissionData.course,
                message: admissionData.message,
                hasQuery: queryData !== null,
                querySubject: queryData?.subject || null,
                queryDescription: queryData?.description || null,
                status: 'NEW'
            };

            const response = await fetch('YOUR_LEAD_API_ENDPOINT', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${token}`,
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(leadPayload)
            });

            if (!response.ok) {
                throw new Error('Failed to call Lead API');
            }

            return await response.json();
        } catch (error) {
            console.error('Error calling Lead API:', error);
            throw error;
        }
    };

    return (
        <>
            {step === 'admission' && (
                <AdmissionForm/>
            )}

            {step === 'querySelection' && (
                <QuerySelectionModal
                    onYes={handleQueryYes}
                    onNo={handleQueryNo}
                    loading={loading}
                />
            )}

            {step === 'queryForm' && (
                <QueryFormFr/>
            )}
        </>
    );
};

export default AdmissionFlowContainer;