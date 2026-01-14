package com.tugalsan.trm.pomeditor;

import com.tugalsan.api.file.server.*;
import com.tugalsan.api.file.xml.server.*;
import com.tugalsan.api.file.xml.server.obj.*;
import java.nio.file.*;

public class Api {

    public static String POM_FILE_NAME = "pom.xml";
    public static String BACKUP_FILE_NAME = "pom.bak";
    public static String TEST_FILE_NAME = "pom.tst";

    public Api(Path loc) {
        this.loc = loc;
    }
    public Path loc;

    public Api backupIfNotExists() {
        var pom = loc.resolve(POM_FILE_NAME);
        var bck = loc.resolve(BACKUP_FILE_NAME);
        if (!TS_FileUtils.isExistFile(pom)) {
            System.out.println("ERROR: Api.backupIfNotExists -> file not exists -> " + pom);
            return this;
        }
        System.out.println("pom:" + pom);
        System.out.println("bck:" + bck);
        TS_FileUtils.copyAs(pom, bck, false);
        return this;
    }

    public Api parse() {
        var pom = loc.resolve(POM_FILE_NAME);
        if (!TS_FileUtils.isExistFile(pom)) {
            System.out.println("ERROR: Api.parse -> file not exists -> " + pom);
            return this;
        }
        var pom_xml = TS_FileXmlTreeUtils.toTree(pom);
//        System.out.println(pom_xml);
        var doc = TS_FileXmlTreeUtils.toDocument(pom_xml);
        TS_FileXmlUtils.save(doc, loc.resolve(TEST_FILE_NAME));
//        System.out.println(pom_xml);
//        var pom_doc = TS_FileXmlTableUtils.parse(pom);
//        var pom_doc_el = pom_doc.getDocumentElement();
//        var modelVersion = pom_doc_el.getElementsByTagName("modelVersion").;
//        var groupId = pom_doc_el.getElementsByTagName("groupId");
//        var artifactId = pom_doc_el.getElementsByTagName("artifactId");
//        var version = pom_doc_el.getElementsByTagName("version");
//
//        System.out.println(TGS_StringUtils.concat("m:", modelVersion, ", g:", groupId, ", a:", artifactId, ", b:", version));
        return this;
    }

}
