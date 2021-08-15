package cn.wolfcode.wolf2w.service.impl;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.exception.LogicException;
import cn.wolfcode.wolf2w.mapper.TravelMapper;
import cn.wolfcode.wolf2w.mapper.UserInfoMapper;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.service.ITravelService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 游记表服务接口实现
 */
@Service
@Transactional
public class TravelServiceImpl extends ServiceImpl<TravelMapper, Travel> implements ITravelService {

    @Autowired
    private UserInfoMapper userInfoMapper;


    //分页
    @Override
    public IPage<Travel> queryPage(TravelQuery qo) {
        IPage<Travel> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Travel> wrapper = Wrappers.<Travel>query();
        wrapper.eq(qo.getDestId() != null, "dest_id", qo.getDestId());
        super.page(page, wrapper);

        for (Travel record : page.getRecords()) {
            record.setAuthor(userInfoMapper.selectById(record.getAuthorId()));
        }

        return page;
    }


    //游记内容的审核
    @Override
    public void audit(Long id, int state) {

        Travel travel = super.getById(id);
        if (travel == null || travel.getState() != travel.STATE_WAITING) {//判断是否满足审核条件
            throw new LogicException("游记对象不存在 或 并不在待审核状态");

        } else if (state == travel.STATE_RELEASE) {//审核通过
            //修改审核状态
            travel.setState(state);
            //修改游记的最后更新时间为当前时间
            travel.setLastUpdateTime(new Date());
            //修改审核时间为当前时间
            travel.setReleaseTime(new Date());
            //更新操作
            super.updateById(travel);

        } else if (state == travel.STATE_REJECT) {//拒绝审核
            //修改审核状态
            travel.setState(state);
            //修改游记的最后更新时间为当前时间
            travel.setLastUpdateTime(new Date());
            //修改审核时间为null
            travel.setReleaseTime(null);
            //更新操作
            super.updateById(travel);
        } else {
            throw new LogicException("审核状态异常");
        }


    }


}
