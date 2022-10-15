package com.devmountain.noteApp.entities;

import javax.persistence.*;

import com.devmountain.noteApp.dtos.NoteDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

@Entity
@Table(name = "Notes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "text")
    private String body;

    @ManyToOne
    @JsonBackReference
    //prevents infinite recursion when you deliver the resource up as JSON through the RESTful API
    // endpoint you will be creating
    private User user;

    public Notes(NoteDto noteDto)
    {
        if(noteDto.getBody() != null)
            this.body = noteDto.getBody();
    }

//    public Notes(){}; - handled by @NoArgsConstructor

//    public Notes(String body, long id){ - handled by @AllArgsConstructor
//        this.body = body;
//        this.id = id;
//    }

    /*
    Handled by @Data
    @Data is a convenient shortcut annotation that bundles the features of
    @ToString , @EqualsAndHashCode , @Getter / @Setter and @RequiredArgsConstructor together
    (https://projectlombok.org/)
     */

//    public String getBody() {
//        return body;
//    }
//
//    public void setBody(String body) {
//        this.body = body;
//    }
//
//    public long getId() {
//        return id;
//    }
//
//    public void setId(long id) {
//        this.id = id;
//    }
}
