import Stack from '@mui/material/Stack';
import Paper from '@mui/material/Paper';
import Grid from '@mui/material/Grid';
import { FC } from "react";

const Table: FC<Customer> = ({age, email, id: _, name}) => {
  return (
    <Grid container style={{ display: 'grid', gridTemplateRows: '1fr 1fr 1fr', gridTemplateColumns: '1fr 2fr 1fr', height: "0" }}>
      <Grid item>
        <Paper style={{ display: 'flex', flexDirection: "column", justifyContent: 'center', alignItems: 'center', height: "100%" }}>
            <span>{name}</span>
            <span>{email}</span>
        </Paper>
      </Grid>
      <Grid item>
        <Paper style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: "100%" }}>
          <Stack>
          <Paper style={{ display: 'flex', flexDirection: "column", justifyContent: 'center', alignItems: 'center', height: "100%" }}>
            <span>{age}</span>
          </Paper>
          </Stack>
        </Paper>
      </Grid>
      <Grid item>
        <Paper style={{ display: 'flex', justifyContent: 'center', alignItems: 'center', height: "100%" }}>
          Баланс
        </Paper>
      </Grid>
    </Grid>
  );
};

export default Table;
