package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;


/**
 * 目的地表服务接口
 */
public interface IDestinationService extends IService<Destination> {
    /**
     * 分页
     *
     * @param qo
     * @return
     */
    IPage<Destination> queryPage(DestinationQuery qo);

    /*
     * @Description: 根据区域id查找出 对应的目的地集合
     * @param: rid
     * @return java.util.List<cn.wolfcode.wolf2w.domain.Destination>
     * @author PandoraHearts
     * @date 2021/8/13 17:08
     */
    List<Destination> getDestByRegionId(Long rid);
}
