import { useState } from 'react';

import AddEntryModal from './AddEntryModal';
import EditEntryModal from './EditEntryModal';
import LoginImgSrc from '../../img/key.png';
import ContactImgSrc from '../../img/contact.png';
import IdImgSrc from '../../img/id.png';
import NoteImgSrc from '../../img/note.png';

const DashboardContent = ({ data, mutate }) => {
  const [showAddEntryModal, setShowAddEntryModal] = useState(false);
  const toggleShowAddEntryModal = () => setShowAddEntryModal(!showAddEntryModal);
  const [showEditEntryModal, setShowEditEntryModal] = useState(false);
  const toggleShowEditEntryModal = () => setShowEditEntryModal(!showEditEntryModal);

  const [modalEntry, setModalEntry] = useState(null);

  return (
    <div className='content'>
      <div className='left'>
        <button onClick={toggleShowAddEntryModal}>Add</button>
      </div>
      <div className='right'>
        <h2>My vaults</h2>
        {data.map(entry => {
          let imgSrc;
          let display1 = '';
          let display2 = '';
          switch (entry.type) {
            case 'Login':
              imgSrc = LoginImgSrc;
              display1 = entry.info.webpage;
              display2 = entry.info.username; break;
            case 'Contact':
              imgSrc = ContactImgSrc;
              display1 = entry.info.name;
              display2 = entry.info.number; break;
            case 'ID':
              imgSrc = IdImgSrc;
              display1 = entry.info.IDType; break;
            case 'Note':
              imgSrc = NoteImgSrc;
              display1 = entry.info.title; break;
            default: break;
          }
          return (
            <div key={entry.id} className='entry'>
              <img src={imgSrc} alt='Type of entry' />

              <div className='display-info'>
                <p onClick={() => {
                  setModalEntry(entry);
                  toggleShowEditEntryModal();
                }} className='display-info-strong' style={display1.length === 0 ? { color: '#999' } : {}}>{
                    display1.length === 0 ? 'Untitled' : display1
                  }</p>
                {display2 && <p className='display-info-weak'>{display2}</p>}
              </div>

            </div>
          );
        })}
      </div>

      {showAddEntryModal && <AddEntryModal show={showAddEntryModal} toggle={toggleShowAddEntryModal} mutate={mutate} />}
      {showEditEntryModal && <EditEntryModal show={showEditEntryModal} toggle={toggleShowEditEntryModal} entry={modalEntry} mutate={mutate} />}
    </div>
  );
};

export default DashboardContent;
