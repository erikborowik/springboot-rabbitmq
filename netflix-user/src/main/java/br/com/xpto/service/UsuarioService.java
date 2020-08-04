package br.com.xpto.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.xpto.data.Usuario;
import br.com.xpto.data.UsuarioRepositorio;
import javassist.NotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepositorio userRepository;

	public Usuario save(Usuario user) {
		return userRepository.save(user);
	}

	public Iterable<Usuario> findAll() {
		return userRepository.findAll();
	}

	public Usuario findById(Integer id) throws NotFoundException {
		Optional<Usuario> user = userRepository.findById(id);
		
		if(user.isPresent()) {
			return user.get();
		}else {
			throw new NotFoundException("Não localizado");
		}
		
	}

}
