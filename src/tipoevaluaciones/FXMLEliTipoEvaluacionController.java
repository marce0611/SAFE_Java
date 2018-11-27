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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntTipoEvaluacion;
import org.datacontract.schemas._2004._07.backsafe.EntTipoEvaluacion;
import validador.FormValidador;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLEliTipoEvaluacionController implements Initializable {

    @FXML
    private Label lblDesc;
    @FXML
    private Button btnEliminar;
    @FXML
    private ComboBox<String> cbDescripcion;

    private ObservableList<String> tipoEvaluacionList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cbDescripcion.setItems(tipoEvaluacionList);

        for (EntTipoEvaluacion er : retornarTiposEvaluacion().getEntTipoEvaluacion()) {

            tipoEvaluacionList.add(er.getDescripcion().getValue());

        }
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ventana de confirmación");
        alert.setHeaderText("Confirmación");
        alert.setContentText("¿Está seguro que desea eliminar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            eliminarTipoEvaluacion_1(cbDescripcion.getValue());

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Eliminar tipo de evaluación");
            alert2.setHeaderText("Tipo de evaluación");
            alert2.setContentText("El tipo de evaluación ha sido eliminado");
            alert2.showAndWait();

            Stage stage2 = (Stage) cbDescripcion.getScene().getWindow();
            stage2.close();

        } else {
            alert.close();
        }

    }

    private static Boolean eliminarTipoEvaluacion_1(java.lang.String descevaluacion) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.eliminarTipoEvaluacion(descevaluacion);
    }

    private static ArrayOfEntTipoEvaluacion retornarTiposEvaluacion() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarTiposEvaluacion();
    }

}
