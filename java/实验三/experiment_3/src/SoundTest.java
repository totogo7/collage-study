import java.util.Scanner;

public class SoundTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请选择设备 (1: 收音机, 2: 随身听, 3: 手机, 4: 闹钟): ");
        int choice = scanner.nextInt();

        Soundable device;

        switch (choice) {
            case 1:
                device = new Radio();
                break;
            case 2:
                device = new Walkman();
                break;
            case 3:
                device = new Mobilephone();
                break;
            case 4:
                device = new Clock();
                break;
            default:
                System.out.println("无效的选择。");
                return;
        }

        device.makeSound(); // 发出声音
        System.out.print("请输入音量(1-10): ");
        int volumeLevel = scanner.nextInt();
        device.adjustVolume(volumeLevel); // 调节音量

        scanner.close();
    }
}
