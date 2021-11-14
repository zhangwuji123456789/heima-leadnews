package com.heima.wemedia.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.model.Wemedia.pojos.WmUser;
import com.heima.wemedia.mapper.WmUserMapper;
import com.heima.wemedia.service.WmUserService;
import org.springframework.stereotype.Service;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/14 17:11
 * @Version 1.0
 */
@Service
public class WmUserServiceImpl extends ServiceImpl<WmUserMapper, WmUser> implements WmUserService {
}
