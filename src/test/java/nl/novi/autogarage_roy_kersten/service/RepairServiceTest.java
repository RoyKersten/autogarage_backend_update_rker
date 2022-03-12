package nl.novi.autogarage_roy_kersten.service;

import nl.novi.autogarage_roy_kersten.model.Car;
import nl.novi.autogarage_roy_kersten.model.Customer;
import nl.novi.autogarage_roy_kersten.model.Repair;
import nl.novi.autogarage_roy_kersten.model.ServiceStatus;
import nl.novi.autogarage_roy_kersten.repository.RepairRepository;
import nl.novi.autogarage_roy_kersten.repository.ServiceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RepairServiceTest {

    @Mock
    ServiceRepository serviceRepository;

    @Mock
    RepairRepository repairRepository;

    @InjectMocks
    RepairServiceImpl repairService;

    @Captor
    ArgumentCaptor<Repair> repairCaptor;


    //Method is inherit from Service
    @Test
    void addServiceTest() {
        //Arrange => create Repair as input for test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Repair storedRepair = new Repair(1L, date, ServiceStatus.UITVOEREN, customer, "uitlaat vervangen", null, car);

        when(serviceRepository.save(storedRepair)).thenReturn(storedRepair);                                            //service Repository because of inheritance

        //Act => call method addService
        repairService.addService(storedRepair);                                                                         //storedService needs to have characteristics of a Repair

        //Assert => capture argument and perform assertions
        verify(serviceRepository).save(repairCaptor.capture());                                                         //serviceRepository because of inheritance
        Repair validateRepair = repairCaptor.getValue();                                                                //serviceRepository because of inheritance

        assertThat(validateRepair.getIdService()).isEqualTo(1L);
        assertThat(validateRepair.getServiceDate()).isEqualTo("2020-06-08");
        assertThat(validateRepair.getServiceStatus()).isEqualTo(ServiceStatus.UITVOEREN);
        assertThat(validateRepair.getIssuesToRepair()).isEqualTo("uitlaat vervangen");
        assertThat(validateRepair.getServiceLine()).isEqualTo(null);
        assertThat(validateRepair.getCar()).isEqualTo(car);
    }

    //Method is inherit from Service
    @Test
    void getServiceByIdTest() {
        //Arrange => create repair object as input for test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Repair repair = new Repair(1L, date, ServiceStatus.UITVOEREN, customer, "uitlaat vervangen", null, car);


        when(serviceRepository.existsById(1L)).thenReturn(true);
        when(serviceRepository.findById(1L)).thenReturn(repair);

        //Act => call method getServiceById
        Repair validateRepair = (Repair) repairService.getServiceById(1L);

        //Assert => validate return of method getServiceById and perform assertions
        assertThat(validateRepair.getIdService()).isEqualTo(1L);
        assertThat(validateRepair.getServiceDate()).isEqualTo("2020-06-08");
        assertThat(validateRepair.getServiceStatus()).isEqualTo(ServiceStatus.UITVOEREN);
        assertThat(validateRepair.getIssuesToRepair()).isEqualTo("uitlaat vervangen");
        assertThat(validateRepair.getServiceLine()).isEqualTo(null);
        assertThat(validateRepair.getCar()).isEqualTo(car);
    }

    //Method is inherit from Service
    @Test
    void deleteServiceByIdTest() {
        //Arrange => check if idRepair exists and return boolean true to pass BadRequestException check
        when(serviceRepository.existsById(1L)).thenReturn(true);

        //Act => call method deleteServiceById
        repairService.deleteServiceById(1L);

        //Assert => verify if mock serviceRepository.deleteById has been called one time
        verify(serviceRepository, times(1)).deleteById(1L);
    }

    //Method is inherit from Service
    @Test
    void updateServiceStatusByIdTest() {
        //Arrange => create repair object as input for test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Repair storedRepair = new Repair(1L, date, ServiceStatus.UITVOEREN, customer, "uitlaat vervangen", null, car);
        when(serviceRepository.existsById(1L)).thenReturn(true);
        when(serviceRepository.findById(1L)).thenReturn(storedRepair);

        Repair updateServiceStatus = new Repair(1L, ServiceStatus.VOLTOOID);                                    //ServiceStatus can only have status: "UITVOEREN" , "NIET_UITVOEREN" OR "VOLTOOID" as defined in enum class ServiceStatus

        //Act => call method updateServiceStatusById
        repairService.updateServiceStatusById(1L, updateServiceStatus);

        //Assert => capture argument and perform assertions
        verify(serviceRepository).save(repairCaptor.capture());                                                         //serviceRepository because of inheritance
        Repair validateRepair = repairCaptor.getValue();

        assertThat(validateRepair.getServiceStatus()).isEqualTo(ServiceStatus.VOLTOOID);                                //validate if serviceStatus has been updated correctly
    }


    @Test
    void getAllRepairsTest() {
        //Arrange => create 3 repairs as input for the test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Repair repair1 = new Repair(1L, date, ServiceStatus.UITVOEREN, customer, "uitlaat vervangen", null, car);
        Repair repair2 = new Repair(2L, date, ServiceStatus.NIET_UITVOEREN, customer, "remmen vervangen", null, car);
        Repair repair3 = new Repair(3L, date, ServiceStatus.VOLTOOID, customer, "olie verversen", null, car);

        List<Repair> repairs = new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);
        repairs.add(repair3);

        when(repairRepository.findAll()).thenReturn(repairs);

        //Act => call method getAllRepairs
        List<Repair> validateRepairs = repairService.getAllRepairs();

        //Assert => validate the return of method getAllRepairs and perform assertions
        assertThat(validateRepairs.size()).isEqualTo(3);
        assertThat(validateRepairs.get(2).getIdService()).isEqualTo(3L);
        assertThat(validateRepairs.get(2).getServiceDate()).isEqualTo("2020-06-08");
        assertThat(validateRepairs.get(2).getServiceStatus()).isEqualTo(ServiceStatus.VOLTOOID);
        assertThat(validateRepairs.get(2).getIssuesToRepair()).isEqualTo("olie verversen");
        assertThat(validateRepairs.get(2).getServiceLine()).isEqualTo(null);
        assertThat(validateRepairs.get(2).getCar()).isEqualTo(car);
    }

    @Test
    void getRepairByStatusTest() {
        //Arrange => create objects as input for test (test should return all repairs with status "VOLTOOID")
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Repair repair1 = new Repair(1L, date, ServiceStatus.VOLTOOID, customer, "uitlaat vervangen", null, car);
        Repair repair2 = new Repair(2L, date, ServiceStatus.UITVOEREN, customer, "remmen vervangen", null, car);

        List <Repair> repairs = new ArrayList<>();
        repairs.add(repair1);
        repairs.add(repair2);

        when(repairRepository.findByServiceStatus(ServiceStatus.VOLTOOID)).thenReturn(repairs);

        //Act => call method getRepairByStatus
        List<Repair> validateRepairs = repairService.getRepairByStatus(ServiceStatus.VOLTOOID);

        //Assert => validate the return of method getAllRepairs and perform assertions
        verify(repairRepository,times(1)).findByServiceStatus(ServiceStatus.VOLTOOID);
        assertThat(validateRepairs.get(0).getServiceStatus()).isEqualTo(ServiceStatus.VOLTOOID);
        assertThat(validateRepairs.get(1).getServiceStatus()).isNotEqualTo(ServiceStatus.VOLTOOID);                     //Status is not equal
    }

    @Test
    void updateRepairByIdTest() {
        //Arrange => create updateRepair and storedRepair object as input for test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Repair updateRepair = new Repair(1L, date, ServiceStatus.VOLTOOID, customer, "uitlaat vervangen", null, car);
        Repair storedRepair = new Repair(1L, date, ServiceStatus.UITVOEREN, customer, "remmen vervangen", null, car);

        when(repairRepository.existsById(1L)).thenReturn(true);
        when(repairRepository.findById(1L)).thenReturn(storedRepair);

        //Act => call method updateRepairById
        repairService.updateRepairById(1L, updateRepair);

        //Assert => check if storedRepair has been updated with values of updateRepair
        assertThat(storedRepair.getIdService()).isEqualTo(1L);
        assertThat(storedRepair.getServiceDate()).isEqualTo("2020-06-08");
        assertThat(storedRepair.getServiceStatus()).isEqualTo(ServiceStatus.VOLTOOID);
        assertThat(storedRepair.getIssuesToRepair()).isEqualTo("uitlaat vervangen");
        assertThat(storedRepair.getServiceLine()).isEqualTo(null);
        assertThat(storedRepair.getCar()).isEqualTo(car);
    }
}
