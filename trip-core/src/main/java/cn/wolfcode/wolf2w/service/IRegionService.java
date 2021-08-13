package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.query.RegionQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * 区域服务接口
 */
public interface IRegionService extends IService<Region> {

    /**
     * 分页
     *
     * @param qo
     * @return
     */
    Page<Region> queryPage(RegionQuery qo);
}
