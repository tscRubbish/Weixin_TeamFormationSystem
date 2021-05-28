import config from "../../../../config/config"

Page({

  /**
   * 页面的初始数据
   */
  data: {
    showImage: false,
    showName: false,
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

  //编辑用户名
  showName() {
    this.setData({ showName: true });
  },

  onNameChange(e) {
    this.setData({name:e.detail})
    console.log(this.data.name)
  },

  onNameClose() {
    this.setData({ showName: false });
  },

  
  //改变介绍
  onIntroChange() {
    
  },

  //返回
  onClickLeft() {
    wx.request({
      url: config.host + "/api/user/changeInfo",
      method: "POST",
      data: {
        "description": this.data.words,
        "email": this.data.email,
        "id": this.data.id,
        "likes": 0,
        "password": "string",
        "pic": this.data.pic,
        "score": 0,
        "tags": [
          "string"
        ],
        "userType": "Admin",
        "username": this.data.name
      },
      success: (res) => {
        console.log(res);
      }
    })
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