package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.DestinationMapper;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
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
        QueryWrapper<Destination> wrapper = new QueryWrapper<>();
        wrapper.like(StringUtils.hasText(qo.getKeyword()), "name", qo.getKeyword());
        wrapper.eq(qo.getParentId() != null, "parent_id", qo.getParentId());
        wrapper.isNull(qo.getParentId() == null, "parent_id");
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


    /*
     * @Description: 根据父id一层一层的查找上去
     * @param: 父id  也可以说是目的地id
     * @return 返回一个目的地 集合
     * @author PandoraHearts
     * @date 2021/8/13 20:54
     */
    @Override
    public List<Destination> queryToastsByParentId(Long parentId) {

        List<Destination> list = new ArrayList<>();
        toasts(list, parentId);
        Collections.reverse(list);
        return list;

    }

    //递归吐司查询，给上面的方法调用
    public void toasts(List<Destination> list, Long parentId) {
        if (parentId == null) {
            return;
        }
        Destination destination = super.getById(parentId);
        list.add(destination);
        if (destination.getParentId() != null) {
            this.toasts(list, destination.getParentId());
        }
    }
}
