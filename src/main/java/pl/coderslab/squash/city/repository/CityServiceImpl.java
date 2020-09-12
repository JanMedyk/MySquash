package pl.coderslab.squash.city.repository;

import org.springframework.stereotype.Service;
import pl.coderslab.squash.city.service.CityService;
import pl.coderslab.squash.model.City;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }


    @Override
    public List<City> findAll() {
        return cityRepository.findAll();
    }
}
