

import { useEffect, useState } from "react";
import Container from "@mui/material/Container";
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import Stack from "@mui/material/Stack";
import Paper from '@mui/material/Paper';
import Toolbar from "./Toolbar";
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import { CustomerService } from "../services/customers";
import { Table } from "@mui/material";
import VirtualizedList from "./Sidebar/List";
import { useMutation, useQuery, useQueryClient } from "@tanstack/react-query";
import Modal from "./Modal";
import axios from "axios";


const Dashboard = () => {
  const customerService = new CustomerService();
  const queryClient = useQueryClient();

  // Queries
  const {data, isLoading} = useQuery<Customer[]>({ queryKey: ['customers'], queryFn: () => fetch('http://localhost:9000/api/customers').then(
    (res) => {
      console.log({res})
      return res.json();
    },
  ), })

  // const mutation = useMutation({
  //   mutationFn: () => fetch("http://localhost:9000/api/customer", {
  //     method: "POST", 
  //     headers: {
  //       'Accept': 'application/json',
  //       'Content-Type': 'application/json'
  //     }, 
  //     body: JSON.stringify(getCreateValues())
  //   }),
  //   onSuccess: () => {
    
    //   },
    // })
    
    const [newCustomer, setNewCustomer] = useState(null);
    const [update, setUpdate] = useState({id: 0, state: false});
    const [account, setAccount] = useState({id: 0, state: false});
    const [deleteAccount, setDeleteAccount] = useState({id: 0, state: false});
    const [create, setCreate] = useState(false);
    
    const getCreateValues = async (customerValues: CustomerDto) => {
      
      const res = await fetch("http://localhost:9000/api/customer", {
        method: "POST", 
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(customerValues)
      });
    if (res.status === 201) {
      queryClient.invalidateQueries({ queryKey: ['customers'] })
    }
  }

  const deleteCustomer = (id: number) => {
    fetch("http://localhost:9000/api/customers/" + id, {
        method: "DELETE"
      })
      .then(() => {
        queryClient.invalidateQueries({ queryKey: ['customers'] })
      })

  }

  const getUpdateValues = async (customerValues: CustomerDto) => {
    fetch("http://localhost:9000/api/customers/" + update.id, {
        method: "PATCH", 
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(customerValues)
      }).then(() => {
        queryClient.invalidateQueries({ queryKey: ['customers'] })
      })
    }

  const getAccountValues = (currency: CustomerDto) => {
    fetch("http://localhost:9000/api/customers/" + account.id + "/account", {
        method: "POST", 
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(currency)
      }).then(() => {
        queryClient.invalidateQueries({ queryKey: ['customers'] })
      })
  }

  const getDeleteAccount = (data: CustomerDto) => {
    const {currency} = data as any
    fetch("http://localhost:9000/api/customers/" + deleteAccount.id + `/account?currency=${currency}`, {
        method: "DELETE", 
      }).then(() => {
        queryClient.invalidateQueries({ queryKey: ['customers'] })
      })
  }


  return (
    <Container style={{padding: "70px 32px"}}>
      <Grid container spacing={2}>
        <Grid item xs={4}>
          { isLoading ? <></> : <VirtualizedList customers={data} edit={(id) => setUpdate({id, state: true})} add={(id) => setAccount({id, state: true})} create={() => setCreate(true)} delete={(id) => deleteCustomer(id)} deleteAccount={(id) => setDeleteAccount({id, state: true})}  />}
        </Grid>
        <Grid item xs={8}>
        <TableContainer component={Paper}>
      <Table sx={{ minWidth: 650 }} aria-label="simple table">
        <TableHead>
          <TableRow>
            <TableCell>Name</TableCell>
            <TableCell align="left">Email</TableCell>
            <TableCell align="left">Age</TableCell>
            <TableCell align="left">Accounts</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {data?.map(({accounts, age, email, name, id}) => (
          <TableRow key={id} sx={{ '&:last-child td, &:last-child th': { border: 0 } }}>
              <TableCell component="th" scope="row">{name}</TableCell>
              <TableCell align="left">{email}</TableCell>
              <TableCell align="left">{age}</TableCell>
               <TableCell align="left">{accounts.map(({balance, number, currency}) => (
                <div style={{borderBottom: "1px solid #ccc", padding: "4px 0"}}>
                  Number: {number}
                  <br/>
                  Currency: {currency}
                  <br/>
                  Balance: {balance}
                </div>
              ))}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </TableContainer>
        </Grid>
      </Grid>
      
      <Modal open={create} close={() => setCreate(false)} type="create_customer" getData={getCreateValues} fieldType="text"/>
      <Modal open={update.state} close={() => setUpdate({ ...update, state: false })} type="update_customer" getData={getUpdateValues} fieldType={"text"}/>
      <Modal open={account.state} close={() => setAccount({...account, state: false})} type="add_account" fieldType="select" getData={getAccountValues}/>
      <Modal open={deleteAccount.state} close={() => setDeleteAccount({...deleteAccount, state: false})} type="del_account" fieldType="select" getData={getDeleteAccount}/>
    </Container>
  )
}

export default Dashboard;