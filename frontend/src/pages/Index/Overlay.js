import { Fragment } from "react";
import { Link } from "react-router-dom";

import useApp from "../../contexts/useApp";
import LogoImgSrc from '../../img/logo.png';

const Overlay = () => {
  const app = useApp();

  return (
    <div className='page-index-overlay'>
      <header>
        <div className='left'>
          <div className='logo-container'>
            <img src={LogoImgSrc} />
            <h1>MonKeyPass</h1>
          </div>
        </div>
        <div className='right'>
          {app.signedIn ?
            <Link to='/dashboard' className='dashboard'>Dashboard</Link> :
            <Fragment>
              <Link to='/sign-up' className='sign-up'>Sign up</Link>
              <Link to='/sign-in' className='sign-in'>Sign in</Link>
            </Fragment>}
        </div>
      </header>
      <div className='desc'>
        <h1>Welcome to MonKeyPass.</h1>
        <h1 className='light'>Encrypted data management for everyone.</h1>
      </div>
    </div>
  );
};

export default Overlay;
