/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import validador.FormValidador;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLModUsuarioController implements Initializable {

    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPaterno;
    @FXML
    private TextField txtMaterno;
    @FXML
    private TextField txtRut;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtEmail;
    @FXML
    private ComboBox<String> cbPerfil;
    @FXML
    private Pane pMedico;
    @FXML
    private TextField txtDisponibilidad;
    @FXML
    private TextField txtMedicoCorreo;
    @FXML
    private TextField txtMedicoTelefono;
    @FXML
    private Pane pTrabajador;
    @FXML
    private TextField txtEstado;
    @FXML
    private TextField txtTrabajadorCorreo;
    @FXML
    private TextField txtTrabajadorTelefono;
    @FXML
    private TextField txtContrasena;

    @FXML
    private Label lblRut;
    @FXML
    private Label lblContrasena;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblAmaterno;
    @FXML
    private Label lblApaterno;
    @FXML
    private Button btnIngresar;
    @FXML
    private ComboBox<?> cbContrato;
    @FXML
    private ComboBox<?> cbEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        /*cbContrato.setItems(contratosList);
        cbEmpresa.setItems(empresasList);
        cbPerfil.setItems(perfilList);

        for (EntPerfilUsuario er : retornarPerfilUsuarios().getEntPerfilUsuario()) {

            perfilList.add(er.getIdPerfil().getValue());

        }
         */
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();

    }

    @FXML
    private void modificarUsuario(ActionEvent event) {

        boolean rut = FormValidador.textFieldNoVacio(txtRut, lblRut, "Campo requerido");
        boolean nombre = FormValidador.textFieldNoVacio(txtNombre, lblNombre, "Campo requerido");
        boolean contrasena = FormValidador.textFieldNoVacio(txtContrasena, lblContrasena, "Campo requerido");
        boolean paterno = FormValidador.textFieldNoVacio(txtPaterno, lblApaterno, "Campo requerido");
        boolean materno = FormValidador.textFieldNoVacio(txtMaterno, lblAmaterno, "Campo requerido");
        boolean direccion = FormValidador.textFieldNoVacio(txtDireccion, lblDireccion, "Campo requerido");
        boolean telef = FormValidador.textFieldNoVacio(txtTelefono, lblTelefono, "Campo requerido");
        boolean email = FormValidador.textFieldNoVacio(txtEmail, lblEmail, "Campo requerido");

        if (rut && nombre && contrasena && paterno && materno && direccion && telef && email) {

            if (FormValidador.validarRut(txtRut.getText())) {
                if (FormValidador.validarCorreo(txtEmail.getText())) {
                    String r = txtRut.getText().replace(".", "");

                    BigDecimal telefono = new BigDecimal(txtTelefono.getText());

                    modificarUsuario_1(r, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText());

                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Modifcar usuario");
                    alert2.setHeaderText("Usuario");
                    alert2.setContentText("El usuario ha sido modificado");
                    alert2.showAndWait();

                    Stage stage2 = (Stage) lblRut.getScene().getWindow();
                    stage2.close();
                } else {
                    lblEmail.setText("Ingrese un correo válido.");
                }

            } else {
                lblRut.setText("Rut inválido");
            }
        }

    }

    @FXML
    private void perfilUsuario(ActionEvent event) {
        /*cbPerfil.setItems(perfilList);

        Integer perfil = new Integer(cbPerfil.getValue());

        if (perfil == 5) {
            pMedico.setDisable(false);
            pTrabajador.setDisable(true);
        } else if (perfil == 6) {
            pMedico.setDisable(true);
            pTrabajador.setDisable(false);
        } else {
            pMedico.setDisable(true);
            pTrabajador.setDisable(true);
        }*/
    }

    @FXML
    private void mostrarContratos(ActionEvent event) {
    }

    @FXML
    private void mostrarEmpresas(ActionEvent event) {
    }

    private static Boolean modificarUsuario_1(java.lang.String rut, java.lang.String contraseña, java.lang.String nombre, java.lang.String appaterno, java.lang.String apmaterno, java.lang.String direccion, java.math.BigDecimal telefono, java.lang.String email) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.modificarUsuario(rut, contraseña, nombre, appaterno, apmaterno, direccion, telefono, email);
    }

}
