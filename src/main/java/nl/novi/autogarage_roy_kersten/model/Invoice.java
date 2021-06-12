package nl.novi.autogarage_roy_kersten.model;

import nl.novi.autogarage_roy_kersten.model.Customer;

import javax.persistence.*;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
// Set Strategy SINGLE_TABLE => Create one table for all subclasses with a subclass type column to differentiate between subclasses
@DiscriminatorColumn(name = "invoice_type")
@Table(name = "invoice")
public abstract class Invoice {


    //attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInvoice;

    @Column(name = "invoice_status")
    private String invoiceStatus;

    @Column(name = "line_total")
    private float lineTotal;

    @Column(name = "invoice_subtotal")
    private float invoiceSubtotal;

    @Column(name = "vat_rate")
    private float vatRate;

    @Column(name = "vat_amount")
    private float vatAmount;

    @Column(name = "invoice_total")
    private float invoiceTotal;

    @OneToMany (mappedBy = "invoice")
    private List<ServiceLine> serviceLine;

    @OneToOne
    private Service service;

    @ManyToOne
    private Customer customer;

    //Constructors
    public Invoice() {
    }

    public Invoice(int idInvoice, String invoiceStatus, Customer customer, float lineTotal, float invoiceSubtotal, float vatRate, float vatAmount, float invoiceTotal) {
        this.idInvoice = idInvoice;
        this.invoiceStatus = invoiceStatus;
        this.customer = customer;
        this.lineTotal = lineTotal;
        this.invoiceSubtotal = invoiceSubtotal;
        this.vatRate = vatRate;
        this.vatAmount = vatAmount;
        this.invoiceTotal = invoiceTotal;


    }


    //Getters and Setters


    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(String invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(float lineTotal) {
        this.lineTotal = lineTotal;
    }

    public float getInvoiceSubtotal() {
        return invoiceSubtotal;
    }

    public void setInvoiceSubtotal(float invoiceSubtotal) {
        this.invoiceSubtotal = invoiceSubtotal;
    }

    public float getVatRate() {
        return vatRate;
    }

    public void setVatRate(float vatRate) {
        this.vatRate = vatRate;
    }

    public float getVatAmount() {
        return vatAmount;
    }

    public void setVatAmount(float vatAmount) {
        this.vatAmount = vatAmount;
    }

    public float getInvoiceTotal() {
        return invoiceTotal;
    }

    public void setInvoiceTotal(float invoiceTotal) {
        this.invoiceTotal = invoiceTotal;
    }


    //Methods


}






