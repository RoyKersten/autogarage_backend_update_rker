package nl.novi.autogarage_roy_kersten.controller;

import nl.novi.autogarage_roy_kersten.model.Inspection;
import nl.novi.autogarage_roy_kersten.model.ServiceStatus;
import nl.novi.autogarage_roy_kersten.model.dto.CallListDto;
import nl.novi.autogarage_roy_kersten.service.InspectionService;
import nl.novi.autogarage_roy_kersten.service.ServiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * The InspectionController class ensures that HTTP Requests en Responses are handled and processed further to the InspectionService interface.
 **/

@CrossOrigin
@RestController
@RequestMapping(value = "/services/inspections")
public class InspectionController extends ServiceController {

    private InspectionService inspectionService;


    @Autowired
    public InspectionController(@Qualifier("inspectionServiceImpl") ServiceServiceImpl serviceServiceImpl, InspectionService inspectionService) {
        super(serviceServiceImpl);
        this.inspectionService = inspectionService;
    }

    //Methods
    //Get all Inspections, need to be defined in subclass Inspection, path: "/services/inspections" should only show inspections
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllInspections() {
        return ResponseEntity.ok(inspectionService.getAllInspections());
    }

    //Get inspections by serviceStatus "niet uitvoeren"
    @GetMapping(value = "/calllist")
    public List<CallListDto> getInspectionByStatus(ServiceStatus serviceStatus) {
        var dtos = new ArrayList<CallListDto>();
        List<Inspection> inspections;
        inspections = inspectionService.getInspectionByStatus(ServiceStatus.NIET_UITVOEREN);

        for (Inspection inspection : inspections) {
            dtos.add(CallListDto.fromService(inspection));
        }
        return dtos;
     }

    //Update inspection by idService (issuesFoundInspection), specific for Inspection, needs to be defined in subclass
    @PutMapping("/{idService}")
    public ResponseEntity<Object> updateInspectionById(@PathVariable("idService") long idService, @RequestBody Inspection updateService) {
        inspectionService.updateInspectionById(idService, updateService);
        return ResponseEntity.ok("update Inspection successfully");
    }
}
