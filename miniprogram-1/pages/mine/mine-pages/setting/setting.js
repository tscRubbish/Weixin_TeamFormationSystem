import Toast from '../../../../miniprogram_npm/@vant/weapp/toast/toast';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    theme: "白天模式",
    fond: "正常",
    isLike: true,
    isGood: true,
    isMedal: true,
    isBusy: false,
    showTheme: false,
    columnsOfTheme: ["白天模式","夜间模式","护眼模式"],
    showFond: false,
    columnsOfFond: ["超小","较小","正常","较大","超大"]
  },

  //主题，字体大小
  onThemeOpen() {this.setData({showTheme: true})},
  onThemeClose() {this.setData({showTheme: false})},
  onThemeChange(event) {
    const { picker, value, index } = event.detail;
    Toast(`当前主题：${value}`);
    this.setData({theme: value})
  },
  onFondOpen() {this.setData({showFond: true})},
  onFondClose() {this.setData({showFond: false})},
  onFondChange(event) {
    const { picker, value, index } = event.detail;
    Toast(`当前主题：${value}`);
    this.setData({fond: value})
  },

  //关注，点赞，积分，勿扰
  onLikeChange() {this.setData({ isLike: !this.data.isLike})},
  onGoodChange() {this.setData({ isGood: !this.data.isGood});},
  onMedalChange() {this.setData({ isMedal: !this.data.isMedal});},
  onBusyChange() {this.setData({ isBusy: !this.data.isBusy});},

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

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})