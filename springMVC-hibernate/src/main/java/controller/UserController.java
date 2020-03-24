package controller;

import dao.BankDAO;
import dao.UserDAO;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.UserDAOService;

import javax.persistence.Entity;
import java.util.List;

@Controller
@Entity
public class UserController {
    private UserDAOService userDAOService;

    @Autowired(required = true)
    @Qualifier(value = "userservice")
    public void setUserDAOService(UserDAOService us) {
        this.userDAOService = us;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listCustomers", this.userDAOService.listUsers());
        return "customer";
    }

    // For add and update person both
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User u) {

        if (u.getUserId() == 0) {
            // new person, add it
            this.userDAOService.addUser(u);
        } else {
            // existing person, call update
            this.userDAOService.updateUser(u);
        }

        return "redirect:/users";

    }

    @RequestMapping("/user/edit/{id}")
    public String editUser(@PathVariable("userId") int userId, Model model) {
        model.addAttribute("user", this.userDAOService.getUser(userId));
        model.addAttribute("listUsers", this.userDAOService.listUsers());
        return "user";
    }
}














//    @Autowired
//   private UserDAO userDAO;
//
//    @RequestMapping("/list")
//    public String listUsers(Model model) {
//
//        //get user from dao
//        List<User> usersList = (List<User>) userDAO.getUser(1);
//
//        //add users to model
//        model.addAttribute("users", usersList);
//
//        return "user-list";

