package com.lcwd.electronic.store.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private String   categoryId;
    @NotBlank(message = "Title is required")
    @Size(min = 4 , message = "title must be min 4 characters")
    private String  title;
    @NotBlank(message = "description requires")
    private String discription;
    private String coverImage;
}
