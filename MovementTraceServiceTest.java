import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class YourClassTest {

    @Mock
    private RestTemplate restTemplate;

    private YourClass yourClass;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        yourClass = new YourClass(restTemplate);
    }

    @Test
    public void testYourMethod() {
        // Mock the token value
        String mockedToken = "mocked_token";
        when(yourClass.getToken()).thenReturn(mockedToken);

        // Prepare the mock response
        String expectedResponse = "data";
        ResponseEntity<String> responseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class)))
                .thenReturn(responseEntity);

        // Call the method under test
        String result = yourClass.yourMethod();

        // Verify the getToken() method was called
        verify(yourClass).getToken();

        // Verify the interaction with RestTemplate
        verify(restTemplate).exchange(anyString(), eq(HttpMethod.GET), any(HttpEntity.class), eq(String.class));

        // Verify the result
        Assertions.assertEquals("Bearer " + mockedToken, result);
    }
}
