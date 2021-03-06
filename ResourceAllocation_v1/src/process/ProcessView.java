package process;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * @author Thierry Meurers
 *
 * This Class defines how a process is visualized.
 * Every ProcessView extends Pane. On this Pane there is
 * - one Rectangle which serves as background
 * - one Text to display the name of the process
 * - 30 small rectangles which are used to visualize the current
 * 		allocation state
 *
 */
public class ProcessView extends Pane {

	final static private String namePrefix = "P ";

	//Needed for the correct placing of the Text an the small resource rectangles
	final static private int WIDTH = 110;
	final static private int HEIGHT = 160;
	final static private int RESSIZE = 16;
	final static private int RESGAP = 2;
	final static private int YOFFSET = 30;

	final static private int RESCOUNT = 5;
	final static private int RESOCCURRENCE = 6;

	//Defines the color of the acquired and pending resource rectangles 
	final static private Color[] clrA = { Color.rgb(157, 60, 255), Color.rgb(181, 230, 29), Color.rgb(203, 15, 227),
			Color.rgb(255, 201, 14), Color.rgb(0, 162, 232) };
	final static private Color[] clrP = { Color.rgb(220, 205, 244), Color.rgb(225, 237, 195), Color.rgb(230, 185, 237),
			Color.rgb(243, 230, 182), Color.rgb(180, 220, 238) };

	private Rectangle background;
	private ResourceLine[] resourceLines;

	
	/**
	 * 1. Create background
	 * 2. Create name-tag
	 * 3. Create 5 "resourceLines"
	 */
	public ProcessView(int[] resNeeded, int id) {
		createBackground();
		createTitle(id);
		resourceLines = new ResourceLine[RESCOUNT];

		for (int i = 0; i < RESCOUNT; i++) {
			resourceLines[i] = new ResourceLine(i, resNeeded[i]);
		}
	}
	
	/**
	 * This method is called by the Controller and passes all required informations
	 * to show the current state.
	 */
	void displayState(int[] res, boolean finished) {
		if (finished) {
			background.setFill(Color.rgb(193, 243, 137));
			for (int i = 0; i < RESCOUNT; i++) {
				resourceLines[i].setHold(0);
			}
		} else {
			background.setFill(Color.BEIGE);
			for (int i = 0; i < RESCOUNT; i++) {
				resourceLines[i].setHold(res[i]);
			}
		}
	}

	/**
	 * Method to initialize the background
	 */
	private void createBackground() {
		background = new Rectangle();
		background.setHeight(HEIGHT);
		background.setWidth(WIDTH);
		background.setArcHeight(10);
		background.setArcWidth(10);
		background.setStroke(Color.BLACK);
		background.setFill(Color.BEIGE);
		this.getChildren().add(background);
	}

	/**
	 * Method to initialize the Text which shows the name
	 */
	private void createTitle(int id) {
		Text name = new Text(namePrefix + id);
		name.setFont(Font.font("Verdana", FontWeight.BOLD, 16));
		name.setX(WIDTH / 2 - name.getLayoutBounds().getWidth() / 2);
		name.setY(22);
		name.setFill(Color.BLACK);

		this.getChildren().add(name);
	}


	/**
	 * A resource line consists of 6 small rectangles which
	 * are representing the current allocation status of the
	 * corresponding resource
	 *
	 */
	class ResourceLine {

		Rectangle[] resCells;
		int pos;
		int needed;

		ResourceLine(int p, int n) {
			pos = p;
			needed = n;
			resCells = new Rectangle[RESOCCURRENCE];
			for (int i = 0; i < RESOCCURRENCE; i++) {
				resCells[i] = createResCell(i);
			}
			setHold(0);
		}

		private Rectangle createResCell(int i) {
			Rectangle res = new Rectangle(RESSIZE, RESSIZE);
			res.setX(RESGAP + (RESGAP + RESSIZE) * i);
			res.setY(YOFFSET + (RESGAP + RESSIZE) * pos);

			res.setFill(Color.TRANSPARENT);
			ProcessView.this.getChildren().add(res);
			return res;
		}

		void setHold(int h) {

			for (int i = 0; i < h; i++) {
				resCells[i].setFill(clrA[pos]);
				resCells[i].setStroke(Color.BLACK);
			}
			for (int i = h; i < needed; i++) {
				resCells[i].setFill(clrP[pos]);
				resCells[i].setStroke(Color.TRANSPARENT);
			}
		}
	}

}
