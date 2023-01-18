import java.util.LinkedList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		Engine engine = new Engine();

		System.out.println("//#######################################################################################\n\n\n");

		engine.setFilePath("C:\\CodingProjects\\Zsrv_ObjectCreation\\src\\file");

		engine.initialization(engine);
		engine.generateClassName(engine);
		engine.readTextFromFile(engine);
		engine.cleaningTextFile(engine);
		engine.generateListAndTypesNames(engine);
		engine.generateListNamesInitial(engine);

		Generator generator = new Generator();

		generator.settersGettersWith(engine);

		System.out.println("\n\n//#######################################################################################");


	}
}