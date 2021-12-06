import { useEffect } from "react";
import { useHistory } from 'react-router-dom';

import useApp from "../contexts/useApp";

const Loading = props => {
  const dest = props.location.state.dest || '/';
  const app = useApp();
  const history = useHistory();

  useEffect(() => {
    if (!app.loading) {
      history.push(dest);
    }
  }, [app.loading, dest, history]);

  return (
    <div className="page">
      <h1>Loading page</h1>
    </div>
  );
};

export default Loading;
