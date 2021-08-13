package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.mapper.DestinationMapper;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 目的地表服务接口实现
 */
@Service
@Transactional
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements IDestinationService {

    @Override
    public IPage<Destination> queryPage(DestinationQuery qo) {
        IPage<Destination> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Destination> wrapper = Wrappers.<Destination>query();
        return super.page(page, wrapper);
    }
}
