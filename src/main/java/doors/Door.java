package doors;

public class Door {
    private boolean open;

    public Door() {
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public void invertOpen() {
        this.setOpen(!this.isOpen());
    }
}
