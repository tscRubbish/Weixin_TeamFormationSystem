import config from '../config/config'

export default (url, data = {},header={}, method = 'GET',success) => {
  return new Promise((resolve, reject) => {
    wx.request({
      url: config.host + url,
      // url: config.ddnsHost + url,
      data,
      method,
      header: header,
      success: success,
      fail: (err) => {
        // console.log('请求失败', err)
        reject(err)
      }
    })
  }).catch((error) => {
    console.error(error);
  })
}