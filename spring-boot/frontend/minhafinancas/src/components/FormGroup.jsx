import React from "react";

function FormGroup(props) {
  return (
    <div className="form-group">
      <div className="form-group">
        <label htmlFor={props.htmlFor}>{props.label}</label>
        {props.children}
      </div>
    </div>
  );
}

export default FormGroup;
