import React, { Component } from "react";
import { connect } from "react-redux";
import { bindActionCreators } from "redux";


import { getSummary } from "./dashboardActions";
import ContentHeader from "../common/template/contentHeader";
import Content from "../common/template/content";
import ValueBox from "../common/widget/valueBox";
import Row from "../common/layout/row";


class Dashboard extends Component {

    componentDidMount(){
        this.props.getSummary();
    }

    render() {
        const {credits, debits, balance} = this.props.summary;
        const balanceFormatted = balance.toFixed(2);
        return (
            <div>
                <ContentHeader title='Dashboard' small='Versão 1.0' />
                <Content>
                    <Row>
                        <ValueBox cols='12 4' color='green' icon='bank'
                            value={`R$ ${credits}`}text='Total de Créditos' />
                        <ValueBox cols='12 4' color='red' icon='credit-card'
                            value={`R$ ${debits}`} text='Total de Débitos' />
                        <ValueBox cols='12 4' color='blue' icon='money'
                            value={`R$ ${balanceFormatted}`}  text='Valor Consolidado' />
                    </Row>
                </Content>
            </div>
        )
    }
}

const mapStateToProps = state => ({summary: state.dashboard.summary});
const mapDispatchToProps = dispatch => bindActionCreators({getSummary}, dispatch);
export default connect(mapStateToProps, mapDispatchToProps)(Dashboard);