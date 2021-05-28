import Toast from '../../miniprogram_npm/@vant/weapp/toast/toast';
import config from "../../config/config";

Page({
  data: {
    tabber: "mine",
    id: 0,
    name: "未登录",
    words: "这个人很懒哦，还没有填写介绍",
    like: 0,
    likeType: "like-o",
    good: 0,
    goodType: "good-job-o",
    medal: 0,
    medalType: 0,
    showShare: false,
    shareOptions: [
      { name: '微信', icon: 'wechat', openType: 'share' },
      { name: '微博', icon: 'weibo' },
      { name: '复制链接', icon: 'link' },
      { name: '分享海报', icon: 'poster' },
      { name: '二维码', icon: 'qrcode' },
    ],
    token: "",
    longToken: "",
    login: "退出登录"
  },
  
  //分享
  onShareOpen() {this.setData({showShare: true})},
  onShareClose() {this.setData({showShare: false})},

  //底部选项
  onTabberChange(event) {
    this.setData({tabber: event.detail})
    wx.redirectTo({url: `/pages/${event.detail}/${event.detail}`})
  },

  //关注
  onLikeChange(e) {
    let type = e.currentTarget.dataset.type
    if (type == "like-o") {
      this.setData({
        like: this.data.like + 1,
        likeType: "like"
      })
      Toast.success('已关注');
    } else if (type == "like") {
      this.setData({
        like: this.data.like - 1,
        likeType: "like-o"
      })
      Toast.success("关注已取消")
    }
  },

  //点赞
  onGoodChange(e) {
    let type = e.currentTarget.dataset.type
    if (type == "good-job-o") {
      this.setData({
        good: this.data.good + 1,
        goodType: "good-job"
      })
      Toast.success("已点赞")
    } else if (type == "good-job") {
      this.setData({
        good: this.data.good - 1,
        goodType: "good-job-o"
      })
      Toast.success("点赞已取消")
    }
  },

  //退出登录
  exit() {
    if (this.data.login == "退出登录") {
      this.setData({
        id: 0,
        name: "未登录",
        login: "点击登录"
      })
    } else {
      wx.navigateTo({
        url: '/pages/login/login',
      })
    }
  },

  onShow() {
    try {
      this.setData({
        id: wx.getStorageSync('id')
      })
    } catch(e) {
      console.log(e);
    }
    if (this.data.id != 0) {
      console.log("wsy")
      this.setData({
        token: wx.getStorageSync("token"),
        longToken: wx.getStorageSync("longToken")
      })
      wx.request({
        url: config.host + "/api/user/getInfo?id=" + this.data.id,
        header: {
          "nju-token": this.data.token,
          "nju-long-token": this.data.longToken
        },
        success: (res) => {
          console.log(res);
          this.setData({name: res.data.content.username});
        },
        fail: (res) => {
          console.log(res)
        }
      })
    }
    if (this.data.id <= 0) {
      this.setData({login: "点击登录"})
    } else {
      this.setData({login: "退出登录"})
    }
  },

  onLoad() {
    
  },
})
