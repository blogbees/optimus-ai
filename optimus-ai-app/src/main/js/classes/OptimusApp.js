import React, { Component } from 'react';
import logo from '../../resources/images/logo.jpg';
import 'semantic-ui-css/semantic.min.css';
import { Header, Button, Icon } from 'semantic-ui-react'
class OptimusApp extends Component {
  render() {
    return (
      <div>
        <Header as='h1' image={logo} textAlign='center' size='big'>
        </Header>
      </div>
    );
  }
}

export default OptimusApp;
