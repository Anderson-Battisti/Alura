package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.CadastroAbrigoDTO;
import br.com.alura.adopet.api.dto.CadastroPetDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.service.AbrigoService;
import br.com.alura.adopet.api.service.PetService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( "/abrigos" )
public class AbrigoController
{
    @Autowired
    private AbrigoService abrigoService;

    @Autowired
    private PetService petService;

    @GetMapping
    public ResponseEntity<List<AbrigoDTO>> listar()
    {
        return ResponseEntity.ok( abrigoService.listar() );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar( @RequestBody @Valid CadastroAbrigoDTO cadastroAbrigoDTO )
    {
        try
        {
            abrigoService.cadastrar( cadastroAbrigoDTO );

            return ResponseEntity.ok().build();
        }

        catch ( Exception e )
        {
            return ResponseEntity.badRequest().body( e.getMessage() );
        }
    }

    @GetMapping( "/{idOuNome}/pets" )
    public ResponseEntity<List<PetDTO>> listarPets( @PathVariable String idOuNome )
    {
        try
        {
            List<PetDTO> pets = abrigoService.listarPetsDoAbrigo( idOuNome );

            return ResponseEntity.ok( pets );
        }

        catch ( ValidacaoException exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping( "/{idOuNome}/pets" )
    @Transactional
    public ResponseEntity<String> cadastrarPet( @PathVariable String idOuNome, @RequestBody @Valid CadastroPetDTO cadastroPetDTO )
    {
        try
        {
            petService.cadastrarPet( abrigoService.carregarAbrigo( idOuNome ) );

            return ResponseEntity.ok().build();
        }

        catch ( ValidacaoException exception )
        {
            return ResponseEntity.notFound().build();
        }
    }

}
