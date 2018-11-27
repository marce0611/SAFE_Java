/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntContrato;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntEmpresa;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntPerfilUsuario;
import org.datacontract.schemas._2004._07.backsafe.EntContrato;
import org.datacontract.schemas._2004._07.backsafe.EntEmpresa;
import org.datacontract.schemas._2004._07.backsafe.EntPerfilUsuario;
import validador.FormValidador;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLIngUsuarioController implements Initializable {

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
    private TextField txtTrabajadorCorreo;
    @FXML
    private TextField txtTrabajadorTelefono;
    @FXML
    private ComboBox<String> cbContrato;
    @FXML
    private TextField txtContrasena;
    @FXML
    private ComboBox<String> cbIdEmpresa;

    private ObservableList<String> contratosList = FXCollections.observableArrayList();
    private ObservableList<String> empresasList = FXCollections.observableArrayList();
    private ObservableList<String> perfilList = FXCollections.observableArrayList();

    @FXML
    private Label lblDisponibilidad;
    @FXML
    private Label lblTelefonoMedico;
    @FXML
    private Label lblTelefonoTrabajador;
    @FXML
    private Label lblRut;
    @FXML
    private Label lblContrasena;
    @FXML
    private Label lblNombre;
    @FXML
    private Label lblPaterno;
    @FXML
    private Label lblDireccion;
    @FXML
    private Label lblTelefono;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblMaterno;
    @FXML
    private Label lblCorreoTrabajador;
    @FXML
    private Label lblCorreoMedico;
    @FXML
    private ComboBox<String> cbEstadoRiesgo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        cbPerfil.setItems(perfilList);

        for (EntPerfilUsuario er : retornarPerfilUsuarios().getEntPerfilUsuario()) {

            perfilList.add(er.getIdPerfil().getValue());

        }

        cbPerfil.setValue("Seleccione perfil");

        cbContrato.setItems(contratosList);

        for (EntContrato er : retornarContratos().getEntContrato()) {

            contratosList.add(er.getId().getValue());

        }

        cbIdEmpresa.setItems(empresasList);

        for (EntEmpresa er : retornarEmpresas().getEntEmpresa()) {

            empresasList.add(er.getIdEmpresa().getValue());

        }

        ObservableList<String> estadoList
                = FXCollections.observableArrayList(
                        "A",
                        "M",
                        "B"
                );

        cbEstadoRiesgo.setItems(estadoList);

    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage2 = (Stage) btnCancelar.getScene().getWindow();
        stage2.close();
    }

    @FXML
    private void ingresarUsuario(ActionEvent event) {

        //Usuario
        boolean r = FormValidador.textFieldNoVacio(txtRut, lblRut, "Campo requerido");
        boolean nombre = FormValidador.textFieldNoVacio(txtNombre, lblNombre, "Campo requerido");
        boolean contrasena = FormValidador.textFieldNoVacio(txtContrasena, lblContrasena, "Campo requerido");
        boolean paterno = FormValidador.textFieldNoVacio(txtPaterno, lblPaterno, "Campo requerido");
        boolean materno = FormValidador.textFieldNoVacio(txtMaterno, lblMaterno, "Campo requerido");
        boolean direccion = FormValidador.textFieldNoVacio(txtDireccion, lblDireccion, "Campo requerido");
        boolean telef = FormValidador.textFieldNoVacio(txtTelefono, lblTelefono, "Campo requerido");
        boolean email = FormValidador.textFieldNoVacio(txtEmail, lblEmail, "Campo requerido");

        switch (cbPerfil.getValue()) {
            case "5":
                boolean disponibilidad = FormValidador.textFieldNoVacio(txtDisponibilidad, lblDisponibilidad, "Campo requerido");
                boolean correoMedico = FormValidador.textFieldNoVacio(txtMedicoCorreo, lblCorreoMedico, "Campo requerido");
                boolean telefonoMedico = FormValidador.textFieldNoVacio(txtMedicoTelefono, lblTelefonoMedico, "Campo requerido");

                if (r && contrasena && nombre && paterno && materno && direccion && telef && email && disponibilidad && correoMedico && telefonoMedico) {
                    if (FormValidador.validarRut(txtRut.getText())) {
                        if (FormValidador.validarCorreo(txtEmail.getText())) {
                            if (FormValidador.validarCorreo(txtMedicoCorreo.getText())) {
                                BigDecimal telefono = new BigDecimal(txtTelefono.getText());
                                BigDecimal perfil = new BigDecimal(cbPerfil.getValue());
                                BigDecimal empresa = new BigDecimal(cbIdEmpresa.getValue());
                                BigDecimal telMedico = new BigDecimal(txtMedicoTelefono.getText());

                                String medicoRut = txtRut.getText().replace(".", "");

                                crearUsuarioMedico(medicoRut, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText(), perfil, empresa, txtDisponibilidad.getText(), txtMedicoCorreo.getText(), telMedico);

                                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                                alert1.setTitle("Ingresar usuario");
                                alert1.setHeaderText("Usuario médico");
                                alert1.setContentText("El usuario médico ha sido ingresado");
                                alert1.showAndWait();

                                Stage stage2 = (Stage) lblRut.getScene().getWindow();
                                stage2.close();

                            } else {
                                lblCorreoMedico.setText("Ingrese un correo válido.");
                            }

                        } else {
                            lblEmail.setText("Ingrese un correo válido.");
                        }

                    } else {
                        lblRut.setText("Rut inválido");
                    }

                }

                break;

            case "6":
                boolean correoTrabajador = FormValidador.textFieldNoVacio(txtTrabajadorCorreo, lblCorreoTrabajador, "Campo requerido");
                boolean telefonoTrabajador = FormValidador.textFieldNoVacio(txtTrabajadorTelefono, lblTelefonoTrabajador, "Campo requerido");

                if (r && contrasena && nombre && paterno && materno && direccion && telef && email && correoTrabajador && telefonoTrabajador) {
                    if (FormValidador.validarRut(txtRut.getText())) {
                        if (FormValidador.validarCorreo(txtEmail.getText())) {
                            if (FormValidador.validarCorreo(txtTrabajadorCorreo.getText())) {
                                BigDecimal telefono = new BigDecimal(txtTelefono.getText());
                                BigDecimal perfil = new BigDecimal(cbPerfil.getValue());
                                BigDecimal empresa = new BigDecimal(cbIdEmpresa.getValue());
                                BigDecimal contrato = new BigDecimal(cbContrato.getValue());
                                BigDecimal telTrabajador = new BigDecimal(txtTrabajadorTelefono.getText());

                                String trabajadorRut = txtRut.getText().replace(".", "");

                                crearUsuarioTrabajador(trabajadorRut, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText(), perfil, empresa, txtTrabajadorCorreo.getText(), telTrabajador, cbEstadoRiesgo.getValue(), contrato);

                                Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                                alert2.setTitle("Ingresar usuario");
                                alert2.setHeaderText("Usuario trabajador");
                                alert2.setContentText("El usuario trabajador ha sido ingresado");
                                alert2.showAndWait();

                                Stage stage2 = (Stage) lblRut.getScene().getWindow();
                                stage2.close();

                            } else {
                                lblCorreoTrabajador.setText("Ingrese un correo válido.");
                            }

                        } else {
                            lblEmail.setText("Ingrese un correo válido.");
                        }

                    } else {
                        lblRut.setText("Rut inválido");
                    }

                }

                break;

            default:

                if (r && contrasena && nombre && paterno && materno && direccion && telef && email) {
                    if (FormValidador.validarRut(txtRut.getText())) {
                        if (FormValidador.validarCorreo(txtEmail.getText())) {
                            BigDecimal telefono = new BigDecimal(txtTelefono.getText());
                            BigDecimal perfil = new BigDecimal(cbPerfil.getValue());
                            BigDecimal empresa = new BigDecimal(cbIdEmpresa.getValue());

                            String userRut = txtRut.getText().replace(".", "");

                            crearUsuario(userRut, txtContrasena.getText(), txtNombre.getText(), txtPaterno.getText(), txtMaterno.getText(), txtDireccion.getText(), telefono, txtEmail.getText(), perfil, empresa);

                            Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                            alert3.setTitle("Ingresar usuario");
                            alert3.setHeaderText("Usuario");
                            alert3.setContentText("El usuario ha sido ingresado");
                            alert3.showAndWait();

                            Stage stage2 = (Stage) lblRut.getScene().getWindow();
                            stage2.close();
                        } else {
                            lblEmail.setText("Ingrese un correo válido.");
                        }
                    } else {
                        lblRut.setText("Rut inválido");
                    }

                }
                break;
        }

    }

    @FXML
    private void perfilUsuario(ActionEvent event) {

        cbPerfil.setItems(perfilList);

        switch (cbPerfil.getValue()) {
            case "5":
                pMedico.setDisable(false);
                pTrabajador.setDisable(true);
                break;
            case "6":
                pMedico.setDisable(true);
                pTrabajador.setDisable(false);
                break;
            default:
                pMedico.setDisable(true);
                pTrabajador.setDisable(true);
                break;
        }

    }

    @FXML
    private void mostrarContratos(ActionEvent event) {
        cbContrato.setItems(contratosList);
    }

    @FXML
    private void mostrarEmpresas(ActionEvent event) {

        cbIdEmpresa.setItems(empresasList);
    }

    private static ArrayOfEntPerfilUsuario retornarPerfilUsuarios() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarPerfilUsuarios();
    }

    private static ArrayOfEntEmpresa retornarEmpresas() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarEmpresas();
    }

    private static ArrayOfEntContrato retornarContratos() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarContratos();
    }

    private static Boolean crearUsuario(java.lang.String rut, java.lang.String contraseña, java.lang.String nombre, java.lang.String appaterno, java.lang.String apmaterno, java.lang.String direccion, java.math.BigDecimal telefono, java.lang.String email, java.math.BigDecimal idPerfil, java.math.BigDecimal idEmpresa) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.crearUsuario(rut, contraseña, nombre, appaterno, apmaterno, direccion, telefono, email, idPerfil, idEmpresa);
    }

    private static Boolean crearUsuarioTrabajador(java.lang.String rut, java.lang.String contraseña, java.lang.String nombre, java.lang.String appaterno, java.lang.String apmaterno, java.lang.String direccion, java.math.BigDecimal telefono, java.lang.String email, java.math.BigDecimal idPerfil, java.math.BigDecimal idEmpresa, java.lang.String mailPrivado, java.math.BigDecimal telPrivado, java.lang.String estadoRiesgo, java.math.BigDecimal contratoId) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.crearUsuarioTrabajador(rut, contraseña, nombre, appaterno, apmaterno, direccion, telefono, email, idPerfil, idEmpresa, mailPrivado, telPrivado, estadoRiesgo, contratoId);
    }

    private static Boolean crearUsuarioMedico(java.lang.String rut, java.lang.String contraseña, java.lang.String nombre, java.lang.String appaterno, java.lang.String apmaterno, java.lang.String direccion, java.math.BigDecimal telefono, java.lang.String email, java.math.BigDecimal idPerfil, java.math.BigDecimal idEmpresa, java.lang.String disponibilidad, java.lang.String mailPrivado, java.math.BigDecimal telefonoPriv) {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.crearUsuarioMedico(rut, contraseña, nombre, appaterno, apmaterno, direccion, telefono, email, idPerfil, idEmpresa, disponibilidad, mailPrivado, telefonoPriv);
    }

}
