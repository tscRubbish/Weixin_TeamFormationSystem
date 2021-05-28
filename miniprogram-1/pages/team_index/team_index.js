// index.js


// 获取应用实例
const app = getApp()

Page({
  data: {
    userVo:{
      "description": "string",
      "email": "string",
      "id": 0,
      "likes": 0,
      "password": "string",
      "pic": "string",
      "score": 0,
      "tags": [
        "string"
      ],
      "userType": "Admin",
      "username": "string"
    },
    teamVo:{
      "id": 1,
      "pic": "",
      "name": "5A430",
      "description": "垃圾回收中心",
      "captain": {
          "id": 2,
          "pic": "",
          "likes": 0,
          "email": "2010075010@qq.com",
          "username": "Tsc",
          "password": "Tsc20010401",
          "score": 0.0,
          "userType": "User",
          "tags": [
              ""
          ],
          "description": ""
      },
      "members": [],
      "captainNotice": "114514",
      "contest": {
          "id": 1,
          "name": "EL比赛",
          "sponsor": {
              "id": 1,
              "pic": "",
              "likes": 0,
              "email": "123",
              "username": "nju_se",
              "password": "12345678",
              "score": 4.0,
              "userType": "Manager",
              "tags": [
                  ""
              ],
              "description": ""
          },
          "startTime": "2021-03-01",
          "endTime": "2021-05-31",
          "description": "南大软院",
          "tags": [
              "编程",
              "算法"
          ],
          "pics": []
      }
    },
    id: 1,
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
      
    ],
    token: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGlzIGlzIG5qdSBzZSB0b2tlbiIsImF1ZCI6IldFQiIsImlzcyI6Ik5KVSIsInVzZXJUeXBlIjoyLCJleHAiOjE2MjIxMTgzMzgsInVzZXJJZCI6MSwiaWF0IjoxNjIyMTE3MTM4fQ.Y3pa82M3LPBTcXsIJYwFZu5q3KFHj5lihRIkvl71IxA",
    longToken: "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aGlzIGlzIG5qdSBzZSB0b2tlbiIsImF1ZCI6IldFQiIsImlzcyI6Ik5KVSIsInVzZXJUeXBlIjoyLCJleHAiOjE2MjIxMjA3MzgsInVzZXJJZCI6MSwiaWF0IjoxNjIyMTE3MTM4fQ.f54vFYAej4RBW6JA3YUmYOMy88-gQFRy0hrUNuxt2EU"
  },
  onTabberChange(event) {
    this.setData({tabber: event.detail})
    wx.redirectTo({url: `/pages/${event.detail}/${event.detail}`})
  },
  onLoad() {
    wx.setNavigationBarTitle({
      title: '组队'
    });

    var that = this;

    wx.request({
      url: 'http://localhost:8081/api/user/getInfo?id=' + that.data.id,
      method: 'GET',
      success(res){
        // console.log(res.data.content);
        that.setData({
          userVo: res.data.content,
        });
       console.log(that.data.userVo);
       console.log(that.data.userVo.email);
      }
    });


    wx.request({
      url: 'http://localhost:8081/api/user/login',
      method: 'POST',
      data: {
        "email": that.data.userVo.email,
        "password": that.data.userVo.password,
        "username": that.data.userVo.username
      },
      success:(res)=>{
        console.log(that.data.userVo.username);
        console.log(res);
      }
    })

    wx.request({
      url: 'http://localhost:8081/api/team/getTeamList',
      method: 'POST',
      header:{
        "nju-token":that.data.token,
        "nju-long-token":that.data.longToken,
      },
      data:that.data.userVo,
      success(res){
        console.log(res);
      }
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
  
  join_team(event){
    // console.log(this.data.TID);
    
  }

})
