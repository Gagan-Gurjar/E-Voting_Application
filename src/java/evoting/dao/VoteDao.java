/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDto;
import evoting.dto.VoteDto;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;

/**
 *
 * @author Win7
 */
public class VoteDao {
    private static PreparedStatement ps, ps2, ps3, ps4, ps5;
    private static Statement st;
    static {
        try {            
            ps = DBConnection.getConnection().prepareStatement("select candidate_id from voting where user_id= ?");  
            ps2 = DBConnection.getConnection().prepareStatement("select candidate_id, username, party, symbol"
                    + " from candidate, user_details where candidate.user_id = user_details.adhar_no and"
                    + " candidate_id =?");
            ps3 = DBConnection.getConnection().prepareStatement("insert into voting values(?,?)");
            
            ps4 = DBConnection.getConnection().prepareStatement("select candidate_id, count(*) from voting group"
                    + " by candidate_id order by count(*) desc");
            ps5 = DBConnection.getConnection().prepareStatement("select * from voting where user_id=?");
            st = DBConnection.getConnection().createStatement();
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
    }    
    

    public static String getCandidateId(String userid) throws SQLException {
        ps.setString(1, userid);
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return rs.getString(1);
        return null;
    }     
    public static CandidateDto getVote(String candidateid) throws Exception,SQLException, IOException {
        CandidateDto candidate = null;
        Blob blob;
        byte[] imageBytes;
        String cImage;
        ps2.setString(1, candidateid);
        System.out.println("getVote() result ke phle");
        ResultSet rs = ps2.executeQuery();
        System.out.println("getVote result ke baad");
        if(rs.next()) {
            blob = rs.getBlob(4);
            imageBytes = blob.getBytes(1L, (int)blob.length());
            cImage = Base64.getEncoder().encodeToString(imageBytes);
            candidate = new CandidateDto(rs.getString(1), rs.getString(2), rs.getString(3), cImage);
        }
        return candidate;
        
    }
    public static boolean addVote(VoteDto vote) throws SQLException {
        ps3.setString(1, vote.getCandidateId());
        ps3.setString(2, vote.getVoterId());
        return (ps3.executeUpdate() != 0);
    }
    public static LinkedHashMap<String, Integer> getResult() throws Exception {
        LinkedHashMap<String, Integer> result = new LinkedHashMap<>();
        ResultSet rs = ps4.executeQuery();
        while(rs.next())
            result.put(rs.getString(1), rs.getInt(2));
        return result;
    }
    public static int getVoteCount() throws SQLException {
        ResultSet rs = st.executeQuery("select count(*) from voting");
        if(rs.next())
            return rs.getInt(1);
        return 0;
    }
    public static boolean validateVote(String userid) throws SQLException {
        ps5.setString(1, userid);
        return ps5.executeQuery().next();
    }
    
}       

