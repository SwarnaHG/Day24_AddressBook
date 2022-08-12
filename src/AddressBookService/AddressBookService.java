package AddressBookService;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;
//import com.addressbookservice.entity.AddressBook;
//import com.addressbooksystem.entity.Contact;


public class AddressBookService {
    static Scanner input = new Scanner(System.in);
    static ArrayList<AddressBook> addressBookNameList = new ArrayList<>();

    public void addContact() {
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
                Contact contact = new Contact(first, last, add, city, state, zip, phone, email);

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
        public boolean duplicateCheck(String enter , String first) {
            for (AddressBook addressBook : addressBookNameList) {
                if (enter.equals(addressBook.userInputBookName)) {
                    for (Contact person : addressBook.contact) {
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
            for (AddressBook addressBook : addressBookNameList) {
                if (enter.equals(addressBook.userInputBookName)) {
                    for (Contact person : addressBook.contact) {
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

            for (AddressBook addressBook : addressBookNameList) {
                if (enter.equals(addressBook.userInputBookName)) {
                    for (Contact person : addressBook.contact) {
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

            AddressBook addressBookObj = new AddressBook(userInputBookName);
            addressBookNameList.add(addressBookObj);
            System.out.println("New Address Book Name is added to list.");
        }

        /*** Checking for unique address Book ***/
        public boolean checkUnique(String userInputBookName) {
            if(addressBookNameList.isEmpty()) {
                if(addressBookList.isEmpty()) {
                    return true;
                }
                for(int i = 0; i < addressBookNameList.size(); i++) {
                    String getName = addressBookNameList.get(i).userInputBookName;
                    for(int i = 0; i < addressBookList.size(); i++) {
                        String getName = addressBookList.get(i).userInputBookName;
                        if(getName.equals(userInputBookName)) {
                            return false;
                        }
                    }
                    return true;
                }

                // display address book
                public void displayAddressBook() {
                    for (AddressBook addressBook : addressBookNameList) {
                        System.out.println("New Address Book Name is added to list.");
                    }

                    /*** Checking for unique address Book ***/
                    public boolean checkUnique(String userInputBookName) {
                        if(addressBookNameList.isEmpty()) {
                            if(addressBookList.isEmpty()) {
                                return true;
                            }
                            for(int i = 0; i < addressBookNameList.size(); i++) {
                                String getName = addressBookNameList.get(i).userInputBookName;
                                for(int i = 0; i < addressBookList.size(); i++) {
                                    String getName = addressBookList.get(i).userInputBookName;
                                    if(getName.equals(userInputBookName)) {
                                        return false;
                                    }
                                }
                                return true;
                            }

                            // display address book
                            public void displayAddressBook() {
                                for (AddressBook addressBook : addressBookNameList) {
                                    for (Contact person : addressBook.contact) {
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

                                for (AddressBook addressBook : addressBookNameList) {
                                    for (Contact person : addressBook.contact) {
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

                                for (AddressBook addressBook : addressBookNameList) {
                                    for (AddressBook addressBook : addressBookList) {
                                        for (Contact person : addressBook.contact) {
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
                                    for (AddressBook addressBook : addressBookNameList) {
                                        for (AddressBook addressBook : addressBookList) {
                                            for (Contact person : addressBook.contact) {
                                                if(countState.equals(person.state)) {
                                                    count++;
                                                }
                                            }
                                        }
                                        System.out.println("\nNumber of persons in same state " + "(" + countState + ") :- " + count + ".\n");
                                    }

                                    /*** Finding address Book ***/
                                    public AddressBook findAddressBook() {
                                        if(addressBookNameList.isEmpty()) {
                                            if(addressBookList.isEmpty()) {
                                                System.out.println("Please create an address book first.");
                                                return null;
                                            }
                                            System.out.println("Please enter the name of the address book :- ");
                                            String getAddressBook = input.next();

                                            for (AddressBook addressBook : addressBookNameList) {
                                                for (AddressBook addressBook : addressBookList) {
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

                                                AddressBook addressBook = findAddressBook();
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
                                                        addressBook.contact.stream().sorted(
                                                                        (contact1, contact2) -> Integer.valueOf(contact1.getZip_code()).compareTo(contact2.getZip_code()))
                                                                .forEach(contact -> System.out.println(contact));
                                                        break;
                                                    default:
                                                        System.out.println("Please choose valid option.");
                                                }
                                            }
                                        }

