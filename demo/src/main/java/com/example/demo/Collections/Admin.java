package com.example.demo.Collections;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "admin")
public class Admin {

    @Id
    public String id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime terminated_at;

    public Admin(LocalDateTime time) {
        this.terminated_at = time;
    }

    public Admin() {

    }

    public String getId() {return id;}

    public LocalDateTime getTerminated_at() { return terminated_at; }

    public void setTerminated_at(LocalDateTime time) { this.terminated_at = time; }

}
