import React from "react";
import {Col, Container, Row} from "react-grid-system";
import {FaEnvelope, FaLine} from "react-icons/fa";

const FootComponent = () => {

  return (
      <footer className="bg-dark text-white mt-5">
        <Container className="py-3">
          <Row style={{ borderBottom: "1px solid #ccc"}}>
            <Col style={{ borderRight: "1px solid #ccc"}}>
              <h5>Latest Blog</h5>
              <p>
                ##
              </p>
            </Col>
            <Col style={{ borderRight: "1px solid #ccc"}}>
              <h5>Contact Us</h5>
              <p>
                <FaEnvelope className="mr-2"/> Email : q82717182q@example.com
              </p>
              <p>
                <FaLine className="mr-2"/> Line : yaoyao25
              </p>
            </Col>
            <Col style={{ borderRight: "1px solid #ccc"}}>
              <h5>About me</h5>
              <ul>
                #
                #
              </ul>
            </Col>
          </Row>
        </Container>
        <Container fluid className="bg-secondary py-1">
          <p className="text-center text-white m-0">&copy; 2023 Example Co.</p>
        </Container>
      </footer>
  );
};

export default FootComponent;
