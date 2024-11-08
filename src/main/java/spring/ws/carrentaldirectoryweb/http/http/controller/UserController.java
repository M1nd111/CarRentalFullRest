package spring.ws.carrentaldirectoryweb.http.http.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRepository;
import spring.ws.carrentaldirectoryweb.core.service.RecordsService;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    RecordsService recordsService;
    @Autowired
    RecordRepository recordRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping
    public String findAll(Model model, HttpSession httpSession){
        var records =  recordsService.findAll();
        model.addAttribute("records", records);
        return "user/users";
    }

}
