## Package Challenge

## Getting Started

These instructions will help in getting the project up and running on your local machine for development and testing purposes.

### Prerequisites
```
Java 1.8 or higher
Maven 3.3.9 or higher 
Import as a maven project.
````

### Installing

Close the repository "git clone https://github.com/reetesh043/Packer-Challenge.git" 

## Running the tests

mvn clean package

## Built With
```
* [Maven](https://maven.apache.org/) - Dependency Management
* [Java 11] (https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
```

## Authors
* **Reetesh Kumar** - *Initial implementation* - [my github](https://github.com/reetesh043/Packer-Challenge.git)


## API Details
```
[public static String pack(String filePath) throws APIException](https://github.com/reetesh043/Packer-Challenge/blob/master/src/main/java/com/mobiquity/packer/Packer.java)
```

API is responsible for fetching the list of index of items to  be packed.
API reads input from the file path line by line and parse to return the list of package.

## Algorithm
Dynamic Programming: The knapsack problem could be divided into multiple sub problems, and the results of the sub-problem is stored to compute the complete problem without solving the sub-problems again and again.

Below is the API defined in the project to get the choosen items using Dynamic Programming.
```
[public static String getPackedItems(int weight, List<Item> items)](https://github.com/reetesh043/Packer-Challenge/blob/master/src/main/java/com/mobiquity/processor/PackageProcessor.java#L99)
```
##### Data Structure
* List - As the list of packages, and the list of items in each package is not predefined, using arrayList to dynamically add the items to the package.
