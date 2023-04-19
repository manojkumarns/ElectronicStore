package com.lcwd.electronic.store.entites;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @Column(name="id")
    private String   categoryId;
    @Column(name = "catagory_title" , length = 60 , nullable = false)
    private String  title;
    @Column(name = "catagory_desc" , length = 500)
    private String discription;
    private String coverImage;

}
