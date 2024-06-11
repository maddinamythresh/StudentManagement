import { useContext, useState } from "react";
import Main from "./Main";
import { Link, useNavigate } from "react-router-dom";
import { RoleContext } from "../App";
export default function Login() {

    const navigate=useNavigate();
    const {val}=useContext(RoleContext)
    
    
    async function handlefun(event){
        event.preventDefault();
        
        const formData=new FormData(event.target);
        const datas= Object.fromEntries(formData.entries());

        // const payload = {
        //     // Your payload data here
        //     username: 'Rajesh',
        //     password: '1234'
        //   };
         
        const response=await fetch("http://localhost:8080/alogin",{
            method:"POST",
            headers:{
                'Content-Type':'application/json',
            },
            body:JSON.stringify(datas)
        })
        const data = await response.text();
        const token=data.split("HelloWorld[")[0];
        const authority=data.split("HelloWorld[")[1].split("]")[0];
        console.log(authority)
        val.role=authority;
        localStorage.setItem("token",token);
        localStorage.setItem("Authority",authority);
        console.log(val)
        if(authority=="ADMIN"){
            navigate('/adminregister');
        }
        else if(authority==="USER"){
            navigate("/");
            
        }
        else{
            navigate("/login");
        }
    } 


    return(
        <>

        <form onSubmit={(event)=>handlefun(event)}>
            <table>
                <tr>
                    <td><label htmlFor="username">Username</label></td>
                    <td><input type="text" name="username"/></td>
                </tr>
                <tr>
                    <td><label htmlFor="password">Password</label></td>
                    <td><input type="password" name="password"/></td>
                </tr>
            </table>
            <button>Submit</button>
        </form>
        
        </>
    )
    

}