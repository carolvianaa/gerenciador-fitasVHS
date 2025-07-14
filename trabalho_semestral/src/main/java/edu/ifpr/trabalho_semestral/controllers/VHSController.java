package edu.ifpr.trabalho_semestral.controllers;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Locale.Category;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import edu.ifpr.trabalho_semestral.entities.VHS;
import edu.ifpr.trabalho_semestral.services.VHSService;
import edu.ifpr.trabalho_semestral.services.CategoriaService;
import edu.ifpr.trabalho_semestral.services.UsuarioService;
import edu.ifpr.trabalho_semestral.entities.Usuario;
import edu.ifpr.trabalho_semestral.entities.Categoria;
import edu.ifpr.trabalho_semestral.entities.TapeStatus;



@Controller
@RequestMapping("/vhs")
public class VHSController {
    
    @Autowired
    VHSService vhsService;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    CategoriaService categoriaService;

    @GetMapping
    public String listarVHS(Model model) {
        List<VHS> vhsList = vhsService.findAll();
        model.addAttribute("vhsList", vhsList);
        return "vhslist";
        }


    @GetMapping("/cadastrovhs")
    public String telaCadastroVHS(Model model) {
        model.addAttribute("vhs", new VHS()); 
         List<Categoria> categoriasDisponiveis = categoriaService.findAll();
        model.addAttribute("vhs", vhs);
        model.addAttribute("categoriasDisponiveis", categoriasDisponiveis); // Adiciona as categorias disponíveis para o formulário
        return "cadastrovhs"; // Retorna a view de cadastro de VHS
    }

    @PostMapping("/cadastrovhs")
    public String cadastrarVHS(
        @RequestParam Integer code, 
        @RequestParam String title,
        @RequestParam String director,
        @RequestParam List<Long> categorias,
        @RequestParam String image,
        @RequestParam LocalDate registrationDate,
        @RequestParam TapeStatus status) {

        VHS vhs = new VHS();
        vhs.setCode(code);
        vhs.setTitle(title);
        vhs.setDirector(director);
        vhs.setImage(image);
        vhs.setRegistrationDate(registrationDate);
        vhs.setStatus(status);
        Set<Categoria> categoriaSet = new HashSet<>();
            for (Long categoriaId : categorias) {
                Categoria categoria = categoriaService.findById(categoriaId);
                if (categoria != null) {
                    categoriaSet.add(categoria);
                }
            }

        vhs.setCategorias(categoriaSet);
        vhsService.save(vhs);
        return "redirect:/vhs"; // redireciona para a lista de VHS após o cadastro
    }

    //deletar
    @GetMapping("/deletar/{id}")
    public String deletarVHS(@PathVariable Long id) {
        vhsService.deleteById(id);
        return "redirect:/vhs"; // redireciona para a lista de VHS após a exclusão
    }

    @GetMapping("/editar/{id}")
    public String editarVHS(@PathVariable Long id, Model model) {
        VHS vhs = vhsService.findById(id);
        List<Categoria> categoriasDisponiveis = categoriaService.findAll();
        model.addAttribute("vhs", vhs);
        model.addAttribute("categoriasDisponiveis", categoriasDisponiveis);
        return "editar"; // Nome do HTML Thymeleaf
    }

    @PostMapping("/editar/{id}")
    public String atualizarVHS(
        @PathVariable Long id,
        @RequestParam Integer code, 
        @RequestParam String title,
        @RequestParam String director,
        @RequestParam List<Long> categorias,
        @RequestParam String image,
        @RequestParam LocalDate registrationDate,
        @RequestParam TapeStatus status) {

        VHS vhs = vhsService.findById(id);
        if (vhs != null) {
            vhs.setCode(code);
            vhs.setTitle(title);
            vhs.setDirector(director);
            vhs.setImage(image);
            vhs.setRegistrationDate(registrationDate);
            vhs.setStatus(status);
            Set<Categoria> categoriaSet = new HashSet<>();
            for (Long categoriaId : categorias) {
                Categoria categoria = categoriaService.findById(categoriaId);
                if (categoria != null) {
                    categoriaSet.add(categoria);
                }
            }
            vhs.setCategorias(categoriaSet);

            vhsService.save(vhs);
        }
        return "redirect:/vhs"; // redireciona para a lista de VHS após a atualização
    }

}
