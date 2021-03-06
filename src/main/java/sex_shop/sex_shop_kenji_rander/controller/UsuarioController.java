package sex_shop.sex_shop_kenji_rander.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	public ModelAndView cadastrarNovoUsuario(Usuario usuario,Model model) {
		logger.trace("Entrou em cadastrarNovoUsuario");
		logger.debug("Usuario recebido {}", usuario);
		logger.debug("Papeis recebidos para o usuario {}", usuario.getPapeis());
		ModelAndView mv;

		if (!usuario.getPapeis().isEmpty()) {
			usuario.setAtivo(true);
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			cadastroUsuarioService.salvar(usuario);
			logger.trace("Redirecionando para a URL /usuario/novo");
			mv = new ModelAndView("mostrarmensagem");
			logger.trace("Encaminhando para a view mensagem");
			model.addAttribute("mensagem", "Usu??rio cadastrado com sussexo!");
		} else {
			logger.error("Nenhum papel colocado no usuario");
			List<Papel> papeis = papelRepository.findAll();
			logger.debug("Papeis encontrados para mostrar {}", papeis);
			usuario.setAtivo(true);
			Papel userRole = papeis.stream()
					  .filter(papel -> "ROLE_USUARIO".equals(papel.getNome()))
					  .findAny()
					  .orElse(null);
			logger.debug("user role is {}", userRole);
			usuario.adicionarPapel(userRole);
			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
			cadastroUsuarioService.salvar(usuario);
			logger.trace("Redirecionando para a URL /usuario/novo");
			mv = new ModelAndView("mostrarmensagem");
			logger.trace("Encaminhando para a view mensagem");
			model.addAttribute("mensagem", "Usu??rio cadastrado com sussexo!");
		}
		return mv;
	}
	
	

}
