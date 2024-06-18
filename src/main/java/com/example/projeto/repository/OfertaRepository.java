package com.example.projeto.repository;
import com.example.projeto.models.OfertaModel;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfertaRepository extends JpaRepository<OfertaModel, Integer> {
    List<OfertaModel> findByImovelModel_Id(Integer imovelId);
}
