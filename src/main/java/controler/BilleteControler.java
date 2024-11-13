package controler;

import dao.BilleteDAO;
import domain.Billete;

public class BilleteControler {

    BilleteDAO billeteDAO = new BilleteDAO();

    public boolean saveTicket(Billete billete){
        boolean BilleteCompradoConExito = billeteDAO.save(billete);
        return BilleteCompradoConExito;
    }
}