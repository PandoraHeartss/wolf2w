package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.TravelContent;
import cn.wolfcode.wolf2w.query.TravelContentQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 游记内容表服务接口
 */
public interface ITravelContentService extends IService<TravelContent> {
    /**
     * 分页
     *
     * @param qo
     * @return
     */
    IPage<TravelContent> queryPage(TravelContentQuery qo);


}
