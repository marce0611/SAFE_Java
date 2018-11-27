/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.naming.NamingException;
import validador.FormValidador;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLEliEmpresaController implements Initializable {

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
    void cancelar(ActionEvent event) {

        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();

    }

    @FXML
    void eliminarEmpresa(ActionEvent event) throws SQLException, NamingException {

        boolean rut = FormValidador.textFieldNoVacio(txtRut, lblRut, "Campo requerido");

        if (rut) {
            if (FormValidador.validarRut(txtRut.getText())) {
                String r = txtRut.getText().replace(".", "");

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Ventana de confirmación");
                alert.setHeaderText("Confirmación");
                alert.setContentText("¿Está seguro que desea eliminar?");
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK) {

                    eliminarEmpresa_1(r);

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Eliminar empresa");
                    alert2.setHeaderText("Empresa");
                    alert2.setContentText("La empresa ha sido eliminada");
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

    private static Boolean eliminarEmpresa_1(java.lang.String rutempresa) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.eliminarEmpresa(rutempresa);
    }

}
