package github.wozniak.flighttrackingservice.configuration;

//@SpringBootTest
//@RunWith(SpringRunner.class)
//class RouteConfigTest {
//
//    private final RouteConfig routeConfig;
//
//    @Autowired
//    RouteConfigTest(RouteConfig routeConfig) {
//        this.routeConfig = routeConfig;
//    }
//
//    @Test
//    void scheduledRouteCreationTest(){
//        routeConfig.createScheduledRoutes(100).forEach(scheduled -> {
//            assertTrue(scheduled.getRoute().getFlightDurationHours() < 11);
//            assertTrue(scheduled.getPlane().getRangeMiles() >= scheduled.getRoute().getFlightDistanceMiles());
//        });
//
//    }
//}