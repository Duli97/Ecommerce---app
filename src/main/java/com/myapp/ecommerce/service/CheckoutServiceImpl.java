package com.myapp.ecommerce.service;

import com.myapp.ecommerce.dao.CustomerRepo;
import com.myapp.ecommerce.dto.Purchase;
import com.myapp.ecommerce.dto.PurchaseResponse;
import com.myapp.ecommerce.entity.Customer;
import com.myapp.ecommerce.entity.Order;
import com.myapp.ecommerce.entity.OrderItem;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    private CustomerRepo customerRepo;

    public CheckoutServiceImpl(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        // retrieve the order info from dto
        Order order = purchase.getOrder();

        // generate tracking number
        String orderTrackingNumber = generateOrderTrackingNumber();
        order.setOrderTrackingNumber(orderTrackingNumber);

        // populate order with orderItems
        Set<OrderItem> orderItems = purchase.getOrderItems();
        orderItems.forEach(item -> order.add(item));

        // populate order with billingAddress and shippingAddress
        order.setBillingAddress(purchase.getBillingAddress());
        order.setShippingAddress(purchase.getShippingAddress());

        // populate customer with order
        Customer customer = purchase.getCustomer();
        customer.add(order);

        // save to the database
        customerRepo.save(customer);

        // return a response
        return new PurchaseResponse(orderTrackingNumber);
    }

    private String generateOrderTrackingNumber() {

        // generate a random UUID number (UUID version-4)

        // For details see: Universally Unique Identifier

        return UUID.randomUUID().toString();
    }
}
