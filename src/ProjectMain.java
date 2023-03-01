import ui.*;
import model.*;


public class ProjectMain {
    public static void main(String[] args){
        LabyrinthAppModel model = new LabyrinthAppModel();
		new LabyrinthApp().setLabyrinthAppModel(model);;
    }
}
