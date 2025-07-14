package edu.ifpr.trabalho_semestral.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.ifpr.trabalho_semestral.entities.Usuario;
import edu.ifpr.trabalho_semestral.services.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller

public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;



    @GetMapping("/login")
    public String telaLogin() {
        return "login"; // abre login.html
    }

    @PostMapping("/login")
    public String login(
        @RequestParam String email, 
        @RequestParam String senha, 
        HttpSession session,
        Model model) {

    Usuario usuario = usuarioService.autenticar(email, senha);

        if (usuario != null) {
            session.setAttribute("usuarioLogado", usuario);
            return "hall"; // redireciona para a página do hall
        } else {
            model.addAttribute("erro", "Usuário ou senha inválidos");
            return "login"; // retorna para a tela de login com erro
        }
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // invalida a sessão do usuário
        return "redirect:/login"; // redireciona para a página de login
    }
    
    @GetMapping("/cadastro")
    public String telaCadastro() {
        return "cadastro"; // abre cadastro.html
    }

    @PostMapping("/cadastro")
    public String cadastrarUsuario(
        @RequestParam String email, 
        @RequestParam String senha, 
        @RequestParam String nome,
        Model model) {

            if (usuarioService.findByEmail(email) != null) {
                    model.addAttribute("erro", "Email já cadastrado");
                    return "cadastro"; // retorna para a tela de cadastro com erro
                }


            Usuario usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(senha);
            usuario.setNome(nome);

            usuarioService.salvarUsuario(usuario);

            return "redirect:/login"; // redireciona para a página de login após cadastro

        }

    @GetMapping("/hall")
    public String telaHall() {
        return "hall"; // abre hall.html
    }
}
