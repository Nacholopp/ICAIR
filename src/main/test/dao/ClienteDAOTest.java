package dao;


import domain.User;
import dao.CustomerDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteDAOTest {

    @Mock
    private CustomerDAO customerDAO;

    @InjectMocks
    private ClienteDAOTest clienteDAOTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerDAO = mock(CustomerDAO.class);

    }

    @Test
    public void testGetUsuario() {
        User usuarioTest = new User(123, "Pablo", "Colmenero", "Martinez", "14/08/2003", "Espania", "email@email.com", "3333 4444 5555 6666", "12/25", 345, true, 127, "Contrasenia");

        when (customerDAO.buscarUsuarioLogeado(usuarioTest)).thenReturn(usuarioTest);

        User usuario = customerDAO.buscarUsuarioLogeado(usuarioTest);

        assertNotNull(usuario);
        assertEquals(123, usuario.getId());
        assertEquals("Pablo", usuario.getNombre());

    }
}