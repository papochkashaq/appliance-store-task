package com.epam.rd.autocode.assessment.appliances.stream;

import com.epam.rd.autocode.assessment.appliances.model.*;

import java.util.*;
import java.util.stream.Collectors;

public class Shop implements Add, Find, Sort {

    private Set<Client> clients = new HashSet<>();
    private Set<Employee> employees = new HashSet<>();
    private Set<Order> orders = new HashSet<>();
    private Set<Appliance> appliances = new HashSet<>();
    private Set<Manufacturer> manufacturers = new HashSet<>();

    public Shop(Set<Client> clients, Set<Employee> employees, Set<Order> orders, Set<Appliance> appliances, Set<Manufacturer> manufacturers) {
        this.clients = clients;
        this.employees = employees;
        this.orders = orders;
        this.appliances = appliances;
        this.manufacturers = manufacturers;
    }

    public Shop() {
    }

    @Override
    public void addClient(Client client) {
        if (clients != null) {
            clients.add(client);
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        if (employees != null) {
            employees.add(employee);
        }
    }

    @Override
    public void addAppliance(Appliance appliance) {
        if (appliances != null) {
            appliances.add(appliance);
        }
    }

    @Override
    public void addOrder(Order order) {
        if (orders != null) {
            orders.add(order);
        }
    }

    @Override
    public void addManufacturer(Manufacturer manufacturer) {
        if (manufacturers != null) {
            manufacturers.add(manufacturer);
        }
    }

    @Override
    public Manufacturer findManufacturerById(long id) {
        if (manufacturers == null || manufacturers.isEmpty()) {
            throw new RuntimeException("Manufacturer with id=" + id + " was not found");
        }
        return manufacturers.stream().filter(manufacturer -> manufacturer.getId() == id).findFirst().orElseThrow(() -> new RuntimeException("Manufacturer with id=" + id + " was not found"));
    }

    @Override
    public Manufacturer findManufacturerByName(String name) {
        if (manufacturers == null || manufacturers.isEmpty()) {
            throw new RuntimeException("Manufacturer with name=" + name + " was not found");
        }
        return manufacturers.stream().filter(manufacturer -> name.equals(manufacturer.getName())).findFirst().orElseThrow(() -> new RuntimeException("Manufacturer with name=" + name + " was not found"));
    }

    @Override
    public List<Order> findOrderByEmployee(Employee employee) {
        if (orders == null || orders.isEmpty()) {
            throw new RuntimeException("There are no orders");
        }
        return orders.stream().filter(order -> Objects.equals(employee, order.getEmployee())).toList();
    }

    @Override
    public Order findCheapestOrder() {
        if (orders == null || orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        return orders.stream().min(Comparator.comparing(Order::calculateOrderSum)).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order findMostExpensiveOrder() {
        if (orders == null || orders.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        return orders.stream().max(Comparator.comparing(Order::calculateOrderSum)).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Manufacturer> sortManufacturersByName() {
        return manufacturers.stream().sorted(Comparator.comparing(Manufacturer::getName)).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Order> sortOrderByClientId() {
        return orders.stream().sorted(Comparator.comparing(Order::getId)).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Appliance> sortAppliancesByCategory() {
        return appliances.stream().sorted(Comparator.comparing(Appliance::getCategory)).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Order> sortOrderByAmount() {
        return orders.stream().sorted(Comparator.comparing(Order::calculateOrderSum)).collect(Collectors.toCollection(LinkedList::new));
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Appliance> getAppliances() {
        return appliances;
    }

    public void setAppliances(Set<Appliance> appliances) {
        this.appliances = appliances;
    }

    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(Set<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }

}
