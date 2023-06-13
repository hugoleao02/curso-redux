import { combineReducers, legacy_createStore as createStore } from "redux";

import numerosReducers from './reducers/numeros'
const reducers = combineReducers({
    numeros: numerosReducers,
});

function storeConfig(){
    return createStore(reducers);
}

export default storeConfig