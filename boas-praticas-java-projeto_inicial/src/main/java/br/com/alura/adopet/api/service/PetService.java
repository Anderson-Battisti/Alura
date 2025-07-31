package br.com.alura.adopet.api.service;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService
{
    @Autowired
    private PetRepository petRepository;
    
    public List<Pet> listarTodosDisponiveis()
    {
        return petRepository.findByAdotadoFalse();
    }
}
