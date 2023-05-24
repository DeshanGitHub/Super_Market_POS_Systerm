package stagep;

import controller.LoginWindowFormController;
import javafx.stage.Stage;

public class NewStage {
    private static NewStage newStage;
    private Stage stage;

    private NewStage() {
       Stage stage2=new Stage();
       stage=stage2;
    }

    public static NewStage getInstance() {
        if (newStage == null) {
            newStage = new NewStage();
        }
        return newStage;
    }

    public Stage getStage() {
        return stage;
    }
}
