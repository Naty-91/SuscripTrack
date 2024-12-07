package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(Model model) {
        return "login"; // Redirige a una plantilla personalizada de login
    }
}
