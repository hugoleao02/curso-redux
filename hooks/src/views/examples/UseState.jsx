import React, { useState } from 'react'
import PageTitle from '../../components/layout/PageTitle'
import SectionTitle from '../../components/layout/SectionTitle'

const UseState = (props) => {
    const [count, setCount] = useState(0);
    const [name, setName] = useState('');

    return (
        <div className="UseState">
            <PageTitle
                title="Hook UseState"
                subtitle="Estado em componentes funcionais!"
            />

            <SectionTitle title="Exercício #01" />
            <div className="center">
                <span className="text">{count}</span>
                <div>
                    <button 
                    onClick={() => setCount(count - 1)}
                    className="btn">-1</button>
                    <button
                    onClick={() => setCount(count + 1)} 
                    className="btn">+1</button>
                    <button
                    onClick={() => setCount(current => current + 1000)} 
                    className="btn">+1000</button>
                </div>
            </div>


            <SectionTitle title="Exercício #02" />
            <input className='intput' type='text'
            onChange={e => setName(e.target.value)}
            value={name}/>
            <spab className="text">{name}</spab>
        </div>
    )
}

export default UseState