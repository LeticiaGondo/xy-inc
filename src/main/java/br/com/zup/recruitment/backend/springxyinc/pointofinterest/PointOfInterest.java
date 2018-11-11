package br.com.zup.recruitment.backend.springxyinc.pointofinterest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Created by Fellipe G on 11/11/2018.
 */
@Entity
@SequenceGenerator(name="seq_poi", initialValue=1000, allocationSize=1)
public class PointOfInterest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="seq_poi")
    private Long id; // point of interest's id

    @NotNull
    @NotBlank
    private String name; // point of interest's name

    @PositiveOrZero
    private int xCoordinate; // latitude

    @PositiveOrZero
    private  int yCoordinate; // longitude

    public PointOfInterest() {
        super();
    }

    public PointOfInterest(@NotNull @NotBlank String name, @PositiveOrZero int xCoordinate, @PositiveOrZero int yCoordinate) {
        this.name = name;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
