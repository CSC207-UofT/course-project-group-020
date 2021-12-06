import { BrowserRouter, Redirect, Switch } from "react-router-dom";

import AppProvider from "./contexts/AppProvider";
import ControlledRoute from "./utils/router/ControlledRoute";
import Index from './pages/Index/Index';
import Loading from './pages/Loading';
import NotFound from './pages/NotFound';
import SignUpSignIn from './pages/SignUpSignIn';
import Dashboard from './pages/Dashboard/Index';

const App = () => {
  return (
    <div id="app">
      <BrowserRouter>
        <AppProvider>
          <Switch>
            <ControlledRoute path='/loading' exact component={Loading} />
            <ControlledRoute path='/' exact component={Index} />
            <ControlledRoute path='/(sign-up|sign-in)/' rule='signed-out-only' component={SignUpSignIn} />
            <ControlledRoute path='/dashboard' rule='signed-in-only' component={Dashboard} />
            <ControlledRoute path='/404' exact component={NotFound} />
            <Redirect to='/404' />
          </Switch>
        </AppProvider>
      </BrowserRouter>
    </div>
  );
};

export default App;
