import { configureStore } from "@reduxjs/toolkit";
import authSlice from "../Slices/AuthSlice"
const store = configureStore({
    reducer:{
        authSlice
    }
})

export default store;