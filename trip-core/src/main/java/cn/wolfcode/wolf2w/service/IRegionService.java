package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.query.RegionQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

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

    /*
     * @Description: 更改热门的状态
     * @param: id 区域表的id  hot 状态
     * @return * @return null
     * @author PandoraHearts
     * @date 2021/8/13 16:37
     */
    void changeHotValue(Long id, int hot);


    /*
     * @Description: 查询热门区域列表
     * @param:
     * @return java.util.List<cn.wolfcode.wolf2w.domain.Destination>
     * @author PandoraHearts
     * @date 2021/8/14 0:51
     */
    List<Region> queryHotRegion();

}
