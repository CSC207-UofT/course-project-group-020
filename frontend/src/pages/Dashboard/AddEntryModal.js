import { useState, useRef, Fragment } from 'react';
import Modal from 'react-modal';

import useApp from '../../contexts/useApp';

const modalStyles = {
  content: {
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '420px',
    height: '70%'
  }
};

const AddEntryModal = ({ show, toggle, mutate }) => {
  const app = useApp();

  const [type, setType] = useState('Login');
  const handleTypeChange = e => {
    reset();
    setType(e.target.value);
  };

  // Login
  const [loginUsername, setLoginUsername] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const [loginPasswordHide, setLoginPasswordHide] = useState(true);
  const loginPasswordEle = useRef(null);
  const [loginWebpage, setLoginWebpage] = useState('');
  const [loginUrl, setLoginUrl] = useState('');
  // Contact
  const [contactName, setContactName] = useState('');
  const [contactNumber, setContactNumber] = useState('');
  const [contactAddress, setContactAddress] = useState('');
  // ID
  const [idType, setIdType] = useState('');
  const [idNumber, setIdNumber] = useState('');
  const [idExpiry, setIdExpiry] = useState('');
  // Note
  const [noteTitle, setNoteTitle] = useState('');
  const [noteContent, setNoteContent] = useState('');

  const reset = () => {
    setLoginUsername(''); setLoginPassword(''); setLoginPasswordHide(true); setLoginWebpage(''); setLoginUrl('');
    setContactName(''); setContactNumber(''); setContactAddress('');
    setIdType(''); setIdNumber(''); setIdExpiry('');
    setNoteTitle(''); setNoteContent('');
  };
  const generateAndFillPassword = () => {
    app.getStrongPassword(14)
      .then(res => res.text())
      .then(setLoginPassword);
  };
  const submit = async () => {
    let data;
    switch (type) {
      case 'Login': data = [loginUsername, loginPassword, loginWebpage, loginUrl]; break;
      case 'Contact': data = [contactName, contactNumber, contactAddress]; break;
      case 'ID': data = [idType, idNumber, idExpiry]; break;
      case 'Note': data = [noteTitle, noteContent]; break;
      default: return;
    }
    const res = await app.createEntry(type, data);
    if (res.status === 200) {
      mutate();
      alert('Added');
    } else {
      alert(`Failed to add (${res.status})`);
    }
    toggle();
    reset();
  };

  return (
    <Modal isOpen={show} onRequestClose={toggle} style={modalStyles}>
      <div className='modal-content-container'>
        <select value={type} onChange={handleTypeChange}>
          <option value='Login'>Login</option>
          <option value='Contact'>Contact</option>
          <option value='ID'>ID</option>
          <option value='Note'>Note</option>
        </select>

        <div className='inputs-container'>
          {type === 'Login' ?
            <Fragment>
              <div className='input-container'>
                <p>Username</p>
                <input value={loginUsername} onChange={e => setLoginUsername(e.target.value)} type='text' />
              </div>
              <div className='input-container input-container-sensitive'>
                <p>Password</p>
                <div className='input-container-sensitive-flex-row'>
                  <input value={loginPassword} onChange={e => setLoginPassword(e.target.value)} type={loginPasswordHide ? 'password' : 'text'} placeholder='A strong password' />
                  <button onClick={() => setLoginPasswordHide(!loginPasswordHide)} ref={loginPasswordEle}>{loginPasswordHide ? '🐵' : '🙈'}</button>
                  <button onClick={generateAndFillPassword}>🗘</button>
                </div>
              </div>
              <div className='input-container'>
                <p>Website</p>
                <input value={loginWebpage} onChange={e => setLoginWebpage(e.target.value)} type='text' placeholder='Ex. Google' />
              </div>
              <div className='input-container'>
                <p>URL</p>
                <input value={loginUrl} onChange={e => setLoginUrl(e.target.value)} type='url' placeholder='Ex. https://google.com' />
              </div>
            </Fragment>
            : type === 'Contact' ?
              <Fragment>
                <div className='input-container'>
                  <p>Name</p>
                  <input value={contactName} onChange={e => setContactName(e.target.value)} type='text' placeholder='Bob Fish' />
                </div>
                <div className='input-container'>
                  <p>Phone number</p>
                  <input value={contactNumber} onChange={e => setContactNumber(e.target.value)} type='text' placeholder='100-200-3456' />
                </div>
                <div className='input-container'>
                  <p>Address</p>
                  <input value={contactAddress} onChange={e => setContactAddress(e.target.value)} type='text' placeholder='8 King St' />
                </div>
              </Fragment>
              : type === 'ID' ?
                <Fragment>
                  <div className='input-container'>
                    <p>Type</p>
                    <input value={idType} onChange={e => setIdType(e.target.value)} type='text' placeholder="Ex. Driver's license" />
                  </div>
                  <div className='input-container'>
                    <p>Number</p>
                    <input value={idNumber} onChange={e => setIdNumber(e.target.value)} type='text' placeholder='AB-12345...' />
                  </div>
                  <div className='input-container'>
                    <p>Expiry</p>
                    <input value={idExpiry} onChange={e => setIdExpiry(e.target.value)} type='date' placeholder='Ex. June 2025' />
                  </div>
                </Fragment>
                : type === 'Note' ?
                  <Fragment>
                    <div className='input-container'>
                      <p>Title</p>
                      <input value={noteTitle} onChange={e => setNoteTitle(e.target.value)} type='text' placeholder='Top secret' />
                    </div>
                    <div className='input-container'>
                      <p>Content</p>
                      <textarea value={noteContent} onChange={e => setNoteContent(e.target.value)} placeholder="My $1B idea: ..." />
                    </div>
                  </Fragment>
                  : null}
        </div>

        <div className='btns-container'>
          <button onClick={submit} className='btn btn-confirm'>Submit</button>
        </div>

      </div>
    </Modal>
  );
};

export default AddEntryModal;
