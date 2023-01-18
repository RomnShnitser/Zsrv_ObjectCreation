import java.util.List;

public class Generator {
	List<String> textList;

	public void setTextList(List<String> textList) {
		this.textList = textList;
	}

	//Methods
	//===============================================================================================================================
	public void settersGettersWith(Engine engine) {
		start(engine);
		printFields(engine);
		settersAndWith(engine);
		gettersRegular(engine);
		constructor(engine);
		System.out.println("}");
	} // # settersGettersWith

	//Helpers
	//===============================================================================================================================
	public void start(Engine engine) {
		System.out.println("public class " + engine.getClassName() + " {\n");
	} // # start

	public void printFields(Engine engine) {
		for (String s : engine.getListTextOfFile()) {
			System.out.println("   " + s + ";");
		}
		System.out.println();
	} // # printFields

	public void gettersRegular(Engine engine) {
		System.out.println("   //Getters");
		System.out.println("   //===============================================================================\n");
		for (int i = 0; i < engine.getListNames().size(); i++) {
			System.out.print("   public " + engine.getListTypes().get(i) + " ");
			if (engine.getListTypes().get(i).contains("boolean")) {
				System.out.print("is");
			}
			else {
				System.out.print("get");
			}

			System.out.print(engine.getListNamesInitial().get(i) +
					"() {return " + engine.getListNames().get(i) + ";}\n");
		}
	} // # gettersRegular

	public void settersAndWith(Engine engine) {
		System.out.println("\n   //Setters");
		System.out.println("   //===============================================================================\n");
		for (int i = 0; i < engine.getListNames().size(); i++) {
			System.out.println("   public void set" +
					engine.getListNamesInitial().get(i) +
					"(" + engine.getListTypes().get(i) + " " +
					engine.getListNames().get(i) + " ) {\n" +
					"      this." + engine.getListNames().get(i) +
					" = " + engine.getListNames().get(i) + ";\n   }"
			);

			System.out.println("   public " + engine.getClassName() + " " +
					"with" + engine.getListNamesInitial().get(i) + "(" +
					engine.getListTypes().get(i) + " " + engine.getListNames().get(i) + ") {\n" +
					"      this." + engine.getListNames().get(i) +
					" = " + engine.getListNames().get(i) + ";\n" +
					"      return this;\n" + "   }\n"
			);
		}
	} // # settersAndWith

	public void constructor(Engine engine) {

		System.out.println("\n   //Constructor");
		System.out.println("   //===============================================================================\n");
		System.out.print("   public " + engine.getClassName() + "(");

		for (int i = 0; i < engine.getListNames().size()-1; i++) {
			System.out.print(engine.getListTypes().get(i) + " " + engine.getListNames().get(i) + ", ");
		}
		int a = engine.getListNames().size()-1;
		System.out.print(engine.getListTypes().get(a) + " " + engine.getListNames().get(a) + ") {\n");

		for (int i = 0; i < engine.getListNames().size(); i++) {
			System.out.println("      this." + engine.getListNames().get(i) +
					" = " + engine.getListNames().get(i) + ";"
			);
		}
		System.out.println("   }");
		System.out.println("   public " + engine.getClassName() + "(){}");
		System.out.println();
	} // # constructor
} // # Generator