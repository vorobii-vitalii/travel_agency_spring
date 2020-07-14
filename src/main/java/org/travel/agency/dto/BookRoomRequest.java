package org.travel.agency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRoomRequest {
    private LocalDate start;
    private LocalDate end;
    private Long roomId;
}
