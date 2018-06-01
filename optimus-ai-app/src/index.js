import React from 'react';
import ReactDOM from 'react-dom';
import './main/resources/css/index.css';
import OptimusApp from './main/js/classes/OptimusApp';
import registerServiceWorker from './registerServiceWorker';

ReactDOM.render(<OptimusApp />, document.getElementById('root'));
registerServiceWorker();
