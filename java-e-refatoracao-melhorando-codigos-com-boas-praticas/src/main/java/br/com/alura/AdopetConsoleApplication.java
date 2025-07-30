package br.com.alura;

import java.util.Scanner;

public class AdopetConsoleApplication
{
    public static void main( String[] args )
    {
        CommandExecutor commandExecutor = new CommandExecutor();

        System.out.println( "##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####" );

        try
        {
            int chosenOption = 0;

            while ( chosenOption != 5 )
            {
                showMenu();

                String writenText = new Scanner( System.in ).nextLine();

                chosenOption = Integer.parseInt( writenText );

                switch ( chosenOption )
                {
                    case 1 -> commandExecutor.executeCommand( new ListsShelterCommand() );
                    case 2 -> commandExecutor.executeCommand( new RegisterShelterCommand() );
                    case 3 -> commandExecutor.executeCommand( new ListPetsFromShelterCommand() );
                    case 4 -> commandExecutor.executeCommand( new ImportPetsFromShelterCommand() );
                    case 5 -> System.exit( 0 );

                    default ->
                    {
                        System.out.println( "NÚMERO INVÁLIDO!" );
                        chosenOption = 0;
                    }
                }
            }

            System.out.println( "Finalizando o programa..." );
        }

        catch ( Exception e )
        {
            e.printStackTrace();
        }
    }

    private static void showMenu()
    {
        System.out.println( "\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:" );
        System.out.println( "1 -> Listar abrigos cadastrados" );
        System.out.println( "2 -> Cadastrar novo abrigo" );
        System.out.println( "3 -> Listar pets do abrigo" );
        System.out.println( "4 -> Importar pets do abrigo" );
        System.out.println( "5 -> Sair" );
    }
}