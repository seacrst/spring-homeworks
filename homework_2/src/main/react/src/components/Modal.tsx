import Button from '@mui/material/Button';
import { useForm } from "react-hook-form"
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';
import { Dispatch, FC, SetStateAction } from "react";
import { ModalState } from "../hooks/use-modal";
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import Select, { SelectChangeEvent } from '@mui/material/Select';


interface ModalProps {
  open: boolean,
  close: Dispatch<SetStateAction<boolean>>
  type: modalType;
  getData: (data: CustomerDto) => void;
  fieldType: "text" | "select"
}

const Modal: FC<ModalProps> = ({open, close, type, getData, fieldType = "text"}) => {

  const {action, fields, title, description} = ModalState.use(type);
  
  const {
    setValue,
    getValues
  } = useForm<Customer | Customer & {currency: Currency} >();

  const submit = () => {
    getData(getValues());
    close(open);
  }

  return (
    <Dialog open={open} onClose={close}>
      <DialogTitle>{title}</DialogTitle>
      <DialogContent>
        <DialogContentText>{description}</DialogContentText>
        {
          fields.map(({type, label, field}) => {

           return fieldType === "text" ? <TextField
            key={label}
            autoFocus
            margin="dense"
            id="name"
            label={label}
            type={type}
            fullWidth
            variant="standard"
            onChange={({target}) => setValue(field, typeof label === "number" ? +target.value : target.value)}
            /> :
              <FormControl fullWidth>
              <InputLabel id="demo-simple-select-label">Валюта</InputLabel>
              <Select
                labelId="demo-simple-select-label"
                id="demo-simple-select"
                label="Валюта"
                onChange={({target}) => setValue(field, (target.value) as number | string)}
              >
                <MenuItem value={"UAH"}>UAH</MenuItem>
                <MenuItem value={"USD"}>USD</MenuItem>
                <MenuItem value={"EUR"}>EUR</MenuItem>
              </Select>
              </FormControl>
          }
          )
        }
      </DialogContent>
      <DialogActions>
        <Button onClick={() => close(open)}>Відмінити</Button>
        <Button onClick={submit} type="submit">{action}</Button>
      </DialogActions>
    </Dialog>

  )
}


export default Modal;