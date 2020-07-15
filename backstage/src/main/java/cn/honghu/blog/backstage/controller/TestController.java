package cn.honghu.blog.backstage.controller;

import cn.honghu.blog.common.utils.ResultUtils;
import cn.honghu.blog.common.web.response.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("test")
@RestController
public class TestController {

    @RequestMapping("test")
    public Result test() {
        return ResultUtils.buildSuccess();
    }
}
