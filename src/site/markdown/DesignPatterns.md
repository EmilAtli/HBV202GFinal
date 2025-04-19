# Design Patterns

This document describes where key design patterns are used in the library system, and which classes play each role.

---

## Observer Pattern

**Intent:** Define a one‐to‐many dependency so that when one object changes state, its dependents are notified and updated automatically.

**Participants:**

- **Subject**:

  - `LibrarySystem`
  - Methods:
    - `addListener(LibraryListener)`
    - `removeListener(LibraryListener)`
    - Notifies via `onBookBorrowed(...)` and `onBookReturned(...)`.

- **Observer (Interface)**:

  - `LibraryListener`
  - Defines callbacks:
    - `void onBookBorrowed(Lending lending)`
    - `void onBookReturned(Lending lending)`

- **Concrete Observers**:
  - `FeeListener`
  - `AvailabilityListener`
  - Each registers with `LibrarySystem` and implements the callbacks to perform side-effects (charging fees, printing availability messages).

**How it fits:**  
When `LibrarySystem.borrowBook(...)` or `.returnBook(...)` is called, it iterates over all registered listeners and invokes the appropriate callback. This decouples the core lending logic from the side‐effects (notifications, fee processing).

---

## Command Pattern

**Intent:** Encapsulate a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations.

**Participants:**

- **Command (Interface)**:

  - `is.hi.hbv202g.Final.ui.Command`
  - Methods:
    - `String name()`
    - `void execute()`

- **Concrete Commands**:

  - `LoginCommand`, `LogoutCommand`, `CreateUserCommand`, `AddBookCommand`, `AddBookMultiAuthorsCommand`,  
    `AddOmnibusCommand`, `ListBooksCommand`, `BorrowBookCommand`, `ReturnBookCommand`,  
    `ExtendLendingCommand`, `ExitCommand`

- **Invoker**:

  - In `Main.main(...)`, the CLI menu loops over a `List<Command>` and, based on user selection, calls `execute()` on the chosen command.

- **Receiver**:
  - The `LibrarySystem` (and `Session`, `Scanner`) passed into each command at construction time.

**How it fits:**  
All UI actions are encapsulated as `Command` objects. Adding a new menu option is as simple as writing a new `Command` implementation and adding it to the `commands` list in `Main`.

---

## Composite Pattern

**Intent:** Compose objects into tree structures to represent part–whole hierarchies. Composite lets clients treat individual objects and compositions uniformly.

**Participants:**

- **Component (Superclass)**:

  - `Book`
  - Defines operations such as `borrowCopy()` and `returnCopy()`.

- **Leaf**:

  - Regular `Book` instances with a specific author list and copy count.

- **Composite**:

  - `Omnibus` extends `Book`
  - Contains a `List<Book>` called `volumes`.
  - Overrides `borrowCopy()` and `returnCopy()` to only affect the omnibus container itself.

- **Client**:
  - `LibrarySystem.borrowBook(...)` and `.returnBook(...)`
  - When encountering an `instanceof Omnibus`, recurses into each contained volume.

**How it fits:**  
An `Omnibus` is treated as a `Book` in the catalog, but internally represents a collection of volumes. Borrowing an omnibus container triggers borrowing each volume in turn (via the `LibrarySystem` recursion).

---
