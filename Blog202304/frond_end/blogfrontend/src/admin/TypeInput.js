import React, {useEffect, useRef, useState} from "react";
import {Col, Container, Row} from "react-grid-system";
import axios from "axios";
import { useLocation,useNavigate } from "react-router-dom";


function TypeInput() {
  const [name, setName] = useState("");
  const [saveResponse, setSaveResponse] = useState("");
  const [error, setError] = useState("");
  const [prevNames, setPrevNames] = useState([]);
  const prevIndexRef = useRef(-1);
  const TYPE_SAVE = "http://localhost:8080/type";
  const location = useLocation();
  const [editType, setEditType] = useState(null);
  const TYPE_UPDATE = "http://localhost:8080/type/update";
  const navigate = useNavigate();

  const handleSave = async (event) => {
    event.preventDefault();
    handleFocus();
    try {
      let response;
      if(editType){
        response = await axios.put(`${TYPE_UPDATE}/${editType.id}`, {
          name
        });
        navigate('/type')
      }else {
        response = await axios.post(TYPE_SAVE, {
          name
        });
      }

      setSaveResponse(response.data);
      setPrevNames([...prevNames, name]);
      setName("");
    } catch (e) {
      setError(e.response.data);
    }
  };
  useEffect(() => {
    prevIndexRef.current = prevNames.length;
    if (location.state && location.state.type){
      setEditType(location.state.type);
      setName(location.state.type.name);
    }
  }, [prevNames.length, location.state]);

  const handleFocus = () => {
    setSaveResponse("");
    setError("");
  }

  const handleKeyUp = (event) => {
    if (event.key === "ArrowUp") {
      if (prevIndexRef.current === 0) {
        return;
      } else {
        prevIndexRef.current -= 1;
        setName(prevNames[prevIndexRef.current]);
      }
    } else if (event.key === "ArrowDown") {
      if (prevIndexRef.current === prevNames.length) {
        //nothing
      } else if (prevIndexRef.current === prevNames.length - 1) {
        setName("");
        prevIndexRef.current += 1;
      } else {
        prevIndexRef.current += 1;
        setName(prevNames[prevIndexRef.current]);
      }
    }
  };

  return (
      <Container style={{margin: "20px"}}>
        <div style={{height: "80px"}}>
          {saveResponse && <div className="alert alert-success ">{saveResponse}</div>}
          {error && <div className="alert alert-danger ">{error}</div>}
        </div>
        <Row>
          <Col>
            <div className="input-group mb-3">
          <span className="input-group-text" id="basic-addon1">
            Type
          </span>
              <input
                  type="text"
                  className="form-control"
                  placeholder="Enter your type name here."
                  value={name}
                  onChange={(event) => setName(event.target.value)}
                  onFocus={handleFocus}
                  onKeyUp={(event) => {
                    if ((event.code === 'ArrowUp' || event.code === 'ArrowDown') && prevNames.length !== 0) {
                      handleKeyUp(event);
                    } else if (event.key === 'Enter') {
                      handleSave(event);
                    }
                  }}
              />
            </div>
          </Col>
        </Row>
        <Row>
          <Col>
            <button type="button" className="btn btn-primary" style={{marginRight: "10px"}} onClick={handleSave}>save
            </button>
            <button type="button" className="btn btn-primary">cancel</button>
          </Col>
        </Row>
      </Container>);
}

export default TypeInput;
