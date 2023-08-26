package com.biabuluo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Link;
/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2023-08-26 20:21:32
 */
public interface LinkService extends IService<Link> {

    ResponseResult allLinks();
}

