package com.epam.rd.autocode.assessment.appliances.model;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Order {

    private long id;
    private Client client;
    private Employee employee;
    private Map<Appliance, BigDecimal> appliances;

    public Order(long id, Client client, Employee employee, Map<Appliance, BigDecimal> appliances) {
        this.id = id;
        this.client = client;
        this.employee = employee;
        this.appliances = appliances;
    }

    public Order() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Map<Appliance, BigDecimal> getAppliances() {
        return appliances;
    }

    public void setAppliances(Map<Appliance, BigDecimal> appliances) {
        this.appliances = appliances;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(client, order.client) && Objects.equals(employee, order.employee) && Objects.equals(appliances, order.appliances);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, employee, appliances);
    }

    @Override
    public String toString() {
        String appliancesString = appliances == null || appliances.isEmpty() ? "[]" :
                appliances.entrySet().stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining(", ", "[", "]"));

        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", employee=" + employee +
                ", appliances=" + appliancesString +
                '}';
    }

}
