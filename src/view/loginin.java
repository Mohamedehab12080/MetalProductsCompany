package view;

import javafx.application.Application;
import javafx.stage.Stage;

public class loginin extends Application{

          static int x=0;
    @Override
    public void start(Stage primaryStage) throws Exception {

        if(x==0){
           x++;
           new Database.connectionProvider();
       }
    Login l=new Login();  
    l.setVisible(true);
    }
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
}
