package com.riatServer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.riatServer.domain.Task;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TaskSaveDto {
    private Long id;
    private String name;
    private String description;
    private List<Long> user_id;
    private Long owner_id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime ownDate;


}