package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.Date;

import com.example.projeto.enums.TipoDesconto;
import com.example.projeto.models.DescontoModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DescontoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private double valor;
    private TipoDesconto tipoDesconto;
    private Date dataExpiracao;

    public DescontoDTO() {}

    public DescontoDTO(DescontoModel model) {
        this.id = model.getId();
        this.valor = model.getValor();
        this.tipoDesconto = model.getTipoDesconto();
        this.dataExpiracao = model.getDataExpiracao();
    }
}
