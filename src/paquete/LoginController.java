/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import static usuario.FXMLIngUsuarioController.crearUsuario;

/**
 * FXML Controller class
 *
 * @author marce
 */
public class LoginController implements Initializable {


    @FXML
    private TextField txtRut;
    @FXML
    private TextField txtContrasena;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ingresar(ActionEvent event) {
        BigDecimal rut = new BigDecimal(txtRut.getText());

        //if (login(rut, txtContrasena.getText(), txtNombre.getText()) {
        //    System.out.println("Usuario ingresado");
        //}

        Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
        alert2.setTitle("Ingresar usuario");
        alert2.setHeaderText("Usuario");
        alert2.setContentText("El usuario ha sido ingresado");
        alert2.showAndWait();

        txtRut.clear();
        txtContrasena.clear();
    }

    private static String login(java.math.BigDecimal rut, java.lang.String contraseña) {
        org.tempuri.Service1 service = new org.tempuri.Service1();
        org.tempuri.IService1 port = service.getBasicHttpBindingIService1();
        return port.login(rut, contraseña);
    }

}
