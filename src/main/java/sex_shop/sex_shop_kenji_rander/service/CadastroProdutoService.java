package sex_shop.sex_shop_kenji_rander.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sex_shop.sex_shop_kenji_rander.model.Produto;
import sex_shop.sex_shop_kenji_rander.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {
	private static final Logger logger = LoggerFactory.getLogger(CadastroProdutoService.class);

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
	public void salvar(Produto produto) {
		logger.trace("Entrou em salvar");
		logger.debug("Salvando o produto {}", produto);
		produtoRepository.save(produto);
		logger.debug("Produto salvo com sucesso {}", produto);
	}
	
}
