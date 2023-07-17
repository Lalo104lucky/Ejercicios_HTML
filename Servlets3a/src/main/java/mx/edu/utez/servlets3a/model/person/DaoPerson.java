package mx.edu.utez.servlets3a.model.person;

import mx.edu.utez.servlets3a.utils.MySQLConnection;

import java.io.InputStream;
import java.sql.*;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
public class DaoPerson {
    private static Connection conn;
    private static PreparedStatement pstm;
    private static ResultSet rs;

    private static final String GET_PERSONS = "SELECT * FROM person JOIN user ON person.id=user.person";

    private static final String INSERT_PERSON="INSERT INTO person (name, lastname, age, email, phone, birthday, image)" +
            "VALUES (?,?,?,?,?,?,?)";
    private static final String INSERT_USER="INSERT INTO user (username, password, role, person)" +
            "VALUES (?,?,?,?)";
    private static final String GET_PERSON = "SELECT * FROM person WHERE id=?";
    private static final String UPDATE_PERSON = "UPDATE person SET name=?, lastname=?, age=?, phone=?, email=? WHERE id=?";
    private static final String DELETE_PERSON = "DELETE FROM PERSON WHERE id=?";

    public static List<BeanPerson> findPersons(){
        List<BeanPerson> personList = new LinkedList<>();
        BeanPerson person = new BeanPerson();
        try{
            conn = new MySQLConnection().getConnection();
            pstm = conn.prepareStatement(GET_PERSONS);
            rs = pstm.executeQuery();
            while (rs.next()){
                person = new BeanPerson();
                person.setId(rs.getLong("person.id"));
                person.setName(rs.getString("name"));
                person.setLastname(rs.getString("lastname"));
                person.setAge(rs.getInt("age"));
                person.setEmail(rs.getString("email"));
                person.setPhone(rs.getString("phone"));
                person.setBirthday(rs.getDate("birthday"));
                byte[] image = rs.getBytes("image");
                String imageStr = Base64.getEncoder().encodeToString(image);
                person.setImage(imageStr);

                person.setUsername(rs.getString("username"));
                person.setPassword(rs.getString("password"));
                person.setRole(rs.getString("role"));
                person.setIdU(rs.getLong("user.id"));

                personList.add(person);
            }
        }catch (SQLException e){
            Logger.getLogger(DaoPerson.class.getName())
                    .log(Level.SEVERE, "Error en findPersons -> ",e);
        } finally {
            closeConnection();
        }
        return personList;
    }

    public static boolean savePerson(BeanPerson person, InputStream imageBytes){
        try {
            conn = new MySQLConnection().getConnection();
            pstm = conn.prepareStatement(INSERT_PERSON, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1,person.getName());
            pstm.setString(2,person.getLastname());
            pstm.setInt(3,person.getAge());
            pstm.setString(4,person.getEmail());
            pstm.setString(5,person.getPhone());
            pstm.setDate(6,new Date(person.getBirthday().getTime()));
            pstm.setBlob(7, imageBytes);
            if (pstm.executeUpdate()==1){
                ResultSet lastIdPerson = pstm.getGeneratedKeys();
                if (lastIdPerson.next()){
                    return saveUser(person.getUsername(), person.getPassword(), person.getRole(), lastIdPerson.getLong(1));
                }else {
                    return false;
                }
            }else {
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(DaoPerson.class.getName())
                    .log(Level.SEVERE, "Error en savePerson -> ", e);
            return false;
        } finally {
            closeConnection();
        }
    }
    public static boolean saveUser (String username, String password, String role, Long person){
        try {
            pstm = conn.prepareStatement(INSERT_USER);
            pstm.setString(1, username);
            pstm.setString(2, password);
            pstm.setString(3, role);
            pstm.setLong(4,person);

            if (pstm.executeUpdate()==1){
                return true;
            }else {
                return false;
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoPerson.class.getName())
                    .log(Level.SEVERE, "Error saveUser -> ", e);
            return false;
        }
    }

    public static BeanPerson findPerson(Long id){
        BeanPerson person = null;
        try{
            conn = new MySQLConnection().getConnection();
            pstm = conn.prepareStatement(GET_PERSON);
            pstm.setLong(1,id);
            rs = pstm.executeQuery();
            if (rs.next()){
                person = new BeanPerson();
                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setLastname(rs.getString("lastname"));
                person.setAge(rs.getInt("age"));
                person.setEmail(rs.getString("email"));
                person.setPhone(rs.getString("phone"));
                return person;
            } else {
                return null;
            }
        }catch (SQLException e) {
            Logger.getLogger(DaoPerson.class.getName())
                    .log(Level.SEVERE, "Error en findPerson -> ", e);
            return null;
        } finally {
            closeConnection();
        }

    }

    public static boolean updatePerson(BeanPerson person){
        try {
            conn = new MySQLConnection().getConnection();
            pstm = conn.prepareStatement(UPDATE_PERSON); //Lo de return generated keys solo cuando se regsitre
            pstm.setString(1,person.getName());
            pstm.setString(2,person.getLastname());
            pstm.setInt(3,person.getAge());
            pstm.setString(4,person.getPhone());
            pstm.setString(5,person.getEmail());
            pstm.setLong(6, person.getId());
            if (pstm.executeUpdate()==1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(DaoPerson.class.getName())
                    .log(Level.SEVERE, "Error en updatePerson -> ", e);
            return false;
        } finally {
            closeConnection();
        }
    }

    public static boolean deletePerson(Long id){
        try {
            conn = new MySQLConnection().getConnection();
            pstm = conn.prepareStatement(DELETE_PERSON); //Lo de return generated keys solo cuando se regsitre
            pstm.setLong(1, id);
            if (pstm.executeUpdate()==1){
                return true;
            }else {
                return false;
            }

        } catch (SQLException e) {
            Logger.getLogger(DaoPerson.class.getName())
                    .log(Level.SEVERE, "Error en deletePerson -> ", e);
            return false;
        } finally {
            closeConnection();
        }
    }

    public static void closeConnection(){
        try {
            if (conn!=null){
                conn.close();
            }
            if (pstm!=null){
                pstm.close();
            }
            if (rs!=null){
                rs.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

