import React,{ useState } from "react";
import NavComponent from "./component/navComponents";
import Blog from "./Blog";
import BlogInput from "./admin/BlogInput";
import TypeInput from "./admin/TypeInput";
import Type from "./Type";
import TagInput from "./admin/TagInput";
import Tag from "./Tag";
import Login from "./Login";
import {  Routes, Route } from "react-router-dom";


function App() {


  return (
    <div className="App">
      <NavComponent />
      <Routes>
        <Route path="/BlogInput" element={<BlogInput />} />
        <Route path="/blog" element={<Blog />} />
        <Route path="/typeInput" element={<TypeInput />} />
        <Route path="/type" element={<Type />} />
        <Route path="/tagInput" element={<TagInput />} />
        <Route path="/tag" element={<Tag />} />
        <Route path="/login" element={<Login />} />

      </Routes>

    </div>
  );
}

export default App;
