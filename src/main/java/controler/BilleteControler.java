package controler;

import dao.BilleteDAO;
import domain.Avion;
import domain.Billete;

import java.util.List;

public class BilleteControler {

    BilleteDAO billeteDAO = new BilleteDAO();

    public boolean saveTicket(Billete billete){
        boolean BilleteCompradoConExito = billeteDAO.save(billete);
        return BilleteCompradoConExito;
    }
    public List<Billete> buscarBilletes(Billete billete) {
        return billeteDAO.buscarBilletes(billete);
    }
}