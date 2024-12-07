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

    // Listar categorías
    @GetMapping
    public String listCategory(Model model) {
        logger.info("Solicitando la lista de todas las categorías...");
        List<Category> listCategory = null;
        try {
            listCategory = categoryRepository.findAll();  // Usar findAll() de JpaRepository
            logger.info("Se han cargado {} categorías.", listCategory.size());
        } catch (Exception e) {
            logger.error("Error al listar las categorías: {}", e.getMessage());
            model.addAttribute("errorMessage", "Error al listar las categorías.");
        }
        model.addAttribute("listCategory", listCategory);
        return "category"; // Nombre del template para mostrar la lista de categorías
    }

    // Formulario para nueva categoría
    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("category", new Category()); // Crear un nuevo objeto Category vacío
        return "category-form"; // Nombre del template para el formulario de nueva categoría
    }

    // Formulario para editar categoría
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        logger.info("Mostrando formulario de edición para la categoría con ID {}", id);
        Optional<Category> categoryOpt = categoryRepository.findById(id);
        if (categoryOpt.isPresent()) {
            model.addAttribute("category", categoryOpt.get()); // Cargar la categoría que se va a editar
        } else {
            logger.warn("No se encontró la categoría con ID {}", id);
            model.addAttribute("errorMessage", "Categoría no encontrada.");
            return "redirect:/categories"; // Redirigir si no se encuentra la categoría
        }
        return "category-form"; // Nombre del template para el formulario de edición
    }

    // INSERTAR CATEGORÍA
    @PostMapping("/insert")
    public String insertCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Insertando nueva categoría con código {}", category.getCode());
        try {
            if (result.hasErrors()) {
                return "category-form";  // Si hay errores, volver al formulario
            }
            if (categoryRepository.existsByCode(category.getCode())) {
                logger.warn("El código de la categoría {} ya existe.", category.getCode());
                String errorMessage = messageSource.getMessage("msg.category-controller.insert.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/categories/new";  // Redirigir a la página de creación si ya existe el código
            }
            categoryRepository.save(category);  // Guardar la nueva categoría
            logger.info("Categoría {} insertada con éxito.", category.getCode());
        } catch (Exception e) {
            logger.error("Error al insertar la categoría {}: {}", category.getCode(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.category-controller.insert.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/categories"; // Redirigir a la lista de categorías
    }

    // ACTUALIZAR CATEGORÍA
    @PostMapping("/update")
    public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, RedirectAttributes redirectAttributes, Locale locale) {
        logger.info("Actualizando categoría con ID {}", category.getId());
        try {
            if (result.hasErrors()) {
                return "category-form";  // Si hay errores, volver al formulario
            }
            if (categoryRepository.existsByCode(category.getCode()) && !categoryRepository.findById(category.getId()).get().getCode().equals(category.getCode())) {
                logger.warn("El código de la categoría {} ya existe para otra categoría.", category.getCode());
                String errorMessage = messageSource.getMessage("msg.category-controller.update.codeExist", null, locale);
                redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
                return "redirect:/categories/edit?id=" + category.getId();  // Redirigir si el código ya existe
            }
            categoryRepository.save(category);  // Guardar los cambios en la categoría
            logger.info("Categoría con ID {} actualizada con éxito.", category.getId());
        } catch (Exception e) {
            logger.error("Error al actualizar la categoría con ID {}: {}", category.getId(), e.getMessage());
            String errorMessage = messageSource.getMessage("msg.category-controller.update.error", null, locale);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
        }
        return "redirect:/categories"; // Redirigir a la lista de categorías
    }

    // ELIMINAR CATEGORÍA
    @PostMapping("/delete")
    public String deleteCategory(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        logger.info("Eliminando categoría con ID {}", id);
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            categoryRepository.deleteById(id);  // Eliminar la categoría
            logger.info("Categoría con ID {} eliminada con éxito.", id);
            redirectAttributes.addFlashAttribute("successMessage", "Categoría eliminada con éxito.");
        } else {
            logger.warn("No se encontró la categoría con ID {}", id);
            redirectAttributes.addFlashAttribute("errorMessage", "Error al eliminar la categoría.");
        }
        return "redirect:/categories"; // Redirigir a la lista de categorías
    }
}
