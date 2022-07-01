package sex_shop.sex_shop_kenji_rander.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sex_shop.sex_shop_kenji_rander.model.Produto;
import sex_shop.sex_shop_kenji_rander.model.Usuario;
import sex_shop.sex_shop_kenji_rander.model.Venda;
import sex_shop.sex_shop_kenji_rander.repository.UsuarioRepository;

@Controller
@RequestMapping("/comprar")
public class CompraController {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping("/produtos")
	public String abrirCompraProduto(Produto produto, Model model) {
		logger.trace("Entrou em direcionar compra produto");
		logger.trace("Produto a ser comprado {}",produto );
		Venda venda = new Venda();
		venda.adicionarProdutos(produto);
		model.addAttribute("venda", venda);
		logger.trace("Venda a ser salva {}",venda );
		logger.trace("produto dentro de venda {}",venda.getProdutos() );
		logger.trace("direcionando para compraproduto");
		return "/compraproduto";
	}
	
	@PostMapping("/salvar")
	public ModelAndView salvarVenda(Venda venda, Model model) {
		logger.trace("Entrou em salvar compra");
		logger.debug("Venda recebida {}", venda);
		List<Usuario> usuarios = usuarioRepository.findAll();
		ModelAndView mv;
//		if (!usuario.getPapeis().isEmpty()) {
//			usuario.setAtivo(true);
//			usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
//			cadastroUsuarioService.salvar(usuario);
//			logger.trace("Redirecionando para a URL /usuario/novo");
//			mv = new ModelAndView ("redirect:/usuario/novo");
//		} else {
//			logger.error("Nenhum papel colocado no usuario");
//			List<Papel> papeis = papelRepository.findAll();
//			logger.debug("Papeis encontrados para mostrar {}", papeis);
//			mv = new ModelAndView("cadastrousuario");
//			mv.addObject("todosPapeis", papeis);
//			logger.trace("Encaminhando para a view cadastrousuario");
//		}
		return mv;
	}
}
