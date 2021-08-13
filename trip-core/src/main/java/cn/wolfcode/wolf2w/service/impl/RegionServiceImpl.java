package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.RegionMapper;
import cn.wolfcode.wolf2w.query.QueryObject;
import cn.wolfcode.wolf2w.query.RegionQuery;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 区域服务接口实现
 */
@Service
@Transactional
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements IRegionService {
    @Override
    public Page<Region> queryPage(RegionQuery qo) {

        QueryWrapper<Region> wrapper = new QueryWrapper<>();
        Page<Region> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        return super.page(page, wrapper);
    }
}
