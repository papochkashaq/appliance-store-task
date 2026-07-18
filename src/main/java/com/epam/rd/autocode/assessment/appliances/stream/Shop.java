package com.epam.rd.autocode.assessment.appliances.stream;

import com.epam.rd.autocode.assessment.appliances.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class Shop implements Add, Find, Sort {

    private final Set<Client> clients = new HashSet<>();
    private final Set<Employee> employees = new HashSet<>();
    private final Set<Order> orders = new HashSet<>();
    private final Set<Appliance> appliances = new HashSet<>();
    private final Set<Manufacturer> manufacturers = new HashSet<>();

    public Shop() {
    }

    @Override
    public void addClient(Client client) {
            clients.add(client);
    }

    @Override
    public void addEmployee(Employee employee) {
            employees.add(employee);
    }

    @Override
    public void addAppliance(Appliance appliance) {
            appliances.add(appliance);
    }

    @Override
    public void addOrder(Order order) {
            orders.add(order);
    }

    @Override
    public void addManufacturer(Manufacturer manufacturer) {
            manufacturers.add(manufacturer);
    }

    @Override
    public Manufacturer findManufacturerById(long id) {
        return manufacturers.stream()
                .filter(manufacturer -> manufacturer.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Manufacturer with id=" + id + " was not found"));
    }

    @Override
    public Manufacturer findManufacturerByName(String name) {
        return manufacturers.stream()
                .filter(manufacturer -> manufacturer.getName() != null)
                .filter(manufacturer -> name.equalsIgnoreCase(manufacturer.getName()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Manufacturer with name=" + name + " was not found"));
    }

    @Override
    public List<Order> findOrderByEmployee(Employee employee) {
        return orders.stream()
                .filter(order -> Objects.equals(employee, order.getEmployee()))
                .toList();
    }

    @Override
    public Order findCheapestOrder() {
        return orders.stream()
                .min(Comparator.comparing(this::calculateOrderSum))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order findMostExpensiveOrder() {
        return orders.stream()
                .max(Comparator.comparing(this::calculateOrderSum))
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public List<Manufacturer> sortManufacturersByName() {
        return manufacturers.stream()
                .sorted(Comparator.comparing(Manufacturer::getName, Comparator.nullsLast(Comparator.naturalOrder())))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Order> sortOrderByClientId() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getId))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Appliance> sortAppliancesByCategory() {
        return appliances.stream()
                .sorted(Comparator.comparing(Appliance::getCategory))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    public List<Order> sortOrderByAmount() {
        return orders.stream()
                .sorted(Comparator.comparing(this::calculateOrderSum))
                .collect(Collectors.toCollection(LinkedList::new));
    }

    public Set<Client> getClients() {
        return clients;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public Set<Appliance> getAppliances() {
        return appliances;
    }

    public Set<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    private BigDecimal calculateOrderSum(Order order) {
        Map<Appliance, BigDecimal> appliances = order.getAppliances();
        if (appliances == null || appliances.isEmpty()) {
            return BigDecimal.ZERO;
        }
        return appliances.values().stream()
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
