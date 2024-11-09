package spring.ws.carrentaldirectoryweb.core.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDate;

@RedisHash("record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecordRedisEntity {
    @Id
    private Integer id;
    private String fio;
    private String phoneNumber;
    private String markName;
    private LocalDate firstDate;
    private LocalDate lastDate;
}
