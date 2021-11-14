package com.heima.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.heima.common.constants.user.UserConstants;
import com.heima.model.Wemedia.pojos.WmUser;
import com.heima.model.admin.pojos.ApUserRealname;
import com.heima.model.article.dto.ApUserRealnameDto;
import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.PageResponseResult;
import com.heima.model.common.dtos.ResponseResult;
import com.heima.model.common.enums.AppHttpCodeEnum;
import com.heima.model.user.pojos.ApUser;
import com.heima.user.feign.ApAuthorFeign;
import com.heima.user.feign.WmUserFeign;
import com.heima.user.mapper.ApUserMapper;
import com.heima.user.mapper.ApUserRealnameMapper;
import com.heima.user.service.ApUserRealnameService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/8 17:54
 * @Version 1.0
 */
@Service
public class ApUserRealnameServiceImpl extends ServiceImpl<ApUserRealnameMapper, ApUserRealname> implements ApUserRealnameService {


    @Autowired(required = false)
    private ApUserRealnameMapper apUserRealnameMapper;

    @Autowired(required = false)
    private ApUserMapper apUserMapper;

    @Autowired(required = false)
    private WmUserFeign wmUserFeign;

    @Autowired(required = false)
    private ApAuthorFeign apAuthorFeign;

    /**
     * APP端查詢用戶列表
     *
     * @param apUserRealnameDto
     * @return
     */
    @Override
    public ResponseResult loadListByStatus(ApUserRealnameDto apUserRealnameDto) {
        if (apUserRealnameDto == null) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        //初始化
        apUserRealnameDto.checkParam();
        //分頁查詢
        Page<ApUserRealname> page = new Page<>(apUserRealnameDto.getPage(), apUserRealnameDto.getSize());
        //
        LambdaQueryWrapper<ApUserRealname> queryWrapper = new LambdaQueryWrapper<>();
        if (apUserRealnameDto.getStatus() != null) {
            queryWrapper.eq(ApUserRealname::getStatus, apUserRealnameDto.getStatus());
        }
        IPage<ApUserRealname> iPage = apUserRealnameMapper.selectPage(page, queryWrapper);
        //封装
        PageResponseResult pageResponseResult = new PageResponseResult((int) iPage.getCurrent(), (int) iPage.getSize(), (int) iPage.getTotal());
        pageResponseResult.setData(iPage.getRecords());
        return pageResponseResult;
    }

    /**
     * 审核用户
     *
     * @param dto
     * @param status
     * @return
     */
    @Override
    public ResponseResult updateStatusById(ApUserRealnameDto dto, Short status) {
        if (dto == null || dto.getId() == null) {
            ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        if (!checkStatus(status)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.PARAM_INVALID);
        }
        ApUserRealname apUserRealname = new ApUserRealname();
        apUserRealname.setId(dto.getId());//更改ID
        apUserRealname.setStatus(status);//更改状态
        if (StringUtils.isNotEmpty(dto.getMsg())) {
            apUserRealname.setReason(dto.getMsg());//审核信息说明
        }
        updateById(apUserRealname);
        if (status.equals(UserConstants.PASS_AUTH)) {
            // 创建自媒体账号并且创建作者账号
            createWmUserAndApAuthor(dto);
        }
        return ResponseResult.okResult(AppHttpCodeEnum.SUCCESS);
    }

    private void createWmUserAndApAuthor(ApUserRealnameDto dto) {
        //获取注册的用户
        ApUserRealname apUserRealname = getById(dto.getId());
        Integer userId = apUserRealname.getUserId();
        ApUser apUser = apUserMapper.selectById(userId);
        // 创建自媒体账号
        WmUser wmUser = wmUserFeign.findByName(apUser.getName());
        if (wmUser == null) {
            wmUser = new WmUser();
            wmUser.setApUserId(apUser.getId());
            wmUser.setName(apUser.getName());
            wmUser.setPassword(apUser.getPassword());
            wmUser.setSalt(apUser.getSalt());
            wmUser.setStatus(9);
            wmUser.setCreatedTime(new Date());
            wmUser.setPhone(apUser.getPhone());
            wmUserFeign.insert(wmUser);
        }
//创建作者账号
        ApAuthor apAuthor = apAuthorFeign.findById(wmUser.getApAuthorId());
        if (apAuthor == null) {
            apAuthor = new ApAuthor();
            apAuthor.setUserId(wmUser.getApUserId());
            apAuthor.setCreatedTime(new Date());
            apAuthor.setWmUserId(wmUser.getId());
            apAuthor.setName(wmUser.getName());
            apAuthor.setType(2);
            apAuthorFeign.insert(apAuthor);
        }


    }

    private boolean checkStatus(Short status) {
        if (status != null && status.equals(UserConstants.PASS_AUTH) || status.equals(UserConstants.FAIL_AUTH)) {
            return true;
        }
        return false;
    }
}
