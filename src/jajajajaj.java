//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ComponentAdapter;
//import java.awt.event.ComponentEvent;
//
//public class jajajajaj extends JFrame {
//
//    private static jajajajaj instance; // 현재 인스턴스를 저장할 static 변수
//    private JSplitPane horizontalSplit;
//    private JPanel leftPanel;
//
//    public jajajajaj() {
//        super("송직한 리뷰");
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        setLayout(new BorderLayout());
//
//        // 패널 초기화
//        leftPanel = new LoginPanel(); // 왼쪽 패널
//        JPanel topRightPanel = new JPanel(new BorderLayout()); // 오른쪽 상단 패널
//        JPanel bottomRightPanel = new ReveiwPagePanel2(); // 오른쪽 하단 패널
//
//        topRightPanel.setBackground(Color.WHITE);
//        topRightPanel.setBackground(Color.WHITE);
//
//        JSplitPane rightVerticalSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT, topRightPanel, bottomRightPanel);
//        rightVerticalSplit.setOneTouchExpandable(false);
//        rightVerticalSplit.setDividerSize(0);
//
//        horizontalSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightVerticalSplit); // horizontalSplit 필드 초기화
//        horizontalSplit.setOneTouchExpandable(true);
//        horizontalSplit.setDividerSize(0);
//
//        add(horizontalSplit, BorderLayout.CENTER);
//
//        // 프레임을 최대화 상태로 설정
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
//
//        // 프레임을 보이도록 설정
//        setVisible(true);
//
//        // 컴포넌트 리스너 추가하여 프레임이 완전히 보이는 시점에 분할 위치를 설정
//        addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentShown(ComponentEvent e) {
//                Rectangle screenBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds(); // 작업표시줄을 제외한 부분에 대해서 화면 크기를 분할함.
//                rightVerticalSplit.setDividerLocation(screenBounds.height / 2);
//                horizontalSplit.setDividerLocation(screenBounds.width / 2);
//            }
//        });
//
//        // 이전 인스턴스가 있다면 닫기
//        if (instance != null) {
//            instance.dispose();
//        }
//
//        // 현재 인스턴스를 이전 인스턴스로 설정
//        instance = this;
//}
//
//    public static jajajajaj getInstance() {
//        return instance;
//    }
//
//    public JPanel getLeftPanel() {
//        return leftPanel;
//    }
//
//    public JSplitPane getHorizontalSplit() {
//        return horizontalSplit;
//    }
//
//    public void setLeftPanel(JPanel newPanel) {
//        leftPanel = newPanel;
//    }
//
//    public void changeLeftPanel(JPanel newPanel) {
//        horizontalSplit.remove(leftPanel); // 기존 leftPanel 제거
//        leftPanel = newPanel; // 새로운 패널으로 leftPanel 교체
//        horizontalSplit.setLeftComponent(leftPanel); // 새로운 leftPanel 설정
//
//        revalidate(); // 변경 사항을 적용하기 위해 revalidate 호출
//        repaint(); // 변경된 패널을 다시 그리기 위해 repaint 호출
//    }
//
//    public void replaceLeftPanelContent(JPanel newContent) {
//        leftPanel.removeAll(); // 기존 패널의 모든 컴포넌트 삭제
//        leftPanel.add(newContent, BorderLayout.CENTER); // 새로운 컨텐츠 패널을 추가
//
//        revalidate(); // 변경 사항을 적용하기 위해 revalidate 호출
//        repaint(); // 변경된 패널을 다시 그리기 위해 repaint 호출
//    }
//
////    // 패널 변경 메서드
////    public static void changeLeftPanelContent2(JPanel newContent) {
////        if (instance != null) {
////            instance.leftPanel.removeAll(); // 기존 패널의 모든 컴포넌트 삭제
////            instance.leftPanel.add(newContent); // 새로운 컨텐츠 패널을 추가
////
////            instance.revalidate(); // 변경 사항을 적용하기 위해 revalidate 호출
////            instance.repaint(); // 변경된 패널을 다시 그리기 위해 repaint 호출
////        }
////    }
//
////    // 패널 변경 메서드를 public으로 선언
////    public void changePPPP(JPanel newContent) {
////        leftPanel.removeAll(); // 기존 패널의 모든 컴포넌트 삭제
////        leftPanel.add(newContent); // 새로운 컨텐츠 패널을 추가
////
////        revalidate(); // 변경 사항을 적용하기 위해 revalidate 호출
////        repaint(); // 변경된 패널을 다시 그리기 위해 repaint 호출
////    }
//
//    public void changePPPP(JPanel newPanel) {
//        horizontalSplit.remove(leftPanel); // 기존 leftPanel 제거
//        leftPanel = newPanel; // 새로운 패널으로 leftPanel 교체
//        horizontalSplit.setLeftComponent(leftPanel); // 새로운 leftPanel 설정
//
//        revalidate(); // 변경 사항을 적용하기 위해 revalidate 호출
//        repaint(); // 변경된 패널을 다시 그리기 위해 repaint 호출
//    }
//
//    public static void main(String args[]) {
//        SwingUtilities.invokeLater(() -> {
//            new jajajajaj();
//        });
//    }
//}
//
