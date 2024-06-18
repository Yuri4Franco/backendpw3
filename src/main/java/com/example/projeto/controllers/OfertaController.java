package com.example.projeto.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.OfertaDTOResposta;
import com.example.projeto.models.ImovelModel;
import com.example.projeto.models.OfertaModel;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.ImovelService;
import com.example.projeto.service.OfertaService;
import com.example.projeto.service.UserService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/ofertas")
public class OfertaController {

	@Autowired
	private OfertaService ofertaService;

	@Autowired
	private ImovelService imovelService;

	@Autowired
	private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OfertaDTOResposta>> getAll() {
        List<OfertaModel> list = ofertaService.getAll();
        List<OfertaDTOResposta> dtoList = list.stream()
                                              .map(OfertaDTOResposta::new)
                                              .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtoList);
    }


	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<OfertaDTOResposta> insert(@RequestBody OfertaModel model, @RequestParam Integer imovelId, @RequestParam Integer usuarioId) {
		
		ImovelModel imovel = imovelService.find(imovelId);

		model.setImovelModel(imovel);

		UserModel userModel = userService.find(usuarioId);

		model.setUserModel(userModel);

		ofertaService.insert(model);

		OfertaDTOResposta dto = new OfertaDTOResposta(model);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
  @GetMapping("/imovel/{imovelId}")
    public ResponseEntity<List<OfertaDTOResposta>> getOfertasByImovelId(@PathVariable Integer imovelId) {
        List<OfertaModel> ofertas = ofertaService.findByImovelId(imovelId);
        List<OfertaDTOResposta> dtos = ofertas.stream()
                                               .map(OfertaDTOResposta::new)
                                               .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(dtos);
    }

    @RequestMapping(value = "/{ofertaId}/valor", method = RequestMethod.PUT)
    public ResponseEntity<OfertaDTOResposta> updateValor(@PathVariable Integer ofertaId, @RequestParam double valor) {
        OfertaModel updatedOferta = ofertaService.updateValor(ofertaId, valor);
        if (updatedOferta != null) {
            return ResponseEntity.status(HttpStatus.OK).body(new OfertaDTOResposta(updatedOferta));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
