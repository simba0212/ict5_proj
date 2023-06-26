package com.ict5.client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentPage extends JPanel {
    
    public PaymentPage(Huge_main main) {
        setLayout(new BorderLayout());

        // 탑 패널
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setPreferredSize(new Dimension(300, 50));
        add(topPanel, BorderLayout.NORTH);

        // "포인트 충전" 글자와 "<<" 버튼 추가
        JLabel rechargeLabel = new JLabel("포인트 충전");
        rechargeLabel.setFont(rechargeLabel.getFont().deriveFont(Font.BOLD, 20f));
        topPanel.add(rechargeLabel, BorderLayout.WEST);

        JButton backButton = new JButton("<<");
        topPanel.add(backButton, BorderLayout.EAST);
        
        // 빈 공간을 추가하여 spacing 조정
        JPanel emptyPanel = new JPanel();
        emptyPanel.setPreferredSize(new Dimension(300, 100));
        topPanel.add(emptyPanel, BorderLayout.SOUTH);
        // line 추가
        JSeparator separator1 = new JSeparator();
        topPanel.add(separator1, BorderLayout.SOUTH);

        // 중앙 패널
        JPanel centerPanel = new JPanel(new BorderLayout());
        add(centerPanel, BorderLayout.CENTER);

        // "충전방법" 글자 추가
        JLabel methodLabel = new JLabel("충전방법");
        methodLabel.setFont(methodLabel.getFont().deriveFont(Font.BOLD, 18f));
        centerPanel.add(methodLabel, BorderLayout.NORTH);

        // "포인트" 버튼과 비활성화된 "카드" 버튼 추가
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 1));
        centerPanel.add(buttonsPanel, BorderLayout.CENTER);

        JButton pointButton = new JButton("포인트");
        pointButton.setPreferredSize(new Dimension(150, 30));
        buttonsPanel.add(pointButton);

        JButton cardButton = new JButton("카드");
        cardButton.setPreferredSize(new Dimension(150, 30));
        cardButton.setEnabled(false);
        buttonsPanel.add(cardButton);
        // line 추가
        JSeparator separator2 = new JSeparator();
        centerPanel.add(separator2, BorderLayout.SOUTH);


        // "금액확인" 글자와 이전 페이지에서 선택한 포인트 표기 추가
        JPanel amountPanel = new JPanel(new BorderLayout());
        centerPanel.add(amountPanel, BorderLayout.SOUTH);

        JLabel amountLabel = new JLabel("금액확인");
        amountLabel.setFont(amountLabel.getFont().deriveFont(Font.BOLD, 18f));
        amountPanel.add(amountLabel, BorderLayout.NORTH);

        int selectedPoints = 500000; // 이전 페이지에서 선택한 포인트
        JLabel selectedPointsLabel = new JLabel(String.valueOf(selectedPoints) + " 포인트");
        selectedPointsLabel.setFont(selectedPointsLabel.getFont().deriveFont(Font.BOLD, 30f));
        selectedPointsLabel.setHorizontalAlignment(JLabel.RIGHT);
        amountPanel.add(selectedPointsLabel, BorderLayout.SOUTH);

        // 하단 패널
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setPreferredSize(new Dimension(300, 200));
        add(bottomPanel, BorderLayout.SOUTH);
        
        // "약관동의" 글자와 "링크" 글자 추가
        JPanel agreementPanel = new JPanel(new BorderLayout());
        bottomPanel.add(agreementPanel, BorderLayout.NORTH);

        JLabel agreementLabel = new JLabel("약관동의 [링크]");
        agreementLabel.setFont(agreementLabel.getFont().deriveFont(Font.BOLD, 18f));
        agreementPanel.add(agreementLabel, BorderLayout.WEST);


        // 체크박스와 "약관에 동의합니다" 글자 추가
        JPanel checkboxPanel = new JPanel(new BorderLayout());
        bottomPanel.add(checkboxPanel, BorderLayout.CENTER);

        JCheckBox agreementCheckbox = new JCheckBox();
        checkboxPanel.add(agreementCheckbox, BorderLayout.WEST);

        JLabel agreeText = new JLabel("약관에 동의합니다");
        checkboxPanel.add(agreeText, BorderLayout.CENTER);

        // "결제" 버튼 추가
        JButton paymentButton = new JButton("결제");
        bottomPanel.add(paymentButton, BorderLayout.SOUTH);
        paymentButton.setPreferredSize(new Dimension(150, 80));
        paymentButton.setEnabled(false);

        
        
        agreementLabel.addMouseListener(new java.awt.event.MouseAdapter() {
        	public void mouseClicked(java.awt.event.MouseEvent evt) {
        		showAgreementDialog();
        	}
        });
        paymentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Perform payment logic here
            }
        });
    }
    // Method to show agreement dialog
    private void showAgreementDialog() {
        String agreementText = 
        		"\r\n"
        		+ "서비스이용약관(자사 제품, 서비스 공급용)\r\n"
        		+ "\r\n"
        		+ "회사가 제품의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고, 이용자에게 그 내용을 고지하고자 하는 경우에 사용하는 약관입니다\r\n"
        		+ "\r\n"
        		+ "서비스이용약관(플랫폼-공급회원용)\r\n"
        		+ "\r\n"
        		+ "회사가 플랫폼의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고, "
        		+ "제품 판매 회원사에게 그 내용을 고지하고자 하는 경우에 사용하는 약관입니다\r\n"
        		+ "\r\n"
        		+ "서비스이용약관(플랫폼-일반 및 공급기업용)\r\n"
        		+ "\r\n"
        		+ "회사가 플랫폼의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고, "
        		+ "제품 판매자 및 서비스 이용자에게 그 내용을 고지하고자 할 때 사용하는 약관입니다.\r\n"
        		+ "\r\n"
        		+ "서비스이용약관(플랫폼-일반회원용)\r\n"
        		+ "\r\n"
        		+ "회사가 플랫폼의 사용 조건과 서비스 이용 절차 등에 관한 사항을 규정하고,"
        		+ " 자사 플랫폼을 이용하는 회원에게 그 내용을 고지하고자 할 때 사용하는 약관입니다.\r\n"
        		+ "\r\n"
        		+ "위치기반 서비스 이용약관\r\n"
        		+ "\r\n"
        		+ "회사가 이용자의 개인위치정보를 사용하여 서비스를 제공하고자 하는 경우, 위치정보를 "
        		+ "수집 및 사용한다는 내용을 이용자에게 고지하고 동의를 받고자 할 때 작성하는 약관입니다";

        JTextArea textArea = new JTextArea(agreementText);
        textArea.setEditable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true); // 단어 경계에서 줄 바꿈

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "약관", JOptionPane.PLAIN_MESSAGE);

        // 계약을 보고나서 결제 버튼 사용가능
        Component[] components = this.getComponents();
        for (Component component : components) {
            if (component instanceof JPanel) {
                JPanel panel = (JPanel) component;
                Component[] subComponents = panel.getComponents();
                for (Component subComponent : subComponents) {
                    if (subComponent instanceof JButton) {
                        JButton button = (JButton) subComponent;
                        if (button.getText().equals("결제")) {
                            button.setEnabled(true);
                            break;
                        }
                    }
                }
            }
        }
    }
}