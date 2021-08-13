package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.mapper.RegionMapper;
import cn.wolfcode.wolf2w.query.RegionQuery;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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


    /*
     * @Description: 更改热门的状态
     * @param: id 区域表的id  hot 状态
     * @return * @return null
     * @author PandoraHearts
     * @date 2021/8/13 16:37
     */
    @Override
    public void changeHotValue(Long id, int hot) {
        UpdateWrapper<Region> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", id)
                .set("ishot", hot);
        super.update(wrapper);
    }

}
