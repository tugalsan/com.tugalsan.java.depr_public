package com.tugalsan.trm.pomeditor;

import com.tugalsan.api.file.server.*;
import com.tugalsan.api.file.txt.server.*;
import com.tugalsan.api.file.xml.server.*;
import com.tugalsan.api.file.xml.server.obj.*;
import com.tugalsan.api.stream.client.*;
import java.nio.file.*;

//WHEN RUNNING IN NETBEANS, ALL DEPENDENCIES SHOULD HAVE TARGET FOLDER!
public class Main {

    public static void main(String[] args) {

        TGS_StreamUtils.forward(10, 20, 3).forEachOrdered(i -> {
            System.out.println(i);
        });

        if (true) {
            return;
        }

        System.out.println("------------ READING XML-FILE ---------------");
        var tree = TS_FileXmlTreeUtils.toTree(Path.of("C:\\var\\input.xml"));

        System.out.println("------------ PRINTING XML-TREE ---------------");
        System.out.println(tree);

        System.out.println("------------ CONVERTING TO DOC ---------------");
        var doc = TS_FileXmlTreeUtils.toDocument(tree);
        var elRoot = doc.getDocumentElement();
        System.out.println("elRoot.id:" + elRoot.getNodeName());

        System.out.println("------------ CONVERTING TO TREE ---------------");
        tree = TS_FileXmlTreeUtils.toTree(doc);

        System.out.println("------------ PRINTING DOC-TREE ---------------");
        System.out.println(tree);

        System.out.println("------------ WRITING XML-FILE ---------------");
        TS_FileXmlUtils.save(doc, Path.of("C:\\var\\output.xml"));

        if (true) {
            return;
        }
//        var lst = List.of(
//                "C:\\me\\codes\\com.tugalsan\\api",
//                "C:\\me\\codes\\com.tugalsan\\app",
//                "C:\\me\\codes\\com.tugalsan\\lib",
//                "C:\\me\\codes\\com.tugalsan\\lib",
//                "C:\\me\\codes\\com.tugalsan\\res",
//                "C:\\me\\codes\\com.tugalsan\\spi",
//                "C:\\me\\codes\\com.tugalsan\\trm"
//        );
        var api = Path.of("C:\\me\\codes\\com.tugalsan\\api");
        var apis = TGS_StreamUtils.toLst(
                TS_DirectoryUtils.subDirectories(api, false, false).stream().map(loc -> new Api(loc))
        );
//        apis.get(0).backupIfNotExists().parse();
        apis.forEach(pom -> pom.backupIfNotExists().parse());
    }

    static void forChild(Path child, Path log_txt) {
        var sb = new StringBuilder("\n" + child + ":");

        var pom_xml = child.resolve("pom.xml");
        if (!TS_FileUtils.isExistFile(pom_xml)) {
            sb.append("//!TS_FileUtils.isExistFile(pom_xml):").append(pom_xml);
            TS_FileTxtUtils.toFile(sb, log_txt, true);
            return;
        }
        var pom_content = TS_FileTxtUtils.toString(pom_xml);

        var idx_start = pom_content.indexOf(DEPS_START);
        if (idx_start == -1) {
            sb.append("//skip_idx_start == -1");
            TS_FileTxtUtils.toFile(sb, log_txt, true);
            return;
        }

        var idx_end = pom_content.indexOf(DEPS_END);
        if (idx_end == -1) {
            sb.append("//skip_idx_end == -1");
            TS_FileTxtUtils.toFile(sb, log_txt, true);
            return;
        }

        var pom_deps = pom_content.substring(idx_start + DEPS_START.length(), idx_end);
        pom_deps = pom_deps.replace(" ", "");

        TS_FileTxtUtils.toFile(sb, log_txt, true);
    }

    static String DEPS_START = "<dependencies>";
    static String DEPS_END = "</dependencies>";
    static String DEP_START = "<dependency>";
    static String DEP_END = "</dependency>";
}
