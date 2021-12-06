import { useState, useEffect } from 'react';
import { Link, useHistory } from 'react-router-dom';
import useSWR from 'swr';

import useApp from "../../contexts/useApp";
import DashboardContent from './DashboardContent';
import LogoImgSrc from '../../img/logo.png';

const Dashboard = () => {
  const app = useApp();
  const history = useHistory();

  const { data, mutate } = useSWR([`${app.baseEndpoint}/get-user-data`, app.getStorage()], (url, body) => fetch(url, {
    method: 'POST',
    headers: { 'Content-Type': 'application/json' },
    body
  }).then(res => res.json()));
  const [entries, setEntries] = useState([]);
  const [username, setUsername] = useState('');

  const signOut = () => {
    app.clearStorage();
    app.setSignedIn(false);
  };
  const deleteAccount = () => {
    app.deleteUser()
      .then(() => {
        app.clearStorage();
        alert('Deleted account');
        app.setSignedIn(false);
        history.push('/');
      });
  };

  useEffect(() => {
    console.log(data);
    if (!data) { return; }
    const logins = []; const contacts = []; const ids = []; const notes = [];
    for (let entry of data) {
      switch (entry.type) {
        case 'Login': logins.push(entry); break;
        case 'Contact': contacts.push(entry); break;
        case 'ID': ids.push(entry); break;
        case 'Note': notes.push(entry); break;
        default: break;
      }
    }
    logins.sort((a, b) => {
      if (a.info.webpage > b.info.webpage) { return 1; }
      else if (a.info.webpage < b.info.webpage) { return -1; }
      else { return 0; }
    });
    contacts.sort((a, b) => {
      if (a.info.name > b.info.name) { return 1; }
      else if (a.info.name < b.info.name) { return -1; }
      else { return 0; }
    });
    ids.sort((a, b) => {
      if (a.info.type > b.info.type) { return 1; }
      else if (a.info.type < b.info.type) { return -1; }
      else { return 0; }
    });
    notes.sort((a, b) => {
      if (a.info.title > b.info.title) { return 1; }
      else if (a.info.title < b.info.title) { return -1; }
      else { return 0; }
    });
    // setUsername(data.username);
    setUsername('a');
    setEntries(logins.concat(contacts, ids, notes));
  }, [data]);

  return (
    <div className="page page-dashboard">
      <header>
        <div className='left'>
          <Link to='/' style={{ textDecoration: 'none' }}>
            <div className='logo'>
              <img src={LogoImgSrc} alt='Logo' />
              <h1>MonKeyPass</h1>
            </div>
          </Link>
        </div>
        <div className='right'>
          <div className='dropdown'>
            <div className='dropdown-btn'>{username.charAt(0).toUpperCase()}</div>
            <div className='dropdown-content'>
              <button onClick={signOut} className='btn-sign-out'>Sign out</button>
              <button onClick={deleteAccount} className='btn-delete'>Delete account</button>
            </div>
          </div>
        </div>
      </header>

      <DashboardContent data={entries} mutate={mutate} />
    </div>
  );
};

export default Dashboard;
