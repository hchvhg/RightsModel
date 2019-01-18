import axios from 'axios'
import qs from 'qs'

var baseURL = 'http://localhost:8080/RightsModel/'

export default {
    'baseURL': baseURL,
    post(url, data) {
        return axios({
            method: 'post',
            baseURL: baseURL, //如果`url`不是绝对地址，那么`baseURL`将会加到`url`的前面
            url,
            data: qs.stringify(data),
            timeout: 15000,
            headers: { //请求头
                'X-Requested-With': 'XMLHttpRequest',
                'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',

            }
        }).then(
            (response) => {
                return checkStatus(response)
            }
        ).then(
            (res) => {
                return checkCode(res)
            }
        )
    },
    get(url, params) {
        return axios({
            method: 'get',
            baseURL: baseURL,
            url,
            params, // 选项是要随请求一起发送的请求参数----一般链接在URL后面  
            timeout: 10000,
            headers: {
                'X-Requested-With': 'XMLHttpRequest'
            }
        }).then(
            (response) => {
                return checkStatus(response)
            }
        ).then(
            (res) => {
                return checkCode(res)
            }
        )
    }
}

// 状态码 判断
function checkStatus (response) {
    // loading
    
    // 如果http状态码正常，则直接返回数据
    if (response && (response.status === 200 || response.status === 304 || response.status === 400)) {
      return response
      // 如果不需要除了data之外的数据，可以直接 return response.data
    }
    // 异常状态下，把错误信息返回去
    return {
      status: -404,
      msg: '这个接口出事了'
    }
    // -----------
    return response
}

function checkCode (res) {
    // 如果code异常(这里已经包括网络错误，服务器错误，后端抛出的错误)，可以弹出一个错误提示，告诉用户
    // if (res.status === -404) {
    //   console.error(res.msg)
    // }
    // if (res.data && (!res.data.success)) {
    //   console.error(res.data.error_msg)
    // }
    return res
}