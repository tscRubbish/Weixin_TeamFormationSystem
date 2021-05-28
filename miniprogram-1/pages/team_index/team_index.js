// index.js
import request from '../../utils/request'


// 获取应用实例
const app = getApp()

Page({
  data: {
    userVo:{},
    teamVo:{},
    id: 2,
    choose_match:"点我选择",
    choose_matchID:1,
    // TODO 这里需要获得可以选择的比赛以及其ID，先随便写一个替代用
    new_introduction:"",
    new_Tpassword:"",
    show:false,
    matchs:["微信小程序开发大赛","LPL"],
    new_Tname:"",
    new_MaxNum:1,
    tabber:"team_index",
    tab_active:0,
    join_TID:"",
    join_Tpassword:"",
    match_array:[],
    token: "",
    longToken: ""
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
      url: 'http://localhost:8081/api/user/login',
      method: 'POST',
      data: {
        "username": 'Tsc',
        "password": 'Tsc20010401',
        "email":'2010075010@qq.com'
      },
      success(res0){
        that.setData({
          token:res0.data.content.njuToken,
          longToken:res0.data.content.njuLongToken,
        });
        // 注意：上面这个登录请求只是为了拿到token，而实际上token应该被保存在app.js里面，这里这样是为了进行测试，下面的两个require才是真实有效的
        
        wx.request({
          url: 'http://localhost:8081/api/user/getInfo?id=' + that.data.id,
          method: 'GET',
          success(res){
            that.setData({
              userVo: res.data.content,
            });
            // console.log(that.data.userVo);
            wx.request({
              url: 'http://localhost:8081/api/team/getTeamList',
              method: 'POST',
              data:that.data.userVo,
              header:{
                'nju-token':that.data.token,
                'nju-long-token':that.data.longToken
              },
              success(res2){
                console.log(res2);
                that.setData({
                  match_array: res2.data.content,
                });
                for (var i = 0; i < that.data.match_array.length; i++){
                  var memberList = that.data.match_array[i].members;
                  var memberIDList = new Array();
                  var memberNameList = new Array();
                  var Num_now = memberList.length + 1;

                  memberIDList.push(that.data.match_array[i].captain.id);
                  memberNameList.push(that.data.match_array[i].captain.username);

                  for (var j = 0; j < memberList.length; j++){
                    memberIDList.push(memberList[j].id);
                    memberNameList.push(memberList[j].name);
                  }
                  var temp = "match_array[" + i + "].memberIDList";
                  var temp2 = "match_array[" + i + "].memberNameList";
                  var temp3 = "match_array[" + i + "].Num_now";
                  that.setData({
                    [temp]:memberIDList,
                    [temp2]:memberNameList,
                    [temp3]:Num_now,
                  })
                }
                console.log(that.data.match_array);
              }
            })
          }
        })

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
    
  },

  createTeam(){
    var that = this;
    wx.request({
      url: 'http://localhost:8081/api/team/create?captaindId=' + that.data.id + '&contestId=' + that.data.choose_matchID + '&name=' + that.data.new_Tname + '&password=' + that.data.new_Tpassword,
      method: 'POST',
      success(res){
        console.log(res);
      }

    })
  }

})
