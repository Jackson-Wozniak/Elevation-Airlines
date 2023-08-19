### Ticker Reservation API Plan

- Users will be able to view all scheduled flights, and buy tickets. 
    Flights must have open seats for tickets to be bought,
    and users must have enough $ in their account for the transaction
- Tickets will sell as the takeoff date approaches, and certain flights
    to popular airports will sell out completely. Likewise, some tickets
    to sold-out flights can be bought 3rd party, with a slight markup
- Users must deposit funds (not real $) to their bank accounts before
    buying tickets, and must hold enough money in their accounts to match
    the value of the ticket. Users will not be able to buy tickets
    if they already own tickets for a flight that is scheduled at the same time

---

### Ticket Reservation URL Endpoints
*Not all endpoints are publicly available*

<details>
 <summary>
    <code>GET</code> <code>/api/v1/reservations/price?departure={departure}&destination={destination}</code> View price & summary of flights from departure or destination
 </summary>

##### URL Parameters
*at least one parameter below is required*
>departure: icao code of desired departure
>departure: icao code of desired destination
</details>

<!-- -------------------------------------------------------------------------------- -->


<details>
 <summary>
    <code>GET</code> <code>/api/v1/reservations/price?date</code> View price & summary of flights on a given day
 </summary>

##### URL Parameters
>None
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET</code> <code>/api/v1/reservations/price?flightIdentifier={id}</code> View price & summary of a scheduled flight
 </summary>

##### URL Parameters
>id: long value matching the id of desired flight | not required
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>GET | SECURED</code> <code>/api/v1/reservations?token={token}</code> View tickets owned for user
 </summary>

##### URL Parameters
>token: user identifier given on login
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>POST | SECURED</code> <code>/api/v1/reservations?token={token}&flight={id}&payment_method={bankToken}</code> Buy ticket
 </summary>

##### URL Parameters
>token: user identifier given on login
>
>flight: id of flight to buy tickets for
> 
>payment method: JSON token for bank account *only used for payment if user chooses not to cover cost with earned miles
</details>

<!-- -------------------------------------------------------------------------------- -->
