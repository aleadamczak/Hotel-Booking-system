package model;
import utilis.MyFileHandler;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BookingModelManager implements Serializable
{
  private String fileName;

  public BookingModelManager(String fileName)
  {
    this.fileName = fileName;
  }


  // Use the MyFileHandler class to retrieve a BookingList object with all Bookings
  public BookingList getAllBookings()
  {
    BookingList allBookings = new BookingList();

    try
    {
      Object[] temp = MyFileHandler.readArrayFromBinaryFile(fileName);
      for (int i = 0; i < temp.length; i++) {
        if (temp[i] instanceof BookingList) {
          for (int j = 0; j < ((BookingList) temp[i]).size(); j++)
            allBookings.addBooking(((BookingList) temp[i]).getBooking(j));
        }
      }

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allBookings;
  }


  // Use the MyFileHandler class to retrieve a GuestList object with all Guests
  public GuestList getAllGuests()
  {
    GuestList allGuests = new GuestList();

    try
    {
      Object[] temp = MyFileHandler.readArrayFromBinaryFile(fileName);
      for (int i = 0; i < temp.length; i++) {
        if (temp[i] instanceof GuestList) {
          for (int j = 0; j < ((GuestList) temp[i]).size(); j++)
            allGuests.addGuest(((GuestList) temp[i]).getGuest(j));
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allGuests;
  }

  // Use the MyFileHandler class to retrieve a RoomList object with all Rooms
  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();

    try
    {
      Object[] temp = MyFileHandler.readArrayFromBinaryFile(fileName);
      for (int i = 0; i < temp.length; i++) {
        if (temp[i] instanceof RoomList) {
          for (int j = 0; j < ((RoomList) temp[i]).size(); j++)
            allRooms.addRoom(((RoomList) temp[i]).getRoom(j));
        }
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allRooms;
  }

  // Method that returns a booking list with the expected arrivals
  public BookingList getExpectedArrivals()
  {
    BookingList allBookings = getAllBookings();
    BookingList expectedArrivals = new BookingList();
    LocalDate currentDate = LocalDate.now();
    Date temp = new Date(currentDate.getDayOfMonth(),
        currentDate.getMonthValue(), currentDate.getYear());

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Booking booking = allBookings.getBooking(i);

      if(booking.getDateInterval().getArrivalDate().equals(temp) && !booking.isCheckIn())
      {
        expectedArrivals.addBooking(booking);
      }
    }
    return expectedArrivals;
  }

  // Method that returns a booking list with the expected departures
  public BookingList getExpectedDepartures()
  {
    BookingList allBookings = getAllBookings();
    BookingList expectedDepartures = new BookingList();
    LocalDate currentDate = LocalDate.now();
    Date temp = new Date(currentDate.getDayOfMonth(), currentDate.getMonthValue(),currentDate.getYear());

    for (int i = 0; i < allBookings.size() ; i++)
  {
    Booking booking = allBookings.getBooking(i);

    if(booking.getDateInterval().getDepartureDate().equals(temp))
    {
      expectedDepartures.addBooking(booking);
    }
  }
    return expectedDepartures;
  }

  public void updateBookings (BookingList bookingList) {

    RoomList allRooms = getAllRooms();
    GuestList guests = getAllGuests();

    ArrayList<Object> allData  = new ArrayList<>();
    allData.add(allRooms);
    allData.add(guests);
    allData.add(bookingList);

    try
    {
      MyFileHandler.writeArrayToBinaryFile(fileName, allData.toArray(new Object[allData.size()]));
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }


  }

  public void updateAllData(ArrayList<Object> allData)
  {
    try
    {
      MyFileHandler.writeArrayToBinaryFile(fileName, allData.toArray(new Object[allData.size()]));
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  //Method that returns a room list will all available rooms from a specific period
  public RoomList getAvailableRoomsForASpecificPeriod(DateInterval dateInterval)
  {
    BookingList allBookings = getAllBookings();
    RoomList availableRooms = getAllRooms();

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Room room = allBookings.getBooking(i).getBookedRoom();

      if(!(allBookings.getBooking(i).getDateInterval().isAvailableDate(dateInterval)))
      {
        availableRooms.removeRoom(room);
      }

    }
    return availableRooms;
  }

  //Method that calculates and returns the price of the booking
  public double getPrice(Booking booking)
  {
    BookingList allBookings = getAllBookings();
    double price=0;

    for (int i = 0; i < allBookings.size(); i++)
    {
      if(allBookings.getBooking(i).equals(booking))
      {
        price = allBookings.getBooking(i).getDateInterval().getNumberOfNights()* allBookings.getBooking(i).getBookedRoom().getPrice();
      }
    }
    return price;
  }

  //Method that returns the price with the discount if the guest is not happy about the facilities of the hotel
  public double priceWithDiscount(Booking booking, double discount)
  {
    BookingList allBookings = getAllBookings();

    double price = 0;

    for (int i = 0; i < allBookings.size(); i++)
    {
      if(allBookings.getBooking(i).equals(booking))
      {
        price = getPrice(booking);
        price = price - ((discount/100)*price);
      }
    }
    return price;
  }
/* updateXML() method updates the XML file with information about all bookings that is used on the Hotel's webpage */
  public void updateXML() throws FileNotFoundException
  {
    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\Ola\\WebstormProjects\\SEP1\\Web\\xml\\bookingList.xml");
    PrintWriter write = new PrintWriter(fileOut);
    write.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?> ");
    write.println("<bookings>");
    for(int i=0; i < getAllBookings().size(); i++)
    {
      write.println("<booking>");
      write.println("<room>" + getAllBookings().getBooking(i).getBookedRoom().getRoomType() + "</room>");
      write.println("<arrival>" + getAllBookings().getBooking(i).getDateInterval().getArrivalDate() + "</arrival>");
      write.println("<departure>" + getAllBookings().getBooking(i).getDateInterval().getDepartureDate()+ "</departure>");
      write.println("</booking>");
    }
    write.println("</bookings>");
    write.close();
    System.out.println("File is created");
  }

  // TODO: Implement the method that filter all booking by first name and last name
    public BookingList filterBookingByName(String firstName, String lastName)
    {
        BookingList allBookings = getAllBookings();
        BookingList filteredBookings = new BookingList();

        for (int i = 0; i < allBookings.size(); i++)
        {
        if(allBookings.getBooking(i).getBookingGuest().getFirstName().equals(firstName) && allBookings.getBooking(i).getBookingGuest().getLastName().equals(lastName))
        {
            filteredBookings.addBooking(allBookings.getBooking(i));
        }
        }
        return filteredBookings;
    }




}
