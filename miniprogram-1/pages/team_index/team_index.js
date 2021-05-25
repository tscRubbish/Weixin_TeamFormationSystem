// index.js


// 获取应用实例
const app = getApp()

Page({
  data: {
    introduction:"",
    choose_match:"点我选择",
    show:false,
    matchs:["微信小程序开发大赛","LPL"],
    new_Tname:"",
    new_Num:0,
    tabber:"team_index",
    tab_active:0,
    TID:"",
    Tpassword:"",
    match_array:[
      {
        id:0,
        Tname:"5A430队",
        match_name:"微信小程序开发大赛",
        Num_now:4,
        Num_all:4,
        TID:"000001",
        Timg_path:"/img/12.jpg",
        notice:"公告内容1",
        memberID_array:["000001","123456","111111","222222"],
        memberName_array:["TSC","WTT","RY","WSR"]
      },
      {
        id:1,
        Tname:"5A430队-2",
        match_name:"微信小程序开发大赛",
        Num_now:1,
        Num_all:3,
        TID:"123456",
        Timg_path:"/img/12.jpg",
        notice:"公告内容2",
        memberID_array:["111111"],
        memberName_array:["TSC"]
      }
      
    ]
  },
  onTabberChange(event) {
    this.setData({tabber: event.detail})
    wx.redirectTo({url: `/pages/${event.detail}/${event.detail}`})
  },
  onLoad() {
    wx.setNavigationBarTitle({
      title: '组队'
    })
  },

  onChange(event) {
    // event.detail 的值为当前选中项的索引
    this.setData({ active: event.detail });
  },

  tab_onChange(event){
      // wx.showToast({
      //   title: `切换到标签 ${event.detail.name}`,
      //   icon: 'none',
      // });
  },

  Num_Change(event){
    //console.log(event.detail);
    this.setData({
      new_Num:event.detail
    })

  },

  match_Change(event) {
    this.setData({
      choose_match:event.detail.value
    })
  },
  showPopup() {
    this.setData({ show: true });
  },

  onClose() {
    this.setData({ show: false });
  },
  
})
