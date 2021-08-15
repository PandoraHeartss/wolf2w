package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.TravelContent;
import cn.wolfcode.wolf2w.mapper.TravelContentMapper;
import cn.wolfcode.wolf2w.query.TravelContentQuery;
import cn.wolfcode.wolf2w.service.ITravelContentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 游记内容表服务接口实现
 */
@Service
@Transactional
public class TravelContentServiceImpl extends ServiceImpl<TravelContentMapper, TravelContent> implements ITravelContentService {

    //分页
    @Override
    public IPage<TravelContent> queryPage(TravelContentQuery qo) {
        IPage<TravelContent> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<TravelContent> wrapper = Wrappers.<TravelContent>query();
        return super.page(page, wrapper);
    }


}
