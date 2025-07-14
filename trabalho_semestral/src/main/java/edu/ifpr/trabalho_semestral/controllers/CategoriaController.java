package edu.ifpr.trabalho_semestral.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.ifpr.trabalho_semestral.entities.Categoria;
import edu.ifpr.trabalho_semestral.services.CategoriaService;

@Controller
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;
    
    @GetMapping("/categorias")
    public String CadastrarCategorias(Model model) {
        model.addAttribute("categorias", categoriaService.findAll());
        return "categorias";
    }
    
    @PostMapping("/categorias")
    public String cadastrarCategoria(
            @RequestParam String nome,
            Model model) {

        if (categoriaService.buscarPorNome(nome) != null) {
            model.addAttribute("erro", "Categoria já cadastrada.");
            return "categorias";
        }

        Categoria categoria = new Categoria();
       // categoria.setCategoriaNome(nome);
        categoria.setNome(nome);

        categoriaService.save(categoria);

        return "redirect:/categorias";
    }

    @GetMapping("/categorias_deletar/{id}")
    public String deletarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            categoriaService.deleteById(id);
            redirectAttributes.addFlashAttribute("mensagem", "Categoria deletada com sucesso.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Categoria está associada a uma ou mais fitas e não pode ser deletada.");
        }
        return "redirect:/categorias";  
    }

    @GetMapping("/categorias_editar/{id}")
    public String editarCategoria(@PathVariable Long id, Model model) {
        Categoria categoria = categoriaService.findById(id);
        if (categoria != null) {
            model.addAttribute("categoria", categoria);
            return "categorias_editar"; 
        } else {
            model.addAttribute("erro", "Categoria não encontrada.");
            return "redirect:/categorias"; 
        }   
    }

    @PostMapping("/categorias_editar")
    public String atualizarCategoria(
            @RequestParam Long id,
            @RequestParam String nome,
            Model model) {
        Categoria categoria = new Categoria();
        categoria = categoriaService.findById(id);
        if (categoria != null) {
            categoria.setNome(nome);
            categoriaService.update(categoria);
            return "redirect:/categorias"; 
        } else {
            model.addAttribute("erro", "Categoria não encontrada.");
            return "categorias_editar"; 
        }
    }
    
}