package nl.novi.autogarage_roy_kersten.controller;

import nl.novi.autogarage_roy_kersten.model.Part;
import nl.novi.autogarage_roy_kersten.service.ItemService;
import nl.novi.autogarage_roy_kersten.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The PartController class ensures that HTTP Requests en Responses are handled and processed further to the PartService interface.
**/

@CrossOrigin
@RestController
@RequestMapping(value = "/items/parts")
public class PartController extends ItemController {

    //Attributes
    private PartService partService;

    //Constructors
    @Autowired
    public PartController(@Qualifier("partServiceImpl") ItemService itemService, PartService partService) {
        super(itemService);
        this.partService = partService;
    }

    //Methods

    //Get all Parts, need to be defined in subclass Part, path: "/items/parts" should only show parts
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllParts() {
        return ResponseEntity.ok(partService.getAllParts());
    }

    //Update Part by idItem
    @PutMapping("/{idItem}")
    public ResponseEntity<Object> updatePartById(@PathVariable("idItem") long idItem, @RequestBody Part updatePart) {
        partService.updatePartById(idItem, updatePart);
        return ResponseEntity.ok("update Part successfully");
    }


}
