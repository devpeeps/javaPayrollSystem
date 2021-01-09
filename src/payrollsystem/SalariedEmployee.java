
// SalariedEmployee concrete class extends abstract class Employee.
//modified by Mary Tom
package payrollsystem;

import java.io.Serializable;

public class SalariedEmployee  implements Serializable {
   private  String firstName;
   private  String lastName;
   private  String socialSecurityNumber;
   private double weeklySalary;
   private static long serialVersionUID = 3456858L;
   // constructor
   public SalariedEmployee(String firstName, String lastName, 
      String socialSecurityNumber, double weeklySalary) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.socialSecurityNumber = socialSecurityNumber;

      if (weeklySalary < 0.0) {
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");
      }

      this.weeklySalary = weeklySalary;
   } 
   public SalariedEmployee(SalariedEmployee another){ //submit entry goes here
       this(another.getFirstName(),another.getLastName(),
               another.getSocialSecurityNumber(),another.getWeeklySalary());
           this.weeklySalary =    another.weeklySalary;
       
   }
   public SalariedEmployee(String firstName){
        this.firstName = firstName;
      this.lastName = "";
      this.socialSecurityNumber = "";
      this.weeklySalary = 0.0;
   }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }
   
   // set salary
   public void setWeeklySalary(double weeklySalary) {
      if (weeklySalary < 0.0) {
         throw new IllegalArgumentException(
            "Weekly salary must be >= 0.0");
      }

      this.weeklySalary = weeklySalary;
   } 

   // return salary
   public double getWeeklySalary() {return weeklySalary;}

   // calculate earnings; override abstract method earnings in Employee
 //  @Override                                                           
   public double earnings() {return getWeeklySalary();}                

   // return String representation of SalariedEmployee object  
   @Override                                                   
   public String toString() {                                  
      return String.format("salaried employee: %s %s  %s%n%s: $%,.2f",
         this.getFirstName(),this.getLastName(),this.getSocialSecurityNumber(),
              "weekly salary", getWeeklySalary());
   }                                                           
} 


