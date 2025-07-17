package br.com.alura.reflection;

import br.com.alura.Person;

public class ObjectToJsonTester
{
    public static void main( String[] args )
    {
        Person person = new Person( "Anderson", 25, "123456789" );

        ObjectToJson objectToJson = new ObjectToJson();
        System.out.println( objectToJson.transform( person ) );
    }
}
