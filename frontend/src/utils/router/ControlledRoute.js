import { Redirect, useLocation, Route } from 'react-router-dom';

import useApp from '../../contexts/useApp';

const ControlledRoute = (props) => {
  const app = useApp();
  const location = useLocation();

  if (app.loading) {
    return (
      <Redirect to={{ pathname: '/loading', state: { dest: location.pathname } }} />
    );
  }

  switch (props.rule) {
    case 'signed-in-only':
      if (!app.signedIn) {
        return <Redirect to='sign-in' />;
      }
      break;
    case 'signed-out-only':
      if (app.signedIn) {
        return <Redirect to='dashboard' />;
      }
      break;
    default:
      break;
  }

  return (
    <Route {...props} />
  );
};

export default ControlledRoute;
