package domain;

import domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteTest {
    private User usuario;

    @BeforeEach
    public void setUp() {
        usuario = new User(123, "Pablo", "Colmenero", "Martinez", "14/08/2003", "Espania", "email@email.com", "3333 4444 5555 6666", "12/25", 345, true, 127, "Contrasenia");
    }

    @Test
    public void testGetId() {
        assertEquals(123, usuario.getId());
    }

    @Test
    public void testSetId() {
        usuario.setId(321);
        assertEquals(321, usuario.getId());
    }
}