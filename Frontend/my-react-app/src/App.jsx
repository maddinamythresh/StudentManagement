import Login from "./components/Login"
import Main from "./components/Main"
import Expence from "./components/Expence"
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import { createContext,useState } from "react";

export const RoleContext=  createContext(null);

export default function App() {
  const [role,setRole]=useState(null);
  const val={
    role,setRole
  }
  return (
    <RoleContext.Provider value={{val}}>

    <Router>
      <div>
        <Routes>
          <Route path="/login" element={<Login/>}></Route>
          <Route path="/adminregister" element={<Main/>}></Route>

        </Routes>
      </div>
    </Router>
    


    </RoleContext.Provider>
  )
}

