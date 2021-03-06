/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.math.BigDecimal;
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
public class FXMLModEmpresaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField txtCorreo;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtRut;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblRut;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblCorreo;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblId;

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
    void modificarEmpresa(ActionEvent event) throws SQLException {

        boolean id1 = FormValidador.textFieldNoVacio(txtId, lblId, "Campo requerido");
        boolean nombre = FormValidador.textFieldNoVacio(txtNombre, lblNombre, "Campo requerido");
        boolean rut = FormValidador.textFieldNoVacio(txtRut, lblRut, "Campo requerido");
        boolean direccion = FormValidador.textFieldNoVacio(txtDireccion, lblDireccion, "Campo requerido");
        boolean telef = FormValidador.textFieldNoVacio(txtTelefono, lblTelefono, "Campo requerido");
        boolean correo = FormValidador.textFieldNoVacio(txtCorreo, lblCorreo, "Campo requerido");

        if (id1 && nombre && rut && direccion && telef && correo) {
            if (FormValidador.validarRut(txtRut.getText())) {
                if (FormValidador.validarCorreo(txtCorreo.getText())) {
                    if (FormValidador.validarCorreo(txtCorreo.getText())) {
                        String r = txtRut.getText().replace(".", "");

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Ventana de confirmación");
                        alert.setHeaderText("Confirmación");
                        alert.setContentText("¿Está seguro que desea modificar?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {

                            BigDecimal id = new BigDecimal(txtId.getText());
                            BigDecimal telefono = new BigDecimal(txtTelefono.getText());

                            modificarEmpresa_1(id, txtNombre.getText(), r, txtDireccion.getText(), telefono, txtCorreo.getText());

                            Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                            alert2.setTitle("Modificar empresa");
                            alert2.setHeaderText("Empresa");
                            alert2.setContentText("La empresa ha sido modificada");
                            alert2.showAndWait();

                        } else {
                            alert.close();
                        }

                        Stage stage2 = (Stage) lblRut.getScene().getWindow();
                        stage2.close();
                    } else {
                        lblCorreo.setText("Ingrese un correo válido.");
                    }

                } else {
                    lblCorreo.setText("Ingrese un correo válido.");
                }

            } else {
                lblRut.setText("Rut inválido");
            }

        }

    }

    private static Boolean modificarEmpresa_1(java.math.BigDecimal idEmpresa, java.lang.String nomEmpresa, java.lang.String runEmpresa, java.lang.String dirEmpresa, java.math.BigDecimal telEmpresa, java.lang.String corEmpresa) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.modificarEmpresa(idEmpresa, nomEmpresa, runEmpresa, dirEmpresa, telEmpresa, corEmpresa);
    }

}
