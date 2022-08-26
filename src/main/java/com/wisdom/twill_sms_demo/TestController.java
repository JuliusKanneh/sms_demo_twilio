package com.wisdom.twill_sms_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TestController {

    private final Service service;

    @Autowired
    public TestController(Service service) {
        this.service = service;
    }

    @GetMapping("/sms")
    public String showMessageBox(){
        return "index";
    }

    @PostMapping("/send/sms")
    public String sendSms(Model model,
                          @RequestParam("number") String number,
                          @RequestParam("message") String message,
                          RedirectAttributes redirectAttributes){
        SmsRequest message_boy = new SmsRequest(number, message);
        service.sendSms(message_boy);
        redirectAttributes.addFlashAttribute("message", "Message send successfully to " + message_boy.getPhoneNumber());
        return "redirect:/sms";
    }
}
