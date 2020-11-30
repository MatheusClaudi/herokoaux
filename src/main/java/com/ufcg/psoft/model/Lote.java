package com.ufcg.psoft.model;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="LOTE_TB")
public class Lote {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
    
	@ManyToOne()
	@JoinColumn(name="produto_id", nullable=false)
    private Produto produto;
    
    private int numeroDeItens;
    
    @Temporal(TemporalType.TIMESTAMP)
   	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataDeValidade;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private InventarioProduto iventario;

    public Lote() {}

    public Lote(Produto produto, int numeroDeItens, Date dataDeValidade) {
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = dataDeValidade;
    }

    public Lote(long id, Produto produto, int numeroDeItens, Date dataDeValidade) {
        this.id = id;
        this.produto = produto;
        this.numeroDeItens = numeroDeItens;
        this.dataDeValidade = dataDeValidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumeroDeItens() {
        return numeroDeItens;
    }

    public void setNumeroDeItens(int numeroDeItens) {
        this.numeroDeItens = numeroDeItens;
    }

    public Date getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(Date dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public InventarioProduto getIventario() {
		return iventario;
	}

	public void setIventario(InventarioProduto iventario) {
		this.iventario = iventario;
	}
	
	public void diminuiQuantidade(Long quantidade) {
		this.numeroDeItens -= quantidade;
	}
	
	public void aumenteQuantidade(Long quantidade) {
		this.numeroDeItens += quantidade;
	}
	
	public SituacaoInventario getSituacaoLote() {
		if (this.numeroDeItens > 0) {
			return SituacaoInventario.DISPONIVEL;
		}
		else  {
			return SituacaoInventario.INDISPONIVEL;
		}
	}

	@Override
    public String toString() {
        return "Lote{" +
                "id=" + id +
                ", produto=" + produto.getId() +
                ", numeroDeItens=" + numeroDeItens +
                ", dataDeValidade='" + dataDeValidade.toString() + '\'' +
                '}';
    }
	
	public static int getMarcadorDeEstoqueBaixo() {
		return 15;
	}
	
	public static Date getDataIndicadoraVencimento(Date date) {
		Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        return cal.getTime();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		Lote other = (Lote) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
