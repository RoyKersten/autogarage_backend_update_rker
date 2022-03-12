package nl.novi.autogarage_roy_kersten.controller;

import nl.novi.autogarage_roy_kersten.model.Activity;
import nl.novi.autogarage_roy_kersten.service.ActivityService;
import nl.novi.autogarage_roy_kersten.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/**
 * The ActivityController class ensures that HTTP Requests en Responses are handled and processed further to the ActivityService interface.
**/

@CrossOrigin
@RestController
@RequestMapping(value = "/items/activities")
public class ActivityController extends ItemController {

    //Attributes
    private ActivityService activityService;

    //Constructors
    @Autowired
    public ActivityController(@Qualifier("activityServiceImpl") ItemService itemService, ActivityService activityService) {
        super(itemService);
        this.activityService = activityService;
    }

    //Methods
    //Get all Activities, need to be defined in subclass Activity, path: "/items/activities" should only show activities
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllActivities() {
        return ResponseEntity.ok(activityService.getAllActivities());
    }

    //Update Activity by idItem
    @PutMapping("/{idItem}")
    public ResponseEntity<Object> updateActivityById(@PathVariable("idItem") long idItem, @RequestBody Activity updateActivity) {
        activityService.updateActivityById(idItem, updateActivity);
        return ResponseEntity.ok("update item successfully");
    }

}
