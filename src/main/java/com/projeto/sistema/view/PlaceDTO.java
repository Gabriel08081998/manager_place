package com.projeto.sistema.view;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDTO {



    private String name; //nome

    private String state;


    public void setSlug(String slugify) {
    }
}
