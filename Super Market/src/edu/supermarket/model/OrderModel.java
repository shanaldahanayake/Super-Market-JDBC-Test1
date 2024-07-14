/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.supermarket.model;

import edu.supermarket.db.DBConnection;
import edu.supermarket.dto.OrderDetailDto;
import edu.supermarket.dto.OrderDto;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;


/**
 *
 * @author Dahanayake
 */
public class OrderModel {
    private final Connection connection;
    
    public OrderModel()throws ClassNotFoundException,SQLException{
        this.connection=DBConnection.getInstance().getConnection();
    }
    
    public String placeOrder(OrderDto orderDto,ArrayList<OrderDetailDto> orderDetailDtos) throws Exception{
        try {
            connection.setAutoCommit(false);
            String orderSql="INSERT INTO Orders VALUES(?,?,?)";
            PreparedStatement orderStatement= connection.prepareStatement(orderSql);
                
            orderStatement.setString(1, orderDto.getOrderId());
            orderStatement.setString(2, orderDto.getDate());
            orderStatement.setString(3, orderDto.getCustID());
            
            boolean isOrderSaved=orderStatement.executeUpdate()>0;
            
            if(isOrderSaved){
                boolean isOrderDetailSaved=true;
                
                String orderDetailSql="INSERT INTO ORDERDETAIL Values(?,?,?,?)";
                for(OrderDetailDto orderDetailDto:orderDetailDtos){
                    PreparedStatement orderDetailStatement=connection.prepareStatement(orderDetailSql);
                    orderDetailStatement.setString(1, orderDto.getOrderId());
                    orderDetailStatement.setString(2, orderDetailDto.getItemCode());
                    orderDetailStatement.setInt(3, orderDetailDto.getQty());
                    orderDetailStatement.setInt(4, orderDetailDto.getDiscount());
                    
                    if(!(orderDetailStatement.executeUpdate()>0)){
                        isOrderDetailSaved=false;
                    }
                }
                if(isOrderDetailSaved){
                    boolean isItemUpdated=true;
                    
                    String itemUpdateSql="UPDATE item Set QtyOnHand=QtyOnHand-? WHERE ItemCode=?";
                    
                    for(OrderDetailDto orderDetailDto:orderDetailDtos){
                         PreparedStatement itemStatement=connection.prepareStatement(itemUpdateSql);
                         itemStatement.setInt(1, orderDetailDto.getQty());
                         itemStatement.setString(2, orderDetailDto.getItemCode());
                         
                         if(!(itemStatement.executeUpdate()>0) ){
                             isItemUpdated=false;
                         }
                    }
                    if(isItemUpdated){
                        connection.commit();
                        return "Sucess";
                    }else{
                        connection.rollback();
                        return "Order Save Error1";
                    }
                    
                    
                }else{
                    connection.rollback();
                    return "Order Save Error2";
                }
                
                
            }else{
                connection.rollback();
                return "Order Save Error3";
            }
                    
        } catch (Exception e) {
           
            connection.rollback();
            e.printStackTrace();
            return e.getMessage();
        }finally{
            connection.setAutoCommit(true);
        }
        
    }
}
