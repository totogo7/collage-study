public class Clock implements Soundable {
    private int volume;

    @Override
    public void makeSound() {
        System.out.println("闹钟发出滴答声...");
    }

    @Override
    public void adjustVolume(int level) {
        volume = level;
        System.out.println("闹钟音量调节为: " + volume);
    }
}
