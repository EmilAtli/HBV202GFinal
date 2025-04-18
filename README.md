# HBV202G - Final Project

## Overview

This is the final project for HBV202G, building on the simple library management system from assignment 8.  
The following has been implemented:

- Textual User Interface: A menu console UI implemented with the Command pattern using scanner.

- Observer Pattern: LibrarySystem fires events (onBookBorrowed, onBookReturned) to registered LibraryListener implementations.

## Features Implemented

- Add/List/Borrow/Return Books via the menu

- Seed data initializer: On startup we preload sample books/users so you can jump straight into the demo

- User Management (Student, FacultyMember, Admin) with Session‑based Login/Logout

- Role enforcement: only Admins can add books; only Faculty can extend lendings

- FeeListener: Students pay 100 kr/day on late returns; Faculty/Admin do not

- Added support for multiple book copies and an AvailabilityListener that reports remaining/total copies whenever a book is borrowed or returned.

- Composite Omnibus support: bundle multiple volumes into a single set so that borrowing or returning the set automatically forwards the action to each contained volume.

- Only Admin can bundle selected books into a composite set

- JUnit Tests: Business logic and listener behavior

## Todo

- [x] Extend UI

- [x] Composite Pattern: support for Omnibus spanning multiple Book volumes.

- [x] JUnit Tests: Business logic and listener behavior

- [ ] Maven Build: package (fat-jar with dependencies), and site (project reports and Javadoc).

- [ ] Packaging Scripts: build.sh and run.sh for easy build and execution without Maven.

- [ ] Generate and include UML diagrams in docs/
