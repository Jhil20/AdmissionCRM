import {createSlice } from "@reduxjs/toolkit";

const initialState = {
    auth : {
        status : false,
        userData : {
            jwtToken : null,
            role : null
        }
    }
}

const authSlice = createSlice({
    name : "auth",
    initialState,
    reducers:{
        login : (state,action) => {
            state.auth.status = true
            state.auth.userData = action.payload
            console.log("paylod in slice",action.payload)
            // localStorage.setItem('Token', action.payload.jwtToken);
            // localStorage.setItem('Role',action.payload.role)
        },
        logout : (state) => {
            state.auth.status = false
            state.auth.userData = null
            // localStorage.removeItem('Token');
            // localStorage.removeItem('Role');
        }
    }
})

export default authSlice.reducer

export const {login,logout} = authSlice.actions