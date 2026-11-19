# java-test-automation-framework![API tests](https://github.com/aniketrick/java-test-automation-framework/actions/workflows/api-tests.yml/badge.svg)

# Java Test Automation Framework

Automated tests in Java for the [Restful Booker](https://restful-booker.herokuapp.com) practice API, run on every push with GitHub Actions. I'm building this up step by step: API tests first, then Selenium UI tests, then performance and accessibility checks.

## What's tested so far

| Test class | What it checks |
|---|---|
| `HealthCheckTest` | The API is up |
| `GetBookingsTest` | Listing bookings returns 200 and JSON |
| `CreateBookingTest` | A new booking is saved with exactly the details sent |
| `BookingLifecycleTest` | Log in, update and delete a booking, and confirm an update without a token is refused (403) |

## How it's built

- **Java 21, JUnit 5, REST Assured, Maven**
- **`BaseApiTest`** holds shared settings (base URL and headers) so each test stays short
- **Each test logs in and creates its own booking** in `@BeforeEach`, so tests don't depend on each other
- **The base URL can be changed without touching code:** `mvn test -DbaseUrl=https://other-environment`
- **GitHub Actions** runs the suite on every push and pull request, and uploads the test reports even when tests fail

## Run it locally

```bash
mvn test
```

## Coming next

- Selenium UI tests using page objects
- Allure test reports
- A small load test (k6) and an accessibility check (axe)