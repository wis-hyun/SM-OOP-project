import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RestaurantList_U extends JPanel {
    private static final String IMAGE_FOLDER_PATH = "C:\\SHE_Project\\Java\\SM_OOP\\SM_OPP2\\store_img2"; // 이미지 폴더 경로
    private static final int IMAGE_WIDTH = 200; // 이미지 너비
    private static final int IMAGE_HEIGHT = 150; // 이미지 높이

    // 화면 크기 가져오기
    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Dimension screenSize = toolkit.getScreenSize();

    // 화면 너비의 절반 계산
    int halfScreenWidth = screenSize.width / 2;
    int screenHeight = screenSize.height;

    public RestaurantList_U(String category) {
        // 패널 크기를 화면 너비의 절반과 전체 화면 높이로 설정
        setPreferredSize(new Dimension(halfScreenWidth, screenHeight));
        setLayout(new BorderLayout());

        // 제목 라벨 생성
        JLabel label = new JLabel(category);
        label.setPreferredSize(new Dimension(halfScreenWidth, 60)); // 라벨의 크기 설정
        label.setFont(new Font("NPS font", Font.BOLD, 30));
        label.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0)); // 제목의 왼쪽에 여백을 준다

        // 가짜용 "" 공백 버튼 생성
        JButton etcButton = new JButton("");
        etcButton.addActionListener(new SortButtonListener());
        etcButton.setBorderPainted(false); // 버튼 테두리 숨기기
        etcButton.setContentAreaFilled(false); // 버튼 배경 숨기기
        addHoverEffect(etcButton); // 마우스 호버 효과 및 둥근 테두리 추가

        // 제목 라벨과 "정렬" 버튼을 포함할 패널 생성
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(label, BorderLayout.CENTER);
        titlePanel.add(etcButton, BorderLayout.EAST);
        titlePanel.setBackground(Color.WHITE);
        add(titlePanel, BorderLayout.NORTH); // 제목 패널을 프레임의 북쪽에 추가

        // 식당 정보를 출력할 패널 생성
        JPanel restaurantPanel = new JPanel();
        restaurantPanel.setLayout(new BoxLayout(restaurantPanel, BoxLayout.Y_AXIS));
        restaurantPanel.setBackground(Color.WHITE);

        // CSV 파일에서 식당 정보 읽기
        List<Restaurant> restaurants = readRestaurantData("store_scroll 2.csv", category);

        // 평점 높은 순으로 정렬
        restaurants.sort(Comparator.comparingDouble(Restaurant::getRating).reversed());

        // 식당별 버튼과 사진 생성
        for (Restaurant restaurant : restaurants) {
            String starRating = getStarRating(restaurant.rating);
            String buttonText = String.format("<html><b style='font-size:20px;'>%s</b><br>" +
                            "%s (%.1f)&nbsp;&nbsp;&nbsp;리뷰수: %d<br>" +
                            "주메뉴: %s<br>" +
                            "주소: %s<br>" +
                            "영업시간: %s<br>" +
                            "브레이크타임: %s&nbsp;&nbsp;&nbsp;&nbsp;휴무일: %s</html>",
                    restaurant.name, starRating, restaurant.rating, restaurant.reviewCount,
                    restaurant.mainDish, restaurant.address, restaurant.hours,
                    restaurant.breakTime, restaurant.holidays);

            JButton button = new JButton();
            button.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

            // 텍스트 라벨 추가
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.fill = GridBagConstraints.BOTH;

            JLabel textLabel = new JLabel(buttonText);
            textLabel.setFont(new Font("NPS font", Font.PLAIN, 20));
            button.add(textLabel, gbc);

            // 식당 이미지 추가
            String imageFilePath = IMAGE_FOLDER_PATH + "/" + restaurant.imageFileName;
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(imageFilePath).getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
            JLabel imageLabel = new JLabel(imageIcon);

            gbc.gridx = 1;
            gbc.gridy = 0;
            gbc.weightx = 0;
            gbc.weighty = 0;
            gbc.anchor = GridBagConstraints.NORTHEAST;
            gbc.insets = new Insets(2, 2, 2, 0); // 여백 추가
            button.add(imageLabel, gbc);

            button.setPreferredSize(new Dimension(halfScreenWidth, (screenHeight - 70) / 4)); // 버튼 크기 조정
            button.setBorderPainted(false);
            button.setContentAreaFilled(false);
            addHoverEffect(button); // 마우스 호버 효과 및 둥근 테두리 추가

            // 버튼 클릭 시 특정 작업 수행
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (restaurant.name.equals("포36거리")) {
                        AdminTPS mainFrame = new AdminTPS("관리자 화면");

                        // 패널 생성
                        AdminRS_U rsPanel = new AdminRS_U();
                        ReveiwPagePanel2 infoPanel = new ReveiwPagePanel2();
                        ReviewBoard rBoard2 = new ReviewBoard();

                        // 메인프레임 패널 추가
                        mainFrame.getLeftPanel().add(rsPanel, BorderLayout.CENTER);
                        mainFrame.getTopRightPanel().add(rBoard2);
                        mainFrame.getBottomRightPanel().add(infoPanel);

                        // 현재 프레임 닫기
                        SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
                    } else if (restaurant.name.equals("홍곱창")) {
                        AdminTPS mainFrame = new AdminTPS("관리자 화면");

                        // 패널 생성
                        AdminRS2_U rsPanel = new AdminRS2_U();
                        ReveiwPagePanel2 infoPanel = new ReveiwPagePanel2();
                        ReviewBoard rBoard2 = new ReviewBoard();

                        // 메인프레임 패널 추가
                        mainFrame.getLeftPanel().add(rsPanel, BorderLayout.CENTER);
                        mainFrame.getTopRightPanel().add(rBoard2);
                        mainFrame.getBottomRightPanel().add(infoPanel);

                        // 현재 프레임 닫기
                        SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
                    } else if (restaurant.name.equals("을의커피")) {
                        AdminTPS mainFrame = new AdminTPS("관리자 화면");

                        // 패널 생성
                        AdminRS3_U rsPanel = new AdminRS3_U();
                        ReveiwPagePanel2 infoPanel = new ReveiwPagePanel2();
                        ReviewBoard rBoard2 = new ReviewBoard();

                        // 메인프레임 패널 추가
                        mainFrame.getLeftPanel().add(rsPanel, BorderLayout.CENTER);
                        mainFrame.getTopRightPanel().add(rBoard2);
                        mainFrame.getBottomRightPanel().add(infoPanel);

                        // 현재 프레임 닫기
                        SwingUtilities.getWindowAncestor((Component) e.getSource()).dispose();
                    }
                }
            });

            restaurantPanel.add(button); // 패널에 버튼 추가
        }

        // 스크롤 기능
        JScrollPane scrollPane = new JScrollPane(restaurantPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private List<Restaurant> readRestaurantData(String filePath, String category) {
        List<Restaurant> restaurants = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;
            while ((line = br.readLine()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue; // Skip the header line
                }
                String[] fields = line.split(",");
                String categoryFromFile = fields[0].trim();
                if (categoryFromFile.equals(category) || category.equals("전체")) {
                    String name = fields[1].trim();
                    double rating = Double.parseDouble(fields[2].trim());
                    int reviewCount = Integer.parseInt(fields[3].trim());
                    String mainDish = fields[4].trim();
                    String address = fields[5].trim();
                    String hours = fields[6].trim();
                    String breakTime = fields[7].trim().isEmpty() ? "없음" : fields[7].trim();
                    String holidays = fields[8].trim().isEmpty() ? "없음" : fields[8].trim();
                    String imageFileName = fields[9].trim();

                    restaurants.add(new Restaurant(name, rating, reviewCount, mainDish, address, hours, breakTime, holidays, imageFileName));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return restaurants;
    }

    // 평점을 주황색 별표로 변환하는 메서드
    private String getStarRating(double rating) {
        int fullStars = (int) rating;
        boolean halfStar = (rating - fullStars) >= 0.5;
        StringBuilder starRating = new StringBuilder();

        // 채워진 별 추가
        for (int i = 0; i < fullStars; i++) {
            starRating.append("<span style='color:orange;'>★</span>"); // HTML 사용하여 스타일 지정
        }

        // 소수점 단위 -> 반 별 추가 (있는 경우)
        if (halfStar) {
            starRating.append("<span style='color:orange;'>☆</span>");
            fullStars++; // 반 별을 추가했으므로 전체 별 개수 증가
        }

        // 빈 별 추가
        for (int i = fullStars; i < 5; i++) {
            starRating.append("<span style='color:orange; border: 1px solid orange;'>☆</span>");
        }

        return starRating.toString();
    }

    // 수정된 부분: 식당 정보를 저장할 클래스
    static class Restaurant {
        String name;
        double rating;
        int reviewCount;
        String mainDish;
        String address;
        String hours;
        String breakTime;
        String holidays;
        String imageFileName;

        public Restaurant(String name, double rating, int reviewCount, String mainDish, String address, String hours, String breakTime, String holidays, String imageFileName) {
            this.name = name;
            this.rating = rating;
            this.reviewCount = reviewCount;
            this.mainDish = mainDish;
            this.address = address;
            this.hours = hours;
            this.breakTime = breakTime;
            this.holidays = holidays;
            this.imageFileName = imageFileName;
        }

        public double getRating() {
            return rating;
        }
    }

    // 정렬 버튼의 액션 리스너
    private class SortButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            String category = source.getText();
            new DataSort(); // 정렬창 열기
        }
    }

    // 버튼에 마우스 호버 효과 및 둥근 테두리 추가
    private void addHoverEffect(JButton button) {
        // 마우스 호버 효과 추가
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBorderPainted(true); // 마우스가 버튼에 들어가면 테두리 그리기
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBorderPainted(false); // 마우스가 버튼에서 나가면 테두리 숨기기
            }
        });

        // 둥근 테두리 추가 (DarkOrange 색상 사용)
        Color darkOrangeColor = new Color(255, 140, 0); // RGB(255, 140, 0)
        Border roundedBorder = BorderFactory.createLineBorder(darkOrangeColor, 4, true);
        button.setBorder(roundedBorder);
    }

    // Test the panel in a JFrame
    public static void main(String[] args) {
        JFrame frame = new JFrame("Restaurant List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new RestaurantList("전체"));
        frame.pack();
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setVisible(true);
    }
}
