package week_07.homework10.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import week_07.homework10.dao.DemoDao;

import javax.annotation.Resource;

@RestController
public class TestController {

    @Resource
    private DemoDao demoDao;

    @GetMapping(value = "/select")
    public String select() {
        demoDao.select();
        return "ok";
    }

    @GetMapping(value = "/save")
    public String save() {
        demoDao.save();
        return "ok";
    }

    @GetMapping(value = "/force")
    public String force() {
        demoDao.forceSelect();
        return "ok";
    }
}
