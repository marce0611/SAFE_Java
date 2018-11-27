/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoevaluaciones;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
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
public class FXMLModTipoEvaluacionController implements Initializable {

    @FXML
    private Label lblId;
    @FXML
    private Label lblDesc;

    @FXML
    private TextField txtId;

    @FXML
    private Button btnCancelar;

    @FXML
    private TextField txtDescripcion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();
    }

    @FXML
    void modificarTipoEvaluacion(ActionEvent event) throws SQLException {

        boolean id1 = FormValidador.textFieldNoVacio(txtId, lblId, "Campo requerido");
        boolean descripcion = FormValidador.textFieldNoVacio(txtDescripcion, lblDesc, "Campo requerido");

        if (id1 && descripcion) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ventana de confirmación");
            alert.setHeaderText("Confirmación");
            alert.setContentText("¿Está seguro que desea modificar?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == ButtonType.OK) {

                BigDecimal id = new BigDecimal(txtId.getText());

                if (modificarTipoEvaluacion_1(id, txtDescripcion.getText())) {
                    System.out.println("Tipo de evaluacion modificado");
                };

                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                alert2.setTitle("Modificar tipo de evaluacion");
                alert2.setHeaderText("Tipo de evaluacion");
                alert2.setContentText("El tipo de evaluacion ha sido modificado");
                alert2.showAndWait();

                Stage stage2 = (Stage) txtDescripcion.getScene().getWindow();
                stage2.close();

            } else {
                alert.close();
            }
        }

    }

    private static Boolean modificarTipoEvaluacion_1(java.math.BigDecimal idTipoeval, java.lang.String descripcion) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.modificarTipoEvaluacion(idTipoeval, descripcion);
    }

}
