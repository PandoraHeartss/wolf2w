package cn.wolfcode.wolf2w.service;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.query.TravelQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;


/**
 * 游记表服务接口
 */
public interface ITravelService extends IService<Travel> {
    /**
     * 分页
     *
     * @param qo
     * @return
     */
    IPage<Travel> queryPage(TravelQuery qo);


    /*
     * @Description: 游记内容的审核
     * @param: id 游记的id
     * @param: state 游记的当前状态
     * @return void
     * @author PandoraHearts
     * @date 2021/8/15 22:07
     */
    void audit(Long id, int state);
}
