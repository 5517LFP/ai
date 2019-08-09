package com.example.ai.IO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class testNew {
    @RequestMapping("/welcome")
    public String newone(Map<String, Object> map) {
        map.put("name", "zhasdfdsfweifasedfa");

        return "index";

    }

}
