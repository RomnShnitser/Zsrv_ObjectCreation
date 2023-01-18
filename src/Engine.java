import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Engine {
	String filePath;
	String fileName;
	String fileNameInitial; //???
	String className;
	List<String> listTextOfFile;
	List<String> listTypes;
	List<String> listNames;
	List<String> listNamesInitial;

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileNameInitial(String fileNameInitial) {
		this.fileNameInitial = fileNameInitial;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setListTextOfFile(List<String> listTextOfFile) {
		this.listTextOfFile = listTextOfFile;
	}

	public void setListTypes(List<String> listTypes) {
		this.listTypes = listTypes;
	}

	public void setListNames(List<String> listNames) {
		this.listNames = listNames;
	}

	public void setListNamesInitial(List<String> listNamesInitial) {
		this.listNamesInitial = listNamesInitial;
	}

	public String getFilePath() {
		return filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public String getFileNameInitial() {
		return fileNameInitial;
	}

	public String getClassName() {
		return className;
	}

	public List<String> getListTextOfFile() {
		return listTextOfFile;
	}

	public List<String> getListTypes() {
		return listTypes;
	}

	public List<String> getListNames() {
		return listNames;
	}

	public List<String> getListNamesInitial() {
		return listNamesInitial;
	}

	public Engine(String filePath, String fileName, String fileNameInitial, String className, List<String> listTextOfFile, List<String> listTypes, List<String> listNames, List<String> listNamesInitial) {
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileNameInitial = fileNameInitial;
		this.className = className;
		this.listTextOfFile = listTextOfFile;
		this.listTypes = listTypes;
		this.listNames = listNames;
		this.listNamesInitial = listNamesInitial;
	}

	public Engine() {}

	public void initialization(Engine engine) {
		try {
			File fileFolder = new File(engine.getFilePath());
			File[] files = fileFolder.listFiles();
			assert files != null;
			if (files.length != 1) {
				System.out.println("ERROR! There should only be ONE file per folder.\nAnd it should be.");
				System.exit(0);
			}
			else {
				for (File file : files) {
					engine.setFileName(file.getName());
				}
			}
		} catch (Exception e) {
			System.out.println("Error! " + e.getMessage());
		}
	}

	public void readTextFromFile(Engine engine) {
		List<String> list = new LinkedList<>();

		try {
			File f = new File(engine.getFilePath() + "\\" + engine.getFileName());
			Scanner scan = new Scanner(f);
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				list.add(line);
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error! File not found. " + e.getMessage());
		}
		engine.setListTextOfFile(list);
	}

	public void generateClassName(Engine engine) {
		String s = engine.getFileName().replace(".java", "");
		engine.setClassName(s);
	}

	public void cleaningTextFile(Engine engine) {
		List<String> list = engine.getListTextOfFile();
		List<String> newList = new LinkedList<>();

		//remove package and import from text lines
		for (String s : list) {
			if (!s.contains("import")
					&& !s.contains("package")
					&& !s.isEmpty()
					&& !s.isBlank()
					&& !s.contains("{")
					&& !s.contains("}")) {
				newList.add(s);
			}
		}

		list.clear(); list.addAll(newList); newList.clear();

		for (String s : list) {
			s = s.trim();
			int i = s.indexOf('=');
			StringBuilder sb = new StringBuilder(s);

			if (i >= 0 && i <= 256) {
				sb.replace(i, s.length(), "");
			}

			s = sb.toString().trim();
			StringBuilder temp = new StringBuilder();

			char[] chars = s.toCharArray();

			for (char c : chars) {
				if (c >= 'A' && c <= 'Z'
						|| c >= 'a' && c <= 'z'
						|| c >= '0' && c <= '9'
						|| c == ' '
						|| c == '-'
						|| c == '_'

				) {
					temp.append(c);
				}
			}

			s = temp.toString().trim();
			newList.add(s);
		}

		engine.setListTextOfFile(newList);
	}

	public void generateListAndTypesNames(Engine engine) {

		List<String> namesList = new LinkedList<>();
		List<String> typesList = new LinkedList<>();

		for (String s : engine.getListTextOfFile()) {
			String[] split = s.split(" ");
			namesList.add(split[split.length - 1]);
			typesList.add(split[split.length - 2]);
		}

		engine.setListNames(namesList);
		engine.setListTypes(typesList);
	}

	public void generateListNamesInitial(Engine engine) {
		List<String> namesIninitialList = new LinkedList<>();

		for (String name : engine.getListNames()) {
			StringBuilder sb = new StringBuilder(name);
			sb.setCharAt(0, (char) (sb.charAt(0) - 32));
			namesIninitialList.add(sb.toString());
		}

		engine.setListNamesInitial(namesIninitialList);
	}


} // #Engine