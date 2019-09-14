import React from 'react';
import "babel-polyfill";
import ReactDOM from 'react-dom';
import './index.css';
import {App,store} from './App';

import registerServiceWorker from './registerServiceWorker';

function render() {
  ReactDOM.render(<App />, document.getElementById('root'));
}
render();
store.subscribe(render);

registerServiceWorker();
