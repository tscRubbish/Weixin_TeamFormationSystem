import Toast from '../../../../miniprogram_npm/@vant/weapp/toast/toast';

Page({

  /**
   * 页面的初始数据
   */
  data: {
    feedback: "",
    rate: 3,
    activeNames: ['1'],
    showProblem: false
  },

  //常见问题
  onProblemOpen() {this.setData({showProblem: true})},
  onProblemClose() {this.setData({showProblem: false})},
  onProblemChange(event) {
    this.setData({
      activeNames: event.detail,
    });
  },

  //反馈
  onUpload() {
    if(this.data.feedback == "") {
      Toast.fail('您还没输入哦');
    }else{
      Toast.success('感谢您的反馈');
    }
    this.setData({feedback: ""});
  },

  //评价
  onRateChange(e) {this.setData({rate: e.detail})},

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
})