public class Mobilephone implements Soundable {
    private int volume;

    @Override
    public void makeSound() {
        System.out.println("手机正在播放铃声...");
    }

    @Override
    public void adjustVolume(int level) {
        volume = level;
        System.out.println("手机音量调节为: " + volume);
    }
}
