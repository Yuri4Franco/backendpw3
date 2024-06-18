package com.example.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.OfertaModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImovelDTOResposta implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String descricao;
    private Integer quartos;
    private Integer vagas;
    private Integer usuarioId;
    private String imagem;
    private List<Integer> ofertaIds;
    private List<OfertaDTOResposta> ofertas;
    private List<ContratoDTOResposta> contratos;

    public ImovelDTOResposta() {}

    public ImovelDTOResposta(ImovelModel model) {
        this.id = model.getId();
        this.descricao = model.getDescricao();
        this.quartos = model.getQuartos();
        this.vagas = model.getVagas();
        this.imagem = model.getImagem();
        this.usuarioId = model.getUserModel().getId();
        //this.ofertaIds = model.getOfertas().stream().map(OfertaModel::getId).collect(Collectors.toList());
       //this.contratos = model.getContratos().stream().map(ContratoDTOResposta::new).collect(Collectors.toList()); 
    }

    public static ImovelDTOResposta transformaEmDTO(ImovelModel model) {
        return new ImovelDTOResposta(model);
    }
}
