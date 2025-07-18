package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.ShelterService;
import br.com.alura.service.PetService;
import java.util.Scanner;

public class AdopetConsoleApplication
{
    public static void main( String[] args )
    {
        ClientHttpConfiguration client         = new ClientHttpConfiguration();
        ShelterService          shelterService = new ShelterService( client );
        PetService              petService     = new PetService( client );

        System.out.println( "##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####" );

        try
        {
            int chosenOption = 0;

            while ( chosenOption != 5 )
            {
                System.out.println( "\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:" );
                System.out.println( "1 -> Listar abrigos cadastrados" );
                System.out.println( "2 -> Cadastrar novo abrigo" );
                System.out.println( "3 -> Listar pets do abrigo" );
                System.out.println( "4 -> Importar pets do abrigo" );
                System.out.println( "5 -> Sair" );

                String writenText = new Scanner( System.in ).nextLine();

                chosenOption = Integer.parseInt( writenText );

                if ( chosenOption == 1 )
                {
                    shelterService.listShelter();
                }

                else if ( chosenOption == 2 )
                {
                    shelterService.registerShelter();
                }

                else if ( chosenOption == 3 )
                {
                    petService.listPetsFromShelter();
                }

                else if ( chosenOption == 4 )
                {
                    petService.importPetsFromShelter();
                }

                else if ( chosenOption == 5 )
                {
                    break;
                }

                else
                {
                    System.out.println( "NÚMERO INVÁLIDO!" );
                    chosenOption = 0;
                }
            }

            System.out.println( "Finalizando o programa..." );
        }

        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }
}