package nl.novi.autogarage_roy_kersten.controller;


import nl.novi.autogarage_roy_kersten.exception.BadRequestException;
import nl.novi.autogarage_roy_kersten.model.Car;
import nl.novi.autogarage_roy_kersten.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;

/**
 * The CarController class ensures that HTTP Requests en Responses are handled and processed further to the CarService interface.
 **/

@CrossOrigin // no addition means no restriction, adding => (origins = "http://localhost:1234") means only this origin has access
@RestController
@RequestMapping(value = "/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    //Methods

    //Create a new Car
    @PostMapping(value = "")
    public ResponseEntity<Object> addCar(@RequestBody Car car) {
        long newId = carService.addCar(car);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{idCar}")
                .buildAndExpand(newId).toUri();
        return ResponseEntity.created(location).body(location);
    }

    //Get all Cars
    @GetMapping(value = "")
    public ResponseEntity<Object> getAllCars() {
        return ResponseEntity.ok(carService.getAllCars());
    }

    //Get customer by idCar
    @GetMapping("/{idCar}")
    public ResponseEntity<Object> getCarById(@PathVariable("idCar") long idCar) {
        Car car = carService.getCarById(idCar);
        return ResponseEntity.ok(car);
    }

    //Delete Customer by idCar
    @DeleteMapping("/{idCar}")
    public ResponseEntity<Object> deleteCarById(@PathVariable("idCar") long idCar) {
        carService.deleteCarById(idCar);
        return ResponseEntity.ok("car successfully deleted");
    }

    //Update car by idCar
    @PutMapping("/{idCar}")
    public ResponseEntity<Object> updateCarById(@PathVariable("idCar") long idCar, @RequestBody Car updateCar) {
        carService.updateCarById(idCar, updateCar);
        return ResponseEntity.ok("update car successfully");
    }

    //Store CarPapers into the database by idCar
    @PostMapping("/{idCar}/carpaper")
    public void uploadCarPapers(@PathVariable("idCar") Long idCarPaper, @RequestParam("file") MultipartFile file) throws IOException {
        if (file.getContentType() == null || !file.getContentType().equals("application/pdf")) {
            throw new BadRequestException();
        }
        carService.uploadCarPaper(idCarPaper, file);
    }

    //Get CarPapers from the database by idCar
    @GetMapping("/{idCar}/carpaper")
    public ResponseEntity<byte[]> getCarPapers(@PathVariable("idCar") Long idCarPaper) {
        var licenseBytes = carService.getCarPaper(idCarPaper);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"carpaper.pdf\"")
                .body(licenseBytes);
    }


}
