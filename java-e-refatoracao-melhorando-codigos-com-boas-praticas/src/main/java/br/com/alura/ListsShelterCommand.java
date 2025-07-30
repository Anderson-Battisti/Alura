package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.ShelterService;

import java.io.IOException;

public class ListsShelterCommand
        implements
            Command
{
    @Override
    public void execute()
    {
        try
        {
            ClientHttpConfiguration client         = new ClientHttpConfiguration();
            ShelterService          shelterService = new ShelterService( client );

            shelterService.listShelter();
        }

        catch ( Exception e )
        {
            System.out.println( e.getMessage() );
        }
    }
}
