package org.launchcode.uTrain.controllers;

import org.launchcode.uTrain.data.UserRepository;
import org.launchcode.uTrain.models.User;
import org.launchcode.uTrain.models.UserSex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("user")
public class UserController {

    AuthenticationController authenticationController;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            return null;
        }
        return  user.get();
    }

    @Autowired
    UserRepository userRepository;

    @GetMapping("index")
    public String userIndexPage(HttpServletRequest request, Model model) {

        User user = (User) getUserFromSession(request.getSession());

        model.addAttribute("title", "Welcome!!");
        model.addAttribute("user", user);
        model.addAttribute("loggedIn", true);

        return "user/index";
    }

    @GetMapping("profile/{userId}")
    public String displayEditProfileForm(Model model, @PathVariable int userId) {

        Optional<User> result = userRepository.findById(userId);
        User updateUser = result.get();

        model.addAttribute("title", "Update " + updateUser.getUsername());
        model.addAttribute("user", updateUser);
        model.addAttribute("loggedIn", true);
        model.addAttribute("sexes", UserSex.values());

        return "user/profile";
    }

    @PostMapping("profile")
    public String processEditPostMDCForm(@ModelAttribute @Valid User user, int userId,
                                         Errors errors, Model model) {

        Optional<User> result = userRepository.findById(userId);
        User updatedUser = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Update " + updatedUser.getUsername());
            model.addAttribute("user", updatedUser);
            model.addAttribute("loggedIn", true);
            return "user/profile";
        }

       userRepository.save(user);

        return "redirect:/user/index";
    }

}
