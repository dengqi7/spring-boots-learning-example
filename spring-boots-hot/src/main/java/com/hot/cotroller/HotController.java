package com.hot.cotroller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengqi
 * @date 2018-05-03
 */
@RestController
public class HotController {

    @RequestMapping("/hot")
    public String testHot(){
        return "is a hot day! and id test a hot project!";
    }
}
