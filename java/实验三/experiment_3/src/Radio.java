public class Radio implements Soundable {
    private int volume;

    @Override
    public void makeSound() {
        System.out.println("收音机正在播放音乐...");
    }

    @Override
    public void adjustVolume(int level) {
        volume = level;
        System.out.println("收音机音量调节为: " + volume);
    }
}
