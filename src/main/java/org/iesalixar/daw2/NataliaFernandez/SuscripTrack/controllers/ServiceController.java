package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.controllers;

import jakarta.validation.Valid;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.repositories.ServiceRepository;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.repositories.CategoryRepository;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities.Service;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities.Category;
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

@Controller
@RequestMapping("/services")
public class ServiceController {

    private static final Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String listServices(Model model) {
        logger.info("Solicitando la lista de todos los servicios...");
        try {
            List<Service> listServices = serviceRepository.findAll();
            logger.info("Se han cargado {} servicios.", listServices.size());
            model.addAttribute("listServices", listServices);
        } catch (Exception e) {
            logger.error("Error al listar los servicios: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar los servicios.");
        }
        return "services";
    }

    @GetMapping("/new")
    public String showNewServiceForm(Model model) {
        model.addAttribute("service", new Service());
        model.addAttribute("listCategories", categoryRepository.findAll());
        return "service-form";
    }

    @GetMapping("/edit")
    public String showEditServiceForm(@RequestParam("id") Long id, Model model) {
        Optional<Service> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            model.addAttribute("service", optionalService.get());
            model.addAttribute("listCategories", categoryRepository.findAll());
        } else {
            model.addAttribute("errorMessage", "Servicio no encontrado.");
            return "redirect:/services";
        }
        return "service-form";
    }

    @PostMapping("/insert")
    public String insertService(@Valid @ModelAttribute("service") Service service, BindingResult result,
                                RedirectAttributes redirectAttributes, Locale locale, Model model) {
        logger.info("Insertando nuevo servicio con código {}", service.getCode());
        try {
            if (result.hasErrors()) {
                model.addAttribute("listCategories", categoryRepository.findAll());
                return "service-form";
            }
            if (serviceRepository.existsServiceByCode(service.getCode())) {
                logger.warn("El código del servicio {} ya existe.", service.getCode());
                String errorMessage = messageSource.getMessage("msg.service-controller.insert.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/services/new";
            }
            serviceRepository.save(service);
            logger.info("Servicio {} insertado con éxito.", service.getCode());
        } catch (Exception e) {
            logger.error("Error al insertar el servicio {}: {}", service.getCode(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.service-controller.insert.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/services";
    }

    @PostMapping("/update")
    public String updateService(@Valid @ModelAttribute("service") Service service, BindingResult result,
                                RedirectAttributes redirectAttributes, Locale locale, Model model) {
        logger.info("Actualizando servicio con ID {}", service.getId());
        try {
            if (result.hasErrors()) {
                model.addAttribute("listCategories", categoryRepository.findAll());
                return "service-form";
            }
            if (serviceRepository.existsServiceByCode(service.getCode()) &&
                    !serviceRepository.findById(service.getId()).get().getCode().equals(service.getCode())) {
                logger.warn("El código del servicio {} ya existe para otro servicio.", service.getCode());
                String errorMessage = messageSource.getMessage("msg.service-controller.update.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/services/edit?id=" + service.getId();
            }
            serviceRepository.save(service);
            logger.info("Servicio con ID {} actualizado con éxito.", service.getId());
        } catch (Exception e) {
            logger.error("Error al actualizar el servicio con ID {}: {}", service.getId(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.service-controller.update.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/services";
    }

    @PostMapping("/delete")
    public String deleteService(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando servicio con ID {}", id);
        try {
            serviceRepository.deleteById(id);
            logger.info("Servicio con ID {} eliminado con éxito.", id);
        } catch (Exception e) {
            logger.error("Error al eliminar el servicio con ID {}: {}", id, e.getMessage());
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar el servicio.");
        }
        return "redirect:/services";
    }
}
