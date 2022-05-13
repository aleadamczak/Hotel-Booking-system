package model;
import utilis.MyFileHandler;
import java.time.LocalDate;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.Serializable;
import utilis.MyFileHandler;
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
      allBookings = (BookingList) MyFileHandler.readFromBinaryFile(fileName);
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
      allGuests = (GuestList) MyFileHandler.readFromBinaryFile(fileName);
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
      allRooms = (RoomList) MyFileHandler.readFromBinaryFile(fileName);
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

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Booking booking = allBookings.getBooking(i);

      if(booking.getDateInterval().getArrivalDate().equals(currentDate))
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

    for (int i = 0; i < allBookings.size() ; i++)
  {
    Booking booking = allBookings.getBooking(i);

    if(booking.getDateInterval().getDepartureDate().equals(currentDate))
    {
      expectedDepartures.addBooking(booking);
    }
  }
    return expectedDepartures;
  }

  //Method that creates a new booking
  public Booking createBooking(Guest guest, Room room, DateInterval dates)
  {
    BookingList allBookings = getAllBookings();
    RoomList availableRooms = getAvailableRoomsForASpecificPeriod(dates);
    Booking booking = null;

    for (int i = 0; i < availableRooms.size(); i++)
    {
      if(availableRooms.getRoom(i).equals(room))
      {
        GuestList allGuests = new GuestList();

         booking = new Booking(allGuests, room, dates);
        allGuests.addGuest(guest);
        allBookings.addBooking(booking);
      }
    }
    return booking;
  }

  //Method that search a booking in the system
  public ArrayList searchBooking(String firstName, String lastName)
  {
    BookingList allBookings = getAllBookings();

    if(allBookings.getBookingsByFullName(firstName, lastName).size()!=0)

      return allBookings.getBookingsByFullName(firstName, lastName);

     else return null;
  }

  //Method that removes a booking from the system
  public void removeBooking(String firstName, String lastName)
  {
    BookingList allBookings = getAllBookings();

    for (int i = 0; i < allBookings.size(); i++)
    {
      Booking booking = allBookings.getBooking(i);

      if (booking.getBookingGuest().getFirstName().equals(firstName)
          && booking.getBookingGuest().getLastName().equals(lastName))
        allBookings.deleteBooking(booking);

      booking.getBookedRoom().setAvailability(false);
      allBookings.deleteBooking(booking);
    }

  }

  // Use the MyFileHandler class to save all Bookings in the BookingList object
  public void saveBooking(BookingList bookings)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, bookings);
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
    RoomList availableRooms = new RoomList();

    for (int i = 0; i < allBookings.size() ; i++)
    {
      Room room = allBookings.getBooking(i).getBookedRoom();

      if(allBookings.getBooking(i).getDateInterval().IsAvailableDate(dateInterval)) availableRooms.addRoom(room);

    }
    return availableRooms;
  }

  //Method that returns a room list will all available rooms of just one type from a specific period
  public RoomList getSpecificTypeRoomsForAPeriod(String roomType, DateInterval dateInterval)
  {
    RoomList availableRooms = getAvailableRoomsForASpecificPeriod(dateInterval);
    RoomList rooms = new RoomList();

    for (int i = 0; i < availableRooms.size(); i++)
    {
      if(availableRooms.getRoom(i).getRoomType().equals(roomType))rooms.addRoom(availableRooms.getRoom(i));
    }
    return rooms;
  }

  //Method that calculates and returns the price of the booking
  public Double getPrice(Booking booking)
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
  public Double priceWithDiscount(Booking booking)
  {
    BookingList allBookings = getAllBookings();

    double price = 0;

    for (int i = 0; i < allBookings.size(); i++)
    {
      if(allBookings.getBooking(i).equals(booking))
      {
        price = getPrice(booking);
        price = price - (0.1*price);
      }
    }
    return price;
  }



}
