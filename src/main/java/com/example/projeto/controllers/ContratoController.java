package com.example.projeto.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.ContratoDTOResposta;
import com.example.projeto.models.ContratoModel;
import com.example.projeto.service.ContratoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(value = "/contratos")
public class ContratoController {

	@Autowired
	private ContratoService service;

	@GetMapping
    public ResponseEntity<List<ContratoDTOResposta>> getAll() {
        List<ContratoModel> contratos = service.getAll();
        List<ContratoDTOResposta> listaRetorno = new ArrayList<>();

        for (ContratoModel contrato : contratos) {
           ContratoDTOResposta dtoResposta = new ContratoDTOResposta(contrato);
		   listaRetorno.add(dtoResposta);
        }

        return new ResponseEntity<>(listaRetorno, HttpStatus.OK);
    }

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody ContratoModel model) {	
		service.insert(model);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	    @RequestMapping(value = "/imovel/{imovelId}", method = RequestMethod.GET)
    public ResponseEntity<List<ContratoModel>> getByImovelId(@PathVariable Integer imovelId) {
        List<ContratoModel> list = service.findByImovelId(imovelId);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

	@RequestMapping(value = "/{contratoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteContrato(@PathVariable Integer contratoId) {
        service.delete(contratoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


	@RequestMapping(value = "/{contratoId}", method = RequestMethod.GET)
    public ResponseEntity<ContratoDTOResposta> getContratoById(@PathVariable Integer contratoId) {
        ContratoModel contrato = service.find(contratoId);
        if (contrato != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ContratoDTOResposta(contrato));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @RequestMapping(value = "/{contratoId}", method = RequestMethod.PUT)
    public ResponseEntity<ContratoDTOResposta> update(@PathVariable Integer contratoId, @RequestBody ContratoModel model) {
        ContratoModel updatedContrato = service.update(contratoId, model);
        if (updatedContrato != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new ContratoDTOResposta(updatedContrato));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
