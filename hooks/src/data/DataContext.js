import React from "react";

export const data = {
    number:123,
    text: 'Context Api...'
}

const DataContext = React.createContext(data);
export default DataContext; 