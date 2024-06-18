package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.projeto.models.OfertaModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@CrossOrigin(origins = "*")
public class OfertaDTOResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String tipo;
    private double valor;
    private String nomeUsuario;
    private Integer imovelId;
    private List<DescontoDTO> descontos;

    public OfertaDTOResposta() {}

    public OfertaDTOResposta(OfertaModel model) {
        this.id = model.getId();
        this.tipo = model.getTipoOferta().getDescricao();
        this.valor = model.getValor();
        this.nomeUsuario = model.getUserModel().getNome();
        this.imovelId = model.getImovelModel().getId();
        this.descontos = model.getDescontos().stream()
                              .map(DescontoDTO::new)
                              .collect(Collectors.toList());
    }
}
