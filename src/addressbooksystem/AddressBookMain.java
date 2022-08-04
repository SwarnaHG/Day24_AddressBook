package addressbooksystem;

import java.io.IOException;
import java.util.Scanner;

public class AddressBookMain {
    public static void main(String[] args) throws IOException {
        System.out.println("--------------------Welcome To Address Book Program-----------------------");
        AddressBook obj = new AddressBook();

        Scanner input = new Scanner(System.in);

        System.out.print("Enter valid option to perform Address Book Application[1.Enter (or) 2.Exit] :- ");
        int enterExit = input.nextInt();
        if (enterExit == 1) {
            while (enterExit != 2) {
                System.out.println("Choose which operation you want to perform from below list :- ");
                System.out.println("1.Add Contact.");
                System.out.println("2.Edit Contact");
                System.out.println("3.Delete Contact.");
                System.out.println("4.Add new address book.");
                System.out.println("5.Display Address Book.");
                System.out.println("6.Search person by City.");
                System.out.println("7.Search person by State.");
                System.out.println("8.Count By City.");
                System.out.println("9.count By State.");
                System.out.println("10.Sort By Name/City/State/Zip-Code.");
                System.out.println("11.Write Into Text File.");
                System.out.println("12.Read from Text File.");
                System.out.println("13.Exit from the Application.");

                System.out.println("\nEnter your choice :- ");
                int userChoice = input.nextInt();

                switch (userChoice) {
                    case 1:
                        obj.addMultiplePersons();
                        break;
                    case 2:
                        obj.editContact();
                        break;
                    case 3:
                        obj.deleteContact();
                        break;
                    case 4:
                        obj.newAddressBook();
                        break;
                    case 5:
                        obj.displayAddressBook();
                        break;
                    case 6:
                        obj.searchPersonByCity();
                        break;
                    case 7:
                        obj.searchPersonByState();
                        break;
                    case 8:
                        obj.countByCity();
                        break;
                    case 9:
                        obj.countByState();
                        break;
                    case 10:
                        obj.sortByName_City_State_zip();
                        break;
                    case 11:
                        obj.writeContactsIntoTextFile();
                        break;
                    case 12:
                        obj.readContactsFromTextFile();
                        break;
                    default:
                        System.out.println("Enter valid choice from the list...");
                }
                if (userChoice == 13) {
                    System.out.println("Successfully exited from the Address Book Application.");
                    break;
                }
            }
        } else if (enterExit == 2) {
            System.out.println("Successfully exited from the Address Book Application.");
        } else {
            System.out.println("Choose Valid option [1.Enter (or) 2.Exit]...");
        }
        input.close();
    }
}

}
