package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.annotation.UserParam;
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

    /*
        当前一个接口操作没问题，如果后续有很多接口都需要获取当前登录用户对象
        此时就出现代码重复，此时该怎么优化？


        思考：能不能使用下面操作获取当前登录用户对象呢？
        public Object info (UserInfo user){
            //user就是当前用户登录对象
            return JsonResult.success(user);
        }


        该怎么实现呢？使用技术：sprigMvc参数解析器

        解析器有2种分类：
        1>springmvc的默认解析器
        2>自定义参数解析器

        springmvc默认参数解析器是无法实现当前登录用户对象注入，需要使用自定义的用户参数解析器才可行
     */
    @GetMapping("/info")
    public Object info(@UserParam UserInfo user) {
        //user就是当前用户登录对象
        return JsonResult.success(user);
    }


    /*
        如果某个接口需要修改用户数据，使用UserInfo接收参数，此时应该使用
        springmvc默认参数解析器呢，还是用户自定义用户参数解析器呢？
        必须是默认，问题来了，怎么使用

        答：使用自定义注解，有这注解的UserInfo类型参数使用自定义的，没有使用默认
     */
    @GetMapping("/updateInfo")
    public Object updateInfo(UserInfo user) {
        //user就是当前用户登录对象
        return JsonResult.success(user);
    }

}
