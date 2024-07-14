/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.supermarket.controller;

import edu.supermarket.dto.OrderDetailDto;
import edu.supermarket.dto.OrderDto;
import edu.supermarket.model.OrderModel;
import java.util.ArrayList;

/**
 *
 * @author Dahanayake
 */
public class OrderController {
    private OrderModel orderModel;
    
    public OrderController() throws Exception{
        orderModel=new OrderModel();
    }
    
    public String placeOrder(OrderDto orderDto, ArrayList<OrderDetailDto> orderDetailDtos) throws Exception{
        return orderModel.placeOrder(orderDto,orderDetailDtos);
    }
}
