package springinflearnstudy.study.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ConfigController {

    @GetMapping("/config")
    public String config(){
        return "admin/config";
    }

}
