package is.hi.hbv202g.Final.ui;

import is.hi.hbv202g.Final.LibrarySystem;

import java.util.Scanner;

public class CreateUserCommand implements Command {
  private final LibrarySystem library;
  private final Scanner scanner;

  public CreateUserCommand(LibrarySystem library, Scanner scanner) {
    this.library = library;
    this.scanner = scanner;
  }

  @Override
  public String name() {
    return "Create User";
  }

  @Override
  public void execute() {
    System.out.println(" Select user type:");
    System.out.println(" 1) Student");
    System.out.println(" 2) Faculty Member");
    System.out.println(" 3) Admin");
    System.out.print("> ");

    String choice = scanner.nextLine().trim();
    System.out.print(" Enter name: ");
    String name = scanner.nextLine().trim();

    switch (choice) {
      case "1":
        System.out.print(" Fee paid? (true/false): ");
        boolean feePaid = Boolean.parseBoolean(scanner.nextLine().trim());
        library.addStudentUser(name, feePaid);
        System.out.println("Student created.\n");
        break;

      case "2":
        System.out.print(" Department: ");
        String dept = scanner.nextLine().trim();
        library.addFacultyMemberUser(name, dept);
        System.out.println("Faculty member created.\n");
        break;

      case "3":
        library.addAdminUser(name);
        System.out.println("Admin created.\n");
        break;

      default:
        System.err.println("! Invalid type.\n");
    }
  }
}