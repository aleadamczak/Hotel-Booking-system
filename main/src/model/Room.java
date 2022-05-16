package model;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * Room class.
 * Returns the room number, the room type, the room price, and the room status.
 *
 * @author Dragos Cotaga
 * @version 1.1
 * @see Room
 * @since 2022-05-10
 */

// Room class
public class Room implements Serializable
    {
    private String roomNumber, roomType;
    private boolean extraBed, available;
    private double price;


    // Constructor for the Room class. Initializes the room number, room type, extra bed, available, and price variables.
    public Room(String roomNumber, String roomType, double price) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.price = price;
        this.available = true;
    }

    public Room(String roomNumber)
    {
        this.roomNumber = roomNumber;

        switch (roomNumber)
        {
            case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10":
                roomType = "Single Room";
                price = 129;
                break;
            case "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32":
                roomType = "Double Room - King Size Bed";
                price = 169;
                break;
            case "33", "34", "35", "36", "37":
                roomType = "Double Room - Twin Size Bed";
                price = 169;
                break;
            case "38", "39", "40":
                roomType = "Single Suite";
                price = 259;
                break;
            case "41":
                roomType = "Double Suite";
                price = 339;
                break;
            case "42":
                roomType = "Triple Suite";
                price = 399;
                break;
            default:
               break;
        }
    }

    // Method: getRoomNumber() - returns the room number of the room object being called on in the method call (Room room = new Room(1); room.getRoomNumber();)
    public String getRoomNumber() {
        return roomNumber;
    }

    // Method: setRoomNumber() - sets the room number of the room object being called on in the method call (Room room = new Room(1); room.setRoomNumber(2);)
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    // Method: getRoomType() - returns the room type of the room object being called on in the method call (Room room = new Room(1); room.getRoomType();)
    public String getRoomType() {
        return roomType;
    }

    // Method: setRoomType() - sets the room type of the room object being called on in the method call (Room room = new Room(1); room.setRoomType("Single");)
    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    // Method: isExtraBed() - returns the extra bed status of the room object being called on in the method call (Room room = new Room(1); room.isExtraBed();)
    public boolean isExtraBed() {
        return extraBed;
    }

    // Method: setExtraBed() - sets the extra bed status of the room object being called on in the method call (Room room = new Room(1); room.setExtraBed(true);)
    public void setExtraBed(boolean extraBed) {
        this.extraBed = extraBed;
    }

    // Method: isAvailable() - returns true if the room is available, false if it is not.
    public boolean isAvailable() {
        return available;
    }

    // Method: setAvailable() - sets the room to available or not available.
    public void setAvailability(boolean available) {
        this.available = available;
    }

    // Method: getPrice() - returns the price of the room.
    public double getPrice() {
        return price;
    }

    // Method: setPrice() - sets the price of the room.
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber + ", Room Type: " + roomType +  ", Price: " + price;
    }

    // Method: toString() - returns the room number, the room type, the room price, and the room status.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (extraBed != room.extraBed) return false;
        if (available != room.available) return false;
        if (Double.compare(room.price, price) != 0) return false;
        if (roomNumber != null ? !roomNumber.equals(room.roomNumber) : room.roomNumber != null) return false;
        return roomType != null ? roomType.equals(room.roomType) : room.roomType == null;
    }

    // Method: hashCode() - returns the hashcode of the room. This is used to compare two rooms and determine if they are the same.
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = roomNumber != null ? roomNumber.hashCode() : 0;
        result = 31 * result + (roomType != null ? roomType.hashCode() : 0);
        result = 31 * result + (extraBed ? 1 : 0);
        result = 31 * result + (available ? 1 : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}



