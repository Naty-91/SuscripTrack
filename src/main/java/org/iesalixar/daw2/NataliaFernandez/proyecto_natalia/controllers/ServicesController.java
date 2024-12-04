package org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.controllers;

import jakarta.validation.Valid;
import org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.repositories.ServicesRepository;
import org.iesalixar.daw2.NataliaFernandez.proyecto_natalia.entities.Services;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Controlador que maneja las operaciones CRUD para la entidad `Services`.
 */
@Controller
@RequestMapping("/services")

public class ServicesController {
}
