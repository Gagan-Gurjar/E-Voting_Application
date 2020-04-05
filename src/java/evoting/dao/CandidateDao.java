/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.AddCandidateDto;
import evoting.dto.CandidateDetails;
import evoting.dto.CandidateDto;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;

/**
 *
 * @author Win7
 */
public class CandidateDao {
    private static Statement st, st2, st3, st4;
    private static PreparedStatement ps, ps1, ps2, ps3, ps4, ps5, ps6, ps7;
    static {
        try {
            st = DBConnection.getConnection().createStatement();
            st2 = DBConnection.getConnection().createStatement();            
            st3 = DBConnection.getConnection().createStatement();            
            st4 = DBConnection.getConnection().createStatement();
            ps = DBConnection.getConnection().prepareStatement("select username from user_details where adhar_no = ?");
            ps1 = DBConnection.getConnection().prepareStatement("insert into candidate values(?,?,?,?,?)");
            ps2 = DBConnection.getConnection().prepareStatement("select * from candidate where candidate_id = ?");
            ps3 = DBConnection.getConnection().prepareStatement("delete from candidate where candidate_id=?");
            ps4 = DBConnection.getConnection().prepareStatement("delete from candidate where user_id=?");
            ps5 = DBConnection.getConnection().prepareStatement("update candidate set city=?, party=?, symbol=? where user_id=?");
            ps6 = DBConnection.getConnection().prepareStatement("select party from candidate where candidate_id=?");
            ps5 = DBConnection.getConnection().prepareStatement("update candidate set city=?, party=? where user_id=?");
        }
        catch(SQLException ex) {
            System.out.println("candidateDao exception");
            ex.printStackTrace();
        }
    }
//    public static String getNewCandidateId() throws SQLException {
//        ResultSet rs = st.executeQuery();
//        if(rs.next())
//            return "C"+(100+(rs.getInt(1)+1));
//        else
//            return "C101";
//    }
    
    public static String getNewCandidateId() throws SQLException {        
        int id = 101;
        String party;
        ResultSet rs = st.executeQuery("select count(*) from candidate");
        if(rs.next()) {        
            id = id + rs.getInt(1);
        }
        String candidateId = "C"+id;        
        party = checkEmpId(candidateId);        
        while(party != null) {
            id++;
            candidateId= "C"+id;            
            party = checkEmpId(candidateId);            
        }
        return candidateId; 
    }
    
    public static String checkEmpId(String candidateId) throws SQLException {       
        ps6.setString(1, candidateId);
        ResultSet rs = ps6.executeQuery();
        String party = null;
        if(rs.next()) {
            party = rs.getString(1);
        }
        return party;
        
    }
    
    public static String getUsernameById(String userid) throws SQLException {
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        if(rs.next()) {
            return rs.getString(1);
        }    
        else {
            return null; 
        }
            
    }
    public static ArrayList<String> getCity() throws SQLException {
       ResultSet rs = st4.executeQuery("select unique city from user_details");
       ArrayList <String> cities = new ArrayList<String>();
        while(rs.next()) {
            cities.add(rs.getString(1)); 
        }      
        return cities; 
    }
    
    public static boolean addCandidate(AddCandidateDto candidate) throws SQLException, IOException {
        System.out.println("CandidateDao addCandidate start\n");
        System.out.println("candidateid" +candidate.getCandidateId());
        System.out.println("candidateid" +candidate.getParty());
        System.out.println("candidateid" +candidate.getUserId());
        
        ps1.setString(1, candidate.getCandidateId());        
        ps1.setString(2, candidate.getParty());
        ps1.setString(3, candidate.getUserId());
        
//        FileInputStream in =(FileInputStream)candidate.getSymbol();
        
                
//                             or
//        ps1.setBinaryStream(3, candidate.getSymbol(), candidate.available());
        
//        System.out.println("setBinay se available\n"+ in.available());
//        ps1.setBinaryStream(4, candidate.getSymbol());
        ps1.setBinaryStream(4, candidate.getSymbol(),candidate.getSymbol().available());
        ps1.setString(5, candidate.getCity());
        System.out.println("candidateid" +candidate.getCity());
        
        System.out.println("\n\nCandidateDao addCandidate last\n\n return by add candidate");
        return (ps1.executeUpdate() != 0);
        
    }
    
    public static ArrayList<String> getCandidateId() throws SQLException {
        ResultSet rs = st3.executeQuery("select candidate_id from candidate");
        ArrayList<String> id = new ArrayList<>();
        while(rs.next()) {
            id.add(rs.getString(1));
        }
        return id;
    }
    
    public static CandidateDetails getDetailsById(String cid) throws SQLException {
        ps2.setString(1, cid);
        ResultSet rs = ps2.executeQuery();
        CandidateDetails candidate = new CandidateDetails();
        Blob blob;
        byte[] imageBytes;
        String base64Image;
        if(rs.next()) {
            blob = rs.getBlob(4);
            imageBytes = blob.getBytes(1L, (int)blob.length());
            Encoder ec = Base64.getEncoder();
            base64Image = ec.encodeToString(imageBytes);
            candidate.setSymbol(base64Image);
            candidate.setCandidateId(cid);
            candidate.setParty(rs.getString(2));
            candidate.setUserId(rs.getString(3));
            candidate.setCity(rs.getString(5));
        }        
        return candidate;
    }
    
    public static boolean deleteCandidate(String candidateId) throws SQLException {
        ps3.setString(1, candidateId);
        int x = ps3.executeUpdate();
        return x>0;
    }
    
    public static boolean deleteCandidateByUserId(String userId) throws SQLException {
        ps4.setString(1, userId);
        int x = ps4.executeUpdate();
        return x > 0;
    }
    
    public static boolean updateCandidate(AddCandidateDto candidate) throws SQLException, IOException {
        if((candidate.getSymbol()) != null) { 
            ps5.setString(1, candidate.getCity());
            ps5.setString(2, candidate.getParty());
            ps5.setBinaryStream(3, candidate.getSymbol(),candidate.getSymbol().available());
            ps5.setString(4, candidate.getUserId());
            
            return (ps5.executeUpdate() != 0);
        }
        else {
            ps7.setString(1, candidate.getCity());
            ps7.setString(2, candidate.getParty());
            ps7.setString(3, candidate.getUserId());
            
            return (ps7.executeUpdate() != 0);
        }
                
        
        
    }
}
