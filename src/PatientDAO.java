import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DAO class for handling Patient related Data Base operations
public class PatientDAO {

    // database details
    private static final String URL = "jdbc:mysql://localhost:3306/madhudb";
    private static final String USER = "root";
    private static final String PASSWORD = "Madhu@mysql";

    // method to create and return DB connection
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
        return conn;
    }

    // 1. add new patient
    public boolean addPatient(Patient p) {
        String sql = "INSERT INTO patients(patientId, name, age, gender, phoneNumber) VALUES(?,?,?,?,?)";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPatientId());
            ps.setString(2, p.getName());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getGender());
            ps.setString(5, p.getPhoneNumber());
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Problem in adding patient: " + e.getMessage());
            return false;
        }
    }

    // 2. update patient info
    public boolean updatePatient(Patient p) {
        String sql = "UPDATE patients SET name=?, age=?, gender=?, phoneNumber=? WHERE patientId=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setInt(2, p.getAge());
            ps.setString(3, p.getGender());
            ps.setString(4, p.getPhoneNumber());
            ps.setString(5, p.getPatientId());
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Problem in updating patient: " + e.getMessage());
            return false;
        }
    }

    // 3. delete patient by id
    public boolean deletePatient(String patientId) {
        String sql = "DELETE FROM patients WHERE patientId=?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, patientId);
            int rows = ps.executeUpdate();
            conn.close();
            return rows > 0;
        } catch (Exception e) {
            System.out.println("Problem in deleting patient: " + e.getMessage());
            return false;
        }
    }

    // 4. get all patients
    public List<Patient> getAllPatients() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getString("patientId"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phoneNumber")
                );
                list.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Problem in fetching patients: " + e.getMessage());
        }
        return list;
    }

    // 5. search patient by name
    public List<Patient> searchByName(String name) {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients WHERE name LIKE ?";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getString("patientId"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phoneNumber")
                );
                list.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Problem in searching patient: " + e.getMessage());
        }
        return list;
    }

    // 6. sort patients by age
    public List<Patient> sortByAge() {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT * FROM patients ORDER BY age ASC";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Patient p = new Patient(
                        rs.getString("patientId"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("gender"),
                        rs.getString("phoneNumber")
                );
                list.add(p);
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Problem in sorting patient: " + e.getMessage());
        }
        return list;
    }

    // 7. show statistics
    public void showStatistics() {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();

            // total count
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM patients");
            if (rs.next()) {
                System.out.println("Total Patients: " + rs.getInt(1));
            }

            // count by gender
            rs = stmt.executeQuery("SELECT gender, COUNT(*) FROM patients GROUP BY gender");
            while (rs.next()) {
                System.out.println(rs.getString(1) + " : " + rs.getInt(2));
            }

            conn.close();
        } catch (Exception e) {
            System.out.println("Problem in showing statistics: " + e.getMessage());
        }
    }
}
