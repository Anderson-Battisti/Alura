package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizacaoTutorDTO;
import br.com.alura.adopet.api.dto.CadastroTutorDTO;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService
{
    @Autowired
    private TutorRepository tutorRepository;

    public void cadastrar( CadastroTutorDTO cadastroTutorDTO )
    {
        if ( tutorRepository.existByTelefoneOrEmail( cadastroTutorDTO.telefone(), cadastroTutorDTO.email() ) )
        {
            throw new ValidacaoException( "Dados já cadastrados!" );
        }

        tutorRepository.save( cadastroTutorDTO );
    }

    public void atualizar( AtualizacaoTutorDTO atualizacaoTutorDTO )
    {
        Tutor tutor = tutorRepository.getReferenceById( atualizacaoTutorDTO.id() );

        tutor.atualizarDados( atualizacaoTutorDTO );
    }
}
