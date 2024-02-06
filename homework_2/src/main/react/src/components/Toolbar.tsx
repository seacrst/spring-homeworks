import { Paper, Stack } from "@mui/material"
import EditIcon from '@mui/icons-material/Edit';
import PersonAddAltOutlinedIcon from '@mui/icons-material/PersonAddAltOutlined';
import styled from '@emotion/styled'
import { Dispatch, FC, SetStateAction, useRef, useState } from "react";
import { CustomerService } from "../services/customers";
import Modal from "./Modal";
import { ModalState } from "../hooks/use-modal";

const Toolbar: FC<{customers: Customer[], handler: Dispatch<SetStateAction<Customer[]>>}> = ({customers, handler}) => {
  const ref = useRef({id: 0});
  const customerService = new CustomerService();

  const [modal, setModal] = useState<{open: boolean, type: modalType}>({open: false, type: "create_customer"});

  const getData = async ({age, email, name}: CustomerDto) => {
    console.log({age, email, name})
    if (modal.type === "create_customer") {
      await customerService.addNewCustomer(name, age, email);
      handler(await customerService.getAllCustomers());
    }

    if (modal.type === "update_customer" && ref.current.id > 0) {
      await customerService.updateCustomer({
        age: age, email, name, id: ref.current.id,
        accounts: []
      });
      customerService.getAllCustomers();
    }
  }


  const fetchCustomer = async (id: string | number) => {
    const res = await customerService.getUserById(id);
    handler([res]);
  }



  return (
    <>
    <Stack spacing={2} style={{padding: "0 16px"}}>
      <Paper style={{textAlign: "center"}}>Клієнти</Paper>
      
      <Row><Text onClick={async () => handler(await customerService.getAllCustomers())}>ВСІ</Text> <AddCustomer onClick={() => setModal({...modal, open: true})}/></Row>
      {
        customers.map(({name, id}) => (
          <Row key={name + id}>
            <Text onClick={() => fetchCustomer(id)}>{name}</Text>
            <Edit onClick={async () => {
              ref.current.id = id;

              await ModalState.load(id);
              setModal({open: true, type: "update_customer"});
            }}/>
          </Row>
        ))
      }
    </Stack>
    <Modal type={modal.type} open={modal.open} close={() => setModal({ ...modal, open: false })} getData={getData} fieldType={"select"}/>
    </>
  )
}

export default Toolbar;


const Edit = styled(EditIcon)`
cursor: pointer;
:hover {
  fill: red;
}
  
`

const AddCustomer = styled(PersonAddAltOutlinedIcon)`
cursor: pointer;
:hover {
  fill: red;
}
  
`


const Row = styled(Paper)`
  padding: 8px 16px; 
  display: flex; 
  justify-content: space-between;
  align-items: center;
`

const Text = styled.span`
  flex-grow: 1.5;
  border-bottom: 1px solid #ccc;
  padding: 2px;
  cursor: pointer;
  :hover {
    border-color: red;
  }
`


