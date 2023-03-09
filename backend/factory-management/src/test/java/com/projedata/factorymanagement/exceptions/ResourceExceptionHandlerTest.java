package com.projedata.factorymanagement.exceptions;

import com.projedata.factorymanagement.FactoryManagementApplication;
import com.projedata.factorymanagement.config.ContainersEnvironment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = FactoryManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ResourceExceptionHandlerTest extends ContainersEnvironment {

    public static final String OBJECT_ALREADY_EXISTS = "Object already exists";
    public static final String OBJECT_NOT_FOUND = "Object Not Found";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;

    @Test
    public void WhenObjectNotFoundExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFoundException(
                        new ObjectNotFoundException(OBJECT_NOT_FOUND),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJECT_NOT_FOUND, response.getBody().getError());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getBody().getStatus());
        assertNotEquals("/user/12345678", response.getBody().getPath());
    }

    @Test
    public void whenDataIntegrityViolationExceptionThenReturnAResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolationException(
                        new DataIntegrityViolationException(OBJECT_ALREADY_EXISTS),
                        new MockHttpServletRequest());

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(OBJECT_ALREADY_EXISTS, response.getBody().getError());
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getBody().getStatus());
        assertNotEquals("/user/12345678", response.getBody().getPath());
    }
}