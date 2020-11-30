package com.ufcg.psoft.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


import exceptions.ObjetoInvalidoException;

@Entity
@Table(name="PRODUTO_TB")
public class Produto {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String nome;

	private BigDecimal preco;

	private String codigoBarra;

	private String fabricante;

	@OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
	private Categoria categoria;
	
	@OneToOne(mappedBy="produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private InventarioProduto iventario;
	
	@Enumerated(EnumType.STRING)
	private SituacaoInventario situacao;
	

	public Produto() {
		this.preco = new BigDecimal(0);
		this.setSituacaoIndisponivel();
	}

	public Produto(String nome, String codigoBarra, String fabricante,
			Categoria categoria, BigDecimal preco) {
		this.nome = nome;
		this.preco = preco;
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.categoria = categoria;
		this.setSituacaoIndisponivel();
	}
	
	public Produto(long id, String nome, String codigoBarra, String fabricante,
			Categoria categoria, BigDecimal preco) {
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.codigoBarra = codigoBarra;
		this.fabricante = fabricante;
		this.categoria = categoria;
		this.setSituacaoIndisponivel();
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

	public Long getId() {
		return id;
	}

	public void setId(long codigo) {
		this.id = codigo;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public InventarioProduto getIventario() {
		return iventario;
	}

	public void setIventario(InventarioProduto iventario) {
		this.iventario = iventario;
	}
	
	public SituacaoInventario getSituacao() {
		return situacao;
	}

	public void setSituacao(SituacaoInventario situacao) {
		this.situacao = situacao;
	}

	public void setSituacaoDisponivel() {
		this.situacao = SituacaoInventario.DISPONIVEL;
	}
	
	public void setSituacaoIndisponivel() {
		this.situacao = SituacaoInventario.INDISPONIVEL;
	}
	
	public boolean produtoEstaDisponivel() {
		return this.situacao == SituacaoInventario.DISPONIVEL;
	}
	
	public BigDecimal getValorDesconto() {
		return this.preco.multiply(BigDecimal.valueOf(this.categoria.getPorcentagemDesconto()).divide(BigDecimal.valueOf(100)));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoBarra == null) ? 0 : codigoBarra.hashCode());
		result = prime * result + ((fabricante == null) ? 0 : fabricante.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Produto other = (Produto) obj;
		if (codigoBarra == null) {
			if (other.codigoBarra != null)
				return false;
		} else if (!codigoBarra.equals(other.codigoBarra))
			return false;
		if (fabricante == null) {
			if (other.fabricante != null)
				return false;
		} else if (!fabricante.equals(other.fabricante))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
