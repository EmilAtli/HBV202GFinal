# HBV202G - Final Project

## Overview

This is the final project for HBV202G, building on the simple library management system from assignment 8.  
The following has been implemented:

- Textual User Interface: A menu console UI implemented with the Command pattern using scanner.

- Observer Pattern: LibrarySystem fires events (onBookBorrowed, onBookReturned) to registered LibraryListener implementations.

## Features Implemented

- Add/List/Borrow/Return Books via the menu

- User Management: Create Students, FacultyMember, Admin

- Fee Notifications: Students incur a 100kr/day fee on late returns; Faculty and Admin do not

## Todo

- [ ] Extend UI

- [ ] Composite Pattern: support for Omnibus spanning multiple Book volumes.

- [ ] JUnit Tests: Business logic and listener behavior

- [ ] Maven Build: package (fat-jar with dependencies), and site (project reports and Javadoc).

- [ ] Packaging Scripts: build.sh and run.sh for easy build and execution without Maven.

- [ ] Generate and include UML diagrams in docs/
