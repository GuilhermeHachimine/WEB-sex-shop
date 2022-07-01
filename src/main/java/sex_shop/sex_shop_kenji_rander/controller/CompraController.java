package sex_shop.sex_shop_kenji_rander.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sex_shop.sex_shop_kenji_rander.model.Produto;
import sex_shop.sex_shop_kenji_rander.model.Usuario;
import sex_shop.sex_shop_kenji_rander.model.Venda;
import sex_shop.sex_shop_kenji_rander.repository.UsuarioRepository;
import sex_shop.sex_shop_kenji_rander.repository.VendaRepository;

@Controller
@RequestMapping("/comprar")
public class CompraController {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		logger.debug("currentPrincipalName recebido {}", currentPrincipalName);
		venda.setDataVenda(LocalDate.now());
		List<Usuario> usuarios = usuarioRepository.findAll();
		Usuario loggedUser2 = usuarios.stream()
				  .filter(usuario -> currentPrincipalName.equals(usuario.getNomeUsuario()))
				  .findAny()
				  .orElse(null);
		logger.debug("Usuário logado {}", loggedUser2.getCodigo());
		venda.setCodigoUsuario(loggedUser2.getCodigo());
		logger.debug("Venda recebida {}", venda);
		ModelAndView mv;
		vendaRepository.save(venda);				
		mv = new ModelAndView("mostrarmensagem");
		model.addAttribute("mensagem", "Compra efetuada");
		return mv;
	}
}
