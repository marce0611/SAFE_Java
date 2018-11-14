/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoevaluaciones;

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
import validador.FormValidador;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLEliTipoEvaluacionController implements Initializable {

    @FXML
    private TextField txtDesc;
    @FXML
    private Label lblDesc;

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
    void eliminarTipoEvaluacion(ActionEvent event) throws SQLException {

        boolean descripcion = FormValidador.textFieldNoVacio(txtDesc, lblDesc, "Campo requerido");

        if (descripcion) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ventana de confirmación");
            alert.setHeaderText("Confirmación");
            alert.setContentText("¿Está seguro que desea eliminar?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {

                eliminarTipoEvaluacion_1(txtDesc.getText());

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Eliminar tipo de evaluación");
                alert2.setHeaderText("Tipo de evaluación");
                alert2.setContentText("El tipo de evaluación ha sido eliminado");
                alert2.showAndWait();

                txtDesc.clear();

            } else {
                alert.close();
            }
        }

    }

    private static Boolean eliminarTipoEvaluacion_1(java.lang.String descevaluacion) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.eliminarTipoEvaluacion(descevaluacion);
    }

}
