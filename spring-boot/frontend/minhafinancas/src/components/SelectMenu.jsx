import React from "react";

function SelectMenu(props){
    
    const option = props.lista.map(option =>{
        return(
            <option key={option.value} value={option.value}>{option.label}</option>
        )
    });

    return(
        <select {...props}>
            {option}
        </select>

    )
}

export default SelectMenu;