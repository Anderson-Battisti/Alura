package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

public class ListPetsFromShelterCommand
    implements
        Command
{
    @Override
    public void execute()
    {
        try
        {
            ClientHttpConfiguration client     = new ClientHttpConfiguration();
            PetService              petService = new PetService( client );

            petService.listPetsFromShelter();
        }

        catch (Exception e)
        {
            System.out.println( e.getMessage() );
        }
    }
}
