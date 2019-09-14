import builder from './api-common';
let BASEURL_01 = builder.BASEURL_01;
/* 登录 */
export const Login = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/oauth/token',
    method: 'GET',
    simulation: false,
    simulator: ''
  });

/*** 退出  */
export const LogOut = builder.build({
    baseUrl: BASEURL_01,
    url: 'mango/oauth/logout',
    method: 'GET',
    simulation: false,
    simulator: ''
  });