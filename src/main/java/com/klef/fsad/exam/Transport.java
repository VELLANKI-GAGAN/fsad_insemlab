package com.klef.fsad.exam;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Transport Entity Class
 * Represents a transport record with various attributes
 */
@Entity
@Table(name = "transport")
public class Transport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transport_id")
    private int id;

    @Column(name = "transport_name", nullable = false, length = 100)
    private String name;

    @Column(name = "transport_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "vehicle_type", length = 100)
    private String vehicleType;

    @Column(name = "source", length = 100)
    private String source;

    @Column(name = "destination", length = 100)
    private String destination;

    @Column(name = "distance")
    private double distance;

    @Column(name = "cost")
    private double cost;

    @Column(name = "driver_name", length = 100)
    private String driverName;

    // Constructors
    public Transport() {
    }

    public Transport(String name, Date date, String status, String description, 
                    String vehicleType, String source, String destination, 
                    double distance, double cost, String driverName) {
        this.name = name;
        this.date = date;
        this.status = status;
        this.description = description;
        this.vehicleType = vehicleType;
        this.source = source;
        this.destination = destination;
        this.distance = distance;
        this.cost = cost;
        this.driverName = driverName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    @Override
    public String toString() {
        return "Transport{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", description='" + description + '\'' +
                ", vehicleType='" + vehicleType + '\'' +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", distance=" + distance +
                ", cost=" + cost +
                ", driverName='" + driverName + '\'' +
                '}';
    }
}
