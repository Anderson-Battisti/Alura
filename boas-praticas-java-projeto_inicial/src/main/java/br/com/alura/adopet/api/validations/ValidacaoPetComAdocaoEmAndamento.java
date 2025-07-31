package br.com.alura.adopet.api.validations;

import java.util.List;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetComAdocaoEmAndamento
    implements 
        ValidacaoSolicitacaoAdocao
{
    @Autowired
    private AdocaoRepository adocaoRepository;
    
    @Autowired
    private PetRepository petRepository;
    
    public void validar( SolicitacaoAdocaoDTO dto )
    {
        if ( adocaoRepository.existsByPetIdAndStatus( dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO ) ) 
        {
            throw new ValidacaoException( "Pet já está aguardando avaliação para ser adotado!" );
        }
    }
}
