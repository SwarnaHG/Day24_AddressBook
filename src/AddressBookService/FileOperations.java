package AddressBookService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVWriter;
public class FileOperations {
    /***
     * UC-13:- Ability to Read or Write the Address Book with Persons Contact into a
     * File using File IO.
     ***/
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public void writeContactsIntoTextFile() throws IOException {

        /*** Writing into text file using FILE-IO. ***/
        FileWriter fileWriter = new FileWriter("files/AddressBookIO.txt");

        String stringAddressBookList = AddressBookService.addressBookNameList.toString();
        String stringAddressBookList = AddressBookService.addressBookList.toString();

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
        Path path = Paths.get("files/AddressBookIO.txt");
        if(!Files.exists(path)) {
            System.out.println("OOPS! File is not there. Creating file....");
            writeContactsIntoTextFile();
        }

        FileInputStream fileInputStream = new FileInputStream("files/AddressBookIO.txt");
        int i = 0;
        while((i = fileInputStream.read()) != -1) {
            System.out.print((char)i);
        }
        fileInputStream.close();
    }
    /**
     * UC-14:- Ability to Read/Write the Address Book with Persons Contact as CSV
     * File.
     **/
    public void writeContactsIntoCSV() throws IOException {


        List<String[]> stringsAddressBook = new ArrayList<>();

        PrintWriter printWriter = new PrintWriter("files/AddressBook.csv");	//Used PrintWriter in place of FileWriter.

        CSVWriter csvWriter = new CSVWriter(printWriter);

        /*** Adding header to the csv file. ***/
        stringsAddressBook.add(new String[] {"First_Name","Last_Name","Address","City","State","Zip_Code","Phone_Number","E-Mail"});

        /*** Adding contacts into stringsAddressBook ***/
        AddressBookService.addressBookNameList.forEach(addressBook -> addressBook.contact.stream().forEach(ad -> {
                    AddressBookService.addressBookList.forEach(addressBook -> addressBook.contact.stream().forEach(ad -> {
                        stringsAddressBook.add(new String[] { ad.getFirst_Name(), ad.getLast_Name(),
                                ad.getAddress(), ad.getCity(), ad.getState(),Integer.toString(ad.getZip_code()),
                                Long.toString(ad.getPhone_number()), ad.getEmail() });}));

                    csvWriter.writeAll(stringsAddressBook);    //Writing contacts into AddressBook.csv file.
                    csvWriter.flush();
                    csvWriter.close();

                    System.out.println("Contacts are added to AddressBook.csv file successfully.");
                }

                //Reading from CSV.
        public void readContactsFromCSV() throws IOException {
            System.out.println("---------------READING FROM CSV FILE--------------");
            Path path = Paths.get("files/AddressBook.csv");
            if(!Files.exists(path)) {
                System.out.println("OOPS! CSV file is not there. Creating CSV file...");
                writeContactsIntoCSV();
            }

            BufferedReader bufferedReader = new BufferedReader(new FileReader("files/AddressBook.csv"));
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                String[] contact = line.split(",");
                System.out.println("AddressBook [firstName=" + contact[0] + ", lastName=" + contact[1] + ", address="
                        + contact[2] + ", cityName=" + contact[3] + ", stateName=" + contact[4] + ", zip=" + contact[5]
                        + ", phoneNumber=" + contact[6] + "]");
            }
            bufferedReader.close();
        }

        /**
         * UC-15 :- Ability to Read or Write the Address Book with Persons Contact as
         * JSON File.
         **/
        public void writeContactsIntoJSON_File() throws IOException {

            String output = gson.toJson(AddressBookService.addressBookNameList);
            String output = gson.toJson(AddressBookService.addressBookList);
            FileWriter fileWriter = new FileWriter("files/AddressBook.json");
            fileWriter.write(output);
            fileWriter.close();

            System.out.println("Contacts are added to AddressBook.json file successfully.");
        }

        //reading contacts from JSON file.
        public void readContactsFromJSON_File() throws IOException {
            System.out.println("---------------READING FROM JSON FILE--------------");

            Path path = Paths.get("files/AddressBook.json");
            if(!(Files.exists(path))) {
                System.out.println("OOPS! JSON file is not there, Creating JSON file..");
                writeContactsIntoJSON_File();
            }
            else {
                FileReader fileReader = new FileReader("files/AddressBook.json");
                Object temp = gson.fromJson(fileReader, Object.class);
                System.out.println(temp);
            }
        }
    }
