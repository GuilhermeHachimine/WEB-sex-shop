package sex_shop.sex_shop_kenji_rander.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda implements Serializable{

	private static final long serialVersionUID = -2621817840480006300L;

	@Id
	@SequenceGenerator(name="gerador", sequenceName="venda_codigo_seq", allocationSize=1)
	@GeneratedValue(generator="gerador", strategy=GenerationType.SEQUENCE)
	private Long codigo;
	
	
	@Column(name = "codigo_usuario")
	private Long codigoUsuario;
	
	@Column(name = "data_venda")
	private LocalDate dataVenda;
	
	private Long quantidade;
	
	@ManyToMany
	@JoinTable(name = "venda_produto", joinColumns = @JoinColumn(name = "codigo_venda"), inverseJoinColumns = @JoinColumn(name = "codigo_produto"))
	private List<Produto> produtos = new ArrayList<>();
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public Long getCodigoUsuario() {
		return codigoUsuario;
	}

	public void setCodigoUsuario(Long codigoUsuario) {
		this.codigoUsuario = codigoUsuario;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	public void adicionarProdutos (Produto produto) {
		produtos.add(produto);
	}

	public void removerPapel(Produto produto) {
		produtos.remove(produto);
	}

	
	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Venda other = (Venda) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Venda [codigo=" + codigo + ", codigoUsuario=" + codigoUsuario + ", dataVenda=" + dataVenda
				+ ", quantidade=" + quantidade + ", produtos=" + produtos + "]";
	}



}
