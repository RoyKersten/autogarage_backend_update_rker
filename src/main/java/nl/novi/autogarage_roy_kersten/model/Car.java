package nl.novi.autogarage_roy_kersten.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


/**
 * The Car class is the blueprint for all Car objects.
 * A Car object contains all basic information of a car like: brand, model, year_of_construction and license_plate_number.
 */

@Entity
@Table(name = "car")

//@JsonIgnoreProperties ({"service", "customer"})           /JSON ignore at class level
public class Car {

    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCar;

    @Column(name = "brand")
    private String brand;

    @Column(name = "model")
    private String model;

    @Column(name = "year_of_construction")
    private String yearOfConstruction;

    @Column(name = "license_plate_number")
    private String licensePlateNumber;

    @OneToMany (mappedBy = "car")
    @JsonIgnore                         //Json ignore at field level
    private List<Service> service;

    @ManyToOne
    private Customer customer;

    @Lob
    byte[] carPaper;

    // Constructors
    public Car() {}

    public Car(Long idCar, String brand, String model, String yearOfConstruction, String licensePlateNumber) {
        this.idCar = idCar;
        this.brand = brand;
        this.model = model;
        this.yearOfConstruction = yearOfConstruction;
        this.licensePlateNumber = licensePlateNumber;

    }


    // Getters and Setters
    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYearOfConstruction() {
        return yearOfConstruction;
    }

    public void setYearOfConstruction(String yearOfConstruction) {
        this.yearOfConstruction = yearOfConstruction;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }


    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Service> getService() {
        return service;
    }

    public void setService(List<Service> service) {
        this.service = service;
    }

    public byte[] getCarPaper() {
        return carPaper;
    }

    public void setCarPaper(byte[] carPaper) {
        this.carPaper = carPaper;
    }

//Methods


}

