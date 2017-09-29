import java.util.ArrayList;

public class Model {
    private ArrayList surnameList = new ArrayList();
    private ArrayList nameList = new ArrayList();
    private ArrayList fathernameList = new ArrayList();
    private ArrayList countryList = new ArrayList();
    private ArrayList regionList = new ArrayList();
    private ArrayList cityList = new ArrayList();
    private ArrayList streetList = new ArrayList();
    private ArrayList houseList = new ArrayList();
    private ArrayList housingList = new ArrayList();
    private ArrayList apartmentList = new ArrayList();

    private int listNumber = 0;

    public void addData(String surname, String name, String fathername, String country, String region, String city,
                        String street, String house, String housing, String apartment) {
        listNumber++;

        surname = surname.toUpperCase();
        surname = surname.charAt(0) + surname.substring(1).toLowerCase();

        name = name.toUpperCase();
        name = name.charAt(0) + name.substring(1).toLowerCase();

        fathername = fathername.toUpperCase();
        fathername = fathername.charAt(0) + fathername.substring(1).toLowerCase();

        country = country.toUpperCase();
        country = country.charAt(0) + country.substring(1).toLowerCase();

        region = region.toUpperCase();
        region = region.charAt(0) + region.substring(1).toLowerCase();

        city = city.toUpperCase();
        city = city.charAt(0) + city.substring(1).toLowerCase();

        street = street.toUpperCase();
        street = street.charAt(0) + street.substring(1).toLowerCase();

        surnameList.add(surname);
        nameList.add(name);
        fathernameList.add(fathername);
        countryList.add(country);
        regionList.add(region);
        cityList.add(city);
        streetList.add(street);
        houseList.add(Integer.parseInt(house));
        housingList.add(Integer.parseInt(housing));
        apartmentList.add(Integer.parseInt(apartment));
    }

    public void clearModel() {
        surnameList = new ArrayList();
        nameList = new ArrayList();
        fathernameList = new ArrayList();
        countryList = new ArrayList();
        regionList = new ArrayList();
        cityList = new ArrayList();
        streetList = new ArrayList();
        houseList = new ArrayList();
        housingList = new ArrayList();
        apartmentList = new ArrayList();

        listNumber = 0;
    }

    public ArrayList<String> getSurnameList() {
        return surnameList;
    }
    public ArrayList<String> getNameList() {
        return nameList;
    }
    public ArrayList<String> getFathernameList() {
        return fathernameList;
    }
    public ArrayList<String> getCountryList() {
        return countryList;
    }
    public ArrayList<String> getRegionList() {
        return regionList;
    }
    public ArrayList<String> getCityList() {
        return cityList;
    }
    public ArrayList<String> getStreetList() {
        return streetList;
    }
    public ArrayList<Integer> getHouseList() {
        return houseList;
    }
    public ArrayList<Integer> getHousingList() {
        return housingList;
    }
    public ArrayList<Integer> getApartmentList() {
        return apartmentList;
    }
    public int getListNumber() {
        return listNumber;
    }
}