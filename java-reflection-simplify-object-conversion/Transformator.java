import java.lang.reflect.Field;
import java.util.Arrays;

public class Transformator
{
    public <I, O> O transform( I input ) throws Exception
    {
        Class<?> source = input.getClass();
        Class<?> target = Class.forName( source.getName() + "DTO" );

        Field[] sourceFields = source.getDeclaredFields();
        Field[] targetFields = target.getDeclaredFields();

        O targetClass = (O) target.getDeclaredConstructor().newInstance();

        Arrays.stream( sourceFields )
              .forEach( sourceField -> Arrays.stream( targetFields )
                                             .forEach( targetField ->
                                             {
                                                 try
                                                 {
                                                     validate( sourceField, targetField );

                                                     targetField.set( targetClass, sourceField.get( input ) );
                                                 }

                                                 catch ( Exception e )
                                                 {
                                                     throw new RuntimeException();
                                                 }
                                             } ) );
        return targetClass;
    }

    private void validate( Field sourceField, Field targetField )
    {
        if ( sourceField.getName().equals( targetField.getName() ) &&
             sourceField.getType().equals( targetField.getType() ) )
        {
            sourceField.setAccessible( true );
            targetField.setAccessible( true );
        }
    }
}