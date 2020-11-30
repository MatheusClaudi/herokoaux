package com.ufcg.psoft.model.DTO;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import com.ufcg.psoft.model.Categoria;
import com.ufcg.psoft.model.InventarioProduto;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.SituacaoInventario;

public class ProdutoDTO {
	
	private Long id;

	private String nome;

	private BigDecimal preco;

	private String codigoBarra;

	private String fabricante;

	private String nomeCategoria;
	
	private String situacao;
	
	public ProdutoDTO() {}

	public ProdutoDTO(Long id, String nome, BigDecimal preco, String codigoBarra, String fabricante,
			String nomeCategoria, String situacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.nomeCategoria = nomeCategoria;
		this.situacao = situacao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public void setNomeCategoria(String nomeCategoria) {
		this.nomeCategoria = nomeCategoria;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	public static ProdutoDTO fromCompleteProduto(Produto produto) {
		ProdutoDTO retorno = new ProdutoDTO();
		retorno.setNome(produto.getNome());
		retorno.setCodigoBarra(produto.getCodigoBarra());
		retorno.setFabricante(produto.getFabricante());
		retorno.setNomeCategoria(produto.getCategoria().getNomeCategoria());
		retorno.setPreco(produto.getPreco());
		retorno.setId(produto.getId());
		retorno.setSituacao(produto.getSituacao().toString());
		
		return retorno;
	}
	
	

}
