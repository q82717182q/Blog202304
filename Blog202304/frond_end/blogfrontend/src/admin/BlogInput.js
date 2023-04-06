import React, { useState , useEffect} from "react";
import axios from "axios";
import { Container, Row, Col } from "react-grid-system";
import MDEditor from '@uiw/react-md-editor';



function BlogInput() {
  const [content, setContent] = useState("");
  const [firstPicture, setFirstPicture] = useState("");
  const [title, setTitle] = useState("");
  const [tags, setTags] = useState([]);
  const [types, setTypes] = useState([]);
  const [published] = useState("false");


  useEffect(() => {
    const fetchTagAndType = async () => {
      try{
        const tagsResponse = await axios.get("http://localhost:8080/tag");
        setTags(tagsResponse.data);
        const typesResponse = await axios.get("http://localhost:8080/type");
        setTypes(typesResponse.data);
      }catch (e){
        console.error(e);
      }
    }
    console.log('I run once.');

    fetchTagAndType();
  }, []);
  const handleSave = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/blog", {
        content, firstPicture, title, published
      });
    } catch (error) {
      console.error(error);
    }
  };
  const handlePublish = async (event) => {
    event.preventDefault();
    try {
      const response = await axios.post("http://localhost:8080/blog/", {
        content, firstPicture, title, published: true
      });
    } catch (error) {
      console.error(error);
    }
  };



  return (
    <Container style={{ margin: "20px" }}>
      <Row>
        <Col>
          <div className="input-group mb-3">
            <span className="input-group-text" id="basic-addon1">
              標題
            </span>
            <input
                type="text"
                className="form-control"
                placeholder="url"
                value={title}
                onChange={(event) => setTitle(event.target.value)}
            />
          </div>
        </Col>
      </Row>
      <Row >
        <Col >
          <div className="mb-3" >
            <label htmlFor="blogInput">文字內容輸入處</label>
            <MDEditor
                value={content}
                onChange={setContent}
            />

          </div>
        </Col>
      </Row>
      <Row>
        <Col>
          <div className="input-group mb-3">
            <span className="input-group-text" id="basic-addon1">
              分類
            </span>
            <select className="form-control">
              {types.map((type) => (
                  <option key={type.id} value={type.id}>
                    {type.name}
                  </option>
              ))}
            </select>
          </div>
        </Col>
        <Col>
          <div className="input-group mb-3">
            <span className="input-group-text" id="basic-addon1">
              標籤
            </span>
            <select className="form-control">
              {tags.map((tag) => (
                  <option key={tag.id} value={tag.id}>
                    {tag.name}
                  </option>
              ))}
            </select>

          </div>
        </Col>
      </Row>
      <Row>
        <Col>
          <div className="input-group mb-3">
            <span className="input-group-text" id="basic-addon1">
              首圖
            </span>
            <input
              type="text"
              className="form-control"
              placeholder="url"
              value={firstPicture}
              onChange={(event) => setFirstPicture(event.target.value)}
            />
          </div>
        </Col>
      </Row>
      <Row>
        <Col>
        <button type="button" className="btn btn-primary" style={{marginRight: "10px"}} onClick={handlePublish} >發布</button>
        <button type="button" className="btn btn-primary" style={{marginRight: "10px"}} onClick={handleSave} >保存</button>
        <button type="button" className="btn btn-primary">取消</button>

        </Col>
      </Row>
    </Container>
  );
}

export default BlogInput;
