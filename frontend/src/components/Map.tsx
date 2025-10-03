import React, { useLayoutEffect, useRef, useState } from "react";
import * as am5 from "@amcharts/amcharts5";
import * as am5map from "@amcharts/amcharts5/map";
import am5geodata_worldLow from "@amcharts/amcharts5-geodata/worldLow";
import am5themes_Animated from "@amcharts/amcharts5/themes/Animated";

interface City {
  id: string;
  title: string;
  destinations?: string[];
  geometry: { type: string; coordinates: [number, number] };
  zoomLevel?: number;
  zoomPoint?: { longitude: number; latitude: number };
}

//https://www.amcharts.com/demos/flight-routes-map/
const Map: React.FC = () => {
  const chartRef = useRef<HTMLDivElement>(null);
  const [currentId, setCurrentId] = useState<string>("KBOS");

  useLayoutEffect(() => {
    if (!chartRef.current) return;

    const root = am5.Root.new(chartRef.current);
    root.setThemes([am5themes_Animated.new(root)]);

    // Map chart
    const chart = root.container.children.push(
      am5map.MapChart.new(root, {
        panX: "translateX",
        panY: "translateY",
        projection: am5map.geoMercator()
      })
    );

    // Container for labels & buttons
    const cont = chart.children.push(
      am5.Container.new(root, { layout: root.horizontalLayout, x: 20, y: 40 })
    );

    cont.children.push(am5.Label.new(root, { centerY: am5.p50, text: "Map" }));

    const switchButton = cont.children.push(
      am5.Button.new(root, {
        themeTags: ["switch"],
        centerY: am5.p50,
        icon: am5.Circle.new(root, { themeTags: ["icon"] })
      })
    );

    switchButton.on("active", () => {
      if (!switchButton.get("active")) {
        chart.set("projection", am5map.geoMercator());
        chart.set("panX", "translateX");
        chart.set("panY", "translateY");
      } else {
        chart.set("projection", am5map.geoOrthographic());
        chart.set("panX", "rotateX");
        chart.set("panY", "rotateY");
      }
    });

    cont.children.push(am5.Label.new(root, { centerY: am5.p50, text: "Globe" }));

    // Polygon & graticule series
    const polygonSeries = chart.series.push(am5map.MapPolygonSeries.new(root, { geoJSON: am5geodata_worldLow }));
    polygonSeries.mapPolygons.template.setAll({
        fill: am5.color(0x006600), // green land
    });
    chart.set("background", am5.Rectangle.new(root, {
  fill: am5.color(0x3399ff) // blue water
}));

    // Line series
    const lineSeries = chart.series.push(am5map.MapLineSeries.new(root, {}));
    lineSeries.mapLines.template.setAll({
      stroke: root.interfaceColors.get("alternativeBackground"),
      strokeOpacity: 0.6
    });

    // Origin series
    const originSeries = chart.series.push(am5map.MapPointSeries.new(root, { idField: "id" }));
    originSeries.bullets.push(() => {
      const circle = am5.Circle.new(root, {
        radius: 6,
        tooltipText: "{title} (Click me!)",
        cursorOverStyle: "pointer",
        tooltipY: 0,
        fill: am5.color(0xffba00),
        stroke: root.interfaceColors.get("background"),
        strokeWidth: 2
      });

      return am5.Bullet.new(root, { sprite: circle });
    });

    // Destination series
    const destinationSeries = chart.series.push(am5map.MapPointSeries.new(root, {}));
    destinationSeries.bullets.push(() => {
      const circle = am5.Circle.new(root, {
        radius: 2,
        tooltipText: "{title}",
        tooltipY: 0,
        fill: am5.color(0x000000),
        stroke: root.interfaceColors.get("background"),
        strokeWidth: 2
      });
      return am5.Bullet.new(root, { sprite: circle });
    });

    let origins: City[] = [];
    let codes: string[] = [];
    let destinations: City[] = [];

    fetch("http://localhost:5258/api/RouteNetwork/Map")
    .then(data => data.json())
    .then(data => data.forEach((element: any) => {
      if(element.airport.airportCode != "KBOS") return;
        //codes.push(element.airport.airportCode);
        const city: City = {
            id: element.airport.airportCode,
            title: element.airport.airportCode,
            destinations: [...element.servicedAirports.map((a: any) => a.airportCode)],
            geometry: {type: "Point", coordinates: [element.airport.longitude, element.airport.latitude]}
        }
        origins.push(city);
        const cities: City[] = element.servicedAirports.map((e: any) => { return {
            id: e.airportCode,
            title: e.airportCode,
            destinations: ["KBOS"],
            geometry: {type: "Point", coordinates: [e.longitude, e.latitude]}
    }});
        destinations = [...destinations, ...cities];
    })).then(_ => {
        //origins.forEach(o => o.destinations = [...codes])
        originSeries.data.setAll(origins);
        destinationSeries.data.setAll(destinations);
  });

    function selectOrigin(id: string) {
      if(id != "KBOS") return;
  const dataItem = originSeries.getDataItemById("KBOS");
  if (!dataItem) return;

  const dataContext = dataItem.dataContext as City;

  const lineData = (dataContext.destinations || []).map(destId => {
    const destItem = destinationSeries.getDataItemById(destId);
    const context = destItem?.dataContext as City;
    if (!destItem) return null;
    return {
      geometry: {
        type: "LineString",
        coordinates: [
          [dataContext.geometry.coordinates[0], dataContext.geometry.coordinates[1]],
          [context.geometry.coordinates[0], context.geometry.coordinates[1]]
        ]
      }
    };
  });

  lineSeries.data.setAll(lineData);
}

    destinationSeries.events.on("datavalidated", () => selectOrigin("KBOS"));

    chart.appear(1000, 100);

    return () => {
      root.dispose();
    };
  }, []);

  return <div ref={chartRef} style={{ width: "100%", height: "600px" }} />;
};

export default Map;