package org.fishbone.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam("name") String name,
                            Model model) {
        model.addAttribute("name", name);
        return "first/hello";
    }

    @GetMapping("/calculator")
    public String calc(@RequestParam("num1") int num1,
                       @RequestParam("num2") int num2,
                       @RequestParam("action") String action,
                       Model model) {
        double result;
        switch (action) {
            case "plus":
                result = num1 + num2;
                break;
            case "minus":
                result = num1 - num2;
                break;
            case "multi":
                result = num1 * num2;
                break;
            case "divide":
                result = num1 / num2;
                break;
            default:
                result = 0;
        }

        model.addAttribute("result", "result is : " + result);
        return "first/calculator";
    }

    @GetMapping("goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }
}
