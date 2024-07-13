/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.supermarket.model;

import edu.supermarket.db.DBConnection;
import java.sql.SQLException;
import java.sql.Connection;
import edu.supermarket.dto.CustomerDto;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author Dahanayake
 */

public class CustomerModel {
    private final Connection connection;
    
    public CustomerModel() throws ClassNotFoundException,SQLException{
        this.connection=DBConnection.getInstance().getConnection();
    }
    
    public String saveCustomer(CustomerDto customerDto) throws ClassNotFoundException,SQLException{
        String sql="INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        
        statement.setString(1, customerDto.getCustId());
        statement.setString(2, customerDto.getCustTitle());
        statement.setString(3, customerDto.getCustName());
        statement.setString(4, customerDto.getdOB());
        statement.setDouble(5, customerDto.getSalary());
        statement.setString(6, customerDto.getAddress());
        statement.setString(7, customerDto.getCity());
        statement.setString(8, customerDto.getProvince());
        statement.setString(9, customerDto.getPostalCode());
        
        
        return statement.executeUpdate()>0?"Success":"Fail";
        
    }
    public ArrayList<CustomerDto> getAllCustomer() throws Exception{
        String sql="SELECT * FROM customer";
        PreparedStatement statment = connection.prepareStatement(sql);
        ResultSet rst=statment.executeQuery();
        
        ArrayList<CustomerDto> customerDtos=new ArrayList<>();
        
        while (rst.next()) {            
            CustomerDto dto=new CustomerDto(rst.getString("CustID"),rst.getString("CustTitle"),rst.getString("CustName"),rst.getString("DOB"),rst.getDouble("salary"),rst.getString("CustAddress"),rst.getString("City"),rst.getString("Province"),rst.getString("PostalCode"));            
            customerDtos.add(dto);
        }
        return customerDtos;
    }
    
    public CustomerDto getCustomer(String customerID)throws Exception{
        String sql="SELECT *FROM customer WHERE CustID=?";
        PreparedStatement statment = connection.prepareStatement(sql);
        statment.setString(1, customerID);
        ResultSet rst=statment.executeQuery();
        
        while(rst.next()){
            CustomerDto dto=new CustomerDto(rst.getString("CustID"),rst.getString("CustTitle"),rst.getString("CustName"),rst.getString("DOB"),rst.getDouble("salary"),rst.getString("CustAddress"),rst.getString("City"),rst.getString("Province"),rst.getString("PostalCode"));            
            return dto;
        }
        return null;   
    }

    public String deleteCustomer(String customerID) throws Exception{
        String sql="DELETE FROM customer WHERE CustID=?";
        PreparedStatement statement=connection.prepareCall(sql);
        statement.setString(1, customerID);
        return statement.executeUpdate()>0?"Success":"Fail";

    }

    public String updateCustomer(CustomerDto customerDto) throws Exception{
        String sql="UPDATE customer SET CustTitle=?, CustName=?, DOB=?, salary=?, CustAddress=?, City=?, Province=?, PostalCode=? WHERE CustID=?";
        PreparedStatement statement=connection.prepareStatement(sql);
        
       
        
        statement.setString(1, customerDto.getCustTitle());
        statement.setString(2, customerDto.getCustName());
        statement.setString(3, customerDto.getdOB());
        statement.setDouble(4, customerDto.getSalary());
        statement.setString(5, customerDto.getAddress());
        statement.setString(6, customerDto.getCity());
        statement.setString(7, customerDto.getProvince());
        statement.setString(8, customerDto.getPostalCode());
        statement.setString(9, customerDto.getCustId());
        
        return statement.executeUpdate()>0?"Success":"Fail";
    }
}
