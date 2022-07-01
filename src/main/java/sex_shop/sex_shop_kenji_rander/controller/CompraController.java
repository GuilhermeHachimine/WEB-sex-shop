package sex_shop.sex_shop_kenji_rander.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sex_shop.sex_shop_kenji_rander.model.Produto;

@Controller
@RequestMapping("/comprar")
public class CompraController {
	private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
	
	
	@PostMapping("/produtos")
	public String abrirCompraProduto(Produto produto, Model model) {
		logger.trace("Entrou em direcionar compra produto");
		logger.trace("Produto a ser comprado {}",produto );
		model.addAttribute("produto", produto);
		logger.trace("direcionando para compraproduto");
		return "/compraproduto";
	}
	
	@PostMapping("/salvar")
	public String salvarVenda(Produto produto, Model model) {
		logger.trace("Entrou em direcionar compra produto");
		logger.trace("Produto a ser comprado {}",produto );
		model.addAttribute("produto", produto);
		logger.trace("direcionando para compraproduto");
		return "/compraproduto";
	}
}
