import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;


public class Program {
    private static Path     curDir;

    private static File[]   getListFiles(File file) {
        File[] files = file.listFiles();
        return files != null ? files : new File[0];
    }

    private static long     getSize(File file) {
        long        size = 0;
        if (file.isFile())
            return file.length();
        for (File f : getListFiles(file)) {
            if (file.isFile())
                size += f.length();
            else
                size += getSize(f);
        }
        return size;
    }

    private static void     listDirContent(String[] cmd) throws IOException {
        File listedDir = cmd.length == 1 ? curDir.toFile()
                : curDir.resolve(cmd[1]).normalize().toFile();
        File[] files = getListFiles(listedDir);
        for (File f : files)
            System.out.printf("%s %dKB\n", f.getName(), Math.round(getSize(f) / 1024.0));
    }

    private static void     changeCurDirectory(String[] cmd) throws IOException {
        if (cmd.length > 2) {
            throw new IOException("cd: too many arguments");
        } else if (cmd.length == 1) {
            throw new IOException("cd: invalid arguments");
        } else if (Files.isDirectory(curDir.resolve(cmd[1]))) {
            curDir = curDir.resolve(cmd[1]).normalize();
            System.out.println(curDir);
        } else {
            throw new IOException("cd: " + cmd[1] + ": No such file or directory");
        }
    }

    private static void     move(String[] cmd) throws IOException {
        if (cmd.length != 3) {
            throw new IOException("mv: invalid arguments");
        } else {
            if (Files.isDirectory(Paths.get(cmd[2]))) {
                Files.move(curDir.resolve(cmd[1]),
                        curDir.resolve(cmd[2]).resolve(Paths.get((cmd[1])).getFileName()));
            } else {
                Files.move(curDir.resolve(cmd[1]), curDir.resolve(cmd[2]));
            }
        }
    }

    public static void      main(String[] args) {
        BufferedReader reader = null;
        while (true) {
            try {
                reader = new BufferedReader(new InputStreamReader(System.in));
                if (curDir == null)
                    curDir = Paths.get("").toAbsolutePath();
                String[] cmd = reader.readLine().split("\\s+");
                switch (cmd[0]) {
                    case "mv":
                        move(cmd);
                        break;
                    case "cd":
                        changeCurDirectory(cmd);
                        break;
                    case "ls":
                        listDirContent(cmd);
                        break;
                    case "exit":
                        System.exit(0);
                    default:
                        System.out.println("Command '" + cmd[0] + "' not found ! ");
                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
