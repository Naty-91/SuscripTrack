package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.controllers;

import jakarta.validation.Valid;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities.Service;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.repositories.ServiceRepository;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.repositories.ServiceRepository;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities.Service;
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

public class ServiceController {


    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);


    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private MessageSource messageSource;




    @GetMapping
    public String listServices(Model model) {
        logger.info("Solicitando la lista de todas los servicios...");
        List<Service> listService = serviceRepository.findAll();  // Usar findAll() de JpaRepository
        model.addAttribute("listServices", listService);
        return "services";
    }






    // Formulario para nueva categoría
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("service", new Service());
        model.addAttribute("listService", serviceRepository.findAll());
        return "service-form";
    }


    // Formulario para editar categoría
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para el servicio con ID {}", id);

        Optional<Service> serviceOpt = serviceRepository.findById(id);  // Usar findById() correctamente
        if (serviceOpt.isPresent()) {
            model.addAttribute("service", serviceOpt.get());
        } else {
            logger.warn("No se encontró el servicio con ID {}", id);
            model.addAttribute("errorMessage", "Servicio no encontrada.");
            return "redirect:/services";
        }

        model.addAttribute("listService", serviceRepository.findAll());
        return "service-form";
    }

    //INSERT

    @PostMapping("/insert")
    public String insertService(@Valid @ModelAttribute("service") Service service, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Insertando nuevo servicioi con código {}", service.getCode());
        try {
            if (result.hasErrors()) {
                return "service-form";  // Devuelve el formulario para mostrar los errores de validación
            }
            if (serviceRepository.existsServiceByCode(service.getCode())) {
                logger.warn("El código de la región {} ya existe.", service.getId());
                String errorMessage = messageSource.getMessage("msg.service-controller.insert.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/services/new";
            }
            serviceRepository.save(service);  // Cambia insertService a save
            logger.info("Región {} insertada con éxito.", service.getId());
        } catch (Exception e) {
            logger.error("Error al insertar la región {}: {}", service.getId(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.service-controller.insert.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/services"; // Redirigir a la lista de categories
    }


    //UPDATE

    @PostMapping("/update")
    public String updateService(@Valid @ModelAttribute("service") Service service, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Actualizando categoria con ID {}", service.getId());
        try {
            if (result.hasErrors()) {
                return "service-form";  // Devuelve el formulario para mostrar los errores de validación
            }
            if (serviceRepository.existsServiceByCode(service.getCode()) && !serviceRepository.findById(service.getId()).get().getCode().equals(service.getCode())) {
                logger.warn("El código del servicio{} ya existe para otra servicio", service.getCode());
                String errorMessage = messageSource.getMessage("msg.service-controller.update.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/services/edit?id=" + service.getId();
            }
            serviceRepository.save(service);  // Cambia updateService a save
            logger.info("Servicio con ID {} actualizada con éxito.", service.getId());
        } catch (Exception e) {
            logger.error("Error al actualizar el servicio con ID {}: {}", service.getId(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.service-controller.update.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/services"; // Redirigir a la lista de servicios
    }

    //DELETE

    @PostMapping("/delete")
    public String deleteService(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando región con ID {}", id);

        Optional<Service> service = serviceRepository.findById(id);
        if (service != null) {
            serviceRepository.deleteById(id);

            logger.info("Service con ID {} eliminada con exito.", id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoría elimianda con éxito");
        } else {
            logger.warn("No se encontró la categoría con ID {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la service.");
        }
        return "redirect:/services";
    }





}
