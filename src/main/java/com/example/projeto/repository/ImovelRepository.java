package com.example.projeto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.projeto.models.ImovelModel;

public interface ImovelRepository extends JpaRepository<ImovelModel, Integer> {

    @Query("SELECT i FROM ImovelModel i WHERE i.id IN (SELECT o.imovelModel.id FROM OfertaModel o WHERE o.id IN (SELECT d.ofertaModel.id FROM DescontoModel d WHERE d.ofertaModel.id=o.id))")
    List<ImovelModel> getAllDesconto();
}