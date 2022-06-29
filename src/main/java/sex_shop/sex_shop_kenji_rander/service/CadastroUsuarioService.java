package sex_shop.sex_shop_kenji_rander.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sex_shop.sex_shop_kenji_rander.model.Usuario;
import sex_shop.sex_shop_kenji_rander.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	private static final Logger logger = LoggerFactory.getLogger(CadastroUsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public void salvar(Usuario usuario) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o usuario {}", usuario);
		usuarioRepository.save(usuario);
		logger.debug("Usuario salvo com sucesso {}", usuario);
	}

}
