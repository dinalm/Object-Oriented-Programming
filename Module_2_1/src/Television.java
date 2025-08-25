public class Television {
    private int channel;
    private boolean on;

    public Television() {
        this.channel = 1;
        this.on = false;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int newChannel) {
        if (newChannel > 10) {
            this.channel = 1;
        } else if (newChannel < 1) {
            this.channel = 1;
        } else {
            this.channel = newChannel;
        }
    }

    public boolean isOn() {
        return on;
    }

    public void pressOnOff() {
        on = !on;
    }
}
