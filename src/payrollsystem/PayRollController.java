/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package payrollsystem;

import java.io.IOException;
import java.util.LinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author chris
 */
public class PayRollController {//implements Runnable {

    /**
     * Initializes the controller class.
     */
      @FXML
    private Label lblSocNumber;

    @FXML
    private Label lblLast;

    @FXML
    private Label lblSalary;
    
    @FXML
    private TextField txtFldFirstName;
    @FXML
    private TextField txtFldLastName;

    @FXML
    private TextField txtFldSocNumber;

    @FXML
    private TextField txtFldSalary;

    @FXML
    private Button btnAddEmployee;

    @FXML
    private Label lblFirstName;

    @FXML
    private Button btnSubmit;
    @FXML
    private TextArea txtAreaSearchResult;

    @FXML
    private TextField txtFldSearchFirstName;
    @FXML
    private Button btnSearchEmployee;
      
    private LinkedList<SalariedEmployee> employees;
    private LinkedList<SalariedEmployee> searchResult;
   
    String firstname,
            lastName,
            socialSecurityNumber;
     double salary;
            EmployeeClient  clientprocess ;  
    /**
     *
     *
     * @throws IOException
     */
    
    public void initialize() {
        
        employees = new LinkedList<>();
        searchResult = new LinkedList<>();
        
        try {
//        while (true)
           clientprocess = new EmployeeClient ();
     //     dataFromprovider =  clientprocess.getResidenceNeeded();
        
        }catch(IOException io){
            System.out.println(io.getMessage());
        }
        
    } 
    
    @FXML
    void AddEmployeeClicked(ActionEvent event) {
         //set methods throw Exception So this should be in a try catch blcok.
        firstname = txtFldFirstName.getText();
        lastName = txtFldLastName.getText();
        socialSecurityNumber = txtFldSocNumber.getText();
        salary = Double.parseDouble(txtFldSalary.getText());
        if (firstname.equalsIgnoreCase("finished"))
            employees.add(new SalariedEmployee("finished"));
        else
            employees.add(new SalariedEmployee(firstname,lastName,socialSecurityNumber,salary));
        
        System.out.println (this.employees);
        
    }
     @FXML
    void submitEntriesButtonClicked(ActionEvent event) {
      clientprocess.createClientThread(this.employees);
    }
     @FXML
    void searchEmployeeButtonClicked(ActionEvent event) {
          String searchName = txtFldSearchFirstName.getText();
           if (searchName!= null){
           clientprocess.createProviderThread(searchName);//,dataFromprovider);
           searchResult = clientprocess.getEmployees ();
           if (this.searchResult!=null){
              System.out.println(searchResult);
         //display the values to the TextArea.
              this.searchResult.forEach((e) -> {
                txtAreaSearchResult.setText(((SalariedEmployee)e).toString());
            });
          }
       }
    }
//    @FXML
//    void ViewEntryButtonClicked(ActionEvent event) {
//        
//        String criterion = txtCriterion.getText();
//        if (criterion!= null){
//           clientprocess.createProviderThread(criterion);//,dataFromprovider);
//        }
//       searchResult = clientprocess.getResidenceNeeded();
//        if (this.searchResult!=null){
//           // System.out.println(searchResult);
//            this.searchResult.forEach((rn) -> {
//                txtAreaDisplay.setText(((ResidenceNeedful)rn).toString());
//            });
//        }
//    }
    
      
    
}
