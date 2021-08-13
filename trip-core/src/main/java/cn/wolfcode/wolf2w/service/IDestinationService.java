package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


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
}
