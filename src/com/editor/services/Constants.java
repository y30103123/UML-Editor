package com.editor.services;

import com.editor.EditorController;
import com.editor.mode.LineMode;
import com.editor.mode.ObjectMode;
import com.editor.mode.Mode;
import com.editor.mode.SelectMode;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by y3010 on 2016/10/23.
 */
public class Constants {

    public enum TYPE {OPTION,SELECT,ASSOCIATE,GENERALIZATION,COMPOSITION,CLASS,CASE,COMPOSITE}

    public static final String SELECT;
    public static final String ASSOCIATE;
    public static final String GENERALIZATION;
    public static final String COMPOSITION;
    public static final String CLASS;
    public static final String CASE;
    public static final String COMPOSITE;
    public static final int DEFAULT_SIZE ;
    public static final Map<String,InputStream > IMAGE_MAP;
    public static final Map<TYPE,Mode> MODE_MAP;
    public static final String CLASS_COLOR ;
    public static final String CASE_COLOR;
    static {
        DEFAULT_SIZE = 100;
        SELECT = "Select";
        ASSOCIATE = "Associate";
        GENERALIZATION = "Generalization";
        COMPOSITION = "Composition";
        CLASS = "Class";
        CASE = "UseCase";
        COMPOSITE = "Composite";
        CLASS_COLOR = "#BDBDBD";
        CASE_COLOR = "#BDBDBD";
        IMAGE_MAP = new HashMap<>();
        IMAGE_MAP.put(SELECT, Constants.class.getResourceAsStream("/com/editor/resources/select.PNG"));
        IMAGE_MAP.put(ASSOCIATE, Constants.class.getResourceAsStream("/com/editor/resources/as_line.PNG"));
        IMAGE_MAP.put(GENERALIZATION,Constants.class.getResourceAsStream("/com/editor/resources/gen_line.PNG"));
        IMAGE_MAP.put(COMPOSITION,Constants.class.getResourceAsStream("/com/editor/resources/com_line.PNG"));
        IMAGE_MAP.put(CLASS,Constants.class.getResourceAsStream("/com/editor/resources/class.PNG"));
        IMAGE_MAP.put(CASE, Constants.class.getResourceAsStream("/com/editor/resources/case.PNG"));

        MODE_MAP = new HashMap<>();
        MODE_MAP.put(TYPE.SELECT, SelectMode.getInstance());
        MODE_MAP.put(TYPE.CLASS,ObjectMode.getInstance());
        MODE_MAP.put(TYPE.CASE,ObjectMode.getInstance());
        MODE_MAP.put(TYPE.ASSOCIATE,LineMode.getInstance());
        MODE_MAP.put(TYPE.COMPOSITION,LineMode.getInstance());
        MODE_MAP.put(TYPE.GENERALIZATION,LineMode.getInstance());
    }
    public static final String[] TYPE_VALUES = {"Options",SELECT,ASSOCIATE,GENERALIZATION,COMPOSITION,CLASS,CASE} ;
}
