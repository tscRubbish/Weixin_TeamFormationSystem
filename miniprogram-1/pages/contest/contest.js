// pages/contest/contest.js
import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
    contest:{},
    bannerList:[{"pic":"/static/images/EL比赛海报.png"}],
    creater:{
      id:123445,
      name:"南大科协"
    },
    info:{
      id:114514,
      name:"EL比赛",
      description:" 3月18日前：宣传组发送推送介绍比赛并通知同学们自行组队。同时队伍管理组建立咨询群并开始答疑。同时对报名程序进行调试\n3月24号：在线上会议中由学长学姐介绍经验。宣传组完成相关推送工作。队伍管理组在咨询群告知。\n 3月21日至3月28日：开放报名链接组队报名。队伍管理组创建小程序、问卷星，并在咨询群提醒同学报名。\n比赛分为算法组和交互组。\n算法组流程：在4月进行4次比赛，以OJ分数作为评奖标准。\n 分别为4月10日(4月11日)，4月17日(4月18日)，4月24日(4月25日)，5月8日(5月9日)，括号内为备用时间 \n 交互组流程：3月22日公布题目，4月中旬进行复赛检查PPT。其中3月24日玩有学长学姐进行技术介绍。5月27日或5月28日线下进行答辩，在答辩3至4天前截止作品提交。评委对作品进行评定"
    },
    infoActive:false,
  },
  showInfomation(event){
    if (this.data.infoActive==false)
    this.setData({infoActive : true});
    else this.setData({infoActive:false});
    console.log("infoActive="+this.data.infoActive);
  },
  toUserList(event){
    wx.redirectTo({
      url: '/pages/userList/userList',
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that=this;
    request('/api/contest/getInfo',{id:options.data},{},'GET',function (result) {
      console.log(result.data.content);
      that.setData({contest:result.data.content});
    });
    console.log(this.data.contest);
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