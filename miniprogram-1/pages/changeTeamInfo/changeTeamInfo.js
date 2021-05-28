// pages/changeTeamInfo/changeTeamInfo.js
Page({

  data: {
    Tname:"123",
    MaxNum: 4,
    choose_match: "LPL",
    show: false,
    matchs:["AAA大赛", "LPL"],
    pic:"",
    Tintroduction:"这是简介",
    Tpassword:"123321"
  },

  onLoad(options) {

  },
  match_Change(event) {
    this.setData({
      choose_match:event.detail.value
    })
  },
  showPopup() {
    this.setData({ show: true });
  },
  changeInfo(){
    // 这个地方需要teamVo作为参数，说明需要将TeamVo从另一个界面传过来
  }

})