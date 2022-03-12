package nl.novi.autogarage_roy_kersten.service;

import nl.novi.autogarage_roy_kersten.model.Car;
import nl.novi.autogarage_roy_kersten.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @Mock
    CarRepository carRepository;

    @InjectMocks
    CarServiceImpl carService;

    @Captor
    ArgumentCaptor<Car> carCaptor;

    @Test
    void addCarTest() {
        //Arrange => create car as input for test
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        when(carRepository.save(car)).thenReturn(car);

        //Act => call method addCar
        carService.addCar(car);

        //Assert => capture argument and perform assertions
        verify(carRepository).save(carCaptor.capture());
        Car validateCar = carCaptor.getValue();

        assertThat(validateCar.getIdCar()).isEqualTo(1L);
        assertThat(validateCar.getBrand()).isEqualTo("volkswagen");
        assertThat(validateCar.getModel()).isEqualTo("polo");
        assertThat(validateCar.getYearOfConstruction()).isEqualTo("2021");
        assertThat(validateCar.getLicensePlateNumber()).isEqualTo("58-AAA-53");
    }

    @Test
    void getAllCars() {
        //Arrange => create 3 cars as input for the test
        Car car1 = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        Car car2 = new Car(2L, "Renault", "megan", "2014", "33-13-FG", 1L);
        Car car3 = new Car(3L, "Ford", "mustang", "2021", "12-ABA-41", 1L);

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        when(carRepository.findAll()).thenReturn(cars);

        //Act => call method getAllCars
        List<Car> validateCars = carService.getAllCars();

        //Assert => validate the return of method getAllCars and perform assertions
        assertThat(validateCars.size()).isEqualTo(3);                                                              //check if the size of the List is 3
        assertThat(validateCars.get(0).getBrand()).isEqualTo("volkswagen");                                        //check if car 1 has been added to the List correctly
        assertThat(validateCars.get(0).getModel()).isEqualTo("polo");
        assertThat(validateCars.get(0).getYearOfConstruction()).isEqualTo("2021");
        assertThat(validateCars.get(0).getLicensePlateNumber()).isEqualTo("58-AAA-53");
    }

    @Test
    void getCarByIdTest() {
        //Arrange => create car object as input for test
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        when(carRepository.existsById(1L)).thenReturn(true);
        when(carRepository.findById(1L)).thenReturn(car);

        //Act => call method getCarById
        Car validateCar = carService.getCarById(1L);

        //Assert => validate return of method getCarById and perform assertions
        assertThat(validateCar.getBrand()).isEqualTo("volkswagen");
        assertThat(validateCar.getModel()).isEqualTo("polo");
        assertThat(validateCar.getYearOfConstruction()).isEqualTo("2021");
        assertThat(validateCar.getLicensePlateNumber()).isEqualTo("58-AAA-53");
    }

    @Test
    void deleteCarByIdTest() {
        //Arrange => check if idCar exists and return boolean true to pass BadRequestException check
        when(carRepository.existsById(1L)).thenReturn(true);

        //Act => call method deleteCarById
        carService.deleteCarById(1L);

        //Assert => verify if mock carRepository.deleteById has been called one time
        verify(carRepository, times(1)).deleteById(1L);
    }


    @Test
    void updateCarById() {

        //Arrange => create updateCar and storedCar object as input for test
        Car updateCar = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        Car storedCar = new Car(1L, "Renault", "megan", "2014", "33-13-FG", 1L);

        when(carRepository.existsById(1L)).thenReturn(true);
        when(carRepository.findById(1L)).thenReturn(storedCar);

        //Act => call method updateCarById
        carService.updateCarById(1L, updateCar);

        //Assert => check if storedCar has now the values of updateCar
        assertThat(storedCar.getBrand()).isEqualTo("volkswagen");
        assertThat(storedCar.getModel()).isEqualTo("polo");
        assertThat(storedCar.getYearOfConstruction()).isEqualTo("2021");
        assertThat(storedCar.getLicensePlateNumber()).isEqualTo("58-AAA-53");
    }

    @Test
    void uploadCarPaperTest() throws IOException {
        //Arrange => create a car object for test
        var optionalCarPaper = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        Long idCarPaper = 1L;
        when(carRepository.findById(idCarPaper)).thenReturn(Optional.of(optionalCarPaper));                             //create mock object when carRepository.findById is called

        // Create a testFile for upload
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "testFile.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Just some content for testFile!".getBytes()
        );

        var carPaper = new Car();
        carPaper.setCarPaper(file.getBytes());
        when(carRepository.save(optionalCarPaper)).thenReturn(carPaper);                                                //create mock object when carRepository.save() is called

        //Act call uploadCarPaper Method
        carService.uploadCarPaper(1L, file);

        //Assert
        verify(carRepository,times(1)).findById(idCarPaper);                                     //verify if carRepository.findById has been called one time
        assertThat(carPaper.getCarPaper()).isNotEmpty();                                                                //verify if carPaper is not empty
    }

    @Test
    void getCarPaperTest() throws IOException {
        //Arrange => create a car object for test
        var optionalCarPaper = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        Long idCarPaper = 1L;
        when(carRepository.findById(idCarPaper)).thenReturn(Optional.of(optionalCarPaper));                             //create mock object when carRepository.findById is called

        // Create a testFile and store
        MockMultipartFile file = new MockMultipartFile(
                "file",
                "testFile.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Just some content for testFile!".getBytes()
        );

        optionalCarPaper.setCarPaper(file.getBytes());                                                                  //store testFile


        //Act call getCarPaper Method
        var validateCarPaperNotEmpty = carService.getCarPaper(1L);

        //Assert
        verify(carRepository,times(1)).findById(idCarPaper);                                     //verify if carRepository.findById has been called one time
        assertThat(validateCarPaperNotEmpty).isNotEmpty();                                                              //verify if carPaper is not empty


    }

}


