import React, {useEffect, useState} from "react";
import axios from "axios";
import { useNavigate} from "react-router-dom";

function Type() {
  const [types, setTypes] = useState([]);
  const [page, setPage] = useState(0);
  const [pageSize] = useState(10);
  const [totalPages, setTotalPages] = useState(0);
  const navigate = useNavigate();
  const getTypes = async (page, size) => {
    try {
      const response = await axios.get("http://localhost:8080/type/page", {params: {page, size}});
      setTypes(response.data.content);
      setTotalPages(response.data.totalPages);
    } catch (e) {
      console.error(e);
    }
  };
  useEffect(() => {
    getTypes(page, pageSize);
  }, [page, pageSize]);
  const handleEdit = (type) => {
    navigate('/typeInput', {state: {type}});
  };

  return (
      <div className="container mt-5">
        <div className="card">
          <div className="card-header">
            <h1 className="text-center">This is Type</h1>
          </div>
          <div className="card-body">
            <table className="table table-hover text-center">
              <thead className="thead-light">
              <tr>
                <th>#</th>
                <th>名稱</th>
                <th>操作</th>
              </tr>
              </thead>
              <tbody>
              {types.map((type, index) => (
                  <tr key={type.id}>
                    <td>{index + 1}</td>
                    <td>{type.name}</td>
                    <td>
                      <button className="btn btn-primary mx-1" onClick={() => handleEdit(type)}>Edit</button>
                      <button className="btn btn-danger">Delete</button>
                    </td>
                  </tr>
              ))}
              </tbody>
              <tfoot>
              <tr>
                <td>
                  <button
                      className="btn btn-primary m-2"
                      onClick={() => {
                        getTypes(page - 1, pageSize).then(() => {
                          setPage(page - 1)
                        });
                      }}
                      disabled={page === 0}>上一頁
                  </button>
                  <button
                      className="btn btn-primary mr-2"
                      onClick={() => {
                        getTypes(page + 1, pageSize).then(() => {
                          setPage(page + 1)
                        });
                        // 這邊建議放在getTags 之後，因為setPage即使先幫page +1 ，送出去還是會呈現沒有+1的結果，因為useState是不會同步更新的
                      }}
                      disabled={page === totalPages - 1}
                  >下一頁
                  </button>
                </td>
              </tr>
              </tfoot>
            </table>
          </div>
        </div>
      </div>
  );
}

export default Type;
