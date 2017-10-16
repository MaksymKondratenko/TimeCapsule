package com.maksymkondratenko.timecapsule.controler;

import com.maksymkondratenko.timecapsule.model.User;
import com.maksymkondratenko.timecapsule.model.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String getIndexPage(ModelMap modelMap) {
        return "index";
    }

    @GetMapping("/login")
    public String getLoginPage(ModelMap modelMap){
        if(!modelMap.containsKey("user")) {
            modelMap.addAttribute("user", new User());
        }
        return "login";
    }

    @PostMapping("/login")
    public String postLoginData(@ModelAttribute("user") User user){
        UserValidator validator = UserValidator.getInstance();
        Map<String, String> users = validator.getUsers();
        for(Map.Entry entry : users.entrySet()){
            if(entry.getKey().equals(user.getUsername()) && entry.getValue().equals(user.getPassword())){
                return "login-successful";
            }
        }
        return "login-failed";
    }

    @GetMapping("/register")
    public String getRegistrationPage(ModelMap modelMap){
        if(!modelMap.containsKey("user")) {
            modelMap.addAttribute("user", new User());
        }
        return "register";
    }

    @PostMapping("/register")
    public String postRegistrationData(@ModelAttribute("user") User user){
        UserValidator validator = UserValidator.getInstance();
        boolean passwordsEqual = validator.passCheck(user.getPassword(), user.getRepeatedPassword());
        if(passwordsEqual) {
            boolean registrationSuccessful = validator.addUser(user.getUsername(), user.getPassword());
            if(registrationSuccessful){
                return "registration-successful";
            } else {
                return "registration-failed";
            }
        } else {
            return "register";
        }

    }

    @GetMapping("/office")
    public String enterOffice(@ModelAttribute("user") User user, ModelMap map){
        List videos = user.getVideos();
        if(videos != null) {
            map.put("videos", user.getVideos());
        }
        return "office";
    }

    @GetMapping("/upload")
    public String upload(){
        return "upload";
    }
}
