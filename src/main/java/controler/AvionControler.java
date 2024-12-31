package controler;

import dao.AvionDAO;
import domain.Avion;
import domain.User;

import java.util.List;

public class AvionControler {

    AvionDAO avionDAO = new AvionDAO();

    public boolean findAvion(Avion avion){
        boolean busquedaExistoso = avionDAO.find(avion);
        return busquedaExistoso;
    }
    public boolean findAvionID(Avion avion){
        boolean busquedaExistoso = avionDAO.findID(avion);
        return busquedaExistoso;
    }
    public boolean removeSit(Avion avion){
        boolean reservaExitosa = avionDAO.reserveSit(avion);
        return reservaExitosa;
    }
    public List<Avion> buscarVuelos(Avion avion) {
        return avionDAO.buscarVuelos(avion);
    }
    public List<Avion> buscarVuelosID(Avion avion) {
        return avionDAO.buscarVuelosID(avion);
    }
    public Avion buscarVueloComprado(Avion avion){
        return avionDAO.buscarVueloComprado(avion);
    }
}
