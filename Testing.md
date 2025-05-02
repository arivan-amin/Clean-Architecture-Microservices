# ✅ Test Naming Convention

### This project follows the Clean Code Manual for naming convention for unit and integration test methods.

## Purpose

To improve test readability, maintain consistency, and make it easier to understand what each test
is verifying at a glance.

## Format

```
{methodUnderTest}Should{ExpectedBehavior}
```

- `methodUnderTest`: The name of the method or feature being tested.
- `Should`: A constant keyword indicating expected behavior.
- `ExpectedBehavior`: The specific outcome or behavior expected.

## ✅ Examples

| Method Under Test | Test Method Name                          | Description                                     |
|-------------------|-------------------------------------------|-------------------------------------------------|
| `findAll`         | `findAllShouldReturnAllEvents`            | Verifies that `findAll()` returns all events.   |
| `save`            | `saveShouldPersistEvent`                  | Verifies that `save()` stores the event.        |
| `deleteById`      | `deleteByIdShouldRemoveEventFromDatabase` | Verifies that deletion by ID works as expected. |
| `calculateTotal`  | `calculateTotalShouldReturnCorrectSum`    | Verifies correct calculation logic.             |

## ✅ Notes

- Use meaningful, concise names for the method and expected behavior.
- Avoid including low-level details unless relevant to the test.
- Apply consistently across all test types (unit, integration, etc.).
