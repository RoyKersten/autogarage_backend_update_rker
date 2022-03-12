package nl.novi.autogarage_roy_kersten.controller;

import nl.novi.autogarage_roy_kersten.model.Repair;
import nl.novi.autogarage_roy_kersten.model.ServiceStatus;
import nl.novi.autogarage_roy_kersten.model.dto.CallListDto;
import nl.novi.autogarage_roy_kersten.service.RepairService;
import nl.novi.autogarage_roy_kersten.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
/**
 * The RepairController class ensures that HTTP Requests en Responses are handled and processed further to the RepairService interface.
 **/

@CrossOrigin
@RestController
@RequestMapping(value = "/services/repairs")
public class RepairController extends ServiceController {

    //Attributes
    private RepairService repairService;


    //Constructors
    @Autowired
    public RepairController(@Qualifier("repairServiceImpl") ServiceService serviceService, RepairService repairService) {
        super(serviceService);
        this.repairService = repairService;
    }

    //Methods
    //Get all Repairs, need to be defined in subclass Repair, path: "/services/repairs" should only show repairs
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllRepairs() {
        return ResponseEntity.ok(repairService.getAllRepairs());
    }

    //Get repairs by serviceStatus "VOLTOOID"
    @GetMapping(value = "/calllist")
    public List<CallListDto> getRepairByStatus(ServiceStatus serviceStatus) {

        var dtos = new ArrayList<CallListDto>();
            List<Repair> repairs;
            repairs = repairService.getRepairByStatus(ServiceStatus.VOLTOOID);

            for (Repair repair : repairs) {
                dtos.add(CallListDto.fromService(repair));
            }
            return dtos;
    }

    //Update Service by idService (issuesToRepair), specific for Repair needs to be defined in subclass
    @PutMapping("/{idService}")
    public ResponseEntity<Object> updateRepairById(@PathVariable("idService") long idService, @RequestBody Repair updateService) {
        repairService.updateRepairById(idService, updateService);
        return ResponseEntity.ok("update Repair successfully");
    }


}
