package spring.ws.carrentaldirectoryweb.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.ws.carrentaldirectoryweb.core.entity.RecordEntity;


@Repository("recordJpaRepository")
public interface RecordRepository extends JpaRepository<RecordEntity, Integer> {
}
