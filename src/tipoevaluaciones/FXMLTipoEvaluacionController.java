/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tipoevaluaciones;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.datacontract.schemas._2004._07.backsafe.ArrayOfEntTipoEvaluacion;
import org.datacontract.schemas._2004._07.backsafe.EntTipoEvaluacion;

/**
 * FXML Controller class
 *
 * @author Lucy
 */
public class FXMLTipoEvaluacionController implements Initializable {

    @FXML
    private TableView<TipoEvaluacion> table;
    @FXML
    private TableColumn<TipoEvaluacion, String> clId;
    @FXML
    private TableColumn<TipoEvaluacion, String> clDescripcion;
    @FXML
    private TableColumn<TipoEvaluacion, String> clEstado;

    private ObservableList<TipoEvaluacion> tipoEvaluacionList = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for (EntTipoEvaluacion er : retornarTiposEvaluacion().getEntTipoEvaluacion()) {

            tipoEvaluacionList.add(new TipoEvaluacion(er.getIdTipoeval().getValue(), er.getDescripcion().getValue(), er.getEstado().getValue()));

        }

        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        clEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        table.setItems(tipoEvaluacionList);
    }

    @FXML
    private Hyperlink lblInicio;

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
    void ingresarTipoEvaluacion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLIngTipoEvaluacion.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    void modificarTipoEvaluacion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLModTipoEvaluacion.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (IOException e) {
            System.out.println("ERROR");
        }

    }

    @FXML
    void eliminarTipoEvaluacion(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLEliTipoEvaluacion.fxml"));
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
        table.getItems().clear();

        for (EntTipoEvaluacion er : retornarTiposEvaluacion().getEntTipoEvaluacion()) {

            tipoEvaluacionList.add(new TipoEvaluacion(er.getIdTipoeval().getValue(), er.getDescripcion().getValue(), er.getEstado().getValue()));

        }

        clId.setCellValueFactory(new PropertyValueFactory<>("id"));
        clDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        clEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        table.setItems(tipoEvaluacionList);
    }

    private static ArrayOfEntTipoEvaluacion retornarTiposEvaluacion() {
        org.tempuri.ServicioAppEscritorio service = new org.tempuri.ServicioAppEscritorio();
        org.tempuri.IServicioAppEscritorio port = service.getBasicHttpBindingIServicioAppEscritorio();
        return port.retornarTiposEvaluacion();
    }



}
