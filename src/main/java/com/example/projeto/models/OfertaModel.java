package com.example.projeto.models;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.projeto.enums.TipoOferta;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = "*")
@Table(name="ofertas")
public class OfertaModel implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer tipoOferta;

    private double valor;

    @ManyToOne
    @JoinColumn(name="imovel_id")
    @JsonIgnore
    private ImovelModel imovelModel;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UserModel userModel;
    
  @OneToMany(mappedBy = "ofertaModel", fetch = FetchType.EAGER)
    private List<DescontoModel> descontos;

    public TipoOferta getTipoOferta() {
        return TipoOferta.toEnum(tipoOferta);
    }

    public void setTipoUsuario(TipoOferta tipoOferta) {
        this.tipoOferta = tipoOferta.getCodigo();
    }

       
    
}
