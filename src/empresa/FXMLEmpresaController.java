/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empresa;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import paquete.Conexion;

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

    private ObservableList<Empresa> empresasList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarBD("safe_db");

            ResultSet rs = con.createStatement().executeQuery("select * from empresa");

            while (rs.next()) {

                empresasList.add(new Empresa(rs.getString("usuario_id"), rs.getString("nombre_empresa"), rs.getString("run_empresa")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        cId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_empresa"));
        cRut.setCellValueFactory(new PropertyValueFactory<>("run_empresa"));

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
        
        try {
            Conexion conexion = new Conexion();
            Connection con = conexion.conectarBD("safe_db");

            ResultSet rs = con.createStatement().executeQuery("select * from empresa");

            while (rs.next()) {

                empresasList.add(new Empresa(rs.getString("usuario_id"), rs.getString("nombre_empresa"), rs.getString("run_empresa")));

            }

        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }

        cId.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));
        cNombre.setCellValueFactory(new PropertyValueFactory<>("nombre_empresa"));
        cRut.setCellValueFactory(new PropertyValueFactory<>("run_empresa"));

        tableView.setItems(empresasList);
    }

}
