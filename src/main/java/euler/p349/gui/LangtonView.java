package euler.p349.gui;

import euler.p349.LangtonAlgorithm;
import euler.p349.entity.Ant;
import euler.p349.entity.Cell;
import euler.p349.entity.Direction;
import euler.p349.entity.Generation;
import euler.p349.entity.Position;
import euler.p349.entity.World;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class LangtonView extends JDialog {
    private JPanel contentPane;
    private JTextField textPosition;
    private JTextField textBlack;
    private JTextField textTime;
    private JLabel labelTime;
    private JLabel labelPosition;
    private JLabel labelBlack;
    private JPanel canvas;

    Generation generation = new Generation(new World(new Ant(new Position(0, 0), Direction.TOP), new ArrayList<>()), 0);

    int cellSize = 12;

    public LangtonView() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Langton's Ant [ESC=Exit]   [R=Reset]   [SPACE=1 Step]   [C=104 Steps (Cycle)]   [K=1000 Steps]");

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onExit();
            }
        });

        contentPane.registerKeyboardAction(e -> onExit(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        contentPane.registerKeyboardAction(e -> onReset(), KeyStroke.getKeyStroke(KeyEvent.VK_R, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(e -> onCalculateNextSteps(1), KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        contentPane.registerKeyboardAction(e -> onCalculateNextSteps(104), KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        contentPane.registerKeyboardAction(e -> onCalculateNextSteps(1000), KeyStroke.getKeyStroke(KeyEvent.VK_K, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        contentPane.registerKeyboardAction(e -> onChangeCellSize(1), KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        contentPane.registerKeyboardAction(e -> onChangeCellSize(1), KeyStroke.getKeyStroke(KeyEvent.VK_ADD, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        contentPane.registerKeyboardAction(e -> onChangeCellSize(-1), KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        contentPane.registerKeyboardAction(e -> onChangeCellSize(-1), KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onChangeCellSize(int change) {
        cellSize += change;
        canvas.repaint();
    }

    private void onReset() {
        generation = new Generation(new World(new Ant(new Position(0, 0), Direction.TOP), new ArrayList<>()), 0);

        textTime.setText("" + generation.time());
        Position position = generation.world().ant().position();
        textPosition.setText("" + position.x() + "; " + position.y());
        textBlack.setText("" + generation.world().blackCells().size());

        canvas.repaint();
    }

    private void onCalculateNextSteps(int steps) {

        for (int i = 0; i < steps; i++) {
            generation = LangtonAlgorithm.calculateNextGeneration(generation);
        }

        textTime.setText("" + generation.time());
        Position position = generation.world().ant().position();
        textPosition.setText("" + position.x() + "; " + position.y());
        textBlack.setText("" + generation.world().blackCells().size());

        canvas.repaint();
    }

    private void onExit() {
        dispose();
    }

    public static void main(String[] args) {
        LangtonView dialog = new LangtonView();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }

    private void createUIComponents() {

        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int centerX = (int) (this.getSize().getWidth() / 2);
                int centerY = (int) (this.getSize().getHeight() / 2);

                World world = generation.world();

                int minX = world.blackCells().stream().map(cell -> cell.position().x()).min(Integer::compare).orElse(0);
                int minY = world.blackCells().stream().map(cell -> cell.position().y()).min(Integer::compare).orElse(0);
                int maxX = world.blackCells().stream().map(cell -> cell.position().x()).max(Integer::compare).orElse(0);
                int maxY = world.blackCells().stream().map(cell -> cell.position().y()).max(Integer::compare).orElse(0);

                int diffX = (maxX + minX) / 2;
                int diffY = (maxY + minY) / 2;

                g2d.setColor(Color.BLACK);

                for (Cell cell : generation.world().blackCells()) {
                    Position cellPosition = cell.position();
                    g2d.drawRoundRect(centerX + (cellPosition.x() * cellSize) - (diffX * cellSize), centerY + (cellPosition.y() * cellSize) - (diffY * cellSize), cellSize, cellSize, cellSize /4, cellSize /4);
                }

                g2d.setColor(Color.RED);
                Position antPosition = world.ant().position();
                g2d.fillOval(centerX + (antPosition.x() * cellSize) - (diffX * cellSize), centerY + (antPosition.y() * cellSize) - (diffY * cellSize), cellSize, cellSize);
            }
        };
    }
}
