package controler;

import dao.CustomerDAO;
import domain.User;

public class UserControler {

    CustomerDAO customerDAO = new CustomerDAO();

    public boolean regUser(User usuario){
        // LLama al DAO para registrar al usuario
        customerDAO.register(usuario);
        if (customerDAO.register(usuario)){
            return true;
        }else{
            return false;
        }
    }

}
