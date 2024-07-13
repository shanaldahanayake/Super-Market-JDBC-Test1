/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.supermarket.controller;

import edu.supermarket.dto.CustomerDto;
import edu.supermarket.dto.ItemDto;
import edu.supermarket.model.CustomerModel;
import java.util.ArrayList;

/**
 *
 * @author Dahanayake
 */
public class CustomerController {
    private CustomerModel customerModel;
    
    public CustomerController() throws Exception{
        this.customerModel=new CustomerModel();
    }
    public String saveCustomer(CustomerDto customerDto) throws Exception{
        String resp=customerModel.saveCustomer(customerDto);
        return resp;
    }
    public ArrayList<CustomerDto> getAllCustomer() throws Exception{
        ArrayList<CustomerDto> customerDtos=customerModel.getAllCustomer();
        return customerDtos;
    }

    public CustomerDto searchCustomer(String customerId)throws Exception{
        CustomerDto customerDto=customerModel.getCustomer(customerId);
        return customerDto;
    }

    

    public String deleteCustomer(String custId) throws Exception {
        String resp=customerModel.deleteCustomer(custId);
        return resp;    
    }

    public String updateCustomer(CustomerDto dto) throws Exception{
        String resp=customerModel.updateCustomer(dto);
        return resp ;
    }
}
