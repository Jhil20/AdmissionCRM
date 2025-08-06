import {createSlice } from "@reduxjs/toolkit";

const initialState = {
    status : false,
    userData : null
}

const authSlice = createSlice({
    name : "auth",
    initialState,
    reducers:{
        login : (state,action) => {
            state.status = true
            state.userData = action.payload
            localStorage.setItem('Token', action.payload.jwtToken);
            localStorage.setItem('Role',action.payload.role)
        },
        logout : (state) => {
            state.status = false
            state.userData = null
            localStorage.removeItem('Token');
            localStorage.removeItem('Role');
        }
    }
})

export default authSlice.reducer

export const {login,logout} = authSlice.actions