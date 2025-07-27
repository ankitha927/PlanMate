import React, { useEffect, useState } from "react";
import axios from "axios";
import './StudyPlanner.css';

const API_BASE = "http://localhost:8080/api/plans";

const StudyPlanner = () => {
  const [plans, setPlans] = useState([]);
  const [form, setForm] = useState({
    subject: "",
  topic: "",
  endDate: "",
  });
  const [editingId, setEditingId] = useState(null);
  const userId = localStorage.getItem("userId");

  useEffect(() => {
    if (!userId) return;
    axios
      .get(`${API_BASE}/user/${userId}`)
      .then((res) => setPlans(res.data))
      .catch((err) => console.error(err));
  }, [userId]);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleAddOrUpdate = () => {
    const planData = {
      ...form,
      user: { id: userId },
    };

    if (editingId) {
      axios.put(`${API_BASE}/update/${editingId}`, planData).then((res) => {
        setPlans(plans.map((p) => (p.id === editingId ? res.data : p)));
        setForm({ subject: "", topic: "", endDate: "" });
        setEditingId(null);
      });
    } else {
      axios.post(`${API_BASE}/add`, planData).then((res) => {
        setPlans([...plans, res.data]);
        setForm({ subject: "", topic: "", endDate: "" });
      });
    }
  };

  const handleDelete = (id) => {
    axios.delete(`${API_BASE}/delete/${id}`).then(() => {
      setPlans(plans.filter((plan) => plan.id !== id));
    });
  };

  const handleEdit = (plan) => {
    setForm({
      
      subject: plan.subject,
      topic: plan.topic,
      endDate: plan.endDate,
    });
    setEditingId(plan.id);
  };

  const handleCancelEdit = () => {
    setEditingId(null);
    setForm({ subject: "", topic: "", endDate: "" });
  };

  const getCountdown = (endDate) => {
  const today = new Date();
  today.setHours(0, 0, 0, 0); // zero out time

  const deadlineDate = new Date(endDate);
  deadlineDate.setHours(0, 0, 0, 0); // zero out time

  const timeDiff = deadlineDate - today;
  const daysLeft = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));

  if (daysLeft > 0) {
    return (
      <span className="countdown green">

        {daysLeft} day(s) left
      </span>
    );
  } else if (daysLeft === 0) {
    return (
      <span style={{ color: "orange", fontWeight: "bold" }}>Due Today!</span>
    );
  } else {
    return (
      <span style={{ color: "red", fontWeight: "bold" }}>Overdue</span>
    );
  }
};

  return (
    <div className="study-planner-container">

      <h2>PLANMATE</h2>

      <div style={{ marginBottom: "10px" }}>
        <input
  name="subject"
  placeholder="Subject"
  value={form.subject}
  onChange={handleChange}
/>
<input
  name="topic"
  placeholder="Topic"
  value={form.topic}
  onChange={handleChange}
/>

<input
  name="endDate"
  type="date"
  value={form.endDate}
  onChange={handleChange}
/>

        <button onClick={handleAddOrUpdate} style={{ marginRight: "10px" }}>
          {editingId ? "Update Plan" : "Add Plan"}
        </button>
        {editingId && (
          <button onClick={handleCancelEdit}>Cancel Edit</button>
        )}
      </div>

      <h3> TASK TO BE DONE</h3>
      <ul style={{ listStyle: "none", paddingLeft: 0 }}>
        {plans.map((plan) => (
          <li key={plan.id} className="study-plan-item">

            <div>
              <strong>{plan.title}</strong> | <em>{plan.subject}</em> |{" "}
              {plan.topic} | 📅 {plan.endDate} | 🕒{" "}
              {getCountdown(plan.endDate)}
            </div>
            <div style={{ marginTop: "5px" }}>
              <button
                onClick={() => handleDelete(plan.id)}
                style={{ marginRight: "10px" }}
              >
                Delete
              </button>
              <button onClick={() => handleEdit(plan)}>Edit</button>
            </div>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default StudyPlanner;
