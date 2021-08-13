package cn.wolfcode.wolf2w.controller;

import cn.wolfcode.wolf2w.domain.Spot;
import cn.wolfcode.wolf2w.service.ISpotService;
import cn.wolfcode.wolf2w.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("spot")
public class SpotController {

    @Autowired
    private ISpotService spotService;


    @GetMapping("/list")
    public Object list() {
        List<Spot> list = spotService.list();
        return JsonResult.success(list);
    }

}
