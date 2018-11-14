/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntEmpresa;
import org.datacontract.schemas._2004._07.backsafe.EntEmpresa;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLEmpresaController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private Hyperlink lblInicio;
    @FXML
    private TableView<Empresa> tableView;
    @FXML
    private TableColumn<Empresa, String> cId;
    @FXML
    private TableColumn<Empresa, String> cNombre;
    @FXML
    private TableColumn<Empresa, String> cRut;
    @FXML
    private TableColumn<Empresa, String> cDireccion;
    @FXML
    private TableColumn<Empresa, String> cTelefono;
    @FXML
    private TableColumn<Empresa, String> cCorreo;
    @FXML
    private TableColumn<Empresa, String> cEstado;

    private ObservableList<Empresa> empresasList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (EntEmpresa er : retornarEmpresas().getEntEmpresa()) {

            empresasList.add(new Empresa(er.getIdEmpresa().getValue(), er.getNomEmpresa().getValue(), er.getRunEmpresa().getValue(), er.getDirEmpresa().getValue(), er.getTelEmpresa().getValue(), er.getCorEmpresa().getValue(), er.getEstEmpresa().getValue()));

        }

        cId.setCellValueFactory(new PropertyValueFactory<>("empresa_id"));
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_empresa"));
        cRut.setCellValueFactory(new PropertyValueFactory<>("rut_empresa"));
        cDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion_empresa"));
        cTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono_empresa"));
        cCorreo.setCellValueFactory(new PropertyValueFactory<>("correo_empresa"));
        cEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tableView.setItems(empresasList);

    }

    @FXML
    void inicio(ActionEvent event) {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/paquete/FXMLDocument.fxml"));
        try {
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

            Stage stage2 = (Stage) lblInicio.getScene().getWindow();
            stage2.close();

        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    void ingresarEmpresa(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLIngEmpresa.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    void modificarEmpresa(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLModEmpresa.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    void eliminarEmpresa(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEliEmpresa.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    private void cargarDatos(ActionEvent event) {
        tableView.getItems().clear();

        for (EntEmpresa er : retornarEmpresas().getEntEmpresa()) {

            empresasList.add(new Empresa(er.getIdEmpresa().getValue(), er.getNomEmpresa().getValue(), er.getRunEmpresa().getValue(), er.getDirEmpresa().getValue(), er.getTelEmpresa().getValue(), er.getCorEmpresa().getValue(), er.getEstEmpresa().getValue()));

        }

        cId.setCellValueFactory(new PropertyValueFactory<>("empresa_id"));
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_empresa"));
        cRut.setCellValueFactory(new PropertyValueFactory<>("rut_empresa"));
        cDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion_empresa"));
        cTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono_empresa"));
        cCorreo.setCellValueFactory(new PropertyValueFactory<>("correo_empresa"));
        cEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        tableView.setItems(empresasList);
    }

    private static ArrayOfEntEmpresa retornarEmpresas() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarEmpresas();
    }

}
