package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.DestinationMapper;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 目的地表服务接口实现
 */
@Service
@Transactional
public class DestinationServiceImpl extends ServiceImpl<DestinationMapper, Destination> implements IDestinationService {


    @Autowired
    private IRegionService regionService;

    //分页
    @Override
    public IPage<Destination> queryPage(DestinationQuery qo) {
        IPage<Destination> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Destination> wrapper = Wrappers.<Destination>query();
        return super.page(page, wrapper);
    }


    //据区域id查找出 对应的目的地集合
    @Override
    public List<Destination> getDestByRegionId(Long rid) {
        //根据区域id查询出 对应的区域对象
        Region region = regionService.getById(rid);

        //从 区域对象拿到  该对象下挂载的目的地id
        List<Long> list = region.parseRefIds();

        //用目的地id查出 对应的 目的地集合
        return super.listByIds(list);
    }

}
