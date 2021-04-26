package io.jiffy.stax.plugin.core.action;

import com.ibm.staf.service.stax.*;
import org.python.core.PyObject;

import java.util.ArrayList;

public class FunctionAction extends STAXActionDefaultImpl {

    static final int INIT = 0;
    private int state = INIT;
    static final int FUNCTION_CALLED = 1;
    static final int COMPLETE = 2;

    static final String INIT_STRING = "INIT";
    static final String FUNCTION_CALLED_STRING = "FUNCTION_CALLED";
    static final String COMPLETE_STRING = "COMPLETE";
    static final String STATE_UNKNOWN_STRING = "UNKNOWN";

    // Function Argument Definitions
    public static final int FUNCTION_DEFINES_NO_ARGS = 0;
    public static final int FUNCTION_ALLOWS_NO_ARGS = 1;
    public static final int FUNCTION_DEFINES_ONE_ARG = 2;
    public static final int FUNCTION_DEFINES_LIST_ARGS = 3;
    public static final int FUNCTION_DEFINES_MAP_ARGS = 4;

    public static final String FUNCTION_DEFINES_NO_ARGS_STRING =
            "FUNCTION_DEFINES_NO_ARGS";
    public static final String FUNCTION_ALLOWS_NO_ARGS_STRING =
            "FUNCTION_ALLOWS_NO_ARGS";
    public static final String FUNCTION_DEFINES_ONE_ARG_STRING =
            "FUNCTION_DEFINES_ONE_ARG";
    public static final String FUNCTION_DEFINES_LIST_ARGS_STRING =
            "FUNCTION_DEFINES_LIST_ARGS";
    public static final String FUNCTION_DEFINES_MAP_ARGS_STRING =
            "FUNCTION_DEFINES_MAP_ARGS";
    public static final String FUNCTION_DEFINES_UNKNOWN_ARGS_STRING =
            "FUNCTION_DEFINES_UNKNOWN_ARGS";

    // Types of Function Arguments
    public static final int ARG_REQUIRED = 1;
    public static final int ARG_OPTIONAL = 2;
    public static final int ARG_OTHER = 3;

    public static final String ARG_REQUIRED_STRING = "ARG_REQUIRED";
    public static final String ARG_OPTIONAL_STRING = "ARG_OPTIONAL";
    public static final String ARG_OTHER_STRING = "ARG_OTHER";

    private String name;
    private String prolog;
    private String epilog;
    private String scope;
    private String requires;
    private ArrayList<STAXFunctionImport> importList = new ArrayList<STAXFunctionImport>();
    private STAXAction action = null;
    private STAXPythonInterpreter savePythonInterpreter = null;
    private boolean definedWithFunctionArgDef = false;

    private int argDefinition = FUNCTION_DEFINES_NO_ARGS;
    private ArrayList<STAXFunctionArgument> argList = new ArrayList<STAXFunctionArgument>();

    // Contains the arguments passed by the call to the function
    private PyObject callArgPyObject;

    private STAXCallAction callAction = new STAXCallAction();

    private String parentFunctionName;
    private String parentXmlFile;
    private String parentXmlMachine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProlog() {
        return prolog;
    }

    public void setProlog(String prolog) {
        this.prolog = prolog;
    }

    public String getEpilog() {
        return epilog;
    }

    public void setEpilog(String epilog) {
        this.epilog = epilog;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getRequires() {
        return requires;
    }

    public void setRequires(String requires) {
        this.requires = requires;
    }

    public ArrayList<STAXFunctionImport> getImportList() {
        return importList;
    }

    public void setImportList(ArrayList<STAXFunctionImport> importList) {
        this.importList = importList;
    }

    public STAXAction getAction() {
        return action;
    }

    public void setAction(STAXAction action) {
        this.action = action;
    }

    public STAXPythonInterpreter getSavePythonInterpreter() {
        return savePythonInterpreter;
    }

    public void setSavePythonInterpreter(STAXPythonInterpreter savePythonInterpreter) {
        this.savePythonInterpreter = savePythonInterpreter;
    }

    public boolean isDefinedWithFunctionArgDef() {
        return definedWithFunctionArgDef;
    }

    public void setDefinedWithFunctionArgDef(boolean definedWithFunctionArgDef) {
        this.definedWithFunctionArgDef = definedWithFunctionArgDef;
    }

    public int getArgDefinition() {
        return argDefinition;
    }

    public void setArgDefinition(int argDefinition) {
        this.argDefinition = argDefinition;
    }

    public ArrayList<STAXFunctionArgument> getArgList() {
        return argList;
    }

    public void setArgList(ArrayList<STAXFunctionArgument> argList) {
        this.argList = argList;
    }

    public PyObject getCallArgPyObject() {
        return callArgPyObject;
    }

    public void setCallArgPyObject(PyObject callArgPyObject) {
        this.callArgPyObject = callArgPyObject;
    }

    public STAXCallAction getCallAction() {
        return callAction;
    }

    public void setCallAction(STAXCallAction callAction) {
        this.callAction = callAction;
    }

    public String getParentFunctionName() {
        return parentFunctionName;
    }

    public void setParentFunctionName(String parentFunctionName) {
        this.parentFunctionName = parentFunctionName;
    }

    public String getParentXmlFile() {
        return parentXmlFile;
    }

    public void setParentXmlFile(String parentXmlFile) {
        this.parentXmlFile = parentXmlFile;
    }

    public String getParentXmlMachine() {
        return parentXmlMachine;
    }

    public void setParentXmlMachine(String parentXmlMachine) {
        this.parentXmlMachine = parentXmlMachine;
    }
}
