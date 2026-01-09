package models;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import models.OrderDetail;

public class Order {

    private int orderId;
    private String code;
    private String customerName;
    private String phone;
    private String email;
    private String address;
    private double totalAmount;
    private int quantity;

    private Integer orderStatusId;
    private Integer accountId;
    private Date createdDate;
    private Integer createdBy;
    private Date modifiedDate;
    private Integer modifiedBy;
    private String paymentMethod;   // COD / VNPAY
    private String paymentStatus;   // PENDING / PAID / FAILED

    
    public Order() {}

    // Constructor dùng khi tạo đơn mới từ Checkout
    public Order(String code, String customerName, String phone, String email,
                 String address, double totalAmount, int quantity) {

        this.code = code;
        this.customerName = customerName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.totalAmount = totalAmount;
        this.quantity = quantity;

        this.orderStatusId = 1; // mặc định: "Chờ xử lý"
        this.accountId = null;
        this.createdBy = null;
        this.modifiedBy = null;
    }

    // Getter - Setter
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Integer getOrderStatusId() {
        return orderStatusId;
    }

    public void setOrderStatusId(Integer orderStatusId) {
        this.orderStatusId = orderStatusId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    private List<OrderDetail> details;

    public List<OrderDetail> getDetails() {
        if(details == null) details = new ArrayList<>();
        return details;
    }

    public void setDetails(List<OrderDetail> details) {
        this.details = details;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

}
