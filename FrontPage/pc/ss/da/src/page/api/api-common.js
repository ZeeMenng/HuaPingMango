import ApiBuilder from '../../ApiBuilder';

const builder = new ApiBuilder({
  baseUrl: 'http://202.106.10.250:62019/mango',
  simulation: false
});
/**
 * http://0.0.0.0:8080/
 * @type {string}
 */
console.log(window.BASEURL_01)
builder.BASEURL_01 = window.BASEURL_01 ? window.BASEURL_01 + 'mango' : 'http://202.106.10.250:62019/mango';

export default builder;
