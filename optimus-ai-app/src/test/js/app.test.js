import React from 'react';
import ReactDOM from 'react-dom';
import OptimusApp from './OptimusApp';

it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<OptimusApp />, div);
  ReactDOM.unmountComponentAtNode(div);
});
