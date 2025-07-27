import { Routes, Route, Link } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import StudyPlanner from './pages/StudyPlanner';
import './App.css';

export default function App() {
  return (
    <div className="app-background">
      <nav>
        <Link to="/login">Login</Link>
        <Link to="/register">Register</Link>
      </nav>
      <Routes>
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/planner" element={<StudyPlanner />} />
      </Routes>
    </div>
  );
}
