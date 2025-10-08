package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {

        private Connection connection;

        public Doctor(Connection connection ) {
            this.connection = connection;

        }




        public void viewDoctor() {
            String query = "SELECT * FROM doctors";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                System.out.println("Doctor List:");
                System.out.println("+------------+--------------------+----------------------+");
                System.out.println("| doctors ID | name               | Secialization        |");
                System.out.println("+------------+--------------------+----------------------+");

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                   String specialization = resultSet.getString("specialization");

                    System.out.printf("| %-10d | %-18s | %-22d  |\n", id, name, specialization);
                    System.out.println("+------------+--------------------+-------------+----------------+");
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        public boolean getdoctorById(int id) {
            String query = "SELECT * FROM doctor WHERE id = ?";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return false;
                } else {
                    return true;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        }
}
