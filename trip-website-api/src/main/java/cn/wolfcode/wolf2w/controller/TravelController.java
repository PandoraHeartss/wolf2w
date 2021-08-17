package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Travel;
import cn.wolfcode.wolf2w.domain.TravelContent;
import cn.wolfcode.wolf2w.domain.UserInfo;
import cn.wolfcode.wolf2w.query.TravelQuery;
import cn.wolfcode.wolf2w.service.ITravelContentService;
import cn.wolfcode.wolf2w.service.ITravelService;
import cn.wolfcode.wolf2w.service.IUserInfoService;
import cn.wolfcode.wolf2w.util.JsonResult;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("travels")
public class TravelController {

    @Autowired
    private ITravelService travelService;

    @Autowired
    private IUserInfoService userInfoService;

    @Autowired
    private ITravelContentService travelContentService;

    @GetMapping("/query")
    public Object queryPage(TravelQuery qo) {
        IPage<Travel> page = travelService.queryPage(qo);
        return JsonResult.success(page);
    }


    //游记页面的明细
    @GetMapping("/detail")
    public Object detail(Long id) {
        Travel travel = travelService.getById(id);
        //关联游记主要内容
        TravelContent content = travelContentService.getById(travel.getId());
        travel.setContent(content);

        //关联作者
        UserInfo userInfo = userInfoService.getById(travel.getAuthorId());
        travel.setAuthor(userInfo);

        return JsonResult.success(travel);
    }

}
