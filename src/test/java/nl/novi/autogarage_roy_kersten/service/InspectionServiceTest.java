package nl.novi.autogarage_roy_kersten.service;

import nl.novi.autogarage_roy_kersten.model.Car;
import nl.novi.autogarage_roy_kersten.model.Customer;
import nl.novi.autogarage_roy_kersten.model.Inspection;
import nl.novi.autogarage_roy_kersten.model.ServiceStatus;
import nl.novi.autogarage_roy_kersten.repository.InspectionRepository;
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
public class InspectionServiceTest {

    @Mock
    ServiceRepository serviceRepository;

    @Mock
    InspectionRepository inspectionRepository;

    @InjectMocks
    InspectionServiceImpl inspectionService;

    @Captor
    ArgumentCaptor<Inspection> inspectionCaptor;


    //Method is inherit from Service
    @Test
    void addServiceTest() {
        //Arrange => create Repair as input for test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Inspection storedInspection = new Inspection (1L, date , ServiceStatus.UITVOEREN ,customer,"banden profiel te laag < 2mm, remmen achter vervangen", null, car);

        when(serviceRepository.save(storedInspection)).thenReturn(storedInspection);                                    //service Repository because of inheritance

        //Act => call method addService
        inspectionService.addService(storedInspection);                                                                 //storedService needs to have characteristics of a Repair

        //Assert => capture argument and perform assertions
        verify(serviceRepository).save(inspectionCaptor.capture());                                                     //serviceRepository because of inheritance
        Inspection validateInspection = inspectionCaptor.getValue();                                                    //serviceRepository because of inheritance

        assertThat(validateInspection.getIdService()).isEqualTo(1L);
        assertThat(validateInspection.getServiceDate()).isEqualTo("2020-06-08");
        assertThat(validateInspection.getServiceStatus()).isEqualTo(ServiceStatus.UITVOEREN);
        assertThat(validateInspection.getIssuesFoundInspection()).isEqualTo("banden profiel te laag < 2mm, remmen achter vervangen");
        assertThat(validateInspection.getServiceLine()).isEqualTo(null);
        assertThat(validateInspection.getCar()).isEqualTo(car);
    }

    //Method is inherit from Service
    @Test
    void getServiceByIdTest() {
        //Arrange => create repair object as input for test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Inspection inspection = new Inspection (1L, date , ServiceStatus.UITVOEREN ,customer,"banden profiel te laag < 2mm, remmen achter vervangen", null, car);

        when(serviceRepository.existsById(1L)).thenReturn(true);
        when(serviceRepository.findById(1L)).thenReturn(inspection);

        //Act => call method getServiceById
        Inspection validateInspection = (Inspection) inspectionService.getServiceById(1L);

        //Assert => validate return of method getServiceById and perform assertions
        assertThat(validateInspection.getIdService()).isEqualTo(1L);
        assertThat(validateInspection.getServiceDate()).isEqualTo("2020-06-08");
        assertThat(validateInspection.getServiceStatus()).isEqualTo(ServiceStatus.UITVOEREN);
        assertThat(validateInspection.getIssuesFoundInspection()).isEqualTo("banden profiel te laag < 2mm, remmen achter vervangen");
        assertThat(validateInspection.getServiceLine()).isEqualTo(null);
        assertThat(validateInspection.getCar()).isEqualTo(car);
    }

    //Method is inherit from Service
    @Test
    void deleteServiceByIdTest() {
        //Arrange => check if idRepair exists and return boolean true to pass BadRequestException check
        when(serviceRepository.existsById(1L)).thenReturn(true);

        //Act => call method deleteServiceById
        inspectionService.deleteServiceById(1L);                                                                //inspectionService

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
        Inspection storedInspection = new Inspection(1L, date, ServiceStatus.UITVOEREN, customer, "banden profiel te laag < 2mm, remmen achter vervangen", null, car);
        when(serviceRepository.existsById(1L)).thenReturn(true);
        when(serviceRepository.findById(1L)).thenReturn(storedInspection);

        Inspection updateServiceStatus = new Inspection(1L, ServiceStatus.NIET_UITVOEREN);                     //ServiceStatus can only have status: "UITVOEREN" , "NIET_UITVOEREN" OR "VOLTOOID" as defined in enum class ServiceStatus

        //Act => call method updateServiceStatusById
        inspectionService.updateServiceStatusById(1L, updateServiceStatus);

        //Assert => capture argument and perform assertions
        verify(serviceRepository).save(inspectionCaptor.capture());                                                     //serviceRepository because of inheritance
        Inspection validateInspection = inspectionCaptor.getValue();

        assertThat(validateInspection.getServiceStatus()).isEqualTo(ServiceStatus.NIET_UITVOEREN);                            //validate if serviceStatus has been updated correctly
    }

    @Test
    void getAllInspectionsTest() {
        //Arrange => create 3 repairs as input for the test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Inspection inspection1 = new Inspection(1L, date, ServiceStatus.VOLTOOID, customer, "uitlaat vervangen", null, car);
        Inspection inspection2 = new Inspection(2L, date, ServiceStatus.NIET_UITVOEREN, customer, "remmen vervangen", null, car);
        Inspection inspection3 = new Inspection(3L, date, ServiceStatus.UITVOEREN, customer, "olie verversen", null, car);

        List<Inspection> inspections = new ArrayList<>();
        inspections.add(inspection1);
        inspections.add(inspection2);
        inspections.add(inspection3);

        when(inspectionRepository.findAll()).thenReturn(inspections);

        //Act => call method getAllRepairs
        List<Inspection> validateInspections = inspectionService.getAllInspections();

        //Assert => validate the return of method getAllRepairs and perform assertions
        assertThat(validateInspections.size()).isEqualTo(3);
        assertThat(validateInspections.get(2).getIdService()).isEqualTo(3L);
        assertThat(validateInspections.get(2).getServiceDate()).isEqualTo("2020-06-08");
        assertThat(validateInspections.get(2).getServiceStatus()).isEqualTo(ServiceStatus.UITVOEREN);
        assertThat(validateInspections.get(2).getIssuesFoundInspection()).isEqualTo("olie verversen");
        assertThat(validateInspections.get(2).getServiceLine()).isEqualTo(null);
        assertThat(validateInspections.get(2).getCar()).isEqualTo(car);
    }

    @Test
    void getInspectionByStatusTest() {
        //Arrange => create objects as input for test (test should return all repairs with status "VOLTOOID")
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Inspection inspection1 = new Inspection(1L, date, ServiceStatus.NIET_UITVOEREN, customer, "uitlaat vervangen", null, car);
        Inspection inspection2 = new Inspection(2L, date, ServiceStatus.UITVOEREN, customer, "remmen vervangen", null, car);

        List <Inspection> inspections = new ArrayList<>();
        inspections.add(inspection1);
        inspections.add(inspection2);

        when(inspectionRepository.findByServiceStatus(ServiceStatus.NIET_UITVOEREN)).thenReturn(inspections);

        //Act => call method getRepairByStatus
        List<Inspection> validateInspections = inspectionService.getInspectionByStatus(ServiceStatus.NIET_UITVOEREN);

        //Assert => validate the return of method getAllRepairs and perform assertions
        verify(inspectionRepository,times(1)).findByServiceStatus(ServiceStatus.NIET_UITVOEREN);
        assertThat(validateInspections.get(0).getServiceStatus()).isEqualTo(ServiceStatus.NIET_UITVOEREN);
        assertThat(validateInspections.get(1).getServiceStatus()).isNotEqualTo(ServiceStatus.NIET_UITVOEREN);            //Status is not equal
    }

    @Test
    void updateInspectionByIdTest() {
        //Arrange => create updateInspection and storedInspection object as input for test
        Customer customer = new Customer(1L, "Karel", "Hoekstra", "+31612345678", "karel.hoekstra@mail.com");
        Car car = new Car(1L, "volkswagen", "polo", "2021", "58-AAA-53", 1L);
        LocalDate date = LocalDate.of(2020, 6, 8);
        Inspection updateInspection = new Inspection(1L, date, ServiceStatus.VOLTOOID, customer, "uitlaat vervangen", null, car);
        Inspection storedInspection = new Inspection(1L, date, ServiceStatus.UITVOEREN, customer, "remmen vervangen", null, car);

        when(inspectionRepository.existsById(1L)).thenReturn(true);
        when(inspectionRepository.findById(1L)).thenReturn(storedInspection);

        //Act => call method updateInspectionById
        inspectionService.updateInspectionById(1L, updateInspection);

        //Assert => check if storedInspection has been updated with values of updateInspection
        assertThat(storedInspection.getIdService()).isEqualTo(1L);
        assertThat(storedInspection.getServiceDate()).isEqualTo("2020-06-08");
        assertThat(storedInspection.getServiceStatus()).isEqualTo(ServiceStatus.VOLTOOID);
        assertThat(storedInspection.getIssuesFoundInspection()).isEqualTo("uitlaat vervangen");
        assertThat(storedInspection.getServiceLine()).isEqualTo(null);
        assertThat(storedInspection.getCar()).isEqualTo(car);
    }

}
