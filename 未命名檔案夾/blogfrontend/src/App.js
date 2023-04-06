import React, { useState } from "react";
import NavComponent from "./component/navComponents";
import axios from "axios";

function App() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    const response = await fetch("http://localhost:8080/login", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ email, password }),
    });
    if (response.ok) {
      console.log("登入成功");
    } else {
      const error = await response.text();
      console.error(error);
    }
  };

  return (
    <div className="App">
      <NavComponent />
      <h1>haha</h1>
      <form onSubmit={handleSubmit} id="login-form">
        <div class="form-group">
          <label htmlFor="email" class="label label-primary">
            {" "}
            email:{" "}
          </label>
          <input
            type="email"
            id="email"
            name="email"
            className="form-control"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>
        <div class="form-group">
          <label htmlFor="password" class="label label-success">
            password:{" "}
          </label>
          <input
            type="text"
            id="password"
            name="password"
            className="form-control"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
        <button className="btn btn-primary" type="submit">
          Submit
        </button>
      </form>
    </div>
  );
}

export default App;
