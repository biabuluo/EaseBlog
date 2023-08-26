package com.biabuluo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.biabuluo.constans.SystemConstants;
import com.biabuluo.domain.ResponseResult;
import com.biabuluo.domain.entity.Link;
import com.biabuluo.domain.vo.LinkVo;
import com.biabuluo.mapper.LinkMapper;
import com.biabuluo.service.LinkService;
import com.biabuluo.utils.BeanCopyUtil;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 友链(Link)表服务实现类
 *
 * @author makejava
 * @since 2023-08-26 20:21:16
 */
@Service("linkService")
public class LinkServiceImpl extends ServiceImpl<LinkMapper, Link> implements LinkService {

    @Override
    public ResponseResult allLinks() {
        //查询所有审核通过的友链
        LambdaQueryWrapper<Link> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Link::getStatus, SystemConstants.LINK_PASS);
        List<Link> linksList = list(queryWrapper);
        //封装进VO
        List<LinkVo> linkVos = BeanCopyUtil.copyBeanList(linksList, LinkVo.class);
        return ResponseResult.okResult(linkVos);
    }
}

