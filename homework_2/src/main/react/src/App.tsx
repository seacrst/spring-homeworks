import {
  QueryClient,
  QueryClientProvider,
} from '@tanstack/react-query'


import "./assets/app.css";
import Dashboard from "./components/Dashboard";

const App = () => {
  const queryClient = new QueryClient()

  return (
    <>
    <QueryClientProvider client={queryClient}>
    
      <Dashboard/>
    </QueryClientProvider>
    </>
  )
}

export default App;
