package com.handyman.backend.infraestructure.controllers;

import com.handyman.backend.infraestructure.models.ApplicationError;
import com.handyman.backend.infraestructure.models.CalculoDTO;
import com.handyman.backend.infraestructure.models.ServiceDTO;
import com.handyman.backend.services.application.ports.input.*;
import com.handyman.backend.services.application.services.QueryCalculoByIdAndWeekService;
import com.handyman.backend.services.application.services.QueryCalculoByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ServiceController {

    private final CreateServiceUseCase createServiceUseCase;
    private final QueryServiceByIdUseCase queryServiceByIdUseCase;
    private final UpdateServiceUseCase updateServiceUseCase;
    private final DeleteServiceUseCase deleteServiceUseCase;

    private final CreateCalculoUseCase createCalculoUseCase;
    private final QueryCalculoByIdService queryCalculoByIdService;

    private final QueryCalculoByIdAndWeekService queryCalculoByIdAndWeekService;

    public ServiceController(CreateServiceUseCase createServiceUseCase, QueryServiceByIdUseCase queryServiceByIdUseCase, UpdateServiceUseCase updateServiceUseCase, DeleteServiceUseCase deleteServiceUseCase, DeleteServiceUseCase deleteServiceUseCase1, CreateCalculoUseCase createCalculoUseCase, QueryCalculoByIdService queryCalculoByIdService, QueryCalculoByIdAndWeekService queryCalculoByIdAndWeekService) {
        this.createServiceUseCase = createServiceUseCase;
        this.queryServiceByIdUseCase = queryServiceByIdUseCase;
        this.updateServiceUseCase = updateServiceUseCase;
        this.deleteServiceUseCase = deleteServiceUseCase1;

        //calculo horas
        this.createCalculoUseCase = createCalculoUseCase;
        this.queryCalculoByIdService = queryCalculoByIdService;
        this.queryCalculoByIdAndWeekService = queryCalculoByIdAndWeekService;
    }

    @RequestMapping(value = "/services", method = RequestMethod.POST)
    public ResponseEntity<?> createService(@RequestBody ServiceDTO serviceDTO) {
        try {
            ServiceDTO serviceDTOOutput = createServiceUseCase.execute(serviceDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(serviceDTOOutput);
        } catch (NullPointerException | IllegalArgumentException e) {
            ApplicationError applicationError = new ApplicationError("InputDataValidationError", "Bad input data",
                    Map.of("error", e.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception e) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            System.out.println("Error......: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }

    }

    @RequestMapping(value = "/services/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getServiceById(@PathVariable("id") Long id) {
        try {
            Optional<ServiceDTO> serviceDTO = queryServiceByIdUseCase.execute(id);
            if (serviceDTO.isPresent()) {
                return ResponseEntity.ok(serviceDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No exist service with this id");
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InpuDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }

    }

    @RequestMapping(value = "/services", method = RequestMethod.PUT)
    public ResponseEntity<?> updateService(@RequestBody ServiceDTO serviceDTO) {
        try {
            ServiceDTO serviceDTOOutput = updateServiceUseCase.execute(serviceDTO);
            return ResponseEntity.ok(serviceDTOOutput);
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InpuDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }

    @RequestMapping(value="/services/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteService(@PathVariable("id") Long id) {
        try {
            Boolean result = deleteServiceUseCase.execute(id);
            if (result) {
                return ResponseEntity.ok("Deleted");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Service can not be deleted");
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InpuDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }
    }


    @RequestMapping(value = "/calculo/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getCalculoById(@PathVariable("id") String id) {
        try {
            Optional<CalculoDTO> calculoDTO = queryCalculoByIdService.execute(id);
            if (calculoDTO.isPresent()) {
                return ResponseEntity.ok(calculoDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No exist service with this id");
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InpuDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }

    }


    @RequestMapping(value = "/calculo/{id}/{semanas}", method = RequestMethod.GET)
    public ResponseEntity<?> getCalculoByIdAndWeek(@PathVariable("id") String id, @PathVariable String semanas) {
        try {
            Optional<CalculoDTO> calculoDTO = queryCalculoByIdAndWeekService.execute(id, semanas);
            if (calculoDTO.isPresent()) {
                return ResponseEntity.ok(calculoDTO.get());
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No exist service with this id");
            }
        } catch (NullPointerException | IllegalArgumentException exception) {
            ApplicationError applicationError = new ApplicationError("InpuDataValidationError", "Bad input data",
                    Map.of("error", exception.getMessage()));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(applicationError);
        } catch (Exception exception) {
            ApplicationError applicationError = new ApplicationError("SystemError", "Try more later", Map.of());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(applicationError);
        }

    }

}

