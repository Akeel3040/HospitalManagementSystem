package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class HospitalManagmentSystem {
       private static  final String url = "jdbc:mysql://localhost:3306/Hospital";

       private static final String username = "root";

       private static final String password = "Lloyd@12";

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e. printStackTrace();
        }
        Scanner Scanner = new Scanner(System.in);
        try{
            Connection connection = DriverManager.getConnection(url,username,password);
            patient patient = new patient(connection,  Scanner);
            Doctor doctor = new Doctor(connection);
            while(true){
                System.out.println("HOSPITAL MANAGEMENT SYSTEM");
                System.out.println("1. Add Patient");
                System.out.println("2. view patients");
                System.out.println("3. Add Doctors");
                System.out.println("4. book appointment");
                System.out.println("5. exist");
                System.out.println(" Enter you choice:");
                int choice = Scanner.nextInt();
                switch (choice){
                    case 1:
                        // Add Patient
                        patient.addpatient();
                        System.out.println();
                    case 2:
                        // view patient
                        patient.viewpatient();
                        System.out.println();
                    case 3:
                        // View doctor
                        doctor.viewDoctor();
                        System.out.println();
                    case 4:
                        // book appointment
                        bookAppointment(patient, doctor , connection, Scanner);
                        System.out.println();
                    case 5:
                        return;
                    default:
                        System.out.println("invalid choice");

                }
            }
        }catch (SQLException e){
            e. printStackTrace();
        }
    }
    public static  void bookAppointment(patient patient, Doctor doctor ,Connection connection , Scanner scanner) {
        System.out.println("Enter appointment Id");
        int patientId = scanner.nextInt();
        System.out.println("Enter Doctor ID");
        int DoctorId = scanner.nextInt();
        System.out.println("Enter appointment date(yyyy-mm-dd)");
        String appointmentDate = scanner.next();
        if (patient.getPatientById(patientId) && doctor.getdoctorById(DoctorId)) ;
        if (CheckDoctorAvaliblity(DoctorId, appointmentDate, connection)) {
            String appointmentQuery = "INSERT INTO appointments(patient_id,doctor_id) VALUES(?,?, ? )";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(appointmentQuery);
                preparedStatement.setInt(1, patientId);
                preparedStatement.setInt(2, DoctorId);
                preparedStatement.setString(3, appointmentDate);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Appointment Booed Successfully");
                } else {
                    System.out.println("Failed to book  Appointment ");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Doctor Not Avaliable of this Date");
         }
        } else {
            System.out.println(" Either patient or Doctor Available doesn't exist");
        }
    }

    public static boolean checkDoctorAvaliblity(int DoctorId, String appointmentDate, Connection  connection ){
    String qurey = "SELECT  count(*) FROM appointments WHERE doctor_id = ? AND appointment_date = ?";
    try{
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(prameterIndex: 1, DoctorId );
        preparedStatement.setInt(parameterIndex: 2, appointmentDate);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            int count = resultSet.getInt(columnIndex:1);
            if(count==0){
                return false;
            }else{
                return true;
            }
        }
    } catch (SQLException e){
        e.printStackTrace();
    }
    return false;
      }
    }





