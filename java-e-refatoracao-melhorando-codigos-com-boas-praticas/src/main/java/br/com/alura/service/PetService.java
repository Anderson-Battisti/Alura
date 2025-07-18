package br.com.alura.service;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Pet;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PetService
{
    private ClientHttpConfiguration client;

    public PetService( ClientHttpConfiguration client )
    {
        this.client = client;
    }

    public void listPetsFromShelter() throws IOException, InterruptedException
    {
        System.out.println( "Digite o id ou nome do abrigo:" );
        String idOrName = new Scanner( System.in ).nextLine();

        String               uri        = "http://localhost:8080/abrigos/" + idOrName + "/pets";
        HttpResponse<String> response   = client.fireGetRequest( uri );
        int                  statusCode = response.statusCode();

        if ( statusCode == 404 || statusCode == 500 )
        {
            System.out.println( "ID ou nome não cadastrado!" );
        }

        String    responseBody = response.body();
        JsonArray jsonArray    = JsonParser.parseString( responseBody ).getAsJsonArray();

        System.out.println( "Pets cadastrados:" );

        for ( JsonElement element : jsonArray )
        {
            JsonObject jsonObject = element.getAsJsonObject();

            long   id    = jsonObject.get( "id" )   .getAsLong();
            String type  = jsonObject.get( "tipo" ) .getAsString();
            String name  = jsonObject.get( "nome" ) .getAsString();
            String breed = jsonObject.get( "raca" ) .getAsString();
            int    age   = jsonObject.get( "idade" ).getAsInt();

            System.out.println( id + " - " + type + " - " + name + " - " + breed + " - " + age + " ano(s)" );
        }
    }

    public void importPetsFromShelter() throws IOException, InterruptedException
    {
        System.out.println( "Digite o id ou nome do abrigo:" );
        String idOrName = new Scanner( System.in ).nextLine();

        System.out.println( "Digite o nome do arquivo CSV:" );
        String fileName = new Scanner( System.in ).nextLine();

        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader( new FileReader( fileName ) );
        }

        catch ( IOException e )
        {
            System.out.println( "Erro ao carregar o arquivo: " + fileName );
        }

        String line;

        while ( ( line = reader.readLine() ) != null )
        {
            String[] fields = line.split( "," );
            String   type   = fields[ 0 ];
            String   name   = fields[ 1 ];
            String   breed  = fields[ 2 ];
            int      age    = Integer.parseInt( fields[ 3 ] );
            String   color  = fields[ 4 ];
            Float    weight = Float.parseFloat( fields[ 5 ] );

            Pet pet = new Pet( type, name, breed, age, color, weight );

            String               uri          = "http://localhost:8080/abrigos/" + idOrName + "/pets";
            HttpResponse<String> response     = client.firePostRequest( uri, pet );
            int                  statusCode   = response.statusCode();
            String               responseBody = response.body();

            if ( statusCode == 200 )
            {
                System.out.println( "Pet cadastrado com sucesso: " + name );
            }

            else if ( statusCode == 404 )
            {
                System.out.println( "Id ou nome do abrigo não encontado!" );
                break;
            }

            else if ( statusCode == 400 || statusCode == 500 )
            {
                System.out.println( "Erro ao cadastrar o pet: " + name );
                System.out.println( responseBody );
                break;
            }
        }

        reader.close();
    }
}
