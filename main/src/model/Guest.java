package model;

import java.io.Serializable;

/**
 * A class representing information about guest's first and last name, address phone number, nationality and birthday.
 *
 * @author Andreea Asimine
 * @version 1.1
 */
public class Guest implements Serializable
{
  //creating fields/attributes for class Guest
  private String firstName;
  private String lastName;
  private String address;
  private String phoneNumber;
  private String nationality;
  private Date birthday;

  /**
   * Three-argument constructor.
   *
   * @param firstName   the guest's first name
   * @param lastName    the guest's last name
   * @param address     the guest's address
   * @param phoneNumber the guest's phone number
   * @param nationality the guest's nationality
   * @param birthday    the guest's birthday
   */
  public Guest(String firstName, String lastName, String address,
      String phoneNumber, String nationality, Date birthday)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.phoneNumber = phoneNumber;
    this.nationality = nationality;
    this.birthday = birthday.copy();
  }

  /**
   * Sets guest's first name.
   *
   * @param firstName what the guest's first name will be set to
   */
  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * Sets guest's last name.
   *
   * @param lastName what the guest's first name will be set to
   */
  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * Sets guest's address.
   *
   * @param address what the guest's address will be set to
   */
  public void setAddress(String address)
  {
    this.address = address;
  }

  /**
   * Sets guest's phone number.
   *
   * @param phoneNumber what the guest's address will be set to
   */
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * Sets guest's nationality.
   *
   * @param nationality what the guest's nationality will be set to
   */
  public void setNationality(String nationality)
  {
    this.nationality = nationality;
  }

  /**
   * Sets guest's birthday.
   *
   * @param birthday what the guest's birthday will be set to
   */
  public void setBirthday(Date birthday)
  {
    this.birthday = birthday.copy();
  }

  /**
   * Gets guest's first name.
   *
   * @return the guest's first name
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets guest's last name.
   *
   * @return the guest's last name
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets guest's address.
   *
   * @return the guest's Address
   */
  public String getAddress()
  {
    return address;
  }

  /**
   * Gets guest's phone number.
   *
   * @return the guest's phone number
   */
  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * Gets guest's nationality.
   *
   * @return the guest's nationality
   */
  public String getNationality()
  {
    return nationality;
  }

  /**
   * Gets guest's birthday.
   *
   * @return the guest's birthday
   */
  public Date getBirthday()
  {
    return birthday.copy();
  }

  /**
   * Returns a string representation of the guest.
   *
   * @return a string representation of the guest
   */
  public String toString()
  {
    return "First name: " + firstName + "\n" + "Last name: " + lastName + "\n"
        + "Address: " + address + "\n" + "Phone number: " + phoneNumber + "\n"
        + "Nationality: " + nationality + "\n" + "Birthday: " + birthday;
  }

  /**
   * Compares object with Guest class object.
   *
   * @param obj the object to compare
   * @return true if the given object is instance of Guest class and is same as guest object else returns false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Guest)) //check if obj is the same type
    {
      return false;
    }
    Guest other = (Guest) obj; //cast obj to this type

    //check if the fields are the same
    return firstName.equals(other.firstName) && lastName.equals(other.lastName)
        && address.equals(other.address) && phoneNumber.equals(
        other.phoneNumber) && nationality.equals(other.nationality)
        && birthday.equals(other.birthday);
  }
}
