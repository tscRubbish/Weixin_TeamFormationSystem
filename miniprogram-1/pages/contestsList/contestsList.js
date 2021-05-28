// pages/ContestsList/contestsList.js
import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    tabber: "contestsList",
    keyword:'',
    page:1,
    hasResult:false,
    contestList:[],
  },
  handleInput(event) {
    console.log(event);
    let type = event.currentTarget.id;
    this.setData({
      [type]: event.detail
    })
    console.log(this.data.keyword)
  },
  async search(event){
    let that=this;
      request('/api/contest/getList',{word:this.data.keyword,page:this.data.page},{},'GET',function(result){
        console.log(result.data.content);
        that.setData({contestList:result.data.content});
        if (that.data.contestList==undefined|| that.data.contestList.length==0) that.setData({hasResult:false});
        else that.setData({hasResult:true});
        console.log(that.data.contestList);
        console.log(that.data.hasResult);
      });
  },
  onTabberChange(event) {
    this.setData({tabber: event.detail})
    wx.redirectTo({url: `/pages/${event.detail}/${event.detail}`})
  },
  prePage(event){
    if (this.data.page>1) {
      this.setData({page:this.data.page-1});
      this.search(event);
    }
  },
  nextPage(event){ 
     this.setData({page:this.data.page+1});
     this.search(event);
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