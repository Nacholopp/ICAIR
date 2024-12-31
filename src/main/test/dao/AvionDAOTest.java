package dao;

import domain.Avion;
import dao.AvionDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AvionDAOTest {

    @Mock
    private AvionDAO avionDAO;

    @InjectMocks
    private AvionDAOTest avionDAOTest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        avionDAO = mock(AvionDAO.class);

    }

    @Test
    public void testGetUsuario() {
        Avion avionTest = new Avion(787, "Madrid", "Bucarest", "15/10/2024", "06:30", "03:00", 300, 18);

        when (avionDAO.buscarVueloComprado(avionTest)).thenReturn(avionTest);

        Avion avion = avionDAO.buscarVueloComprado(avionTest);

        assertNotNull(avion);
        assertEquals(787, avion.getId());
        assertEquals("Madrid", avion.getOrigen());

    }

}
