package lt.Sinomoro.Indexer.Utility;

public class Data{
    private String post_code;
    private String address;
    private String street;
    private String number;
    private String city;

    public Data(boolean empty) {
        this.post_code = "";
        this.address = "";
        this.street ="";
        this.number = "";
        this.city = "";
    }


    @Override
    public String toString() {
        return  city+ " " + street +" "+ number +" - "+ post_code ;
    }

    public String getPost_code() {
        return post_code;
    }

    public String getAddress() {
        return address;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

}