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
import sex_shop.sex_shop_kenji_rander.repository.ProdutoRepository;
import sex_shop.sex_shop_kenji_rander.service.CadastroProdutoService;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping("/product")
	public ModelAndView cadastrarNovoProduto(Produto produto,Model model) {
		logger.trace("LaLaLaLaLa LaLaLaLaLa LaLaLaLaLa");
		logger.trace("Entrou em cadastrarNovoProduto");
		produto.setCodigo(null);
		logger.debug("Produto recebido {}", produto);
		cadastroProdutoService.salvar(produto);
		logger.trace("Entrou em index");
		ModelAndView mv = new ModelAndView("index");
		//Se voce precisar pode inserir outros objetos no model para que sejam usados
		// na view index.html
		//mv.addObject("nome", valor);
		List<Produto> produtos = produtoRepository.findAll();
		logger.debug("Produtos do banco: {}", produtos);
		logger.trace("Encaminhando para a view index");
		model.addAttribute("produtos", produtos);
		return mv;
	}
}
