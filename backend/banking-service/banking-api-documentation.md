### Banking API Overview

- Users can create bank accounts that act as a 3rd party to
  pay for plane tickets
- Users can deposit (fake) money in the click of a button, view their account
    or sign in during checkout and pay through there
- Transactions use JWT auth, with tokens expiring in 3 minutes. This expiration date
    is meant to regulate how long users can sit in ticket checkouts for more security

---

### Banking URL Endpoints
*All endpoints have a security filter*

<details>
 <summary>
    <code>GET</code> <code>/api/v1/banking/account?token={token}</code> Get account balance & overview
 </summary>

##### URL Parameters
>token: JWT token relating to user account (must be logged in)
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>PUT</code> <code>/api/v1/banking/account/login</code> Login with credentials, creates extended 15 minute JWT token
 </summary>

##### Request Object Parameters
>Login Request:
>   username
>   password
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>PUT</code> <code>/api/v1/banking/account/login</code> Login with credentials, generates 3 minute JWT token
 </summary>

##### Request Object Parameters
>Login Request:
>   username
>   password
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>POST</code> <code>/api/v1/banking/account</code> Create new account
 </summary>

##### Request Object Parameters
>Registration Request:
>   username
>   password
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>PUT</code> <code>/api/v1/banking/deposit?token={token}</code> Deposit money to account
 </summary>

##### Request Object Parameters
>Registration Request:
>   token: JWT token relating to account
</details>

<!-- -------------------------------------------------------------------------------- -->

<details>
 <summary>
    <code>PUT</code> <code>/api/v1/banking/withdrawal?token={token}</code> Withdraw money from account
 </summary>

##### Request Object Parameters
>Registration Request:
>   token: JWT token relating to account
</details>