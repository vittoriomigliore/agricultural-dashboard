package it.vittoriomigliore.agriculturaldashboard.core.service;

import it.vittoriomigliore.agriculturaldashboard.core.entity.Field;
import it.vittoriomigliore.agriculturaldashboard.core.entity.Irrigation;
import it.vittoriomigliore.agriculturaldashboard.core.repository.IrrigationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class IrrigationService {
    IrrigationRepository irrigationRepository;

    @Autowired
    public IrrigationService(IrrigationRepository irrigationRepository) {
        this.irrigationRepository = irrigationRepository;
    }

    public Irrigation saveIrrigation(Irrigation irrigation) {
        return irrigationRepository.save(irrigation);
    }

    public List<Irrigation> getLastFiveMinutesIrrigationByField(Field field) {
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(5);
        return irrigationRepository.findByFieldAndAfterDateTime(field, dateTime);
    }
}
