import axios from 'axios'

const URL = 'http://localhost:8080/api/todos'


export const changeDescription = (event) => ({
    type: 'DESCRIPTION_CHANGED',
    payload: event.target.value
})


export const getAll = (description) => {
    const search = description ? `&description__regex=${description}` : ''
    const request = axios.get(`${URL}?sort=-createdAt${search}`)
    return {
        type: 'TODO_SEARCHED',
        payload: request
    }
}


export const add = (description) => {
    return dispacth => {
        axios.post(URL, { description })
            .then(resp => dispacth(clear()))
            .then(resp => dispacth(getAll()))
    }
}

export const markAsDone = todo => {
    return dispacth => {
        axios.put(`${URL}/${todo.id}`, { ...todo, done: true })
            .then(resp => dispacth(getAll()));

    }
}

export const markAsPending = todo => {
    return dispacth => {
        axios.put(`${URL}/${todo.id}`, { ...todo, done: false })
            .then((resp => dispacth(getAll())));
    }
}


export const remove = todo => {
    return dispacth =>{
        axios.delete(`${URL}/${todo.id}`)
            .then(resp => dispacth(getAll()));
    }
}

export const clear = () => {
    return {type: 'TODO_CLEAR'}
}