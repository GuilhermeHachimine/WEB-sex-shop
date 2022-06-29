package sex_shop.sex_shop_kenji_rander.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import sex_shop.sex_shop_kenji_rander.model.Produto;
import sex_shop.sex_shop_kenji_rander.repository.ProdutoRepository;

@Controller
public class IndexController {
	@Autowired
	private ProdutoRepository produtoRepository;

	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

	@GetMapping(value = {"/", "/index.html"})
	public ModelAndView index(Model model) {
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
	
	@GetMapping("/login")
	public String login() {
		logger.trace("Entrou em login");
		logger.trace("Encaminhando para a view login");
		return "login";
	}

	
}