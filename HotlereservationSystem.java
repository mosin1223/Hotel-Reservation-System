import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

public class HotlereservationSystem {
    static final String url = "jdbc:mysql://localhost:3306/hotle_db";
    static final String name = "root";
    static final String password = "root177";

    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        Scanner scanner;
        Connection connection;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            connection = DriverManager.getConnection(url, name, password);
            while (true) {
                scanner = new Scanner(System.in);
                System.out.println();
                System.out.println("HOTLE RESERVATION SYSTEM");
                System.out.println("1.Reserve a room");
                System.out.println("2.View Reservation");
                System.out.println("3.get room number");
                System.out.println("4.Update room number");
                System.out.println("5.Delette room number");
                System.out.println("0.Exit");
                System.out.println("Chose any option ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        reserveRoom(connection, scanner);
                        break;
                    case 2:
                        viewReservation(connection);
                        break;
                    case 3:
                        getRoomNum(connection, scanner);
                        break;
                    case 4:
                        updateRoom(connection, scanner);
                        break;
                    case 5:
                        deleteReservation(connection, scanner);
                        break;
                    case 0:
                        Exit();
                        return;
                    default:
                        System.out.println("Invalid choice Try again!!");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    static void reserveRoom(Connection con, Scanner write) {
        try {
            System.out.println("Enter Guest Name : ");
            String guest_name = write.next();
            System.out.println("Enter Room number  : ");
            int Room_num = write.nextInt();
            System.out.println("Enter Guest Number : ");
            String guest_Num = write.next();

            String query = "INSERT INTO reservation(guest_name , Room_No, Contact_Num )" +
                    "VALUES('" + guest_name + "' , " + Room_num + " , '" + guest_Num + "'  )";
            try {
                Statement stmt = con.createStatement();
                int rowsaffected = stmt.executeUpdate(query);

                if (rowsaffected > 0) {
                    System.out.println("Room reserve succesfully");
                } else {
                    System.out.println("Room can not be reserved");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void viewReservation(Connection connection) throws SQLException {
        String query = "SELECT *from reservation";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Current Resrvation");
            System.out.println("+----------------+---------------+-------------+----------------+-----------------------+");
            System.out.println("| RESERVATION ID | GUEST         | ROOM NUMBER | CONTACT NUMBER | RESERVATION DATE");
            System.out.println("+----------------+---------------+-------------+----------------+-----------------------+");
            while (rs.next()) {
                int reservation_id = rs.getInt("Rservation_Id");
                String guest = rs.getString("guest_name");
                int room = rs.getInt("Room_No");
                String contact = rs.getString("Contact_Num");
                String reservationDate = rs.getTimestamp("Reservation_Date").toString();

                System.out.println(reservation_id + "                 " + guest + "                  " + room + "        " + contact + "     " + reservationDate);
                System.out.println("+----------------+---------------+-------------+----------------+-----------------------+");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void getRoomNum(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter REservatio_id :: ");
            int id = scanner.nextInt();
            ;
            System.out.println("Enter Guest Name :: ");
            String name = scanner.next();

            String query = "SELECT Room_No FROM reservation " +
                    "WHERE Rservation_Id = " + id +
                    " AND guest_name  = '" + name + "'";
            try {
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query);
                if (rs.next()) {
                    int room = rs.getInt("Room_No");
                    System.out.println("Room num for reservation id " + id + " , name " + name + " , room " + room);
                } else {
                    System.out.println("Room not find ");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception r) {
            System.out.println(r.getMessage());
        }
    }

    static void updateRoom(Connection connection, Scanner scanner) {
        try {
            System.out.println("Enter REservatio_id :: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter New Guest Name : ");
            String guest_name = scanner.next();
            System.out.println("Enter New Room number  : ");
            int Room_num = scanner.nextInt();
            System.out.println("EnterNew  Guest Number : ");
            String guest_Num = scanner.next();

            String query = "UPDATE reservation SET guest_name = '" + guest_name + "', " +
                    "Room_No = " + Room_num + "," +
                    "Contact_Num = '" + guest_Num + "' " +
                    "WHERE Rservation_Id = " + id;
            try {
                if(!reservationExist(connection,id)){
                    System.out.println("reservation Doesnot Exist");
                    return;
                }

                Statement stmt = connection.createStatement();
                int affected = stmt.executeUpdate(query);

                if (affected > 0) {
                    System.out.println("changes succesfully ");

                } else {
                    System.out.println("Not changed");
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void deleteReservation(Connection con, Scanner Scan) {
        System.out.println("Enter id for deletation of rrservation ");
        int id = Scan.nextInt();

        String query = "DELETE FROM reservation WHERE Rservation_Id = " + id;
        try {

            if(!reservationExist(con,id)){
                System.out.println("reservation Doesnot Exist");
                return;
            }
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);

            if (rs > 0) {
                System.out.println("DEleted successfully ");
            } else {
                System.out.println("Not deleted");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    static boolean reservationExist(Connection con, int reservationID) {
        String sql = "SELECT Rservation_Id FROM reservation WHERE Rservation_Id = " + reservationID;
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
    public  static void Exit() throws InterruptedException {
        System.out.print("EXITING SYSTEM ");
        int i=5;
        while(i!=0){
            System.out.print(".");
            Thread.sleep(450);i--;
        }
        System.out.println();
        System.out.println("THANKYU FOR USING HOTLE RESERVATION SYSTEM");
     }

}
