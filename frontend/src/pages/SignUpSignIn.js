import { useState } from 'react';
import { Link, useLocation } from 'react-router-dom';

import useApp from '../contexts/useApp';
import LogoImgSrc from '../img/logo.png';

const SignUpSignIn = () => {
  const app = useApp();
  const path = useLocation().pathname.toLowerCase();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const verifyReadableAscii = str => {
    return /^[\x20-\x7E]*$/.test(str);
  };
  const handleUsernameChange = e => {
    if (verifyReadableAscii(e.target.value)) { setUsername(e.target.value); }
  };
  const handlePasswordChange = e => {
    if (verifyReadableAscii(e.target.value)) { setPassword(e.target.value); }
  };
  const submit = async () => {
    const payload = JSON.stringify({ username, password });
    if (path === '/sign-up') {
      const res = await app.createUser(payload);
      switch (res.status) {
        case 200:
          app.fillStorage(payload);
          alert('User created');
          app.setSignedIn(true); break;
        case 409:
          alert('Account already exists, please sign in'); break;
        default:
          alert(`Something went wrong (${res.status})`); break;
      }
    } else if (path === '/sign-in') {
      const res = await app.verifyUser(payload);
      switch (res.status) {
        case 200:
          app.fillStorage(payload);
          alert('User verified');
          app.setSignedIn(true); break;
        case 404:
          alert('Account not found'); break;
        case 401:
          alert('Wrong password'); break;
        default:
          alert(`Something went wrong (${res.status})`); break;
      }
    }
  };

  return (
    <div className='page page-auth'>
      <header>
        <Link to='/'>
          <img src={LogoImgSrc} />
          <h1>MonKeyPass</h1>
        </Link>
      </header>

      <div className='content'>
        <div className='left'>
          <h1>Welcome to MonKeyPass</h1>
          <h2>Encrypted, secure data management<br />for everyone</h2>
        </div>
        <div className='right'>
          <div className='nav-row'>
            <Link to='/sign-up' className={path === '/sign-up' ? 'active' : ''}>CREATE AN ACCOUNT</Link>
            <Link to='/sign-in' className={path === '/sign-in' ? 'active' : ''}>LOG IN</Link>
          </div>
          <input type='text' value={username} onChange={handleUsernameChange} />
          <input type='password' value={password} onChange={handlePasswordChange} />
          <button onClick={submit}>Submit</button>
        </div>
      </div>
    </div>
  );
};

export default SignUpSignIn;
