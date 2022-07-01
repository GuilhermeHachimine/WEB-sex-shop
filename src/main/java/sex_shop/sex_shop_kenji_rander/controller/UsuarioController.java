package sex_shop.sex_shop_kenji_rander.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sex_shop.sex_shop_kenji_rander.model.Papel;
import sex_shop.sex_shop_kenji_rander.model.Usuario;
import sex_shop.sex_shop_kenji_rander.repository.PapelRepository;
import sex_shop.sex_shop_kenji_rander.service.CadastroUsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/novo")
	public ModelAndView direcionarCadastroUsuario(Usuario usuario) {
		logger.trace("Entrou em direcionarCadastroUsuario");
		ModelAndView mv = new ModelAndView("cadastrousuario");
		List<Papel> papeis = papelRepository.findAll();
		logger.debug("Papeis encontrados para mostrar {}", papeis);
		mv.addObject("todosPapeis", papeis);
		logger.trace("Encaminhando para a view cadastrousuario");
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView cadastrarNovoUsuario(Usuario usuario) {
		logger.trace("Entrou em cadastrarNovoUsuario");
		logger.debug("Usuario recebido {}", usuario);
		logger.debug("Papeis recebidos para o usuario {}", usuario.getPapeis());
		ModelAndView mv;
		if (!usuario.getPapeis().isEmpty()) {
			usuario.setAtivo(true);
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			cadastroUsuarioService.salvar(usuario);
			logger.trace("Redirecionando para a URL /usuario/novo");
			mv = new ModelAndView ("redirect:/usuario/novo");
		} else {
			logger.error("Nenhum papel colocado no usuario");
			List<Papel> papeis = papelRepository.findAll();
			logger.debug("Papeis encontrados para mostrar {}", papeis);
			mv = new ModelAndView("cadastrousuario");
			mv.addObject("todosPapeis", papeis);
			logger.trace("Encaminhando para a view cadastrousuario");
		}
		return mv;
	}
	
	

}
