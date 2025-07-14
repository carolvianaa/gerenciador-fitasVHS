package edu.ifpr.trabalho_semestral.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.ifpr.trabalho_semestral.entities.Usuario;
import edu.ifpr.trabalho_semestral.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario != null && encoder.matches(senha, usuario.getSenha())) {
            return usuario;
        }
        return null;
    }

    public void salvarUsuario(Usuario usuario) {
        // Criptografa a senha antes de salvar
        String senhaCriptografada = encoder.encode(usuario.getSenha());
        usuario.setSenha(senhaCriptografada);
        
        // Salva o usuário no repositório
       usuarioRepository.save(usuario);
    }

    public Usuario findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
