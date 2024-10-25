public class Walkman implements Soundable {
    private int volume;

    @Override
    public void makeSound() {
        System.out.println("随身听正在播放音乐...");
    }

    @Override
    public void adjustVolume(int level) {
        volume = level;
        System.out.println("随身听音量调节为: " + volume);
    }
}
