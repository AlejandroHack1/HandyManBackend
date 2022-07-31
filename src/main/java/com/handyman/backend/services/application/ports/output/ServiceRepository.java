package com.handyman.backend.services.application.ports.output;

import com.handyman.backend.services.application.domain.Service;
import com.handyman.backend.services.application.domain.valueObjs.ServiceId;

import java.util.Optional;

public interface ServiceRepository {

    void store(Service service);

    Optional<Service> get(ServiceId serviceId);

    void update(Service service);

    Boolean delete(ServiceId serviceId);
}
