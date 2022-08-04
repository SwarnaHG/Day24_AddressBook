package addressbooksystem;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class AddressBook {
    static Scanner input = new Scanner(System.in);
    static ArrayList<AddressBookList> addressBookNameList = new ArrayList<>();

    // building add contact feature
    public void addContact() {
        if (addressBookNameList.isEmpty()) {
            System.out.println("\nPlease add Address book to add contacts.");
            return;
        } else {
            System.out.println("Enter address book name in which you want to add contacts :- ");
            String enter = input.next();
            System.out.print("\nEnter First Name:- ");
            String first = input.next();
            System.out.print("Enter Last Name:- ");
            String last = input.next();
            System.out.print("Enter Address:- ");
            String add = input.next();
            System.out.print("Enter City:- ");
            String city = input.next();
            System.out.print("Enter State:- ");
            String state = input.next();
            System.out.print("Enter Zip Code:- ");
            int zip = input.nextInt();
            System.out.print("Enter Phone Number:- ");
            long phone = input.nextLong();
            System.out.print("Enter E-mail:- ");
            String email = input.next();
            Contact_info contact = new Contact_info(first, last, add, city, state, zip, phone, email);
            if (duplicateCheck(enter , first)) {
                addressBookNameList.stream()
                        .filter(find -> find.userInputBookName.contains(enter))
                        .forEach(addressBook -> addressBook.contact.add(contact));

                System.out.println("\nContact added Successfully.\n");
            }
            else {
                System.out.println("\nYou have already this person in your contact list.\n");
                return;
            }
        }
    }
    /**
     * UC-7:- Ability to ensure there is no Duplicate Entry of the same Person in a
     * particular Address Book.
     **/
    public boolean duplicateCheck(String enter , String first) {
        for (AddressBookList addressBook : addressBookNameList) {
            if (enter.equals(addressBook.userInputBookName)) {
                for (Contact_info person : addressBook.contact) {
                    if (first.equals(person.first_Name)) {
                        return false;
                    }
                    else {
                        continue;
                    }
                }
            }
            else {
                continue;
            }
        }
        return true;
    }

    // Building edit contact feature
    public void editContact() {
        System.out.println("Enter address book name in which you want to edit contacts :- ");
        String enter = input.next();
        System.out.println("\nEnter first name to edit :- ");
        String name = input.next();
        for (AddressBookList addressBook : addressBookNameList) {
            if (enter.equals(addressBook.userInputBookName)) {
                for (Contact_info person : addressBook.contact) {
                    if (name.equals(person.first_Name)) {
                        System.out.print("\nSelect option to edit..." + " 1.First_name." + " 2.Last_name." + " 3.Address."
                                + " 4.City" + " 5.State" + " 6.Zip_code" + " 7.Phone_number" + " 8.Email :- ");
                        int option = input.nextInt();
                        switch (option) {
                            case 1:
                                System.out.print("Enter new first name :- ");
                                String newFirstName = input.next();
                                person.setFirst_Name(newFirstName);
                                System.out.println("First name is updated.");
                                break;
                            case 2:
                                System.out.print("Enter new last name :- ");
                                String newLastName = input.next();
                                person.setLast_Name(newLastName);
                                System.out.println("Last name is updated.");
                                break;
                            case 3:
                                System.out.print("Enter new Address :- ");
                                String newAddress = input.next();
                                person.setAddress(newAddress);
                                System.out.println("Address is updated.");
                                break;
                            case 4:
                                System.out.print("Enter new city name :- ");
                                String newCity = input.next();
                                person.setCity(newCity);
                                System.out.println("City is updated.");
                                break;
                            case 5:
                                System.out.print("Enter new state name :- ");
                                String newState = input.next();
                                person.setState(newState);
                                System.out.println("State is updated.");
                                break;
                            case 6:
                                System.out.print("Enter new Zip code :- ");
                                int newZip = input.nextInt();
                                person.setZip_code(newZip);
                                System.out.println("Zip code is updated.");
                                break;
                            case 7:
                                System.out.print("Enter new phone number :- ");
                                long newPhone = input.nextLong();
                                person.setPhone_number(newPhone);
                                System.out.println("Phone number is updated.");
                                break;
                            case 8:
                                System.out.print("Enter new email :- ");
                                String newEmail = input.next();
                                person.setEmail(newEmail);
                                System.out.println("Email is updated.");
                                break;
                            default:
                                System.out.println("Please enter a number between 1 to 8 only...");
                        }
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
    }
    // adding deleting contact by name feature
    public void deleteContact() {
        System.out.println("Enter address book name in which you want to delete contacts :- ");
        String enter = input.next();
        System.out.print("Enter first name to delete contact:- ");
        String deleteByName = input.next();
        for (AddressBookList addressBook : addressBookNameList) {
            if (enter.equals(addressBook.userInputBookName)) {
                for (Contact_info person : addressBook.contact) {
                    if (deleteByName.equals(person.first_Name)) {
                        addressBook.contact.remove(person);
                        System.out.println("\nSelected contact deleted successfully.");
                        break;
                    } else {
                        continue;
                    }
                }
            } else {
                continue;
            }
        }
    }
    // UC-5:- adding multiple person details feature added.
    public void addMultiplePersons() {
        System.out.println("Enter how many contacts you want to add :- ");
        int userWant = input.nextInt();
        for (int i = 1; i <= userWant; i++) {
            addContact();
        }
    }
    // UC-6:- Ability to add multiple address books to system
    public void newAddressBook() {

        System.out.print("Enter Address Book Name :- ");
        String userInputBookName = input.next();

        if(!checkUnique(userInputBookName)) {
            System.out.println("OOPS! You aleady have AddressBook with same name.\n");
            return;
        }

        AddressBookList addressBookObj = new AddressBookList(userInputBookName);
        addressBookNameList.add(addressBookObj);
        System.out.println("New Address Book Name is added to list.");
    }
    /*** Checking for unique address Book ***/
    public boolean checkUnique(String userInputBookName) {
        if(addressBookNameList.isEmpty()) {
            return true;
        }
        for(int i = 0; i < addressBookNameList.size(); i++) {
            String getName = addressBookNameList.get(i).userInputBookName;
            if(getName.equals(userInputBookName)) {
                return false;
            }
        }
        return true;
    }

    // display address book
    public void displayAddressBook() {
        for (AddressBookList addressBook : addressBookNameList) {
            System.out.println(addressBook);
        }
    }
    /**
     * UC-9:- Ability to view Persons by City or State - Maintain Dictionary of City
     * and Person as well as State and Person.
     **/
    public void searchPersonByCity() {

        System.out.print("\nEnter city to search person by city name :- ");
        String searchCity = input.next();

        /***** Creating dictionary of city(keys) and name(values) *****/
        Dictionary cityWiseDict = new Hashtable();
        for (AddressBookList addressBook : addressBookNameList) {
            for (Contact_info person : addressBook.contact) {
                if (searchCity.equals(person.city)) {
                    cityWiseDict.put(person.first_Name, searchCity);
                } else {
                    continue;
                }
            }
        }
        System.out.println("Persons who are in same city " + searchCity + " :- ");
        for(Enumeration i = cityWiseDict.keys(); i.hasMoreElements();) {
            System.out.println(i.nextElement());
        }
        System.out.println(" ");
    }

    public void searchPersonByState() {
        System.out.print("\nEnter state to search person by State name :- ");
        String searchState = input.next();

        /***** Creating dictionary of city(keys) and name(values) *****/
        Dictionary stateWiseDict = new Hashtable();
        for (AddressBookList addressBook : addressBookNameList) {
            for (Contact_info person : addressBook.contact) {
                if (searchState.equals(person.state)) {
                    stateWiseDict.put(person.first_Name, searchState);
                } else {
                    continue;
                }
            }
        }
        System.out.println("Persons who are in same state " + searchState + " :- ");
        for(Enumeration i = stateWiseDict.keys(); i.hasMoreElements();) {
            System.out.println(i.nextElement());
        }
        System.out.println(" ");
    }

    /**
     * UC-10:- Ability to get number of contact persons i.e. count by City or State.
     **/
    public void countByCity() {
        System.out.println("Enter city name to count :- ");
        String countCity = input.next();
        int count = 0;
        for (AddressBookList addressBook : addressBookNameList) {
            for (Contact_info person : addressBook.contact) {
                if(countCity.equals(person.city)) {
                    count++;
                }
            }
        }
        System.out.println("\nNumber of persons in same city " + "(" + countCity + ") :- " + count + ".\n");
    }

    public void countByState() {
        System.out.println("Enter state name to count :- ");
        String countState = input.next();
        int count = 0;
        for (AddressBookList addressBook : addressBookNameList) {
            for (Contact_info person : addressBook.contact) {
                if(countState.equals(person.state)) {
                    count++;
                }
            }
        }
        System.out.println("\nNumber of persons in same state " + "(" + countState + ") :- " + count + ".\n");
    }

    /*** Finding address Book ***/
    @@ -328,126 +334,91 @@
            System.out.println("Please create an address book first.");
			return null;
}
		System.out.println("Please enter the name of the address book :- ");
                String getAddressBook = input.next();

                for (AddressBookList addressBook : addressBookNameList) {
                if(getAddressBook.equals(addressBook.userInputBookName)) {
                return addressBook; // returning addressBook if found in the address book list.
                }
                }
                System.out.println("Address Book does not exist.");
                return null;
                }
/**
 * UC-12:- Ability to sort the entries in the address book by City, State, or
 * Zip.
 **/
public void sortByName_City_State_zip() {

        AddressBookList addressBook = findAddressBook();
        System.out.println("Please select any of the below options." + "\n" + "1. To Sort By Name." + "\n"
        + "2. To Sort By City." + "\n" + "3. To Sort By State." + "\n" + "4. To Sort By Zip Code. :- ");
        int choice = input.nextInt();
        switch (choice) {
        case 1:
        addressBook.contact.stream()
        .sorted((contact1, contact2) -> contact1.getFirst_Name().compareTo(contact2.getFirst_Name()))
        .forEach(contact -> System.out.println(contact));
        break;
        case 2:
        addressBook.contact.stream()
        .sorted((contact1, contact2) -> contact1.getCity().compareTo(contact2.getCity()))
        .forEach(contact -> System.out.println(contact));
        break;
        case 3:
        addressBook.contact.stream()
        .sorted((contact1, contact2) -> contact1.getState().compareTo(contact2.getState()))
        .forEach(contact -> System.out.println(contact));
        break;
        case 4:
        addressBook.contact.stream()
        .sorted((contact1, contact2) -> Integer.valueOf(contact1.getZip_code()).compareTo(contact2.getZip_code()))
        addressBook.contact.stream().sorted(
        (contact1, contact2) -> Integer.valueOf(contact1.getZip_code()).compareTo(contact2.getZip_code()))
        .forEach(contact -> System.out.println(contact));
        break;
default:
        System.out.println("Please choose valid option.");
        }
        }

// main method
public static void main(String[] args) {
        System.out.println("--------------------Welcome To Address Book Program-----------------------");
        AddressBook obj = new AddressBook();
/***
 * UC-13:- Ability to Read or Write the Address Book with Persons Contact into a
 * File using File IO.
 ***/
public void writeContactsIntoTextFile() throws IOException {

        Scanner input = new Scanner(System.in);
        /*** Writing into text file using FILE-IO. ***/
        FileWriter fileWriter = new FileWriter("AddressBookIO.txt");

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
        System.out.println("11.Exit from the Application.");

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
default:
        System.out.println("Enter valid choice from the list...");
        }
        if (userChoice == 11) {
        System.out.println("Successfully exited from the Address Book Application.");
        break;
        }
        }
        } else if (enterExit == 2) {
        System.out.println("Successfully exited from the Address Book Application.");
        } else {
        System.out.println("Choose Valid option [1.Enter (or) 2.Exit]...");
        String stringAddressBookList = addressBookNameList.toString();

        for(int i = 0; i < stringAddressBookList.length(); i++) {
        fileWriter.write(stringAddressBookList.charAt(i));
        fileWriter.flush();
        }
        fileWriter.close();

        System.out.println("Data Added into AddressBookIO.txt File.\n");
        }

// reading contacts data from text file.
public void readContactsFromTextFile() throws IOException {
        System.out.println("---------------READING FROM TEXT FILE--------------");

        /** Writing into file if file does not exist(Empty file is created) in system. **/
        Path path = Paths.get("AddressBookIO.txt");
        if(!Files.exists(path)) {
        System.out.println("OOPS! File is not there. Creating file....");
        writeContactsIntoTextFile();
        }

        FileInputStream fileInputStream = new FileInputStream("AddressBookIO.txt");
        int i = 0;
        while((i = fileInputStream.read()) != -1) {
        System.out.print((char)i);
        }
        input.close();
        fileInputStream.close();
        }
        }



        }
