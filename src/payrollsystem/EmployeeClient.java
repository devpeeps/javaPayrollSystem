package payrollsystem;/* Author Mary TomThis program demonstrates the use of java rmi and registry service.The client looks up for a book object registered by the server.The client can invoke the methods from  the BookInterface available to the client.The tcp connections are only required if other messages are to be exchanged between client server other than the using the objects from the registry. */import java.net.*;import java.io.*;import java.rmi.NotBoundException;import java.rmi.registry.LocateRegistry;import java.rmi.registry.Registry;import java.util.LinkedList;import java.util.logging.Level;import java.util.logging.Logger;public class EmployeeClient {    private Socket s = null;    private static LinkedList<SalariedEmployee> employees;// = new LinkedList<>();;    ServerConnection conn;    ProviderConnection provider;    Thread providerThread;    public EmployeeClient() throws IOException {        this.employees = new LinkedList<>();    }    public void createClientThread(LinkedList<SalariedEmployee> employees) {        //starts the connection thread        conn = new ServerConnection(employees);        employees.clear();    }    @SuppressWarnings("empty-statement")    public LinkedList<SalariedEmployee> getEmployees() {        while (providerThread.isAlive())           ;        System.out.println("after thread" + employees);        return employees;    }    public void createProviderThread(String criterion) {        providerThread = new Thread(new ProviderConnection(criterion));        providerThread.start();        System.out.println("slariedEmplyees " + employees);    }    class ServerConnection extends Thread {        private int serverPort;        ObjectInputStream in;        ObjectOutputStream out;        private Socket s = null;        private boolean updated = false;        private LinkedList<SalariedEmployee> employeesLocal = new LinkedList<>();        public ServerConnection(LinkedList<SalariedEmployee> employeesList) {            try {                serverPort = 8888;                s = new Socket("localhost", serverPort);                System.out.println(s.getInetAddress());                out = new ObjectOutputStream(s.getOutputStream());                in = new ObjectInputStream(s.getInputStream());                System.out.println("out " + out.getClass() + "in " + in.getClass());                System.out.println(employeesList);                for (int i = 0; i < employeesList.size(); i++) { //adding each linkedlist item into employeesLocal >employeesLocal                    this.employeesLocal.add(new SalariedEmployee((SalariedEmployee) employeesList.get(i)));                }                this.start();            } catch (IOException e) {                System.out.println("Connection:" + e.getMessage());            }        }        public LinkedList<SalariedEmployee> getData() {            return this.employeesLocal;        }        @Override        public void run() {            System.out.println("thread running");            try {                out.writeObject("Enter");                while (employeesLocal.size() > 0) {                    SalariedEmployee data = employeesLocal.removeFirst();                    System.out.println(data);                    out.writeObject(data);                }                //Server can use this to know that last object sent                if (employeesLocal.size() == 0) {                    out.writeObject(new SalariedEmployee("finished"));                }            } catch (IOException e) {                System.err.println("IOException:  " + e);            }        }    }    class ProviderConnection implements Runnable {        private int serverPort;        ObjectInputStream in;        ObjectOutputStream out;        private Socket s = null;        private boolean updated = false;        // private ResidenceNeedful residence = null;        private String firstName;        private LinkedList<SalariedEmployee> employeesSalaried;        public ProviderConnection(String searchName) {            employeesSalaried = new LinkedList<>();            try {                serverPort = 8888;                s = new Socket("localhost", serverPort);                System.out.println(s.getInetAddress());                out = new ObjectOutputStream(s.getOutputStream());                in = new ObjectInputStream(s.getInputStream());                System.out.println("out" + out.getClass() + "in" + in.getClass());                firstName = new String(searchName);            } catch (IOException e) {                System.out.println("Connection:" + e.getMessage());            }        }//   public void getData(LinkedList<Person> residence){//       //   }        @Override        public void run() {            SalariedEmployee data;            System.out.println("thread running");            try {                out.writeObject(firstName);                while ((data = (SalariedEmployee) in.readObject()) != null) {                    if (data.getFirstName().equalsIgnoreCase("finished")) {                        System.out.println("no more");                        break;                    }                    System.out.println("data from server" + data);                    employees.add(new SalariedEmployee(data));                    System.out.println("employees matching criterion " + employees);                }            } catch (IOException | ClassNotFoundException e) {            }        }    }    public static void main(String args[]) {    }}