import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Grid from '../template/grid'
import IconButton from '../template/iconButton'
import { changeDescription, getAll, add, clear } from './todoActions' 

class TodoForm extends Component {
    constructor(props){
        super(props)
        this.keyHandler = this.keyHandler.bind(this)
    }

    componentDidMount() {
        this.props.getAll()
    }

    keyHandler(e) {
        const { add,clear, getAll, description} = this.props;
        if (e.key === 'Enter') {
            e.shiftKey ? getAll(description) : add(description);
        } else if (e.key === 'Escape') {
            clear()
        }
    }

    render() {
        const { add, getAll, description} = this.props;

        return (
            <div role='form' className='todoForm'>
            <Grid cols='12 9 10'>
                <input id='description' className='form-control' placeholder='Adicione uma tarefa' onChange={this.props.changeDescription} onKeyUp={this.keyHandler} value={this.props.description}></input>
            </Grid>
    
            <Grid cols='12 3 2'>
                <IconButton btnStyle='primary' icon='plus'
                onClick={() => add(description)}></IconButton>
                <IconButton btnStyle='info' icon='search'
                onClick={() => getAll(description)}/>
                <IconButton btnStyle='default' icon='close' 
                onClick={this.props.clear}/>
            </Grid>
        </div>
        )
    }
}

const mapStateToProps = state => ({description: state.todo.description})
const mapDispatchToProps = dispatch => 
    bindActionCreators({add, changeDescription, getAll, clear }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(TodoForm)