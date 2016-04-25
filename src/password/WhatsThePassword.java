/*
_|          _|            _|      _|_|        _|_|_|                                          _|    _|                
_|          _|    _|_|    _|    _|          _|          _|_|      _|_|_|  _|    _|  _|  _|_|      _|_|_|_|  _|    _|  
_|    _|    _|  _|    _|  _|  _|_|_|_|        _|_|    _|_|_|_|  _|        _|    _|  _|_|      _|    _|      _|    _|  
  _|  _|  _|    _|    _|  _|    _|                _|  _|        _|        _|    _|  _|        _|    _|      _|    _|  
    _|  _|        _|_|    _|    _|          _|_|_|      _|_|_|    _|_|_|    _|_|_|  _|        _|      _|_|    _|_|_|  
                                                                                                                  _|  
                                                                                                              _|_|    
- CEO and Founder - Tilak Maddy
*/

package password;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Chintu
 */
public class WhatsThePassword extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FirstLayout.fxml"));        
        
        Scene scene = new Scene(root);       
        
        stage.setTitle("What's The Password");
        stage.initStyle(StageStyle.TRANSPARENT);       
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
