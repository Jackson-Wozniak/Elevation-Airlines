airports.csv comes from our-airports.com, but importantly is pre-filtered before being
    placed in the zip file. I have removed airports with types that are irrelevant to
    Elevation Airlines (closed, heliport, seaplane), and also remove extra data such as links.
    I keep both the indent code, as well as the local code. Airports without a GPS code are
    also removed before saving.


Much of the economic data comes from city_economics.md, which includes passenger/fare data
    for major cities and regions in the US.

Method for cleaning data:

- to avoid bloat in the .csv files, all relevant information is filtered and formatted, to make
  the files read by the server as simple as possible
- In the current iteration of the project, economic data for airports is pre-parsed HERE,
  and all economic data and runway data is aggregated into two numbers:
    1. Airport Market Size (1 - 10)
        this the size of the airport and the surrounding market. Those in large markets, i.e. cities
        will have a higher value, indicating higher demand for routes to this airport, and a higher
        bandwidth for this airport (i.e. more flights per day allowed)
    2. Market Growth Value (1 - 10)
       primarily using economic data, this number seeks to summarize the growth expected for this
       airport's area, so that the server can accurately determine whether to invest in this airport
       with more routes

Once the data is cleaned, the server reads this data to then create the route network.
Future efforts could make dynamic market patterns more robust, but for now we can assume a fixed
market size for airports and their nearby economies