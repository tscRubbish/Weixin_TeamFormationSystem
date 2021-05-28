// pages/userList/userList.js
import request from '../../utils/request'
Page({

  /**
   * 页面的初始数据
   */
  data: {
      tagsList:["软件","管理","数学","物理","化学 ","生物","地质","天文","文学","艺术","体育","经济","计算机","历史","政治","其他"],
      tagsCheck:[false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false],keyword:'',
      page:1,
      hasResult:false,
      userList:[],
  },
  tagsCheckChange(event){
    console.log(event.currentTarget.dataset.index);
    var index=event.currentTarget.dataset.index;
    var osel="tagsCheck["+index+"]"
    this.setData({[osel] : !this.data.tagsCheck[event.currentTarget.dataset.index]});
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
      request('/api/user/search',{word:this.data.keyword,page:this.data.page},{},'GET',function(result){
        console.log(result.data.content);
        that.setData({userList:result.data.content});
        if (that.data.userList==undefined|| that.data.userList.length==0) that.setData({hasResult:false});
        else that.setData({hasResult:true});
        console.log(that.data.userList);
        console.log(that.data.hasResult);
      });
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