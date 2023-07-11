import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class AuthenticationTest {

    @Mock
    private RestTemplate restTemplate;

    private Authentication authentication;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        authentication = new Authentication(restTemplate);
    }

    @Test
    public void testAuthenticate() {
        // Prepare the mock response
        TokenResponse expectedResponse = new TokenResponse("testToken");
        ResponseEntity<TokenResponse> responseEntity = ResponseEntity.ok(expectedResponse);
        when(restTemplate.exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(TokenResponse.class)))
                .thenReturn(responseEntity);

        // Call the method under test
        ResponseEntity<TokenResponse> actualResponseEntity = authentication.authenticate();

        // Verify the interaction with RestTemplate
        verify(restTemplate).exchange(anyString(), any(HttpMethod.class), any(HttpEntity.class), eq(TokenResponse.class));

        // Verify the result
        Assertions.assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
        Assertions.assertEquals(expectedResponse, actualResponseEntity.getBody());
    }
}
