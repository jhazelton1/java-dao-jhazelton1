# Data Access Objects

---

## Description
Create a series of Data Access Objects (DAOs) that will allow for clean, easy interaction with the database schema created in the Schema Design assignment.

---

## Instructions

Create a PersonDao class. Implement the following public methods in the PersonDao class. Note: You may implement any number of additional private methods to support the following required public methods.
 1. Implement a method get(id), where id is the primary key of the Person you want to retrieve
 2. Implement a save(Person) method such that:
   - The data in the Person parameter is saved as a new entry in the database if the Id field is null
   - The data in the Person parameter is used to update an existing entry in the database if the Id field is not null and already exists in the database
   - An exception is thrown if the Id field is not null and does not already exist in the database
 4. Implement a findInterestGroup(Interest, Location) method that:
   - Executes the Stored Procedure defined in the Schema Design Assignment
   - Transforms the ResultSet obtained from executing the Stored Procedure into a Set<Person>
   - Returns the Set<Person>
 
Create the LocationDao and InterestDao classes
 1. Implement get(id) for both classes
 2. Implement save(Location) and save(Interest)
 3. Add a "location" field to the Person class tthat is the data type Location
 4. Add an "interests" field to the Person class that is the data type Set<Interest>
 5. Modify the get(id) method in the PersonDao to interact with the LocationDao and InterestDao in order to ensure that all Person objects retrieved have accurate data in their interests and location fields.
 6. Modify the save(Person) method in the PersonDao to also update the Location Table, the Interest Table, and any related Join Tables
 
---

## Steps

* Create POJOS (Person, Location, Interest) to represent the tables in the database schema.

1. PersonDao
  * id - Long
  * firstName - String
  * lastName - String
  * age - Integer
  * get(id)
  * save(id)
  * findInterestGroup(Interest, Location)
  
2. LocationDao
  * id - Long
  * city - String
  * state - String
  * country - String
  * get(id)
  * save(Location)

3. InterestDao
  * id - Long
  * title - String
  * get(id)
  * save(Interest)
