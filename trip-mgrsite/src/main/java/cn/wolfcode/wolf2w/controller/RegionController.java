package cn.wolfcode.wolf2w.controller;


import cn.wolfcode.wolf2w.domain.Destination;
import cn.wolfcode.wolf2w.domain.Region;
import cn.wolfcode.wolf2w.query.QueryObject;
import cn.wolfcode.wolf2w.query.RegionQuery;
import cn.wolfcode.wolf2w.service.IDestinationService;
import cn.wolfcode.wolf2w.service.IRegionService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("region")
public class RegionController {

    @Autowired
    private IRegionService regionService;

    @Autowired
    private IDestinationService destinationService;

    @GetMapping("/list")
    public Object list(Model model, @ModelAttribute("qo") RegionQuery qo) {

        //page
        Page<Region> page = regionService.queryPage(qo);
        model.addAttribute("page", page);

        //dests
        List<Destination> dests = destinationService.list();
        model.addAttribute("dests", dests);

        return "region/list";
    }

}
