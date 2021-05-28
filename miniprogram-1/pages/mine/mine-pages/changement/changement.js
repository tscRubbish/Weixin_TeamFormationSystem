import config from "../../../../config/config"
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    showImage: false,
    showIntro: false,
    id: 0,
    name: null,
    words: null,
    pic: "",
    email: ""
  },

  //编辑头像
  showImage() {
    this.setData({ showImage: true });
  },

  onImageClose() {
    this.setData({ showImage: false });
  },

  //改变介绍
  onIntroChange(e) {
    this.setData({words:e.detail})
    console.log(this.data.words)
  },

  //保存
  save() {
    wx.request({
      url: config.host + "/api/user/getInfo?id=" + this.data.id,
      header: {
        "nju-token": app.globalData.token,
        "nju-long-token": app.globalData.longToken
      },
      success: (res) => {
        wx.request({
          url: config.host + "/api/user/changeInfo",
          method: "POST",
          header: {
            "nju-token": app.globalData.token,
            "nju-long-token": app.globalData.longToken
          },
          data: {
            "description": this.data.words,
            "email": res.data.content.email,
            "id": this.data.id,
            "likes":res.data.content.likes,
            "score":res.data.content.score,
            "password": res.data.content.password,
            "pic": this.data.pic,
            "tags": [
              "string"
            ],
            "userType": "Admin",
            "username": res.data.content.username
          },
          success: (res) => {
            console.log(res);
          }
        })
      }
    })
  },

  //返回
  onClickLeft() {
    wx.navigateBack({
      delta: 1,
    })
    wx.showToast({ title: '返回', icon: 'none' });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      id: options.id,
      name: options.name,
      intro: options.intro,
      email: options.email
    })

  },
})