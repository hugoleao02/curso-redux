import React, { Component } from 'react'
import { connect } from 'react-redux'
import { bindActionCreators } from 'redux'

import Grid from '../template/Grid'
import IconButton from '../template/IconButton'
import { add, changeDescription, search, clear, searchDescription } from './todoActions' 

class TodoForm extends Component {
    constructor(props){
        super(props)
        this.keyHandler = this.keyHandler.bind(this)
    }

    componentDidMount() {
        this.props.search()
    }

    keyHandler(e) {
        const { add, clear ,searchDescription, description } = this.props 
        if (e.key === 'Enter') {
            e.shiftKey ? searchDescription() : add(description)
        } else if (e.key === 'Escape') {
            clear()
        }
    }

    render() {
        const { add ,searchDescription, description } = this.props
        return (
            <div role='form' className='todoForm'>
            <Grid cols='12 9 10'>
                <input id='description' className='form-control' placeholder='Adicione uma tarefa' onChange={this.props.changeDescription} onKeyUp={this.keyHandler} value={this.props.description}></input>
            </Grid>
    
            <Grid cols='12 3 2'>
                <IconButton btnStyle='primary' icon='plus' onClick={() => add(description)}></IconButton>

                <IconButton btnStyle='info' icon='search' onClick={() => searchDescription(description)}/>

                <IconButton btnStyle='default' icon='close' onClick={this.props.clear}/>
            </Grid>
        </div>
        )
    }
}

const mapStateToProps = state => ({description: state.todo.description})
const mapDispatchToProps = dispatch => 
    bindActionCreators({ add, changeDescription, search, clear,searchDescription }, dispatch)
export default connect(mapStateToProps, mapDispatchToProps)(TodoForm)