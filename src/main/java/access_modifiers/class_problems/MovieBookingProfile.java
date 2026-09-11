public class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    // No-argument constructor
    public MovieBookingProfile() {
    }

    // Convenience constructor
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Confirmed
    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // OTP - write only
    public void setOtp(String otp) {
        this.otp = otp;
    }
}