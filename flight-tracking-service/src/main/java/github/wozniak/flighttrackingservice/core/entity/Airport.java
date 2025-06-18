package github.wozniak.flighttrackingservice.core.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "airport")
@Table(name = "airports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Airport {

    @Id
    @Column(name = "icao_code")
    private String icaoCode;

    @Column(name = "name")
    private String name;

    @Column(name = "size")
    private String size;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "continent")
    private String continent;

    @Column(name = "country")
    private String country;

    @Column(name = "state")
    private String state;

    @Column(name = "city")
    private String city;

    @Column(name = "passengersPerYear")
    private int passengersPerYear;

    @Column(name = "runwayLengthFt")
    private int runwayLengthFt;

    public Airport(Builder builder){
        this.icaoCode = builder.icaoCode;
        this.name = builder.name;
        this.size = builder.size;
        this.latitude = builder.latitude;
        this.longitude = builder.longitude;
        this.continent = builder.continent;
        this.country = builder.country;
        this.state = builder.state;
        this.city = builder.city;
        this.passengersPerYear = builder.passengersPerYear;
        this.runwayLengthFt = builder.runwayLengthFt;
    }

    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof Airport)) return false;
        return this.icaoCode.equalsIgnoreCase(((Airport) obj).getIcaoCode());
    }

    public static class Builder{
        private final String icaoCode;
        private final String name;
        private final String size;
        private double latitude;
        private double longitude;
        private String continent;
        private String country;
        private String state;
        private String city;
        private int passengersPerYear;
        private int runwayLengthFt;

        public Builder(String icaoCode, String name, String size){
            this.icaoCode = icaoCode;
            this.name = name;
            this.size = size;
        }

        public Builder coordinates(double latitude, double longitude){
            this.latitude = latitude;
            this.longitude = longitude;

            return this;
        }

        public Builder location(String continent, String country, String state, String city){
            this.continent = continent;
            this.country = country;
            this.state = state;
            this.city = city;

            return this;
        }

        public Builder specs(int passengersPerYear, int runwayLengthFt){
            this.passengersPerYear = passengersPerYear;
            this.runwayLengthFt = runwayLengthFt;
            return this;
        }

        public Airport build(){
            return new Airport(this);
        }
    }
}
