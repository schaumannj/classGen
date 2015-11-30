import com.sun.codemodel.*;

import java.io.File;
import java.io.IOException;

/**
 * @author sch062
 * @since 11/30/15.
 */
public class ClassGenerator {

    public static void main(String ... args) {
        try {
            ClassGenerator.generateSource();
        } catch (JClassAlreadyExistsException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void generateSource() throws JClassAlreadyExistsException, IOException {
        //Instantiate an instance of the JCodeModel class
        JCodeModel codeModel = new JCodeModel();

        //JDefinedClass will let you create a class in a specified package.
        JDefinedClass classToBeCreated = codeModel._class("com.acision.ams.readers.Bar");

        // Create public Kyes
        JFieldVar key1 = classToBeCreated.field((JMod.PUBLIC | JMod.STATIC | JMod.FINAL), String.class, "ARAB");
        JDocComment comment = new JDocComment(codeModel);
        comment.addReturn().append("sksksk");
        comment.add("sadsdassada");


        //Creating private fields in the class
        JFieldVar field1 = classToBeCreated.field(JMod.PRIVATE, Long.class, "foo");

        //The codeModel instance will have a list of Java primitives which can be
        //used to create a primitive field in the new class
        JFieldVar field2 = classToBeCreated.field(JMod.PRIVATE, codeModel.DOUBLE, "bar");

        //Create getter and setter methods for the fields
        JMethod field1GetterMethod = classToBeCreated.method(JMod.PUBLIC, field1.type(), "getFoo");
        //code to create a return statement with the field1
        field1GetterMethod.body()._return(field1);
        JMethod field1SetterMethod = classToBeCreated.method(JMod.PUBLIC, codeModel.VOID, "setFoo");
        //code to create an input parameter to the setter method which will take a variable of type field1
        field1SetterMethod.param(field1.type(), "inputFoo");
        //code to create an assignment statement to assign the input argument to the field1
        field1SetterMethod.body().assign(JExpr._this().ref ("foo"), JExpr.ref ("inputFoo"));

        JMethod field2GetterMethod = classToBeCreated.method(JMod.PUBLIC, field2.type(), "getBar");
        field2GetterMethod.body()._return(field2);
        JMethod field2SetterMethod = classToBeCreated.method(JMod.PUBLIC, codeModel.VOID, "setBar");
        field2SetterMethod.param(field2.type(), "inputBar");
        field2SetterMethod.body().assign(JExpr._this().ref ("bar"), JExpr.ref ("inputBar"));


        //This will generate the code in the specified file path.
        File file = new File("generated");
        file.mkdirs();
        codeModel.build(file);
    }
}
