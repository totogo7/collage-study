// USBTest.java

// 定义接口 USB
interface USB {
    void install();
    void uninstall();
}

// 定义外设类 Printer, Mouse, Keyboard 实现USB接口
class Printer implements USB {
    @Override
    public void install() {
        System.out.println("打印机已安装");
    }

    @Override
    public void uninstall() {
        System.out.println("打印机已卸载");
    }
}

class Mouse implements USB {
    @Override
    public void install() {
        System.out.println("鼠标已安装");
    }

    @Override
    public void uninstall() {
        System.out.println("鼠标已卸载");
    }
}

class Keyboard implements USB {
    @Override
    public void install() {
        System.out.println("键盘已安装");
    }

    @Override
    public void uninstall() {
        System.out.println("键盘已卸载");
    }
}

// 定义计算机类
class Computer {
    private USB[] usbDevices = new USB[3];  // 计算机的USB接口

    // 插入USB设备
    public void plugInDevice(USB device, int port) {
        if (port >= 0 && port < usbDevices.length) {
            usbDevices[port] = device;
            device.install();
        } else {
            System.out.println("无效的接口");
        }
    }

    // 卸载USB设备
    public void unplugDevice(int port) {
        if (port >= 0 && port < usbDevices.length && usbDevices[port] != null) {
            usbDevices[port].uninstall();
            usbDevices[port] = null;
        } else {
            System.out.println("无效的接口或没有设备");
        }
    }
}

// 主类 USBTest
public class USBTest {
    public static void main(String[] args) {
        // 创建外设设备
        Printer printer = new Printer();
        Mouse mouse = new Mouse();
        Keyboard keyboard = new Keyboard();

        // 创建计算机对象
        Computer computer = new Computer();

        // 插入设备
        computer.plugInDevice(printer, 0);
        computer.plugInDevice(mouse, 1);
        computer.plugInDevice(keyboard, 2);

        // 卸载设备
        computer.unplugDevice(1); // 卸载鼠标
        computer.unplugDevice(0); // 卸载打印机
    }
}
