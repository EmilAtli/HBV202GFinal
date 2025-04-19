# HBV202G Library Management Final Project

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

A console‑based library management system demonstrating Java, Maven, JUnit, UML, design patterns, and repeatable packaging.

---

## Table of Contents

1. [Overview](#overview)
2. [Maven Goals](#maven-goals)
3. [Packaging & Running](#packaging--running)
4. [Design Documentation](#design-documentation)
5. [Generated Documentation](#generated-documentation)
6. [Features](#features)
7. [Acknowledgments](#Acknowledgments)
8. [License](#license)

---

## Overview

This project implements a simple library system with:

- **Domain model**: `Book`, `Author`, `User` hierarchy (`Student`, `FacultyMember`, `Admin`), `Lending`, `Omnibus`.
- **Event listeners**: `FeeListener`, `AvailabilityListener` (Observer pattern).
- **CLI UI**: a `Command` interface with concrete commands for login/logout, book management, lending operations, etc.
- **Packaging**: Maven Assembly Plugin produces a fat JAR; cross‑platform shell scripts for build & run.

---

## Maven Goals

Use the following Maven commands:

```bash
mvn clean        # remove previous build artifacts
mvn compile      # compile source code
mvn test         # run JUnit tests (business logic only)
mvn package      # compile + create fat JAR (Final-1.0-SNAPSHOT-all.jar)
mvn exec:java    # run the application via Maven
mvn site         # generate project site (Markdown + Javadoc)
```

## Packaging & Running

### Build the fat JAR

```bash
# macOS/Linux
./createjar.sh

# Windows
./createjar.cmd
```

This runs mvn clean package and copies
`target/Final-1.0-SNAPSHOT-all-jar-with-dependencies.jar` → `library-app.jar`

### Run the application

```bash
# macOS/Linux
./runjar.sh

# Windows
./runjar.cmd
```

No IDE or Maven required. Just a Java runtime.

## Design Documentation

You’ll find detailed design docs in the `src/site/markdown/` folder (automatically published by mvn site):

- UML Class Diagram: `target/site/markdown/UML.html`

- Design Patterns: `target/site/markdown/DesignPatterns.html`

## Generated Documentation

To view the full Maven generated site locally:

```bash
mvn clean site
# then open:
# macOS:
open target/site/index.html
# Linux:
xdg-open target/site/index.html
# Windows:
start target\site\index.html
```

The site includes:

- Home page (overview & README content)

- Design pages (UML & patterns)

- API Javadoc under API → Javadoc

## Features

- **User** Management: Student, FacultyMember, Admin, with session‑based login/logout.

- **Role Enforcement:** Only Admins may add books; only Faculty can extend lendings.

- **Book Operations:** Add, List, Borrow, Return books via a menu‑driven CLI.

- **Seed Data:** `LibraryInitializer.seed(...)` preloads sample users/books on startup.

- **Fees:** `FeeListener` charges late fees for Students; Faculty/Admin exempt.

- **Availability Notifications:** `AvailabilityListener` prints remaining/total copies on borrow/return.

- **Composite Omnibus:** Bundle multiple `Book` volumes into an `Omnibus`; borrowing/returning cascades to each volume.

- **JUnit Tests:** Coverage for business logic and listeners (non‑UI).

**Packaging Scripts:** `createjar.sh`/`.cmd` & `runjar.sh`/`.cmd` for repeatable, Maven‑free distribution.

## Acknowledgments

This project was originally bootstrapped from Helmut Wolfram Neukirchen’s skeleton:
[HBV202GAssignment8](https://github.com/helmutneukirchen/HBV202GAssignment8).

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
