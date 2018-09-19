package seckil.opc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.transform.Result;


@Controller
@RequestMapping(value = "/test")
public class SampleController {

    @RequestMapping(value = "/test")
    public String test(Model model){
        model.addAttribute("name","opc");
        return "hello";
    }

}
