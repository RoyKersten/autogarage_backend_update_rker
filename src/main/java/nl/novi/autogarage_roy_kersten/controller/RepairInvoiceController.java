package nl.novi.autogarage_roy_kersten.controller;

import nl.novi.autogarage_roy_kersten.service.InvoiceService;
import nl.novi.autogarage_roy_kersten.service.RepairInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * The RepairInvoiceController class ensures that HTTP Requests en Responses are handled and processed further to the RepairInvoiceService interface.
 **/

@CrossOrigin
@RestController
@RequestMapping(value = "/invoices/repairs")
public class RepairInvoiceController extends InvoiceController {

    //Attributes
    private RepairInvoiceService repairInvoiceService;

    //Constructors
    @Autowired
    public RepairInvoiceController(@Qualifier("repairInvoiceServiceImpl") InvoiceService invoiceService, RepairInvoiceService repairInvoiceService) {
        super(invoiceService);
        this.repairInvoiceService = repairInvoiceService;
    }

    //Methods
    //Get all repair invoices, need to be defined in subclass RepairInvoice, path: "/invoices/repairs" should only show repair invoices
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllRepairInvoices() {
        return ResponseEntity.ok(repairInvoiceService.getAllRepairInvoices());
    }
}
