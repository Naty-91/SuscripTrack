package org.iesalixar.daw2.NataliaFernandez.SuscripTrack.controllers;
import jakarta.validation.Valid;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.entities.Category;
import org.iesalixar.daw2.NataliaFernandez.SuscripTrack.repositories.CategoryRepository;
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
@RequestMapping("/categories")

public class CategoryController {


    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public String listCategory(Model model) {
        logger.info("Solicitando la lista de todas las categorías...");
        List<Category> listCategory = categoryRepository.findAll();  // Usar findAll() de JpaRepository
        model.addAttribute("listCategory", listCategory);
        return "categories";
    }

    // Formulario para nueva categoría
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("listCategory", categoryRepository.findAll());
        return "category-form";
    }


    // Formulario para editar categoría
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para la categoría con ID {}", id);

        Optional<Category> categoryOpt = categoryRepository.findById(id);  // Usar findById() correctamente
        if (categoryOpt.isPresent()) {
            model.addAttribute("category", categoryOpt.get());
        } else {
            logger.warn("No se encontró la categoría con ID {}", id);
            model.addAttribute("errorMessage", "Categoría no encontrada.");
            return "redirect:/category";
        }

        model.addAttribute("listCategory", categoryRepository.findAll());
        return "category-form";
    }

    //INSERT

    @PostMapping("/insert")
    public String insertCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Insertando nueva categoría con código {}", category.getCode());
        try {
            if (result.hasErrors()) {
                return "category-form";  // Devuelve el formulario para mostrar los errores de validación
            }
            if (categoryRepository.existsByCode(category.getCode())) {
                logger.warn("El código de la región {} ya existe.", category.getCode());
                String errorMessage = messageSource.getMessage("msg.category-controller.insert.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/categories/new";
            }
            categoryRepository.save(category);  // Cambia insertCategory a save
            logger.info("Región {} insertada con éxito.", category.getCode());
        } catch (Exception e) {
            logger.error("Error al insertar la región {}: {}", category.getCode(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.category-controller.insert.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/categories"; // Redirigir a la lista de categories
    }


    //UPDATE

    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Actualizando categoria con ID {}", category.getId());
        try {
            if (result.hasErrors()) {
                return "category-form";  // Devuelve el formulario para mostrar los errores de validación
            }
            if (categoryRepository.existsByCode(category.getCode()) && !categoryRepository.findById(category.getId()).get().getCode().equals(category.getCode())) {
                logger.warn("El código de la categoria {} ya existe para otra categoria", category.getCode());
                String errorMessage = messageSource.getMessage("msg.category-controller.update.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/categorys/edit?id=" + category.getId();
            }
            categoryRepository.save(category);  // Cambia updateCategory a save
            logger.info("Región con ID {} actualizada con éxito.", category.getId());
        } catch (Exception e) {
            logger.error("Error al actualizar la región con ID {}: {}", category.getId(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.category-controller.update.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/categories"; // Redirigir a la lista de categories
    }

    //DELETE

    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando región con ID {}", id);

        Optional<Category> category = categoryRepository.findById(id);
        if (category != null) {
            categoryRepository.deleteById(id);

            logger.info("Category con ID {} eliminada con exito.", id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoría elimianda con éxito");
        } else {
            logger.warn("No se encontró la categoría con ID {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la category.");
        }
        return "redirect:/categories";
    }








}
