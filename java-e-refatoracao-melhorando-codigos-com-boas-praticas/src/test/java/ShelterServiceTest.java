import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class ShelterServiceTest
{
    @Test
    public void mustCheckIfFireRequisitionWillBeCalled() throws IOException, InterruptedException
    {
        String userInput = String.format( "Test%spets.csv", System.lineSeparator() );
        ByteArrayInputStream bais = new ByteArrayInputStream( userInput.getBytes() );
        System.setIn( bais );

        when( client.dispararRequisicaoPost( anyString(), any() ) ).thenReturn( response );

        petService.importarPetsDoAbrigo();
        verify( client.dispararRequisicaoPost( anyString(), anyString() ), atLeast( 1 ) );
    }
}
