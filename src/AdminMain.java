import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.*;

public class AdminMain {
    public static void main(String[] args) {
        // ThreePaneSplit 창 생성
        AdminTPS mainFrame = new AdminTPS("송직한 리뷰");

        // 패널 생성
        AdminRS rsPanel = new AdminRS();
        AdminInfo infoPanel = new AdminInfo("포36거리");
        ReviewBoard rBoard2 = new ReviewBoard();

        // 메인프레임 패널 추가
        mainFrame.getLeftPanel().add(rsPanel, BorderLayout.CENTER);
        mainFrame.getTopRightPanel().add(rBoard2);
        mainFrame.getBottomRightPanel().add(infoPanel);
        mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        mainFrame.setVisible(true);
    }
}
