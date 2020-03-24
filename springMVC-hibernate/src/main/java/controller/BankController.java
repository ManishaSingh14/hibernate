package controller;

import dao.BankDAO;
import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/user")
public class BankController {

    @Autowired
    private BankDAO bankDAO;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model) {
        User user = new User();
        model.addAttribute("register", user);
        return "register";
    }

    }
