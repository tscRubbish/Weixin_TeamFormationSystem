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
    date: "",
    showDate: false
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

  onNameClose() {
    this.setData({ showName: false });
  },

  
  //改变介绍
  onIntroChange() {
    
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
      intro: options.intro
    })
  },
})