import React from "react";

const Field = ({ label, name, value, errors, onChange }) =>
    <div className="form-group">
        <label htmlFor={name}>{label}</label>
        <input type="text"
               name={name}
               className="form-control"
               value={value}
               onChange={onChange}/>
        <span className="form-text text-danger">{errors?.join(", ")}</span>
    </div>;

export default Field
