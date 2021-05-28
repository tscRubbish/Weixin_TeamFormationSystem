// pages/Team_details/Team_details.js
Page({


  data: {
    active:2,
    Tname:"",
    match_name:"",
    TID:"",
    Timg_path:"",
    notice:"",
    memberID_array:[],
    memberName_array:[],
  },


  onLoad(event) {
    wx.setNavigationBarTitle({
      title: '队伍信息'
    });
    this.setData({
      Tname:event.Tname,
      match_name:event.match_name,
      TID:event.TID,
      Timg_path:event.Timg_path,
      notice:event.notice,
      memberID_array:event.memberID_array,
      memberName_array:event.memberName_array,
    });
    // console.log(event.memberID_array.split(","));

  },

  click_notice(event){
    wx.showModal({
      title: '公告',
      content: event.target.dataset.notice,
      showCancel:false,
      confirmText:"我知道了",
    })

  },

  click_member(event){
    wx.showActionSheet({
      itemList: String(event.target.dataset.membername_array).split(","),
      success (res) {
        //TODO
        //这里应该根据队员的ID跳转到对应的队员信息页面上
      },
    
    })
  },

  changeTeamInfo(event){
    wx.navigateTo({
      url: '/pages/changeTeamInfo/changeTeamInfo',
    })
  }

  
})