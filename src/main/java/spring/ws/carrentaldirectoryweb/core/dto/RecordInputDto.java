package spring.ws.carrentaldirectoryweb.core.dto;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Data
public class RecordInputDto {
    private String modelName;
    private String stateNumber;
    private LocalDate date;
    private Integer year;
}
