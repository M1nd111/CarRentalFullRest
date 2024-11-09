package spring.ws.carrentaldirectoryweb.core.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import spring.ws.carrentaldirectoryweb.core.entity.RecordRedisEntity;

import java.util.Optional;

@Repository
public interface RecordRedisRepository extends CrudRepository<RecordRedisEntity, Integer> {
}
