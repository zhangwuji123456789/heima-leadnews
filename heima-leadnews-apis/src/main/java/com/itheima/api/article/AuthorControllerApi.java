package com.itheima.api.article;

import com.heima.model.article.pojos.ApAuthor;
import com.heima.model.common.dtos.ResponseResult;
import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author Ma zhi lin
 * @Date 2021/8/15 15:52
 * @Version 1.0
 */

public interface AuthorControllerApi {
    /**
     * 根据ID查询作者信息
     * @param id
     * @return
     */
    public ApAuthor findByUserId(@PathVariable("id") Integer id);

    /**
     * 保存作者信息
     * @param apAuthor
     * @return
     */
    public ResponseResult  save(@RequestBody ApAuthor apAuthor);
}
