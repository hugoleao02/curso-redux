import React, { Component } from "react";
import { bindActionCreators } from "redux";
import { connect } from "react-redux";
import { getList } from './billingCycleActions';

class BillingCycleList extends Component {
    componentDidMount() {
        this.props.getList();
    }

    renderRows() {
        const { list } = this.props;
        console.log("list:", list);

        if (!list || list.length === 0) {
            return <tr><td colSpan="3">Nenhum registro encontrado.</td></tr>;
        }

        return list.content.map(bc => (
            <tr key={bc.id}>
                <td>{bc.name}</td>
                <td>{bc.month}</td>
                <td>{bc.year}</td>
            </tr>
        ));
    }

    render() {
        return (
            <div>
                <table className="table">
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>Mês</th>
                            <th>Ano</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.renderRows()}
                    </tbody>
                </table>
            </div>
        );
    }
}

const mapStateToProps = state => ({
    list: state.billingCycle.list
});

const mapDispatchToProps = dispatch =>
    bindActionCreators({ getList }, dispatch);

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(BillingCycleList);
