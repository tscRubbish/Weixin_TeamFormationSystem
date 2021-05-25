package com.example.weixin.bl;

import com.example.weixin.vo.ResponseVO;

public interface RecommendService {

    public ResponseVO addPic(Integer id,String pic);

    public ResponseVO getRecommend();

    public ResponseVO deletePic(String pic);
}
