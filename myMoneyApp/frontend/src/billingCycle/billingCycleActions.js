import axios from 'axios';
import { toastr } from 'react-redux-toastr';
import { initialize } from 'redux-form';
import { showTabs, selectTab } from '../common/tab/tabActions';

const BASE_URL = 'http://localhost:8080/api';

const INITIAL_VALUES = {credits: [{}], debits: [{}]}

export function getList() {
  const request = axios.get(`${BASE_URL}/billingCycles`);
  return {
    type: 'BILLING_CYCLES_FETCHED',
    payload: request
  }
}
export function create(values) {
  return submit(values, 'post')
}

export function update(values) {
  return submit(values, 'put')
}

export function remove(values) {
  return submit(values, 'delete')
}


function submit(values, method) {
  return dispatch => {
      const id = values.id ? values.id : ''
      axios[method](`${BASE_URL}/billingCycles${`/${id}`}`, values)
          .then(resp => {
              toastr.success('Sucesso', 'Operação Realizada com sucesso.')
              dispatch(init())
          })
          .catch(error => {
            handleRequestError(error);
          });
  }
}


function handleRequestError(error) {
  if (error.response && error.response.data && error.response.data.errors) {
    const errors = error.response.data.errors;
    errors.forEach((errorMsg) => toastr.error('Erro', errorMsg));
  } else {
    toastr.error('Erro', 'Erro ao processar a solicitação.');
  }
}



export function showUpdate(billingCycles) {
  return [
    showTabs('tabUpdate'),
    selectTab('tabUpdate'),
    initialize('billingCycleForm', billingCycles)
  ]
}

export function showDelete(billingCycles) {
  return [
    showTabs('tabDelete'),
    selectTab('tabDelete'),
    initialize('billingCycleForm', billingCycles)
  ]
}



export function init() {
  return [
    showTabs('tabList', 'tabCreate'),
    selectTab('tabList'),
    getList(),
    initialize('billingCycleForm', INITIAL_VALUES)
  ]
}