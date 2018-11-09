/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntUsuario;
import org.datacontract.schemas._2004._07.backsafe.EntUsuario;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLModEmpresaController implements Initializable {

    @FXML
    private Button btnModificar;
    @FXML
    private ComboBox<String> cbId;

    /**
     * Initializes the controller class.
     */
    private ObservableList<String> usuarioList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cbId.setItems(usuarioList);

        for (EntUsuario er : retornarUsuarios().getEntUsuario()) {

            usuarioList.add(er.getIdUsuario().getValue());

        }
    }

    private TextField txtId;

    @FXML
    private TextField txtRut;

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnCancelar;

    @FXML
    void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();

    }

    @FXML
    void modificarEmpresa(ActionEvent event) throws SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ventana de confirmación");
        alert.setHeaderText("Confirmación");
        alert.setContentText("¿Está seguro que desea modificar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            BigDecimal id = new BigDecimal(cbId.getValue());

            modificarEmpresa_1(id, txtNombre.getText(), txtRut.getText());

            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
            alert2.setTitle("Modificar empresa");
            alert2.setHeaderText("Empresa");
            alert2.setContentText("La empresa ha sido modificada");
            alert2.showAndWait();

            txtId.setText("");
            txtNombre.setText("");
            txtRut.setText("");

        } else {
            alert.close();
        }

    }

    private static ArrayOfEntUsuario retornarUsuarios() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarUsuarios();
    }

    private static Boolean modificarEmpresa_1(java.math.BigDecimal usuarioId, java.lang.String nomEmpresa, java.lang.String runEmpresa) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.modificarEmpresa(usuarioId, nomEmpresa, runEmpresa);
    }

    @FXML
    private void mostrarId(ActionEvent event) {

        cbId.setItems(usuarioList);
    }

}
