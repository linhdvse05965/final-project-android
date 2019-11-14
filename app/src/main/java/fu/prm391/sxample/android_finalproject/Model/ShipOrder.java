package fu.prm391.sxample.android_finalproject.Model;

public class ShipOrder {
    private String phone;
    private String address;

    public ShipOrder() {
    }

    public ShipOrder(String phone, String address) {
        this.phone = phone;
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
