/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paquete;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.datacontract.schemas._2004._07.backsafe.EntUsuario;

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
    @FXML
    private Button btnIngresar;
    @FXML
    private Label lblError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ingresar(ActionEvent event) {
        EntUsuario us = login(txtRut.getText(), txtContrasena.getText());
        try {
            if (us != null) {
                int perfil = Integer.parseInt(us.getIdPerfil().getValue());
                if (perfil == 1) {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
                    Parent root1 = (Parent) fxmlLoader.load();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root1));
                    stage.show();

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Ingreso exitoso");
                    alert2.setHeaderText("Ingreso exitoso");
                    alert2.setContentText("Bienvenido de nuevo");
                    alert2.showAndWait();

                    Stage stage2 = (Stage) txtRut.getScene().getWindow();
                    stage2.close();
                } else {
                    txtRut.clear();
                    txtContrasena.clear();
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Error al ingresar");
                    alert2.setHeaderText("Solo administradores pueden entrar");
                    alert2.setContentText("Intente nuevamente");
                    alert2.showAndWait();
                }
            } else {

                lblError.setText("Error al ingresar, intente nuevamente.");
                txtRut.clear();
                txtContrasena.clear();
            }
        } catch (IOException e) {
            System.out.println("ERROR");
        }
    }

    private static EntUsuario login(java.lang.String rut, java.lang.String contraseña) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.login(rut, contraseña);
    }

}
