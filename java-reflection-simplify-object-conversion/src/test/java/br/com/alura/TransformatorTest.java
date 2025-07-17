package br.com.alura;

import br.com.alura.reflection.Transformator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

public class TransformatorTest
{
    Person  person   = new Person( "João", 32, "04333958210" );
    Address address  = new Address( "Lajeado", 37 );

    @Test
    public void shouldTransform() throws ClassNotFoundException, InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException
    {
        Transformator transformator = new Transformator();
        PersonDTO     transformated = transformator.transform( person );

        Assertions.assertInstanceOf( PersonDTO.class, transformated );

        Assertions.assertEquals( "João", transformated.getName() );
        Assertions.assertEquals( 32, transformated.getAge() );
        Assertions.assertEquals( "04333958210", transformated.getCpf() );
    }

    @Test
    public void shouldNotTransform()
    {
        Assertions.assertThrows( ClassNotFoundException.class, () ->
        {
            Transformator transformator = new Transformator();
            transformator.transform( address );
        } );
    }
}
