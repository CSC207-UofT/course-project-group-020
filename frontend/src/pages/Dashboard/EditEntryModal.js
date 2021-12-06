import { useState, useEffect, Fragment } from 'react';
import Modal from 'react-modal';

import useApp from '../../contexts/useApp';
import DeleteImgSrc from '../../img/delete.png';

const editModalStyles = {
  content: {
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '420px',
    height: '70%'
  }
};
const deleteModalStyles = {
  content: {
    top: '50%',
    left: '50%',
    transform: 'translate(-50%, -50%)',
    width: '300px',
    height: '200px'
  }
};

const EditEntryModal = ({ show, toggle, entry, mutate }) => {
  const app = useApp();
  const [type, setType] = useState(null);
  const [id, setId] = useState(null);
  // Login
  const [loginUsername, setLoginUsername] = useState('');
  const [loginPassword, setLoginPassword] = useState('');
  const [loginPasswordHide, setLoginPasswordHide] = useState(true);
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

  const [showDeleteConfirmModal, setShowDeleteConfirmModal] = useState(false);
  const toggleDeleteConfirmModal = () => setShowDeleteConfirmModal(!showDeleteConfirmModal);

  useEffect(() => {
    if (!entry) { return; }
    setType(entry.type === 'Login' ? 'Login' : entry.type);
    setId(entry.id);
    switch (entry.type) {
      case 'Login':
        setLoginUsername(entry.info.username); setLoginPassword(entry.info.password);
        setLoginWebpage(entry.info.webpage); setLoginUrl(entry.info.url); break;
      case 'Contact':
        setContactName(entry.info.name); setContactNumber(entry.info.number);
        setContactAddress(entry.info.address); break;
      case 'ID':
        setIdType(entry.info.IDType); setIdNumber(entry.info.IDNumber);
        setIdExpiry(entry.info.IDExpirationDate); break;
      case 'Note':
        setNoteTitle(entry.info.title); setNoteContent(entry.info.content); break;
      default: return;
    }
  }, [entry]);

  const saveEntry = async () => {
    let data;
    switch (type) {
      case 'Login': data = [loginUsername, loginPassword, loginWebpage, loginUrl]; break;
      case 'Contact': data = [contactName, contactNumber, contactAddress]; break;
      case 'ID': data = [idType, idNumber, idExpiry]; break;
      case 'Note': data = [noteTitle, noteContent]; break;
      default: return;
    }
    const res = await app.updateEntry(id, type, data);
    if (res.status === 200) {
      mutate();
      alert('Saved');
    } else {
      alert(`Failed to save (${res.status})`);
    }
    toggle();
  };
  const deleteEntry = async () => {
    const res = await app.deleteEntry(id);
    if (res.status === 200) {
      mutate();
      alert('Deleted');
    } else {
      alert(`Failed to delete (${res.status})`);
    }
    toggleDeleteConfirmModal();
    toggle();
  };

  return (
    <Modal isOpen={show} onRequestClose={toggle} style={editModalStyles}>
      <div className='modal-content-container'>

        <h2>Your {type}</h2>

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
                  <button onClick={() => setLoginPasswordHide(!loginPasswordHide)}>{loginPasswordHide ? '🐵' : '🙈'}</button>
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
          <button onClick={toggleDeleteConfirmModal} className='btn btn-delete'>Delete</button>
          <button onClick={saveEntry} className='btn btn-confirm'>Save</button>
        </div>

      </div>

      <Modal isOpen={showDeleteConfirmModal} onRequestClose={toggleDeleteConfirmModal} style={deleteModalStyles}>
        <div className='modal-content-container modal-content-container-delete-confirm'>
          <img src={DeleteImgSrc} alt='Trash can' />
          <p>This will permanently delete this entry.<br />Are you sure?</p>
          <button onClick={deleteEntry}>Confirm</button>
        </div>
      </Modal>

    </Modal>
  );
};

export default EditEntryModal;
