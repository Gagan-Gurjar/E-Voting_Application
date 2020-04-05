/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDto;
import evoting.dto.UserDetails;
import evoting.dto.UserDto;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author Win7
 */
public class UserDao {    
    private static PreparedStatement ps, ps2, ps3, ps4;
    private static Statement st;
    static {
        try {
            st = DBConnection.getConnection().createStatement();
            ps = DBConnection.getConnection().prepareStatement("select user_type from user_details where adhar_no=? and password=?");
            ps2 = DBConnection.getConnection().prepareStatement("select candidate_id, username, party, symbol"
                    + " from candidate, user_details where candidate.user_id = user_details.adhar_no and candidate.city=(select"
                    + " city from user_details where adhar_no=?)");
            ps3 = DBConnection.getConnection().prepareStatement("select * from user_details where adhar_no=?");
            ps4 = DBConnection.getConnection().prepareStatement("delete from user_details where adhar_no=?");
        }
        catch(SQLException ex) {
            if(DBConnection.getConnection() != null)
                    System.out.println("not null");
            ex.printStackTrace();
                    
        }
    }
    
    
    public static String validateUser(UserDto user) throws SQLException {
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            System.out.println("User dao"+rs.getString(1));
            return rs.getString(1);
        } 
            
        else
            return null;
    }
    public static ArrayList<CandidateDto> viewCandidate(String userId) throws SQLException {
        ArrayList<CandidateDto> candidate = new ArrayList<>();
        ps2.setString(1, userId);
        ResultSet rs = ps2.executeQuery();
        Blob blob;
        byte[] imageBytes;
        String base64Image;
        while(rs.next()) {
            blob = rs.getBlob(4);
            imageBytes = blob.getBytes(1L, (int)blob.length());
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
            candidate.add(new CandidateDto(rs.getString(1), rs.getString(2), rs.getString(3), base64Image));
            
        }
        return candidate;
        
    }
    public static ArrayList<String> getUserId() throws SQLException {
        ResultSet rs = st.executeQuery("select adhar_no from user_details where user_type='Voter'");
        ArrayList<String> id = new ArrayList<>();
        while(rs.next()) {
            id.add(rs.getString(1));
        }
        return id;
    }
    public static UserDetails getUserDetailsById(String uid) throws SQLException {
        ps3.setString(1, uid);
        ResultSet rs = ps3.executeQuery();
        UserDetails user = new UserDetails();
        if(rs.next()) {  
            user.setUserid(rs.getString(1));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6)); 
            user.setMobile(rs.getLong(7));
        }        
        return user;
    }
    public static ArrayList<UserDetails> getAllUsers() throws SQLException {
        ResultSet rs = st.executeQuery("select adhar_no, username, address, city, email, mobile_no from user_details where user_type='Voter'");
        ArrayList <UserDetails> users = new ArrayList <UserDetails>();
        while(rs.next()) {
            UserDetails user = new UserDetails();
            user.setUserid(rs.getString("adhar_no"));
            user.setUsername(rs.getString("username"));
            user.setAddress(rs.getString("address"));
            user.setCity(rs.getString("city"));            
            user.setEmail(rs.getString("email"));            
            user.setMobile(rs.getLong("mobile_no"));            
            users.add(user);
        }      
        return users;
    }
    public static boolean removeUser(String userId) throws SQLException {
        ps4.setString(1, userId);
        int x = ps4.executeUpdate();
        return x>0;
    }
}
