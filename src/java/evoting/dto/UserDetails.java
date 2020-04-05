/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

/**
 *
 * @author Win7
 */
public class UserDetails {
    private String userid;
    private String email;
    private String address;
    private long mobile;
    private String city;
    private String password;
    private String username;
    
    public UserDetails(String username, String userid, String email, String address, long mobile, String city, String password) {
        this.username = username;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.userid = userid;
        
    }

    @Override
    public String toString() {
        return "UserDetails{" + "userid=" + userid + ", email=" + email + ", address=" + address + ", mobile=" + mobile + ", city=" + city + ", password=" + password + ", username=" + username + '}';
    }
    
    
   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public UserDetails() {

    }
}
