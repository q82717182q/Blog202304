import React, {useEffect} from "react";
import {Link} from "react-router-dom";
import axios from "axios";

const NavComponent = () => {

  return (
      <div>
        <nav className="navbar navbar-light bg-dark navbar-expand-lg flex-nowrap">
          <ul className="navbar-nav" style={{margin: "0 10px"}}>
            <li className="nav-item">
              <Link className="nav-link text-light" to="/BlogInput">
                BlogInput
              </Link>
            </li>
            <li className="nav-item">
              <Link className="nav-link text-light" to="/blog">
                Blog
              </Link>
            </li>
            <li>
              <Link className="nav-link text-light" to="/typeInput">
                TypeInput
              </Link>
            </li>
            <li>
              <Link className="nav-link text-light" to="/type">
                Type
              </Link>
            </li>
            <li>
              <Link className="nav-link text-light" to="/tagInput">
                TagInput
              </Link>
            </li>
            <li>
              <Link className="nav-link text-light" to="/tag">
                Tag
              </Link>
            </li>
            <li>
              <Link className="nav-link text-light" to="/login">
                Login
              </Link>
            </li>
          </ul>
        </nav>
      </div>
  );
};

export default NavComponent;
