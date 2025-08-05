package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AbrigoDTO;
import br.com.alura.adopet.api.dto.CadastroAbrigoDTO;
import br.com.alura.adopet.api.dto.PetDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService
{
    @Autowired
    private AbrigoRepository abrigoRepository;

    @Autowired
    private PetRepository petRepository;

    public List<AbrigoDTO> listar()
    {
        return abrigoRepository.findAll()
                               .stream()
                               .map( AbrigoDTO::new )
                               .toList();
    }

    public void cadastrar( CadastroAbrigoDTO cadastroAbrigoDTO )
    {
        if ( abrigoRepository.existsByNomeOrTelefoneOrEmail( cadastroAbrigoDTO.nome(), cadastroAbrigoDTO.telefone(), cadastroAbrigoDTO.email() ) )
        {
            throw new ValidacaoException( "Dados já cadastrados para outro abrigo." );
        }

        abrigoRepository.save( new Abrigo( cadastroAbrigoDTO ) );
    }

    public List<PetDTO> listarPetsDoAbrigo( String idOuNome )
    {
        return petRepository.findByAbrigo( carregarAbrigo )
                            .stream()
                            .map( PetDTO::new )
                            .toList();
    }

    public Abrigo carregarAbrigo( String idOuNome )
    {
        Optional<Abrigo> optional;

        try
        {
            optional = abrigoRepository.findById( Long.parseLong( idOuNome ) );
        }

        catch ( NumberFormatException e )
        {
            optional = abrigoRepository.findByNome( idOuNome );
        }

        return optional.orElseThrow( new ValidacaoException( "Abrigo não encontrado" ) );
    }
}
