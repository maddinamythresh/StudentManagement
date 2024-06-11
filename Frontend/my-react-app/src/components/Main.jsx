import { createContext, useContext } from "react"
import authority from "../Context/authority";

export const TaskContext = createContext();
export default function Main(role) {

    async function handleSubmit(event){
        event.preventDefault();
        const formData= new FormData(event.target);
        const obj = Object.fromEntries(formData.entries());
        console.log(obj);
        const tokens=localStorage.getItem("token");

        console.log("Token"+localStorage.getItem("token"));
        const response=await fetch("http://localhost:8080/register",{
        method:"POST",
        headers:{
            "Content-Type":"application/json",
            Authorization:"Bearer "+tokens
        },
        body:JSON.stringify(obj)
        
    })
        const data=await response.text();
        console.log(data);
    }
    

    return (
        <>
            {authority()?
            <form onSubmit={(event)=>handleSubmit(event)}>

                <h1>Register Student</h1>

                <table>
                    <tr>
                        <td><label htmlFor="rollNo" >Roll No</label></td>
                        <td><input name="rollNo" /></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="username">Username</label></td>
                        <td><input name="username" /></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="password">Password</label></td>
                        <td><input name="password" /></td>
                    </tr>
                    <tr>
                        <td><label htmlFor="roles">Roles</label></td>
                        <td><select id="Roles" name="roles" >
                            
                            <option value="User">User</option>
                            <option value="Admin">Admin</option>
                            <option value="admin,user">User,Admin</option>
                        </select></td>
                    </tr>

                </table>

                <button>Submit</button>

            </form>

            :null}
 


        </>

    )



}