package isel.sisinf.g17.model;

public class ClienteParticular extends Cliente {
    private int cc;

    public int getCc() {
        return cc;
    }

    public void setCc(int cc) {
        if (cc < 0 || cc > 99999999)
            throw new IllegalArgumentException("Invalid cc");
        this.cc = cc;
    }
}
