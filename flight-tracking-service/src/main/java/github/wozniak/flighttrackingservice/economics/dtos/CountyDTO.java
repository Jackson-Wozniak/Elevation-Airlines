package github.wozniak.flighttrackingservice.economics.dtos;

import github.wozniak.flighttrackingservice.core.utils.NumberFormat;
import github.wozniak.flighttrackingservice.economics.entity.County;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CountyDTO {
    private String name;
    private String state;
    private String gdp;
    private String gdpGrowth;

    public CountyDTO(County county){
        this.name = county.getName();
        this.state = county.getState();
        this.gdp = NumberFormat.currency(county.getGdpUSD());
        this.gdpGrowth = NumberFormat.percent(county.getGdpCAGR());
    }

    public static List<CountyDTO> fromList(List<County> counties){
        return counties.stream().map(CountyDTO::new).toList();
    }
}
