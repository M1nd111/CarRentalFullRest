package spring.ws.carrentaldirectoryweb.core.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsWebDto;
import spring.ws.carrentaldirectoryweb.core.dto.RecordsReadDto;
import spring.ws.carrentaldirectoryweb.core.entity.RecordRedisEntity;
import spring.ws.carrentaldirectoryweb.core.entity.RecordsEntity;
import spring.ws.carrentaldirectoryweb.core.repository.RecordRedisRepository;
import spring.ws.carrentaldirectoryweb.core.repository.RecordsRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class RecordsService {

    private final RecordsRepository recordsRepository; // JPA репозиторий для базы данных
    private final RecordRedisRepository recordRedisRepository; // Redis репозиторий

    // Метод для получения всех записей: сначала из Redis, если нет - из базы данных
    public List<RecordsReadDto> findAll() {
        // Пытаемся получить все записи из Redis
        List<RecordRedisEntity> redisRecords = (List<RecordRedisEntity>) recordRedisRepository.findAll();
        if (!redisRecords.isEmpty()) {
            // Если данные есть в Redis, возвращаем их
            return redisRecords.stream()
                    .map(recordRedisEntity -> RecordsReadDto.builder()
                            .id(recordRedisEntity.getId())
                            .fio(recordRedisEntity.getFio())
                            .phoneNumber(recordRedisEntity.getPhoneNumber())
                            .markName(recordRedisEntity.getMarkName())
                            .first_date(recordRedisEntity.getFirstDate())
                            .last_date(recordRedisEntity.getLastDate())
                            .build())
                    .collect(Collectors.toList());
        }

        // Если данных нет в Redis, получаем их из базы данных
        List<RecordsEntity> dbRecords = recordsRepository.findAll();
        return dbRecords.stream()
                .map(recordsEntity -> RecordsReadDto.builder()
                        .id(recordsEntity.getId())
                        .fio(recordsEntity.getFio())
                        .phoneNumber(recordsEntity.getPhoneNumber())
                        .markName(recordsEntity.getMarkName())
                        .first_date(recordsEntity.getFirstDate())
                        .last_date(recordsEntity.getLastDate())
                        .build())
                .collect(Collectors.toList());
    }

    // Метод для добавления записи в базу данных и Redis
    public Boolean addEntity(RecordsWebDto recordsWebDto) {
        // Сохраняем запись в базе данных (JPA)
        RecordsEntity savedRecord = recordsRepository.save(RecordsEntity.builder()
                .fio(recordsWebDto.getFio())
                .phoneNumber(recordsWebDto.getPhoneNumber())
                .markName(recordsWebDto.getMarkName())
                .firstDate(recordsWebDto.getFirsDate())
                .lastDate(recordsWebDto.getLastDate())
                .build());

        // Сохраняем запись в Redis для кэширования
        recordRedisRepository.save(RecordRedisEntity.builder()
                .id(savedRecord.getId())
                .fio(savedRecord.getFio())
                .phoneNumber(savedRecord.getPhoneNumber())
                .markName(savedRecord.getMarkName())
                .firstDate(savedRecord.getFirstDate())
                .lastDate(savedRecord.getLastDate())
                .build());

        return true;
    }

    // Метод для удаления всех записей из базы данных и Redis
    public Boolean delAll() {
        recordsRepository.deleteAll(); // Удаление из базы данных
        recordRedisRepository.deleteAll(); // Удаление из Redis
        return true;
    }

    // Метод для поиска записи по ID из Redis, если не найдено, ищем в базе данных
//    public Optional<RecordsEntity> findById(Integer id) {
//        // Ищем в Redis
//        Optional<RecordRedisEntity> redisRecord = recordRedisRepository.findById(id);
//        if (redisRecord.isPresent()) {
//            // Если нашли в Redis, возвращаем это
//            return Optional.of(RecordsEntity.builder()
//                                    .id().fio().phoneNumber().markName().firstDate().lastDate()
//
//                    redisRecord.get().getId(),
//                    redisRecord.get().getFio(),
//                    redisRecord.get().getPhoneNumber(),
//                    redisRecord.get().getMarkName(),
//                    redisRecord.get().getFirstDate(),
//                    redisRecord.get().getLastDate()
//            ));
//        }
//
//        // Если не нашли в Redis, ищем в базе данных
//        return recordsRepository.findById(id);
//    }
}
