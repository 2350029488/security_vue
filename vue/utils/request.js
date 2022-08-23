import axios from 'axios'
import {getToken} from "./auth";

// axios.defaults.withCredentials=true

const request = axios.create({
  //这个就是前后端交互时,请求路径前缀
  baseURL: 'http://localhost:8099',
  timeout: 5000
})

// request 拦截器
// 可以自请求发送前对请求做一些处理
// 比如统一加token，对请求参数统一加密
request.interceptors.request.use(config => {
  config.headers['Content-Type'] = 'application/json;charset=utf-8';
// 设置请求头 重要的一步，不设置后端排除之外的接口不会携带请求头
  //有token才携带到后端  不然登录注册携带token会报错
 if (getToken()){
   config.headers['token'] = getToken();
 }


  return config
}, error => {
  return Promise.reject(error)
});

// response 拦截器cnpm install --save vue-axios
// 可以在接口响应后统一处理结果
request.interceptors.response.use(
  response => {
    let res = response.data;
    // 如果是返回的文件
    if (response.config.responseType === 'blob') {
      return res
    }
    // 兼容服务端返回的字符串数据
    if (typeof res === 'string') {
      res = res ? JSON.parse(res) : res
    }
    return res;
  },
  error => {
    console.log('err' + error) // for debug
    return Promise.reject(error)
  }
)


export default request

