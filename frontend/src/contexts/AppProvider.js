import { useState, useEffect } from 'react';

import AppContext from './AppContext';

const storageKey = 'passman-store';
const baseEndpoint = 'http://localhost:8080';

const AppProvider = ({ children }) => {
  const [loading, setLoading] = useState(true);
  const [signedIn, setSignedIn] = useState(false);

  const getStorage = () => {
    return localStorage.getItem(storageKey);
  };
  const fillStorage = payload => {
    localStorage.setItem(storageKey, payload);
  };
  const clearStorage = () => {
    localStorage.removeItem(storageKey);
  };
  const verifyUser = storage => {
    return fetch(`${baseEndpoint}/verify-user`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: storage
    });
  };
  const createUser = payload => {
    return fetch(`${baseEndpoint}/create-user`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: payload
    });
  };
  const deleteUser = () => {
    const { username, password } = JSON.parse(getStorage());
    return fetch(`${baseEndpoint}/delete-user`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password })
    });
  };
  const createEntry = (type, data) => {
    const { username, password } = JSON.parse(getStorage());
    return fetch(`${baseEndpoint}/create-entry`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password, type, data })
    });
  };
  const updateEntry = (id, type, data) => {
    const { username, password } = JSON.parse(getStorage());
    return fetch(`${baseEndpoint}/update-entry`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password, id, type, data })
    });
  };
  const deleteEntry = id => {
    const { username, password } = JSON.parse(getStorage());
    return fetch(`${baseEndpoint}/delete-entry`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password, id })
    });
  };
  const getStrongPassword = length => {
    return fetch(`${baseEndpoint}/generate-password`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ length })
    });
  };

  useEffect(() => {
    const refresh = async () => {
      const storage = getStorage();
      const isSignedIn = storage !== null;
      setSignedIn(isSignedIn);
      setLoading(false);
    };

    refresh();
  }, []);

  console.log({ loading, signedIn });

  const contextVal = {
    baseEndpoint,
    loading,
    signedIn,
    setSignedIn,
    getStorage,
    fillStorage,
    clearStorage,
    verifyUser,
    createUser,
    deleteUser,
    createEntry,
    updateEntry,
    deleteEntry,
    getStrongPassword
  };

  return (
    <AppContext.Provider value={contextVal}>
      {children}
    </AppContext.Provider>
  );
};

export default AppProvider;
