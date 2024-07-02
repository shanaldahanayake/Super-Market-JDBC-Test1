/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.supermarket.model;

import edu.supermarket.db.DBConnection;
import java.sql.SQLException;
import java.sql.Connection;
import edu.supermarket.dto.ItemDto;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.sql.ResultSet;
/**
 *
 * @author Dahanayake
 */

public class ItemModel {
    private final Connection connection;
    
    public ItemModel() throws ClassNotFoundException,SQLException{
        this.connection=DBConnection.getInstance().getConnection();
    }
    
    public String saveItem(ItemDto itemDto) throws ClassNotFoundException,SQLException{
        String sql="INSERT INTO item VALUES(?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(sql);
        
        statement.setString(1, itemDto.getCode());
        statement.setString(2, itemDto.getDescription());
        statement.setString(3, itemDto.getPackSize());
        statement.setDouble(4, itemDto.getUnitPrice());
        statement.setInt(5, itemDto.getQoh());
        
        return statement.executeUpdate()>0?"Success":"Fail";
        
    }
    public ArrayList<ItemDto> getAllItem() throws Exception{
        String sql="SELECT * FROM item";
        PreparedStatement statment = connection.prepareStatement(sql);
        ResultSet rst=statment.executeQuery();
        
        ArrayList<ItemDto> itemDtos=new ArrayList<>();
        
        while (rst.next()) {            
            ItemDto dto=new ItemDto(rst.getString("ItemCode"),rst.getString("Description"),rst.getString("PackSize"),rst.getDouble("UnitPrice"),rst.getInt("QtyOnHand"));
            itemDtos.add(dto);
        }
        return itemDtos;
    }
    
    public ItemDto getItem(String itemCode)throws Exception{
        String sql="SELECT *FROM item WHERE ItemCode=?";
        PreparedStatement statment = connection.prepareStatement(sql);
        statment.setString(1, itemCode);
        ResultSet rst=statment.executeQuery();
        
        while(rst.next()){
            ItemDto dto=new ItemDto(rst.getString("ItemCode"),rst.getString("Description"),rst.getString("PackSize"),rst.getDouble("UnitPrice"),rst.getInt("QtyOnHand"));
            
            return dto;
        }
        return null;   
    }
}
