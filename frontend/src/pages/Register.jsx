import { useState } from 'react';
import axios from 'axios';
import './Register.css'; // ✅ Import the CSS

export default function Register() {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [msg, setMsg] = useState('');

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const res = await axios.post('http://localhost:8080/api/auth/register', {
        name,
        email,
        password
      });
      setMsg(res.data);
    } catch (err) {
      setMsg('Registration failed');
    }
  };

  return (
    <form onSubmit={handleRegister} className="register-container">
      <h2>Register</h2>
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={e => setName(e.target.value)}
        required
      />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={e => setEmail(e.target.value)}
        required
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={e => setPassword(e.target.value)}
        required
      />
      <button type="submit">Register</button>
      <p>{msg}</p>
    </form>
  );
}
