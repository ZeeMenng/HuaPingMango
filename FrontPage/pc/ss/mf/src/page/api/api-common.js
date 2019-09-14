import ApiBuilder from '../../ApiBuilder';

const builder = new ApiBuilder({
  baseUrl: 'http://localhost:3000/apis',
  simulation: false
});
/**
 * http://0.0.0.0:8080/
 * @type {string}
 */

builder.BASEURL_01 = window.BASEURL_01 ? window.BASEURL_01 : 'http://172.16.100.231:60011/';

export default builder;
