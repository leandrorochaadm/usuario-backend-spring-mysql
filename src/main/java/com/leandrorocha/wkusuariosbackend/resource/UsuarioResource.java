package com.leandrorocha.wkusuariosbackend.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leandrorocha.wkusuariosbackend.model.Usuario;
import com.leandrorocha.wkusuariosbackend.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository repository;

	@GetMapping
	public List<Usuario> lista() {
		return repository.findAll();
	}

	@GetMapping("/{id}")
	public Usuario pesquisaPorId(@PathVariable Long id) {
		return repository.findById(id).orElse(null);
	}

	@PostMapping
	public Usuario inserir(@Valid @RequestBody Usuario usuario) {
		return repository.save(usuario);
	}

	@PutMapping("/{id}")
	public Usuario atualizar(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioNovo = pesquisaPorId(id);
		if (usuarioNovo == null)
			return null;
		BeanUtils.copyProperties(usuario, usuarioNovo, "id");

		return repository.save(usuarioNovo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void excluir(@PathVariable Long id) {
		repository.deleteById(id);
	}

}
