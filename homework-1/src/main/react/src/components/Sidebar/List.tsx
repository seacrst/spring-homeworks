import { Button } from "@mui/material";
import Box from '@mui/material/Box';
import AddBoxIcon from '@mui/icons-material/AddBox';
import EditIcon from '@mui/icons-material/Edit';
import List from "@mui/material/List/List";
import ListItem from '@mui/material/ListItem';
import ListItemButton from '@mui/material/ListItemButton';
import ListItemText from '@mui/material/ListItemText';
import { FixedSizeList, ListChildComponentProps } from 'react-window';
import DeleteIcon from '@mui/icons-material/Delete';

import PersonRemoveIcon from '@mui/icons-material/PersonRemove';

function renderRow(props: ListChildComponentProps) {
  const { index, style, data } = props;

  return (
    <ListItem style={style} key={index} component="div" disablePadding>
      <ListItemButton>
        <ListItemText primary={data} />
      </ListItemButton>
    </ListItem>
  );
}

export default function VirtualizedList({customers, edit, add, create, delete: deleteCustomer, deleteAccount}: {create: () => void, customers: Customer[], edit: (id: number) => void, add: (id: number) => void, delete: (id: number) => void , deleteAccount: (id: number) => void}) {
  return (
    <List dense>
      <ListItem>
          <ListItemText>Create 
            <Button onClick={create}>
              <AddBoxIcon/>
            </Button>
          </ListItemText>
        </ListItem>

      {
      customers.map( ({name, id}) => (
        <ListItem key={id}>
          <ListItemText>
            {name} <Button onClick={() => edit(id)}><EditIcon/></Button> 
            <Button onClick={() => add(id)}><AddBoxIcon/></Button>
            <Button onClick={() => deleteCustomer(id)}><PersonRemoveIcon/></Button>
            <Button onClick={() => deleteAccount(id)}><DeleteIcon/></Button>
            </ListItemText>
        </ListItem>
      ) )
    }</List>
    // <Box sx={{ width: '100%', height: 400, maxWidth: 360, bgcolor: 'background.paper' }}>
    //   <FixedSizeList
    //     itemData={}
    //     height={400}
    //     width={360}
    //     itemSize={customers.length}
    //     itemCount={200}
    //     overscanCount={5}
    //   >
    //     {renderRow}
    //   </FixedSizeList>
    // </Box>
  );
}
