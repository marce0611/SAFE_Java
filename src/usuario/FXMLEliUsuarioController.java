/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import validador.FormValidador;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLEliUsuarioController implements Initializable {

    @FXML
    private TextField txtRut;
    @FXML
    private Label lblRut;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private Button btnCancelar;

    @FXML
    void eliminarUsuario(ActionEvent event) {

        boolean r = FormValidador.textFieldNoVacio(txtRut, lblRut, "Campo requerido");

        if (r) {
            if (FormValidador.validarRut(txtRut.getText())) {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Ventana de confirmación");
                alert.setHeaderText("Confirmación");
                alert.setContentText("¿Está seguro que desea eliminar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    eliminarUsuario_1(txtRut.getText());

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Eliminar usuario");
                    alert2.setHeaderText("Usuario");
                    alert2.setContentText("El usuario ha sido eliminado");
                    alert2.showAndWait();

                    Stage stage2 = (Stage) lblRut.getScene().getWindow();
                    stage2.close();
                } else {
                    alert.close();
                }
            } else {
                lblRut.setText("Rut inválido");
            }

        }

    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();
    }

    private static Boolean eliminarUsuario_1(java.lang.String rut) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.eliminarUsuario(rut);
    }

}
