package ch.albin.meisterschaften.train;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class WriteAssetsProvider {

    private static final String ENUM_PREFIX = "" +
            "package " + extractPackage() + ";" +
            System.lineSeparator() +
            System.lineSeparator() +
            "import java.net.URL;" +
            System.lineSeparator() +
            System.lineSeparator() +
            "public enum Assets {" +
            System.lineSeparator() +
            System.lineSeparator() +
            "";
    private static final String ENUM_POSTFIX = "" +
            System.lineSeparator() +
            "    final String filename;" +
            System.lineSeparator() +
            System.lineSeparator() +
            "    Assets(String filename){" +
            System.lineSeparator() +
            "        this.filename = filename;" +
            System.lineSeparator() +
            "    }" +
            System.lineSeparator() +
            System.lineSeparator() +
            "    public URL asUrl(){" +
            System.lineSeparator() +
            "        return Assets.class.getClassLoader().getResource(filename);" +
            System.lineSeparator() +
            "    }" +
            System.lineSeparator() +
            "}";
    private static final String PATH_TO_ENUM = "src/main/java/ch/albin/meisterschaften/train/assets/Assets.java";
    private static final String PATH_TO_RESOURCES = "src/main/resources";

    public static void main(String[] args) {
        new WriteAssetsProvider().run();
    }

    private void run() {
        File resourcesFolder = new File(PATH_TO_RESOURCES);

        if (resourcesFolder.isDirectory()) {
            List<File> filesInResources = Arrays.asList(Objects.requireNonNull(resourcesFolder.listFiles()));

            StringBuilder stringBuilderForContentInEnum = new StringBuilder(ENUM_PREFIX);

            addFilesToStringBuilder(filesInResources, "", stringBuilderForContentInEnum);

            //Deleting last 2 chars to be able to add ;
            stringBuilderForContentInEnum.delete(stringBuilderForContentInEnum.length() - 3, stringBuilderForContentInEnum.length() - 1);

            stringBuilderForContentInEnum.append(";");


            stringBuilderForContentInEnum.append(ENUM_POSTFIX);

            try {
                writeInEnum(stringBuilderForContentInEnum.toString());
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        throw new IllegalArgumentException("Wrong path (" + PATH_TO_RESOURCES + ") to resources");
    }

    private void addFilesToStringBuilder(List<File> filesToBeAdded, String folder, StringBuilder stringBuilder) {
        filesToBeAdded.forEach(file -> {
            if (file.isDirectory()) {
                String folderName = file.getName();
                List<File> filesInFolder = Arrays.asList(Objects.requireNonNull(file.listFiles()));
                addFilesToStringBuilder(filesInFolder, folder + folderName + "/", stringBuilder);
                return;
            }
            String fileName = file.getName().split("\\.")[0];
            String fileNameWithExtension = file.getName();

            stringBuilder.append(prepareAttributes(fileName, fileNameWithExtension, folder));
            stringBuilder.append(",").append(System.lineSeparator());
        });
    }

    private void writeInEnum(String content) throws IOException {
        FileWriter fileWriter = new FileWriter(PATH_TO_ENUM);

        fileWriter.write(content);

        fileWriter.flush();
        fileWriter.close();
    }

    private String prepareAttributes(String fileName, String fileNameWithExtension, String folder) {
        return "    " + capitalize(fileName) + "(\"" + folder + fileNameWithExtension + "\")";
    }

    private static String extractPackage() {
        String extractedPackage;

        int indexOfJava = PATH_TO_ENUM.toLowerCase().indexOf("java");
        int indexOfAssets = PATH_TO_ENUM.toLowerCase().indexOf("/assets.java");

        // +5 because of Java and /
        extractedPackage = PATH_TO_ENUM.substring(indexOfJava + 5, indexOfAssets);

        extractedPackage = extractedPackage.replaceAll("/", ".");

        return extractedPackage;
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}