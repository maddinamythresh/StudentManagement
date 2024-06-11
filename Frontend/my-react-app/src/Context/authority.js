import { useContext } from "react";
import { RoleContext } from "../App";

export default async function authority(){
 const {val}=useContext(RoleContext)
    const tokens=localStorage.getItem("token");
    const response=await fetch("http://localhost:8080/confirmrole",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            Authorization:"Bearer "+tokens
        }
        
    })
    if(response.ok){
        const confirm= await response.text();
      return confirm===val.tokens;

    }
    

}