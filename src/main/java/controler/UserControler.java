package controler;

import dao.CustomerDAO;
import domain.User;

public class UserControler {

    CustomerDAO customerDAO = new CustomerDAO();

    public boolean regUser(User usuario){
        boolean registroExistoso = customerDAO.register(usuario);
        return registroExistoso;
    }
    public boolean logUser(User usuario){
        boolean loginExistoso = customerDAO.logear(usuario);
        return loginExistoso;
    }

    public User buscarUsuarioLogeado(User usuario){
        return customerDAO.buscarUsuarioLogeado(usuario);
    }
    public boolean setDatosbancarios(User usuario){
        return customerDAO.updateDatosBancarios(usuario);
    }

    public boolean setPremium(User usuario){
        return customerDAO.setUserPremium(usuario);
    }
}
