import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import play.*;
import models.*;
import play.db.jpa.Blob;
import play.jobs.*;
import play.libs.MimeTypes;
import play.test.*;
 
@OnApplicationStart
public class Bootstrap extends Job 
{ 
  public void doJob() {
	  
	  
    Fixtures.deleteDatabase();
    Fixtures.loadModels("data.yml");
   }
}