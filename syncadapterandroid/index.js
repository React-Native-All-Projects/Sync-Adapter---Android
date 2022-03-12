/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';

AppRegistry.registerHeadlessTask('MainService', () =>
  require('./src/services/main-service')
);
AppRegistry.registerHeadlessTask('NetworkService', () =>
  require('./src/services/network-service')
);
AppRegistry.registerComponent(appName, () => App);
