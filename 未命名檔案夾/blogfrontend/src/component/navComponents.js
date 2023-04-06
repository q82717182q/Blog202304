import React from "react";
import { Link } from "react-router-dom";


const NavComponent = () => {
  return (
    <div>
      <nav>
        <nav className="nav bar">
          <ul className="navbar-nav">
            <li className="nav-item">
              <Link className="nav-link" to="">
                Register
              </Link>
              <Link className="nav-link" to="">
                public home
              </Link>
            </li>
          </ul>

        </nav>
      </nav>
    </div>
  )
}

export default NavComponent;