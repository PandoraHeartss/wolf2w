package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Spot;
import cn.wolfcode.wolf2w.domain.SpotContent;
import cn.wolfcode.wolf2w.service.ISpotContentService;
import cn.wolfcode.wolf2w.service.ISpotService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spot")
public class SpotContentController {

    @Autowired
    private ISpotContentService spotContentService;

    @Autowired
    private ISpotService spotService;


    @GetMapping("/detail")
    public Object list(Long id) {

        Spot spot = spotService.getById(id);

        SpotContent spotContent = spotContentService.getById(id);

        spot.setContent(spotContent);
        return JsonResult.success(spot);
    }

}
