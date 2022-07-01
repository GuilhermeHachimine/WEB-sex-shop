package sex_shop.sex_shop_kenji_rander.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import sex_shop.sex_shop_kenji_rander.model.Produto;
import sex_shop.sex_shop_kenji_rander.service.CadastroProdutoService;

@Controller
@RequestMapping("/produto")
public class ProdutoController {
	private static final Logger logger = LoggerFactory.getLogger(ProdutoController.class);
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@GetMapping("/novo")
	public ModelAndView direcionarCadastroProduto(Produto produto) {
		logger.trace("Entrou em direcionarCadastroProduto");
		ModelAndView mv = new ModelAndView("cadastroproduto");
		logger.trace("Encaminhando para a view cadastroproduto");
		return mv;
	}
	
	
	@PostMapping("/novo")
	public ModelAndView cadastrarNovoProduto(Produto produto) {
		logger.trace("LaLaLaLaLa LaLaLaLaLa LaLaLaLaLa");
		logger.trace("Entrou em cadastrarNovoProduto");
		produto.setCodigo(null);
		logger.debug("Produto recebido {}", produto);
		ModelAndView mv;
		mv = new ModelAndView("index");
		cadastroProdutoService.salvar(produto);
		logger.trace("Redirecionando para a URL /index");
		return mv;
	}
	
	@PostMapping("/remover")
	public ModelAndView remover(Long codigo,Model model) {
		cadastroProdutoService.remover(codigo);
		logger.trace("Redirecionando para a URL /lotes/alteracaosucesso");
		ModelAndView mv = new ModelAndView("mostrarmensagem");
		logger.trace("Encaminhando para a view mensagem");
		model.addAttribute("mensagem", "Produto removido com sussexo!");
		return mv;
	}
	

}
