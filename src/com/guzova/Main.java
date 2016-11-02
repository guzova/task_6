package com.guzova;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {

    static final String[] poem = {
            "Заметался пожар голубой,",
            "Позабылись родимые дали.",
            "В первый раз я запел про любовь,",
            "В первый раз отрекаюсь скандалить.",
            "",
            "Был я весь - как запущенный сад,",
            "Был на женщин и зелие падкий.",
            "Разонравилось пить и плясать",
            "И терять свою жизнь без оглядки.",
            "",
            "Мне бы только смотреть на тебя,",
            "Видеть глаз злато-карий омут,",
            "И чтоб, прошлое не любя,",
            "Ты уйти не смогла к другому.",
            "",
            "Поступь нежная, легкий стан,",
            "Если б знала ты сердцем упорным,",
            "Как умеет любить хулиган,",
            "Как умеет он быть покорным.",
            "",
            "Я б навеки забыл кабаки",
            "И стихи бы писать забросил.",
            "Только б тонко касаться руки",
            "И волос твоих цветом в осень.",
            "",
            "Я б навеки пошел за тобой",
            "Хоть в свои, хоть в чужие дали...",
            "В первый раз я запел про любовь,",
            "В первый раз отрекаюсь скандалить. "};

    public static void main(String[] args) throws Exception {
        File root = new File("root.txt");
        Scanner input = new Scanner(root);
        String rootDir = input.nextLine() + "\\" + input.next();//сохраняем путь к директории в строку
        Collection<String> files = new LinkedList<>();
        File filesToCreate = new File("files.txt");
        input = new Scanner(filesToCreate);
        while (input.hasNextLine()) {
            files.add(input.nextLine());
        }
        foo(rootDir, files);
    }

    static void foo(String rootDir, Collection<String> files) throws IOException {
        File rootDirectory = new File(rootDir);
        rootDirectory.mkdirs();
        int rootLevel = nestingLevel(rootDir);
        for (String file : files) {
            int level = nestingLevel(rootDir + file) - rootLevel;
            File fileToCreate = new File(rootDir + file);
            fileToCreate.getParentFile().mkdirs();
            fileToCreate.createNewFile();
            FileWriter fw = new FileWriter(fileToCreate);
            fw.write(fileToCreate.getAbsolutePath() + System.lineSeparator());
            fw.write("" + level + System.lineSeparator());
            fw.write(poem[level] + System.lineSeparator());
            fw.flush();
        }
    }

    static int nestingLevel(String path) {
        int level = 0;
        int last = -1;
        while ((last = path.indexOf('\\', last + 1)) != -1) {
            level++;
        }
        return level;
    }
}
