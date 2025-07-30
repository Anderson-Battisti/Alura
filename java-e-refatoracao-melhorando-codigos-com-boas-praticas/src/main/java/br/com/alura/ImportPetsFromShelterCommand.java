package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;

public class ImportPetsFromShelterCommand
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

            petService.importPetsFromShelter();
        }

        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
        }
    }
}
