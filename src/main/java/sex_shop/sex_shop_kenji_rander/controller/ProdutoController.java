package sex_shop.sex_shop_kenji_rander.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sex_shop.sex_shop_kenji_rander.model.Produto;
import sex_shop.sex_shop_kenji_rander.repository.ProdutoRepository;
import sex_shop.sex_shop_kenji_rander.service.CadastroProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@GetMapping("/novo")
	public ModelAndView direcionarCadastroProduto(Produto produto) {
		logger.trace("Entrou em direcionarCadastroProduto");
		ModelAndView mv = new ModelAndView("cadastroproduto");
		logger.trace("Encaminhando para a view cadastroproduto");
		return mv;
	}
	
	
	@PostMapping("/produto/novo")
	public ModelAndView cadastrarNovoProduto(Produto produto) {
		logger.trace("LaLaLaLaLa LaLaLaLaLa LaLaLaLaLa");
		logger.trace("Entrou em cadastrarNovoProduto");
		produto.setCodigo(null);
		logger.debug("Produto recebido {}", produto);
		ModelAndView mv;
		mv = new ModelAndView("cadastroproduto");
		cadastroProdutoService.salvar(produto);
		logger.trace("Redirecionando para a URL /produto/novo");
		return mv;
	}
}
