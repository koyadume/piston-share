package in.koyad.piston.common.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class FileUtil {

	public static Map<String, List<Property>> readIniAsMap(Path path) throws IOException {
		Map<String, List<Property>> sections = new LinkedHashMap<>();
		
		try(Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> processLine(line, sections));
		}
		
		return sections;
	}
	
	private static void processLine(String line, Map<String, List<Property>> sections) {
//		System.out.println("Line : " + line);
		String section = "";
		if(line.startsWith("[") && line.endsWith("]")) {
			section = line.substring(1, line.length()-1);
			sections.put(section, new ArrayList<Property>());
		} else {
			Iterator<String> iter = sections.keySet().iterator();
			while(iter.hasNext()) {
				section = iter.next();
			}
			String[] parts = line.split("=");
			if(parts.length == 2) {
				List<Property> properties = sections.get(section);
				properties.add(new Property(parts[0].trim(), parts[1].trim()));
			}
		}
	}
	
	public static String getFileAsString(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}
	
	public static void main(String[] args) {
		Path path = Paths.get(args[0]);
		try {
			Map<String, List<Property>> sections = readIniAsMap(path);
			Iterator<String> iter = sections.keySet().iterator();
			while(iter.hasNext()) {
				String section = iter.next();
				System.out.println("[" + section + "]");
				for(Property property : sections.get(section)) {
					System.out.println(property.getKey() + "=" + property.getValue());
				}
				System.out.println("");
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
}
