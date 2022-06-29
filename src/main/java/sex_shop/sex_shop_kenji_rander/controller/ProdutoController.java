package sex_shop.sex_shop_kenji_rander.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sex_shop.sex_shop_kenji_rander.model.Produto;
import sex_shop.sex_shop_kenji_rander.repository.ProdutoRepository;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/novo")
	public ModelAndView direcionarCadastroProduto(Produto produto) {
		logger.trace("Entrou em direcionarCadastroProduto");
		ModelAndView mv = new ModelAndView("cadastroproduto");
		List<Produto> produtos = produtoRepository.findAll();
		logger.debug("Produtos encontrados para mostrar {}", produtos);
		mv.addObject("todosProdutos", produtos);
		logger.trace("Encaminhando para a view cadastroproduto");
		return mv;
	}
}
