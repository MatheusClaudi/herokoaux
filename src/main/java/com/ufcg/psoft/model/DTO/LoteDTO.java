package com.ufcg.psoft.model.DTO;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufcg.psoft.model.Lote;

public class LoteDTO {

	private Long idLote;
	private Long idProduto;
    private int numeroDeItens;
    
    @Temporal(TemporalType.TIMESTAMP)
   	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dataDeValidade;
   

    public LoteDTO() {
    }

        
    public LoteDTO(Long idLote, Long idProduto, int numeroDeItens, Date dataDeValidade) {
		super();
		this.idLote = idLote;
		this.idProduto = idProduto;
		this.numeroDeItens = numeroDeItens;
		this.dataDeValidade = dataDeValidade;
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

	public Long getIdLote() {
		return idLote;
	}

	public void setIdLote(Long idLote) {
		this.idLote = idLote;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}
	
	public static LoteDTO fromCompleteLote(Lote lote) {
		LoteDTO resposta = new LoteDTO();
		resposta.setIdLote(lote.getId());
		resposta.setIdProduto(lote.getProduto().getId());
		resposta.setNumeroDeItens(lote.getNumeroDeItens());
		resposta.setDataDeValidade(lote.getDataDeValidade());
		return resposta;
	}
    
    
}
