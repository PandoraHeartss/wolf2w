package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.query.DestinationQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 目的地表控制层
 */
@Controller
@RequestMapping("destination")
public class DestinationController {

    @Autowired
    private IDestinationService destinationService;

    @RequestMapping("/list")
    public String list(Model model, @ModelAttribute("qo") DestinationQuery qo) {
        //分页
        IPage<Destination> page = destinationService.queryPage(qo);
        model.addAttribute("page", page);
        //吐司查询
        List<Destination> toasts = destinationService.queryToastsByParentId(qo.getParentId());
        model.addAttribute("toasts", toasts);

        return "destination/list";
    }

    @RequestMapping("/get")
    @ResponseBody
    public Object get(Long id) {
        return JsonResult.success(destinationService.getById(id));
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public Object saveOrUpdate(Destination destination) {
        destinationService.saveOrUpdate(destination);
        return JsonResult.success();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        destinationService.removeById(id);
        return JsonResult.success();
    }


}
