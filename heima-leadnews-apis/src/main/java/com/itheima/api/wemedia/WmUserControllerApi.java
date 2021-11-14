package com.itheima.api.wemedia;

import com.heima.model.Wemedia.pojos.WmUser;
import com.heima.model.common.dtos.ResponseResult;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/14 16:19
 * @Version 1.0
 */

public interface WmUserControllerApi {
    /**
     * 保存自媒体用户
     * @param wmUser
     * @return
     */
    ResponseResult insert(WmUser wmUser);

    /**
     * 根据名称查询自媒体用户
     * @param name
     * @return
     */
    WmUser findName(String name);


}
